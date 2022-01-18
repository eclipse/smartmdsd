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
package org.eclipse.smartmdsd.ui;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;

public class SmartMDSDProjectImportContributionRegistry {
	private static Collection<ISmartMDSDProjectImportContribution> registry;
	
	public static void initialize() {
		registry = new ArrayList<>();
		IConfigurationElement[] configurations = Platform.getExtensionRegistry().getConfigurationElementsFor("org.eclipse.smartmdsd.ui.SmartMDSDProjectImportContributionEP");
		try {
			for(IConfigurationElement config: configurations) {
					// get the "class" object from the extension (which should implement the AbstractGenerator interface)
					Object object = config.createExecutableExtension("class");
					if(object instanceof ISmartMDSDProjectImportContribution) {
						ISmartMDSDProjectImportContribution contribution = (ISmartMDSDProjectImportContribution)object;
						System.out.println("Register SmartMDSD Project Imports Contribution for: "+contribution.getParentProjectNature());
						registry.add(contribution);
					}
			}
		} catch (CoreException e) {
			e.printStackTrace();
		}
	}
	
	public static Collection<IProject> getFilteredProjectImports(IWorkspaceRoot root, String current_nature_id, IProject current_project) {
		Collection<IProject> result = new ArrayList<>();
		try {
			IProject[] allWorkspaceProjects = root.getProjects();
			if (allWorkspaceProjects != null) {
				for(ISmartMDSDProjectImportContribution contribution: registry) {
					if(current_nature_id.contentEquals(contribution.getParentProjectNature())) {
						for(String imported_nature: contribution.getImportedProjectNatures()) {
							for(IProject candidate: allWorkspaceProjects) {
								if(candidate.hasNature(imported_nature) && candidate != current_project) {
									result.add(candidate);
								}
							}
						}
					}
				}
			}
		} catch (CoreException e) {
			e.printStackTrace();
		}
		return result;
	}
}
