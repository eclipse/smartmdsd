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
package org.eclipse.smartmdsd.ecore.service.domainModelsDatasheet

import java.util.Collection
import org.eclipse.emf.ecore.EObject
import org.eclipse.smartmdsd.ui.factories.ISmartMDSDModelFactory
import org.eclipse.smartmdsd.ecore.base.genericDatasheet.GenericDatasheetFactory
import org.eclipse.smartmdsd.ecore.base.genericDatasheet.MandatoryDatasheetElementNames
import org.eclipse.smartmdsd.ecore.base.genericDatasheet.DefaultDatasheetProperties

class SmartMDSDDomainModelsDatasheetFactory implements ISmartMDSDModelFactory {
	override getEPackage() {
		return DomainModelsDatasheetPackage.eINSTANCE;
	}
	
	override getParentEPackages() {
		return newArrayList
	}
		
	override createDefaultModel(String projectName, Collection<EObject> parentModels) {
		val datasheet = DomainModelsDatasheetFactory.eINSTANCE.createDomainModelsDatasheet();
		datasheet.setName(projectName);
		val baseURI = GenericDatasheetFactory.eINSTANCE.createMandatoryDatasheetElement();
		baseURI.setName(MandatoryDatasheetElementNames.BASE_URI);
		baseURI.setValue("http://www.servicerobotik-ulm.de");
		datasheet.getElements().add(baseURI);
		val short_description = GenericDatasheetFactory.eINSTANCE.createMandatoryDatasheetElement();
		short_description.setName(MandatoryDatasheetElementNames.SHORT_DESCRIPTION);
		short_description.setValue("A short description for the "+projectName+" datasheet");
		datasheet.getElements().add(short_description);
		
		val supplier = GenericDatasheetFactory.eINSTANCE.createDatasheetProperty();
		supplier.setName(DefaultDatasheetProperties.SUPPLIER.getLiteral());
		supplier.setValue("No supplier specified");
		datasheet.getElements().add(supplier);
		
		val homepage = GenericDatasheetFactory.eINSTANCE.createDatasheetProperty();
		homepage.setName(DefaultDatasheetProperties.HOMEPAGE.getLiteral());
		homepage.setValue("http://www.example.com");
		datasheet.getElements().add(homepage);
		
		val purpose = GenericDatasheetFactory.eINSTANCE.createDatasheetProperty();
		purpose.setName(DefaultDatasheetProperties.PURPOSE.getLiteral());
		purpose.setValue("Example");
		datasheet.getElements().add(purpose);
		
		return datasheet;
	}
}
