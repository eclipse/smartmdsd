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

public class DomainModelsNature extends AbstractSmartMDSDNature {
	public static final String NATURE_ID = "org.eclipse.smartmdsd.ui.natures.DomainModelsNature";
	
	public static SmartMDSDModelingLanguageRegistry getRegistry() {
		return languages_registry.get(NATURE_ID);
	}
	
	public static SmartMDSDModelingLanguage getDSL(EPackage epackage) {
		return getRegistry().getLanguage(epackage);
	}
	
	@Override
	public String getNatureID() {
		return NATURE_ID;
	}
	
	@Override
	public List<String> getImportedProjectNatureIds() {
		return Arrays.asList(DomainModelsNature.NATURE_ID);
	}
}
