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

import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.smartmdsd.ui.models.SmartMDSDModelingLanguage;
import org.eclipse.smartmdsd.ui.models.SmartMDSDModelingLanguageRegistry;

public class ComponentNature extends AbstractSmartMDSDNature {
	public static final String NATURE_ID = "org.eclipse.smartmdsd.ui.natures.ComponentNature";

	public static SmartMDSDModelingLanguageRegistry getRegistry() {
		return languages_registry.get(NATURE_ID);
	}
	
	public static SmartMDSDModelingLanguage getDSL(EPackage epackage) {
		return getRegistry().getLanguage(epackage);
	}
	
//	public enum DSL implements LanguageInterface {
//		ComponentDefinition (ComponentDefinitionActivator.ORG_ECLIPSE_SMARTMDSD_XTEXT_COMPONENT_COMPONENTDEFINITION_COMPONENTDEFINITION),
//		ComponentParameters (ComponentParameterActivator.ORG_ECLIPSE_SMARTMDSD_XTEXT_COMPONENT_COMPONENTPARAMETER_COMPONENTPARAMETER),
//		ComponentDatasheet (ComponentDatasheetActivator.ORG_ECLIPSE_SMARTMDSD_XTEXT_COMPONENT_COMPONENTDATASHEET_COMPONENTDATASHEET),
//		SkillRealization (SkillRealizationActivator.ORG_ECLIPSE_SMARTMDSD_XTEXT_BEHAVIOR_SKILLREALIZATION_SKILLREALIZATION),
//		ComponentDependencies (ComponentExternalDependenciesActivator.ORG_ECLIPSE_SMARTMDSD_XTEXT_COMPONENT_COMPONENTEXTERNALDEPENDENCIES_COMPONENTEXTERNALDEPENDENCIES);
//		
//		
//		// Reverse-lookup map for getting a language enum from a String languageId
//		private static final Map<String, DSL> lookup = new HashMap<String, DSL>();
//		static {
//	        for (DSL lang : DSL.values()) {
//	            lookup.put(lang.getLanguageID(), lang);
//	        }
//	    }
//		// static factory method to construct a LANGUAGES enum element from a full languageID
//		public static DSL getFromID(String languageID) {
//	        return lookup.get(languageID);
//	    }
//		
//		// private internal value representation as String
//		private String languageID;
//		// private constructor only used by the enum itself
//		private DSL(String languageID) {
//			this.languageID = languageID;
//		}
//		
//		
//		@Override
//		public String getLanguageID() {
//			return languageID;
//		}
//		
//		@Override
//		public String getKey() {
//			return this.name();
//		}
//		
//		@Override
//		public Injector getInjector() {
//			switch (this) {
//			case ComponentDefinition:
//				return ComponentDefinitionActivator.getInstance().getInjector(languageID);
//			case ComponentParameters:
//				return ComponentParameterActivator.getInstance().getInjector(languageID);
//			case ComponentDatasheet:
//				return ComponentDatasheetActivator.getInstance().getInjector(languageID);
//			case SkillRealization:
//				return SkillRealizationActivator.getInstance().getInjector(languageID);
//			case ComponentDependencies:
//				return ComponentExternalDependenciesActivator.getInstance().getInjector(languageID);
//			// add further cases when new languages appear
//			default:
//				return null;	
//			}
//		}
//		
//		@Override
//		public String getModelFileExtension() {
//			return getInjector().getInstance(FileExtensionProvider.class).getPrimaryFileExtension();
//		}
//		
//		// this map collects the Sirius Diagram Viewpoint names for the related languages
//		private static final Map<String, String> siriusViewpoints = new HashMap<String, String>();
//		static {
//			siriusViewpoints.put(ComponentDefinition.languageID, "ComponentDefinitionViewpoint");
//		}
//		
//		@Override
//		public String getSiriusViewpointName() {
//			return siriusViewpoints.get(languageID);
//		}
//
//		@Override
//		public boolean isDefaultLanguage() {
//			switch (this) {
//			case ComponentDefinition: 
//			case ComponentDatasheet:
//			case ComponentParameters:
//				return true;
//			default:
//				return false;
//			}
//		}
//	}

	@Override
	public String getNatureID() {
		return NATURE_ID;
	}
	
	@Override
	public List<String> getImportedProjectNatureIds() {
		return Arrays.asList(DomainModelsNature.NATURE_ID);
	}
}
