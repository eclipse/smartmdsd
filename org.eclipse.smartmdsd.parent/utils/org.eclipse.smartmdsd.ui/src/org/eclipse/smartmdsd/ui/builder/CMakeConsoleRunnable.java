/********************************************************************************
 * Copyright (c) 2021 Technische Hochschule Ulm, Servicerobotics Ulm, Germany
 * headed by Prof. Dr. Christian Schlegel
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *   Alex Lotz, Dennis Stampfer, Matthias Lutz
 ********************************************************************************/
package org.eclipse.smartmdsd.ui.builder;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.cdt.core.ConsoleOutputStream;
import org.eclipse.cdt.core.ICommandLauncher;
import org.eclipse.cdt.core.resources.IConsole;
import org.eclipse.cdt.managedbuilder.core.IBuilder;
import org.eclipse.cdt.ui.CUIPlugin;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.smartmdsd.ui.Activator;
import org.eclipse.smartmdsd.ui.preferences.SmartMDSDPreferencesPage;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsoleConstants;
import org.eclipse.ui.console.IConsoleManager;
import org.eclipse.ui.console.IConsoleView;

public class CMakeConsoleRunnable implements IWorkspaceRunnable {
	public static final String CONSOLE_ID = "org.eclipse.smartmdsd.ui.console.cmake";
	public static final String CONSOLE_NAME = "CMake Console";
	
	private IProject project;
	private IConsole console;
	private String buildConfigurationName;
	private MultiStatus cmakeStatus;
	
	public CMakeConsoleRunnable(IProject project, String buildConfigurationName) {
		this.project = project;
		this.buildConfigurationName = buildConfigurationName;
		this.console = CUIPlugin.getDefault().getConsoleManager(CONSOLE_NAME, CONSOLE_ID).getConsole(project);
		this.console.start(project);
		this.cmakeStatus = new MultiStatus(CONSOLE_ID, IStatus.INFO, "");
	}
	
