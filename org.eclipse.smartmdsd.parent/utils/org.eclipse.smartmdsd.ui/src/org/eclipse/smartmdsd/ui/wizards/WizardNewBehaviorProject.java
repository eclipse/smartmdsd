/********************************************************************************
 * Copyright (c) 2019 Technische Hochschule Ulm, Servicerobotics Ulm, Germany
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
package org.eclipse.smartmdsd.ui.wizards;

import java.io.ByteArrayInputStream;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.ui.dialogs.WizardNewProjectCreationPage;
import org.eclipse.smartmdsd.ui.natures.SmartMDSDNatureEnum;

public class WizardNewBehaviorProject extends AbstractProjectCreationWizard {

	public static final String WIZARD_ID = "org.eclipse.smartmdsd.ui.wizards.behavior";
	
	@Override
	protected WizardNewProjectCreationPage createFirstPage() {
		WizardNewProjectCreationPage page = new WizardNewProjectCreationPage("BehaviorWizard");
		page.setTitle("Behavior Project (Tier 3)");
		page.setDescription("This wizard creates a Behavior project with the focus on behavior task models.");
		page.setInitialProjectName("BehaviorAnyName");
		return page;
	}

	@Override
	protected boolean isModelingProject() {
		return false;
	}

	@Override
	protected void customizeProject(IProject project, IFolder modelFolder, IProgressMonitor monitor) throws CoreException {
		// add a dummy CMakeLists.txt file so that the project builder does not show build errors anymore
		IFolder smartsoft = project.getFolder("smartsoft");
		smartsoft.create(true, true, monitor);
		IFile cmake = smartsoft.getFile("CMakeLists.txt");
		String cmake_string = "cmake_minimum_required(VERSION 3.5)\nproject("+project.getName()+")";
		cmake.create(new ByteArrayInputStream(cmake_string.getBytes()), true, monitor);
	}

	@Override
	protected SmartMDSDNatureEnum getCurrentNatureEnum() {
		return SmartMDSDNatureEnum.BehaviorNature;
	}

}
