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
package org.eclipse.smartmdsd.ecore.component.componentParameter

import java.util.Collection
import org.eclipse.emf.ecore.EObject
import org.eclipse.smartmdsd.ui.factories.ISmartMDSDModelFactory
import org.eclipse.smartmdsd.ecore.component.componentDefinition.ComponentDefinition
import java.util.Collections
import org.eclipse.smartmdsd.ecore.component.componentDefinition.ComponentDefinitionPackage

class SmartMDSDComponentParameterFactory implements ISmartMDSDModelFactory {
	override getEPackage() {
		return ComponentParameterPackage.eINSTANCE;
	}
	
	override getParentEPackages() {
		return Collections.singletonList(ComponentDefinitionPackage.eINSTANCE)
	}
		
	override createDefaultModel(String projectName, Collection<EObject> parentModels) {
		var ComponentDefinition component = null;
		for(parent: parentModels) {
			if(parent instanceof ComponentDefinition) {
				component = parent
			}
		}
		
		val model = ComponentParameterFactory.eINSTANCE.createComponentParamModel();
		val parameter = ComponentParameterFactory.eINSTANCE.createComponentParameter();
		parameter.setName(projectName);
		if(component !== null) {
			parameter.setComponent(component);
		}
		model.setParametrization(parameter);
		return model;
	}
}
