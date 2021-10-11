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
import java.util.Map;

import org.eclipse.cdt.core.CommandLauncher;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.smartmdsd.ui.Activator;
import org.eclipse.smartmdsd.ui.preferences.SmartMDSDPreferencesPage;

public class SmartMDSDBuildCommandLauncher extends CommandLauncher 
{
	private String[] calculateSmartMdsdEnvironment(IProject project) {
		ILaunchManager manager = DebugPlugin.getDefault().getLaunchManager();
		Map<String, String> environment = manager.getNativeEnvironment();
		Integer index = 0;
		int env_size = environment.size();

		String ros_distro_dir = Activator.getDefault().getPreferenceStore().getString(SmartMDSDPreferencesPage.PROP_ROS_DISTRIBUTION_DIR);
		File ros_dir = new File(ros_distro_dir);
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
			String python_version = "3";
			if(ros_dir.getName().contentEquals("melodic") || ros_dir.getName().contentEquals("kinetic")) {
				python_version = "2.7";
			}
			environmentVariables[index++] = "PYTHONPATH="+ros_dir.getPath()+"/lib/python"+python_version+"/dist-packages:/usr/lib/python"+python_version+"/dist-packages";
		}
		
		return environmentVariables;
	}
	
	@Override
	public Process execute(IPath commandPath, String[] args, String[] env, IPath workingDirectory,
			IProgressMonitor monitor) throws CoreException 
	{
		String[] environment = calculateSmartMdsdEnvironment(getProject());
		if(workingDirectory != null) {
			// make sure the working directory actually exists
			workingDirectory.toFile().mkdirs();
		}
		return super.execute(commandPath, args, environment, workingDirectory, monitor);
	}
	
	
	@Override
	protected String[] constructCommandArray(String command, String[] commandArgs) {
		String[] full_arguments = new String[3];
		full_arguments[0] = command;
		full_arguments[1] = "-c";
		
		String command_line = "cmake";
		
		command_line = command_line + " -DBUILD_DEPENDENCIES=OFF";
		
		String defaultBuildType = Activator.getDefault().getPreferenceStore().getString(SmartMDSDPreferencesPage.PROP_CMAKE_BUILD_TYPE);
		command_line = command_line +" -DCMAKE_BUILD_TYPE="+defaultBuildType;
		
		String ros_distro_dir = Activator.getDefault().getPreferenceStore().getString(SmartMDSDPreferencesPage.PROP_ROS_DISTRIBUTION_DIR);
		File ros_dir = new File(ros_distro_dir);
		if(ros_dir.exists()) {
			command_line = command_line + " -DCMAKE_PREFIX_PATH="+ros_dir.getPath();
		}

		command_line = command_line + " .. && echo && echo 'Build Project: "+getProject().getName()+"' && cmake --build . --";
		for(String arg: commandArgs) {
			command_line = command_line+" "+arg;
		}
		
		full_arguments[2] = command_line;
		
		return full_arguments;
	}
}
