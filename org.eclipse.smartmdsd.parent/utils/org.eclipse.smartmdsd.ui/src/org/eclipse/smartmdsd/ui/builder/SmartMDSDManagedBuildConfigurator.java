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

import org.eclipse.cdt.managedbuilder.core.IManagedBuildInfo;
import org.eclipse.cdt.managedbuilder.core.ManagedBuilderCorePlugin;
import org.eclipse.cdt.managedbuilder.makegen.IManagedBuilderMakefileGenerator;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.smartmdsd.ui.Activator;
import org.eclipse.smartmdsd.ui.preferences.SmartMDSDPreferencesPage;

public class SmartMDSDManagedBuildConfigurator implements IManagedBuilderMakefileGenerator {

	public static final String BUILDER_ID = "org.eclipse.smartmdsd.ui.smartmdsd.builder";
	
	private IProject project = null;
	private IPath buildWorkingDir = null;
	private String buildConfigurationName = "";
	
	
	@Override
	public void generateDependencies() throws CoreException {
		regenerateDependencies(false);
	}

	@Override
	public MultiStatus generateMakefiles(IResourceDelta delta) throws CoreException {
		return regenerateMakefiles();
	}

	@Override
	public IPath getBuildWorkingDir() {
		return buildWorkingDir;
	}

	@Override
	public String getMakefileName() {
		return "Makefile";
	}

	@Override
	public void initialize(IProject project, IManagedBuildInfo info, IProgressMonitor monitor) {
		this.project = project;
		String generatorFolderName = Activator.getDefault().getPreferenceStore().getString(SmartMDSDPreferencesPage.PROP_GENERATOR_FOLDER);
		String buildFolderName = Activator.getDefault().getPreferenceStore().getString(SmartMDSDPreferencesPage.PROP_BUILD_FOLDER);
		this.buildWorkingDir = new Path(generatorFolderName + IPath.SEPARATOR + buildFolderName);
		this.buildConfigurationName = info.getConfigurationName();
	}

	@Override
	public boolean isGeneratedResource(IResource resource) {
		if(resource != null) {
			return resource.isDerived();	
		}
		return false;
	}

	@Override
	public void regenerateDependencies(boolean force) throws CoreException {
		// no-op
	}

	@Override
	public MultiStatus regenerateMakefiles() throws CoreException {
		MultiStatus status = new MultiStatus(
				ManagedBuilderCorePlugin.getUniqueIdentifier(),
				IStatus.INFO,
				"", //$NON-NLS-1$
				null);

		if(project == null) {
			return status;
		}
		
		String defaultBuildType = Activator.getDefault().getPreferenceStore().getString(SmartMDSDPreferencesPage.PROP_CMAKE_BUILD_TYPE);
		if(!buildConfigurationName.contentEquals(defaultBuildType)) {
			CDTProjectHelpers.setActiveBuildTypeFor(project, defaultBuildType);
		}
		
//		// Create the CMake console that can execute the "cmake .." command and redirect its message to the console
//		CMakeConsoleRunnable cmakeRunnable = new CMakeConsoleRunnable(project, buildConfigurationName);
//		// trigger execution of the cmakeRunnable
//		ResourcesPlugin.getWorkspace().run(cmakeRunnable, project, IWorkspace.AVOID_UPDATE, null);		
//		// return the cmake run status (which is either OK or ERROR)
//		return cmakeRunnable.getStatus();
		
		return new MultiStatus(
				ManagedBuilderCorePlugin.getUniqueIdentifier(),
				IStatus.OK,
				"CMake of ["+project.getName()+"] successfully finished!", null);
	}
}