	public void printInfoLine(String text) throws CoreException {
		ConsoleOutputStream info = console.getInfoStream();
		try {
			info.write(text+"\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String getCMakeMessages(IProject project) {
		return CUIPlugin.getDefault().getConsoleManager(CONSOLE_NAME, CONSOLE_ID).getConsoleDocument(project).get();
	}
	
	public static String getCdtBuildMessages(IProject project) {
		return CUIPlugin.getDefault().getConsoleManager().getConsoleDocument(project).get();
	}
	
	@Override
	public void run(IProgressMonitor monitor) throws CoreException {
		// collect CMake arguments
		List<String> cmakeArgimentsList = new ArrayList<String>();
		cmakeArgimentsList.add("-DBUILD_DEPENDENCIES=OFF");
		cmakeArgimentsList.add("-DCMAKE_BUILD_TYPE="+buildConfigurationName);
		
		String ros_distro_dir = Activator.getDefault().getPreferenceStore().getString(SmartMDSDPreferencesPage.PROP_ROS_DISTRIBUTION_DIR);
		File ros_dir = new File(ros_distro_dir);
		if(ros_dir.exists()) {
			cmakeArgimentsList.add("-DCMAKE_PREFIX_PATH="+ros_dir.getPath());
		}
	
		String[] environmentVariables = calculateEnvironmentVariables(project, ros_dir);

		cmakeArgimentsList.add("..");
		String[] cmakeArguments = new String[cmakeArgimentsList.size()];
		cmakeArguments = cmakeArgimentsList.toArray(cmakeArguments);

		IBuilder builder = CDTProjectHelpers.getSmartMDSDBuilderOf(project);
		ICommandLauncher launcher = builder.getCommandLauncher();
		
		// open and pin the CMake console tab
		setCMakeConsolePinned(true);
		
		printInfoLine("Start CMake for ["+project.getName()+"]");
		
		// create build folder if it does not yet exist
		if(builder.getBuildLocation().toFile().exists() != true) {
			builder.getBuildLocation().toFile().mkdirs();
			project.refreshLocal(2, monitor);
		}
		
		// execute CMake process using the build launcher with collected CMake arguments and custom environment
		Process cmakeProcess = launcher.execute(builder.getBuildCommand(), cmakeArguments, environmentVariables, builder.getBuildLocation(), monitor);
		launcher.waitAndRead(console.getOutputStream(), console.getErrorStream(), monitor);
		if(cmakeProcess.exitValue() != 0) {
			cmakeStatus = new MultiStatus(
					CONSOLE_ID,
					IStatus.ERROR,
					"CMake of ["+project.getName()+"] canceled with Errors!");
		} else {
			cmakeStatus = new MultiStatus(
					CONSOLE_ID,
					IStatus.OK,
					"CMake of ["+project.getName()+"] successfully finished!");
			printInfoLine("CMake of ["+project.getName()+"] successfully finished!\n");
			// unpin the CMake console tab, so other consoles can be displayed
			setCMakeConsolePinned(false);
		}
		
	}
	
	public MultiStatus getStatus() {
		return cmakeStatus;
	}
	
	private String[] calculateEnvironmentVariables(IProject project, File ros_dir) {
		ILaunchManager manager = DebugPlugin.getDefault().getLaunchManager();
		Map<String, String> environment = manager.getNativeEnvironment();
		Integer index = 0;
		int env_size = environment.size();

		if(ros_dir.exists()) {
			env_size = env_size + 3;
		}
		
		String[] environmentVariables = new String[env_size];
		String workspacePath = project.getWorkspace().getRoot().getLocation().toOSString();
		for(Map.Entry<String, String> entry: environment.entrySet()) {
			if(entry.getKey().contentEquals("SMART_PACKAGE_PATH")) {
				environmentVariables[index] = entry.getKey() + "=" + entry.getValue() + ":" + workspacePath;
			} else if(entry.getKey().equals("PATH") && ros_dir.exists()) {
				environmentVariables[index] = entry.getKey() + "=" + ros_dir.getPath()+"/bin:" + entry.getValue();
			} else {
				environmentVariables[index] = entry.getKey() + "=" + entry.getValue();
			}
			index++;
		}
		
		if(ros_dir.exists()) {
			// add ROS main environment variables:
			environmentVariables[index++] = "ROS_ROOT="+ros_dir.getPath()+"/share/ros";
			environmentVariables[index++] = "ROS_PACKAGE_PATH="+ros_dir.getPath()+"/share";
			environmentVariables[index++] = "PYTHONPATH="+ros_dir.getPath()+"/lib/python2.7/dist-packages";
		}
		
		return environmentVariables;
	}
	
	private void setCMakeConsolePinned(boolean pinned) {
		Display.getDefault().syncExec(new Runnable() {
			@Override
			public void run() {
				IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
				IWorkbenchPage page = window.getActivePage();
				if (page != null) {
					IViewPart consoleViewPart = page.findView(IConsoleConstants.ID_CONSOLE_VIEW);
					if(consoleViewPart == null) {
						try {
							IWorkbenchPart activePart = page.getActivePart();
							consoleViewPart = page.showView(IConsoleConstants.ID_CONSOLE_VIEW);
							page.activate(activePart);
						} catch (PartInitException e) {
							e.printStackTrace();
						}
					}
					
					// bring console view into focus
					page.bringToTop(consoleViewPart);

					// find CMake console by the name, display and pin it
					if (consoleViewPart instanceof IConsoleView) {
						IConsoleView consoleView = (IConsoleView)consoleViewPart;
						ConsolePlugin plugin = ConsolePlugin.getDefault();
						IConsoleManager consoleManager = plugin.getConsoleManager();
						org.eclipse.ui.console.IConsole[] ui_consoles = consoleManager.getConsoles();
						for (int i = 0; i < ui_consoles.length; i++) {
							org.eclipse.ui.console.IConsole ui_console = ui_consoles[i];
							if(ui_console.getName().startsWith(CONSOLE_NAME)) {
								if(pinned == true) {
									consoleView.display(ui_console);
								}
								consoleView.setPinned(pinned);
							}
							// reset to the build console if the CMake console is to be unpinned
							if(pinned == false && ui_console.getName().startsWith("CDT Build Console")) {
								consoleView.display(ui_console);
							}
						}
					}
				}
			}
		});
	}
}
