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

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.xtext.resource.FileExtensionProvider;

import com.google.inject.Injector;

public class SmartMDSDModelingLanguage {
	private ISmartMDSDXtextContribution xtextDSL;
	private String viewpoint_name;
	
	public SmartMDSDModelingLanguage(ISmartMDSDXtextContribution dsl) {
		this.xtextDSL = dsl;
		// the viewpoint is optionally set using the specific setter method (see below)
		this.viewpoint_name = "";
	}
	
	public EPackage getEPackage() {
		return xtextDSL.getEPackage();
	}

	public String getLanguageName() {
		return getEPackage().getName();
	}
	
	public String getXtextEditorID() {
		return xtextDSL.getXtextEditorID();
	}
	
	public Injector getInjector() {
		return xtextDSL.getXtextInjector();
	}
	
	public String getModelFileExtension() {
		return getInjector().getInstance(FileExtensionProvider.class).getPrimaryFileExtension();
	}
	
	public boolean isDefaultLanguage() {
		return xtextDSL.isDefaultLanguage();
	}
	
	public void setSiriusViewpoint(ISmartMDSDSiriusContribution siriusContribution) {
		viewpoint_name = siriusContribution.getViewpointName();
	}
	
	public String getSiriusViewpointName() {
		return viewpoint_name;
	}
	
	public boolean equals(EPackage epackage) {
		return getEPackage().getName().contentEquals(epackage.getName());
	}
}
