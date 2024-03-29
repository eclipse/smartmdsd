/********************************************************************************
 * Copyright (c) 2017 Technische Hochschule Ulm, Servicerobotics Ulm, Germany
 * headed by Prof. Dr. Christian Schlegel
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *   Alex Lotz, Matthias Lutz, Dennis Stampfer
 ********************************************************************************/
package org.eclipse.smartmdsd.xtend.smartsoft.generator;

import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.mwe.utils.DirectoryCleaner;
import org.eclipse.xtext.generator.IFileSystemAccess2;
import org.eclipse.xtext.generator.IOutputConfigurationProvider;
import org.eclipse.xtext.generator.JavaIoFileSystemAccess;
import org.eclipse.xtext.generator.OutputConfiguration;

import com.google.inject.Injector;

public class GeneratorHelper {
	private final IProject project;
	private Injector injector;
	
	public GeneratorHelper(Injector injector, Resource resource) {
		this.injector = injector;
		project = getCurrentProjectFrom(resource);
	}
	
	public static IProject getCurrentProjectFrom(Resource resource) {
		return ResourcesPlugin.getWorkspace().getRoot().getProject(resource.getURI().segment(1));
	}
	
	public void createFolder(String outputConfigName) {
		createFolder(outputConfigName, injector, project);
	}
	
