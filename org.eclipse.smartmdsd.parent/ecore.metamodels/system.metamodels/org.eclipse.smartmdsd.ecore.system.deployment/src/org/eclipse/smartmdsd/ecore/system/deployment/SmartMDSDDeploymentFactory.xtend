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
package org.eclipse.smartmdsd.ecore.system.deployment

import java.util.Collection
import org.eclipse.emf.ecore.EObject
import org.eclipse.smartmdsd.ui.factories.ISmartMDSDModelFactory
import java.util.Collections
import org.eclipse.smartmdsd.ecore.system.componentArchitecture.ComponentArchitecturePackage
import org.eclipse.smartmdsd.ecore.system.componentArchitecture.SystemComponentArchitecture

class SmartMDSDDeploymentFactory implements ISmartMDSDModelFactory {
	override getEPackage() {
		return DeploymentPackage.eINSTANCE;
	}
	
	override getParentEPackages() {
		return Collections.singletonList(ComponentArchitecturePackage.eINSTANCE)
	}
		
	override createDefaultModel(String projectName, Collection<EObject> parentModels) {
		var SystemComponentArchitecture compArch = null;
		for(parent: parentModels) {
			if(parent instanceof SystemComponentArchitecture) {
				compArch = parent
			}
		}
		
		val model = DeploymentFactory.eINSTANCE.createDeploymentModel();
		model.setName(projectName);
		if(compArch !== null) {
			model.setComponentArch(compArch);
		}
		return model;
	}
}
