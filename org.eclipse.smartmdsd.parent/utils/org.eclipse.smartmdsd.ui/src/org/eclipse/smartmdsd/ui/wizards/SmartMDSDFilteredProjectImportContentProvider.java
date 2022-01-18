/********************************************************************************
 * Copyright (c) 2022 Technische Hochschule Ulm, Servicerobotics Ulm, Germany
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
package org.eclipse.smartmdsd.ui.wizards;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.smartmdsd.ui.SmartMDSDProjectImportContributionRegistry;
import org.eclipse.smartmdsd.ui.natures.AbstractSmartMDSDNature;
import org.eclipse.smartmdsd.ui.natures.SmartMDSDNatureEnum;
import org.eclipse.ui.model.WorkbenchContentProvider;

public class SmartMDSDFilteredProjectImportContentProvider extends WorkbenchContentProvider {
	private IProject currentProject = null;
	private SmartMDSDNatureEnum currentNatureEnum = null;
	
	public SmartMDSDFilteredProjectImportContentProvider(IProject currentProject) {
		this.currentProject = currentProject;
	}
	
	public SmartMDSDFilteredProjectImportContentProvider(SmartMDSDNatureEnum currentNatureEnum) {
		this.currentNatureEnum = currentNatureEnum;
	}
	
	@Override
	public Object[] getChildren(Object element) {
		IWorkspaceRoot root = null;
		if (element instanceof IWorkspace) {
			root = ((IWorkspace) element).getRoot();
		} else if(element instanceof IWorkspaceRoot) {
			root = (IWorkspaceRoot)element; 
		} else {
			return new Object[0];
		}
		IProject[] allWorkspaceProjects = root.getProjects();
		if (allWorkspaceProjects != null) {
			List<IProject> filteredProjects = new ArrayList<IProject>();
			try {
				AbstractSmartMDSDNature currentNatureObject = null;
				if(currentProject != null) {
					for(SmartMDSDNatureEnum nature: SmartMDSDNatureEnum.values()) {
						if(currentProject.hasNature(nature.getId())) {
							currentNatureObject = nature.getSmartMDSDNatureFrom(currentProject);
						}
					}
				} else if(currentNatureEnum != null) {
					currentNatureObject = currentNatureEnum.createSmartMDSDNatureObject();
				}
				
				if(currentNatureObject != null) {
					List<String> relatedProjectNatureIds = currentNatureObject.getImportedProjectNatureIds();
					for (IProject project : allWorkspaceProjects) {
						if (project.isOpen()) {
							for(String natureId: relatedProjectNatureIds) {
								if(project.hasNature(natureId) == true) {
									if(project != currentProject) {
										filteredProjects.add(project);
									}
								}
							}
						}
					}
					filteredProjects.addAll(SmartMDSDProjectImportContributionRegistry.getFilteredProjectImports(root, currentNatureObject.getNatureID(), currentProject));
				}
			} catch (CoreException e) {
				e.printStackTrace();
			}
			return filteredProjects.toArray();
		}
		return new Object[0];
	}
}
