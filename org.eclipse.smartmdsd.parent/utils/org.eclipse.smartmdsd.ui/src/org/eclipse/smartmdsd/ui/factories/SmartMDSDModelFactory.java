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
package org.eclipse.smartmdsd.ui.factories;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.smartmdsd.ui.models.SmartMDSDModelingLanguage;
import org.eclipse.smartmdsd.ui.natures.AbstractSmartMDSDNature;
import org.eclipse.smartmdsd.ui.natures.SmartMDSDNatureHelpers;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.ide.IDE;

public class SmartMDSDModelFactory extends XtextResourceFactory {
	private AbstractSmartMDSDNature project_nature;
	private static Map<String, ISmartMDSDModelFactory> registry = null;
	
	public SmartMDSDModelFactory(IProject project, IFolder modelFolder) {
		super(project, modelFolder);
		this.project_nature = SmartMDSDNatureHelpers.getFirstSmartMDSDNatureFrom(project);
		if(registry == null) {
			initializeFactoryRegistry();
		}
	}
	
	public static synchronized void initializeFactoryRegistry() {
		// create new registry map
		registry = new HashMap<String, ISmartMDSDModelFactory>();
		// get all SmartMDSDModelFactoryEP extension points
		IConfigurationElement[] configurations = Platform.getExtensionRegistry().getConfigurationElementsFor("org.eclipse.smartmdsd.ui.SmartMDSDModelFactoryEP");
		try {
			for(IConfigurationElement config: configurations) {
					// get the "class" object from the extension (which should implement the AbstractGenerator interface)
					Object object = config.createExecutableExtension("class");
					if(object instanceof ISmartMDSDModelFactory) {
						ISmartMDSDModelFactory factory = (ISmartMDSDModelFactory)object;
						String packageURI = factory.getEPackage().getNsURI();
						System.out.println("Register SmartMDSD model factory for package "+packageURI);
						registry.put(packageURI, factory);
					}
			}
		} catch (CoreException e) {
			e.printStackTrace();
		}
	}
	
	public boolean createModel(EPackage epackage, IProgressMonitor monitor) {
		Resource resource = loadOrCreateResource(epackage.getName(),null);
		return resource != null && resource.isLoaded();
	}
	
	public boolean createSelectedModels(List<String> selectedModelTypes, IProgressMonitor monitor) {
		boolean allResourcesCreated = true;
		for(String languageName: selectedModelTypes) {
			Resource resource = loadOrCreateResource(languageName,null);
			if(resource == null || !resource.isLoaded()) {
				allResourcesCreated = false;
			}
		}
		return allResourcesCreated;
	}
	
	public void openSelectedModelsInEditor(IWorkbench workbench, List<String> selectedModelTypes) {
		IWorkbenchPage page = workbench.getActiveWorkbenchWindow().getActivePage();
		for(String languageName: selectedModelTypes) {
			SmartMDSDModelingLanguage dsl = project_nature.getLanguage(languageName);
			if(dsl.getSiriusViewpointName().isEmpty()) {
				// open model file in related editor
				String fileName = getProject().getName() + "." + dsl.getModelFileExtension();
				IFile modelFile = getModelFolder().getFile(fileName);
				try {
					IDE.openEditor(page, modelFile);
				} catch (PartInitException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private Resource loadOrCreateResource(String languageName, List<String> processedLangauges) {
		if(processedLangauges == null) {
			processedLangauges = new ArrayList<String>();
		}
		// this list is used to detect dependency cycles
		// if a cycle is detected, then the recursive execution
		// will be interrupted to prevent a stack overflow.
		processedLangauges.add(languageName);
		
		// get the language and the related model-factory
		SmartMDSDModelingLanguage language = project_nature.getLanguage(languageName);
		ISmartMDSDModelFactory factory = registry.get(language.getEPackage().getNsURI());
		
		// try loading the resource
		Resource resource = loadEMFResource(language.getInjector());
		if(resource == null || !resource.isLoaded()) {
			// collect parent resources recursively (if there are any at all)
			List<Resource> parentResources = new ArrayList<Resource>();
			Collection<EPackage> parentPackages = factory.getParentEPackages();
			if(parentPackages != null) {
				for(EPackage parentPackage: parentPackages) {
					if(processedLangauges.contains(parentPackage.getName())) {
						// the language of the parent package has already been processed in the current recursion stack
						// we must not process it again, as this would lead to an infinite recursion cycle and by that
						// to a stack overflow. A dependency cycle typically results from a poor design of model
						// references. For example if Model A references Model B, and Model B back-references Model A.
						// The cycle can be also more hidden, spanning over multiple references in several models until
						// the cycle loop is closed, so it is good to check for them in any case.
						System.err.println("A dependency cycle detected, skip dependnecy loading to prefent a stack overflow.");
					} else {
						// recursively call resource creation for all parent packages and collect the results
						parentResources.add(loadOrCreateResource(parentPackage.getName(), processedLangauges));
					}
				}
			}
			// create a new resource
			resource = createNewXtextResource(language.getInjector(), parentResources);
			// collect parent model objects
			List<EObject> parentModels = new ArrayList<EObject>();
			for(Resource parent: parentResources) {
				parentModels.add(parent.getContents().get(0));
			}
			// create the default model using the factory
			EObject model = factory.createDefaultModel(getProject().getName(), parentModels);
			// save the model resource
			saveEMFModelInResource(model, resource);
		}
		return resource;
	}
}