	public static void createFolder(String outputConfigName, Injector specificInjector, IProject specificProject) {
		OutputConfiguration config = getOutputConfiguration(outputConfigName, specificInjector);
		if(config != null) {
			String folderStr = config.getOutputDirectory();
			IPath path = new Path(folderStr);
			// create folder with its potential intermediate folders if they do not yet exist
			try {
				IProgressMonitor monitor = new NullProgressMonitor();
				IFolder current_folder = specificProject.getFolder(path.segment(0));
				if(!current_folder.exists()) {
					current_folder.create(true, true, monitor);
				}
				for(int segment_idx=1; segment_idx<path.segmentCount(); segment_idx++) {
					current_folder = current_folder.getFolder(path.segment(segment_idx));
					if(!current_folder.exists()) {
						current_folder.create(true, true, monitor);
					}	
				}
				current_folder.setDerived(config.isSetDerivedProperty(), monitor);
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void deleteFolder(String outputConfigName) {
		deleteFolder(outputConfigName, injector, project);
	}
	
	public static void deleteFolder(String outputConfigName, Injector specificInjector, IProject specificProject) {
		OutputConfiguration config = getOutputConfiguration(outputConfigName, specificInjector);
		if(config != null) {
			String folderStr = config.getOutputDirectory();
			IFolder ifolder = specificProject.getFolder(new Path(folderStr));
			// create folder if it does not yet exist
			try {
				IProgressMonitor monitor = new NullProgressMonitor();
				if(ifolder.exists()) {
					ifolder.delete(true, monitor);
				}
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void deleteSubfolder(String outputConfigName, String subFolderPath) {
		OutputConfiguration config = getOutputConfiguration(outputConfigName, injector);
		if(config != null) {
			String baseFolderStr = config.getOutputDirectory();
			IFolder baseFolder = project.getFolder(baseFolderStr);
			if(baseFolder.exists()) {
				IFolder subFolder = baseFolder.getFolder(new Path(subFolderPath));
				if(subFolder.exists()) {
					try {
						subFolder.delete(true, new NullProgressMonitor());
					} catch (CoreException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	public void invokeDirectoryCleaner() {
		invokeDirectoryCleaner(injector, project);
	}
	public static void invokeDirectoryCleaner(Injector specificInjector, IProject specificProject) {
		invokeDirectoryCleaner(IFileSystemAccess2.DEFAULT_OUTPUT, Collections.<String> emptyList(), specificInjector, specificProject);
	}
	public void invokeDirectoryCleaner(String outputConfigName) {
		invokeDirectoryCleaner(outputConfigName, injector, project);
	}
	public static void invokeDirectoryCleaner(String outputConfigName, Injector specificInjector, IProject specificProject) {
		invokeDirectoryCleaner(outputConfigName, Collections.<String> emptyList(), specificInjector, specificProject);
	}
	
	public void invokeDirectoryCleaner(String outputConfigName, Collection<String> excludes) {
		invokeDirectoryCleaner(outputConfigName, excludes, injector, project);
	}
	public static void invokeDirectoryCleaner(String outputConfigName, Collection<String> excludes, Injector specificInjector, IProject specificProject) {
		// create a new directory cleaner
		DirectoryCleaner directoryCleaner = new DirectoryCleaner();
		
		// add items to be excluded
		for(String exclude: excludes) {
			directoryCleaner.addExclude(exclude);
		}
		
		OutputConfiguration config = getOutputConfiguration(outputConfigName, specificInjector);
		if(config!= null) {
			// get project folder
			IFolder genFolder = specificProject.getFolder(new Path(config.getOutputDirectory()));
			
			// create folder if it does not yet exist
			try {
				IProgressMonitor monitor = new NullProgressMonitor();
				if(!genFolder.exists()) {
					genFolder.create(true, true, monitor);
				}
			} catch (CoreException e) {
				e.printStackTrace();
			}
			
			// execute directory cleaner
			try {
				//System.out.println("Clean directory: "+genFolder.getLocation().toString());
				directoryCleaner.cleanFolder(genFolder.getLocation().toString());
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
	
	public IFileSystemAccess2 getConfiguredFileSystemAccess() {
		return getConfiguredFileSystemAccess(injector);
	}
	
	public IFileSystemAccess2 getConfiguredFileSystemAccess(Injector specificInjector) {
		return getConfiguredFileSystemAccess(specificInjector, project);
	}
	
	public static IFileSystemAccess2 getConfiguredFileSystemAccess(Injector specificInjector, IProject specificProject) {
		JavaIoFileSystemAccess fsa = specificInjector.getInstance(JavaIoFileSystemAccess.class);
		IOutputConfigurationProvider outCP = specificInjector.getInstance(IOutputConfigurationProvider.class);
		
		Set<OutputConfiguration> configs = outCP.getOutputConfigurations();
		
		Map<String, OutputConfiguration> outputs = new HashMap<String, OutputConfiguration>();
		for(OutputConfiguration c: configs) {
			outputs.put(c.getName(), c);
		}
		fsa.setOutputConfigurations(outputs);
		
		// set-up absolute paths for each configuration
		for(OutputConfiguration config: configs) {
			if(config.getName().equals(ExtendedOutputConfigurationProvider.PROJECT_ROOT_FOLDER)) {
				fsa.setOutputPath(config.getName(), specificProject.getLocation().toString());
			} else {
				String outputDirectory = config.getOutputDirectory();
				IFolder f = specificProject.getFolder(new Path(outputDirectory));	
				fsa.setOutputPath(config.getName(), f.getLocation().toString());
			}
		}
		
		return fsa;
	}
	
	public void refreshFolder(String outputConfigName, int depth) {
		refreshFolder(outputConfigName, depth, injector, project);
	}
	
	public static void refreshFolder(String outputConfigName, int depth, Injector specificInjector, IProject specificProject) {
		OutputConfiguration config = getOutputConfiguration(outputConfigName, specificInjector);
		if(config!= null) {
			try {
				// refresh src-gen folder to reflect generation changes
				IProgressMonitor monitor = new NullProgressMonitor();
				if(config.getName().equals(ExtendedOutputConfigurationProvider.PROJECT_ROOT_FOLDER)) {
					specificProject.refreshLocal(2, monitor);
				} else {
					specificProject.getFolder(new Path(config.getOutputDirectory())).refreshLocal(2, monitor);
				}
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}
	}
	
	public OutputConfiguration getOutputConfiguration(String outputConfigName) {
		return getOutputConfiguration(outputConfigName, injector);
	}
	
	public static OutputConfiguration getOutputConfiguration(String outputConfigName, Injector specificInjector) {
		IOutputConfigurationProvider outCP = specificInjector.getInstance(IOutputConfigurationProvider.class);
		Set<OutputConfiguration> configs = outCP.getOutputConfigurations();
		for(OutputConfiguration config: configs) {
			if(config.getName().equals(outputConfigName)) {
				return config;
			}
		}
		return null;
	}
}
