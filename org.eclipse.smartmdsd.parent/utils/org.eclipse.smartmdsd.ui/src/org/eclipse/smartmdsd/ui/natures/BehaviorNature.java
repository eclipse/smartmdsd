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

public class BehaviorNature extends AbstractSmartMDSDNature {

	public static final String NATURE_ID = "org.eclipse.smartmdsd.ui.natures.BehaviorNature";
	
	public static SmartMDSDModelingLanguageRegistry getRegistry() {
		return languages_registry.get(NATURE_ID);
	}
	
	public static SmartMDSDModelingLanguage getDSL(EPackage epackage) {
		return getRegistry().getLanguage(epackage);
	}
	
//	public enum DSL implements LanguageInterface {
//		TaskRealization (TaskRealizationActivator.ORG_ECLIPSE_SMARTMDSD_XTEXT_BEHAVIOR_TASKREALIZATION_TASKREALIZATION)
//		;
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
//		@Override
//		public String getLanguageID() {
//			return languageID;
//		}
//
//		@Override
//		public Injector getInjector() {
//			switch (this) {
//			case TaskRealization:
//				return TaskRealizationActivator.getInstance().getInjector(languageID);
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
//		@Override
//		public String getSiriusViewpointName() {
//			return null;
//		}
//
//		@Override
//		public String getKey() {
//			return this.name();
//		}
//
//		@Override
//		public boolean isDefaultLanguage() {
//			switch (this) {
//			case TaskRealization: 
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
