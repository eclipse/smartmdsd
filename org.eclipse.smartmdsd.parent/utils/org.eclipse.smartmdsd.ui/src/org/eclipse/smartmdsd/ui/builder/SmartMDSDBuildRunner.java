/********************************************************************************
 * Copyright (c) 2019 Technische Hochschule Ulm, Servicerobotics Ulm, Germany
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

import org.eclipse.cdt.core.ICommandLauncher;
import org.eclipse.cdt.core.IMarkerGenerator;
import org.eclipse.cdt.core.resources.IConsole;
import org.eclipse.cdt.managedbuilder.core.AbstractBuildRunner;
import org.eclipse.cdt.managedbuilder.core.IBuilder;
import org.eclipse.cdt.managedbuilder.core.IConfiguration;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.smartmdsd.ui.Activator;
import org.eclipse.smartmdsd.ui.preferences.SmartMDSDPreferencesPage;

public class SmartMDSDBuildRunner extends AbstractBuildRunner {

	public SmartMDSDBuildRunner() { }

	@Override
	public boolean invokeBuild(int kind, IProject project, IConfiguration configuration, IBuilder builder,
			IConsole console, IMarkerGenerator markerGenerator, IncrementalProjectBuilder projectBuilder,
			IProgressMonitor monitor) throws CoreException 
	{
		boolean rebuild_required = false;
		String defaultBuildType = Activator.getDefault().getPreferenceStore().getString(SmartMDSDPreferencesPage.PROP_CMAKE_BUILD_TYPE);
		switch(kind) {
		case IncrementalProjectBuilder.CLEAN_BUILD:
			printInfoMessage(console, "Clean project "+project.getName());
			if(cleanProject(project, configuration, builder, console, monitor) == true) {
				project.refreshLocal(IResource.DEPTH_INFINITE, monitor);
				printInfoMessage(console, "Project "+project.getName()+" has cleaned successfully!\n");
			} else {
				printInfoMessage(console, "An error occurred while cleaning the project "+project.getName());
				rebuild_required = true;	
			}
			break;
		case IncrementalProjectBuilder.FULL_BUILD:
		case IncrementalProjectBuilder.INCREMENTAL_BUILD:
			printInfoMessage(console, "Configure CMake ("+defaultBuildType+") for project "+project.getName());
			if(configureCMake(project, builder, defaultBuildType, console, monitor) == true) {
				printInfoMessage(console, "CMake finished successfully!\n");
				printInfoMessage(console, "Build project "+project.getName()+" ("+defaultBuildType+")");
				if(buildProject(project, builder, builder.getIncrementalBuildTarget(), defaultBuildType, console, monitor) == true) {
					project.refreshLocal(IResource.DEPTH_INFINITE, monitor);
					printInfoMessage(console, "Project "+project.getName()+" has built successfully!");
				} else {
					printInfoMessage(console, "Errors occurred while building the project "+project.getName());
					rebuild_required = true;	
				}
			} else {
				printInfoMessage(console, "Errors occurred while configuring CMake for the project "+project.getName());
				rebuild_required = true;
			}
			break;
		default:
			// something else
			break;
		}
		return rebuild_required;
	}
	
	private void printInfoMessage(IConsole console, String message) throws CoreException {
		try {
			console.getInfoStream().write(message+"\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private boolean configureCMake(IProject project, IBuilder builder, String build_type, IConsole console, IProgressMonitor monitor) throws CoreException {
		List<String> cmakeArgumentsList = new ArrayList<String>();
		cmakeArgumentsList.add("-DBUILD_DEPENDENCIES=OFF");

		File ros_dir = null;
		if(!Platform.getOS().equals(Platform.OS_WIN32)) {
			cmakeArgumentsList.add("-DCMAKE_BUILD_TYPE="+build_type);
			
			String ros_distro_dir = Activator.getDefault().getPreferenceStore().getString(SmartMDSDPreferencesPage.PROP_ROS_DISTRIBUTION_DIR);
			ros_dir = new File(ros_distro_dir);
			if(ros_dir.exists()) {
				cmakeArgumentsList.add("-DCMAKE_PREFIX_PATH="+ros_dir.getPath());
			}
		}
	
		String[] environmentVariables = calculateEnvironmentVariables(project, ros_dir);
		
		cmakeArgumentsList.add("..");
		String[] cmakeArguments = new String[cmakeArgumentsList.size()];
		cmakeArguments = cmakeArgumentsList.toArray(cmakeArguments);

		// make sure the build location exists
		builder.getBuildLocation().toFile().mkdirs();
		
		ICommandLauncher launcher = builder.getCommandLauncher();
		Process cmakeProcess = launcher.execute(builder.getBuildCommand(), cmakeArguments, environmentVariables, builder.getBuildLocation(), monitor);
		launcher.waitAndRead(console.getOutputStream(), console.getErrorStream(), monitor);
		return cmakeProcess.exitValue() == 0;
	}
	
	private boolean buildProject(IProject project, IBuilder builder, String build_target, String build_type, IConsole console, IProgressMonitor monitor) throws CoreException {
		List<String> buildArgumentsList = new ArrayList<String>();

		buildArgumentsList.add("--build");
		buildArgumentsList.add(".");
		buildArgumentsList.add("--target");
		buildArgumentsList.add(build_target);
		if(Platform.getOS().equals(Platform.OS_WIN32)) {
			buildArgumentsList.add("--config");
			buildArgumentsList.add(build_type);
		} else if (Platform.getOS().equals(Platform.OS_LINUX)) {
			buildArgumentsList.add("--");
		}
		buildArgumentsList.add(builder.getArguments());
		
		String[] buildArguments = new String[buildArgumentsList.size()];
		buildArguments = buildArgumentsList.toArray(buildArguments);
		
		String ros_distro_dir = Activator.getDefault().getPreferenceStore().getString(SmartMDSDPreferencesPage.PROP_ROS_DISTRIBUTION_DIR);
		File ros_dir = new File(ros_distro_dir);
		String[] environmentVariables = calculateEnvironmentVariables(project, ros_dir);
		
		ICommandLauncher launcher = builder.getCommandLauncher();
		Process cmakeProcess = launcher.execute(builder.getBuildCommand(), buildArguments, environmentVariables, builder.getBuildLocation(), monitor);
		launcher.waitAndRead(console.getOutputStream(), console.getErrorStream(), monitor);
		return cmakeProcess.exitValue() == 0;
	}
	
	private boolean cleanProject(IProject project, IConfiguration configuration, IBuilder builder, IConsole console, IProgressMonitor monitor) throws CoreException {
		IPath cleanCommandPath = null;
		String[] cleanArguments = null;
		
		if(Platform.getOS().equals(Platform.OS_LINUX)) {
			// for Linux we delete the contents of the build folder
			// using the command: bash -c "rm -rf * -v"
			cleanCommandPath = new Path("bash");
			cleanArguments = new String[2];
			cleanArguments[0] = "-c";
			cleanArguments[1] = configuration.getCleanCommand() +" * -v";
		} else if(Platform.getOS().equals(Platform.OS_WIN32)) {
			// for windows we use a regular cmake clean (this can be changed in the future)
			cleanCommandPath = builder.getBuildCommand();
			List<String> buildArgumentsList = new ArrayList<String>();
			buildArgumentsList.add("--build");
			buildArgumentsList.add(".");
			buildArgumentsList.add("--target");
			buildArgumentsList.add(builder.getCleanBuildTarget());
			cleanArguments = new String[buildArgumentsList.size()];
			cleanArguments = buildArgumentsList.toArray(cleanArguments);
		}
		
		String ros_distro_dir = Activator.getDefault().getPreferenceStore().getString(SmartMDSDPreferencesPage.PROP_ROS_DISTRIBUTION_DIR);
		File ros_dir = new File(ros_distro_dir);
		String[] environmentVariables = calculateEnvironmentVariables(project, ros_dir);

		if(cleanCommandPath != null) {
			ICommandLauncher launcher = builder.getCommandLauncher();
			Process cmakeProcess = launcher.execute(cleanCommandPath, cleanArguments, environmentVariables, builder.getBuildLocation(), monitor);
			launcher.waitAndRead(console.getOutputStream(), console.getErrorStream(), monitor);
			return cmakeProcess.exitValue() == 0;
		}
		return false;
	}
	
	private String[] calculateEnvironmentVariables(IProject project, File ros_dir) {
		ILaunchManager manager = DebugPlugin.getDefault().getLaunchManager();
		Map<String, String> environment = manager.getNativeEnvironment();
		Integer index = 0;
		int env_size = environment.size();

		if(ros_dir != null && ros_dir.exists()) {
			env_size = env_size + 3;
		}
		
		String[] environmentVariables = new String[env_size];
		String workspacePath = project.getWorkspace().getRoot().getLocation().toOSString();
		for(Map.Entry<String, String> entry: environment.entrySet()) {
			if(entry.getKey().contentEquals("SMART_PACKAGE_PATH")) {
				environmentVariables[index] = entry.getKey() + "=" + entry.getValue() + ":" + workspacePath;
			} else if(entry.getKey().equals("PATH") && ros_dir != null && ros_dir.exists()) {
				environmentVariables[index] = entry.getKey() + "=" + ros_dir.getPath()+"/bin:" + entry.getValue();
			} else {
				environmentVariables[index] = entry.getKey() + "=" + entry.getValue();
			}
			index++;
		}
		
		if(ros_dir != null && ros_dir.exists()) {
			// add ROS main environment variables:
			environmentVariables[index++] = "ROS_ROOT="+ros_dir.getPath()+"/share/ros";
			environmentVariables[index++] = "ROS_PACKAGE_PATH="+ros_dir.getPath()+"/share";
			String python_version = "3";
			if(ros_dir.getName().contentEquals("melodic") || ros_dir.getName().contentEquals("kinetic")) {
				python_version = "2.7";
			}
			environmentVariables[index++] = "PYTHONPATH="+ros_dir.getPath()+"/lib/python"+python_version+"/dist-packages:/usr/lib/python"+python_version+"/dist-packages";
		}
		
		return environmentVariables;
	}
}
