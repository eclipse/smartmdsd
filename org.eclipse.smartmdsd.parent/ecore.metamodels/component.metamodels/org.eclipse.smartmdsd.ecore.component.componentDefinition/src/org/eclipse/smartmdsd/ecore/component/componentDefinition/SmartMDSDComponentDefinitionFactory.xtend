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
package org.eclipse.smartmdsd.ecore.component.componentDefinition

import java.util.Collection
import org.eclipse.emf.ecore.EObject
import org.eclipse.smartmdsd.ui.factories.ISmartMDSDModelFactory

class SmartMDSDComponentDefinitionFactory implements ISmartMDSDModelFactory {
	override getEPackage() {
		return ComponentDefinitionPackage.eINSTANCE;
	}
	
	override getParentEPackages() {
		return newArrayList
	}
		
	override createDefaultModel(String projectName, Collection<EObject> parentModels) {
		val model = ComponentDefinitionFactory.eINSTANCE.createComponentDefModel();
		val component = ComponentDefinitionFactory.eINSTANCE.createComponentDefinition();
		component.setName(projectName);
		component.setLogo("/"+projectName+"/model/logo.png");
		model.setComponent(component);
		return model;
	}
}
