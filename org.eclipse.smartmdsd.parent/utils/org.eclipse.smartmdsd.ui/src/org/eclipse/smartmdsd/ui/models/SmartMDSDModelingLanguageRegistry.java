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
package org.eclipse.smartmdsd.ui.models;

import java.util.Map;
import java.util.Collection;
import java.util.HashMap;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.smartmdsd.ui.natures.SmartMDSDNatureEnum;

public class SmartMDSDModelingLanguageRegistry {
	private Map<String, SmartMDSDModelingLanguage> language_registry = new HashMap<String, SmartMDSDModelingLanguage>();
	
	public SmartMDSDModelingLanguageRegistry(SmartMDSDNatureEnum nature) {
		createRegistry(nature);
	}
	
	public Collection<SmartMDSDModelingLanguage> getAllLanguages() {
		return language_registry.values();
	}

	public Collection<String> getLanguageNames() {
		return language_registry.keySet();
	}
	
	public SmartMDSDModelingLanguage getLanguage(String languageName) {
		return language_registry.get(languageName);
	}
	
	public SmartMDSDModelingLanguage getLanguage(EPackage epackage) {
		return language_registry.get(epackage.getName());
	}
	
	public SmartMDSDModelingLanguage getLanguageFrom(String xtextEditorID) {
		for(SmartMDSDModelingLanguage language: language_registry.values()) {
			if(language.getXtextEditorID().contentEquals(xtextEditorID)) {
				return language;
			}
		}
		return null;
	}
	
	private void createRegistry(SmartMDSDNatureEnum nature) {
		// get all SmartMDSDModelContributionEP extension points
		IConfigurationElement[] configurations = Platform.getExtensionRegistry().getConfigurationElementsFor("org.eclipse.smartmdsd.ui.SmartMDSDModelContributionEP");
		try {
			// collect xtext contributions
			for(IConfigurationElement config: configurations) {
					// get the "class" object from the extension (which should implement the AbstractGenerator interface)
					Object object = config.createExecutableExtension("class");
					if(object instanceof ISmartMDSDXtextContribution) {
						ISmartMDSDXtextContribution xtextDSL = (ISmartMDSDXtextContribution)object;
						if(xtextDSL.getSmartMDSDNatureEnum().equals(nature)) {
							String nature_name = nature.name();
							String languageName = xtextDSL.getEPackage().getName();
							if(language_registry.containsKey(languageName)) {
								System.err.println("SmartMDSD modeling-language with the name "+languageName+" exists in the nature "+nature_name+". The modeling-language mapping will be overridden!");
							}
							System.out.println("Register SmartMDSD modeling-language "+languageName+" with nature "+nature_name);
							language_registry.put(languageName, new SmartMDSDModelingLanguage(xtextDSL));
						}
					}
			}
			// collect Sirius contributions
			for(IConfigurationElement config: configurations) {
				// get the "class" object from the extension (which should implement the AbstractGenerator interface)
				Object object = config.createExecutableExtension("class");
				if(object instanceof ISmartMDSDSiriusContribution) {
					ISmartMDSDSiriusContribution siriusContribution = (ISmartMDSDSiriusContribution)object;
					if(siriusContribution.getSmartMDSDNatureEnum().equals(nature)) {
						SmartMDSDModelingLanguage language = getLanguage(siriusContribution.getEPackage());
						if(language != null) {
							System.out.println("Add Sirius viewpoint "+siriusContribution.getViewpointName()+" to language "+language.getLanguageName());
							language.setSiriusViewpoint(siriusContribution);
						}
					}
				}
			}
		} catch (CoreException e) {
			e.printStackTrace();
		}
	}
}
