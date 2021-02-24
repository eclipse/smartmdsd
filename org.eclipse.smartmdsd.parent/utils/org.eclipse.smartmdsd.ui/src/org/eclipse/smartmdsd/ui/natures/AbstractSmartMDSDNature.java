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
package org.eclipse.smartmdsd.ui.natures;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectNature;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.smartmdsd.ui.builder.CDTProjectHelpers;
import org.eclipse.smartmdsd.ui.models.SmartMDSDModelingLanguage;
import org.eclipse.smartmdsd.ui.models.SmartMDSDModelingLanguageRegistry;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IEditorRegistry;
import org.eclipse.ui.PlatformUI;

/**
 * This is a base class for all project-natures implemented in the SmartMDSD Toolchain.
 * Each nature implementation serves two purposes:
 * 1) To provide a specific project-type related to a specific role-view (e.g. DomainModels at Tier2, or Components at Tier3)
 * 2) To specify the related model-types (with their model injectors) and to specify the dependency of this nature to other nature types
 *    (e.g. the Component nature depends on models from DomainModels). This dependency needs to be implement by the getDependentNatureFor() method;
 * 
 * @author alex-lotz
 *
 */
public abstract class AbstractSmartMDSDNature implements IProjectNature {
	// implement these abstract methods in derived classes
	public abstract String getNatureID();
	public abstract List<String> getImportedProjectNatureIds();

	protected IProject project;

	@Override
	public void configure() throws CoreException {
		if(project != null) {
			CDTProjectHelpers.setSmartMDSDCdtBuilderFor(project);
		}
	}

	@Override
	public void deconfigure() throws CoreException {
		if(project != null) {
			CDTProjectHelpers.resetDefaultCdtBuilderFor(project);
		}
	}

	@Override
	public IProject getProject() {
		return project;
	}

	@Override
	public void setProject(IProject project) {
		this.project = project;
	}
	
	
	protected static Map<String, SmartMDSDModelingLanguageRegistry> languages_registry = new HashMap<String, SmartMDSDModelingLanguageRegistry>();
	public static void initializeRegistry() {
		for(SmartMDSDNatureEnum nature: SmartMDSDNatureEnum.values()) {
			languages_registry.put(nature.getId(), new SmartMDSDModelingLanguageRegistry(nature));
		}
	}
	
	public SmartMDSDModelingLanguage getLanguage(String languageName) {
		return languages_registry.get(getNatureID()).getLanguage(languageName);
	}
	
	public SmartMDSDModelingLanguage getLanguageFrom(IResource modelResource) {
		try {
			IProject project = modelResource.getProject();
			if(project.hasNature(getNatureID())) {
				IEditorRegistry editorRegistry = PlatformUI.getWorkbench().getEditorRegistry();
				IEditorDescriptor editor = editorRegistry.getDefaultEditor(modelResource.getName());
				if(editor != null) {
					// this can be null if resource-type is unknown
					return languages_registry.get(getNatureID()).getLanguageFrom(editor.getId());
				}
			}
		} catch (CoreException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Collection<SmartMDSDModelingLanguage> getAllSupportedLanguages() {
		return languages_registry.get(getNatureID()).getAllLanguages();
	}
}
