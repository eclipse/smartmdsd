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
package org.eclipse.smartmdsd.ecore.system.causeEffectChain

import java.util.Collection
import org.eclipse.emf.ecore.EObject
import org.eclipse.smartmdsd.ui.factories.ISmartMDSDModelFactory
import java.util.Collections
import org.eclipse.smartmdsd.ecore.system.activityArchitecture.ActivityArchitecturePackage
import org.eclipse.smartmdsd.ecore.system.activityArchitecture.ActivityArchitectureModel

class SmartMDSDCauseEffectChainFactory implements ISmartMDSDModelFactory {
	override getEPackage() {
		return CauseEffectChainPackage.eINSTANCE;
	}
	
	override getParentEPackages() {
		return Collections.singletonList(ActivityArchitecturePackage.eINSTANCE)
	}
		
	override createDefaultModel(String projectName, Collection<EObject> parentModels) {
		var ActivityArchitectureModel activityArch = null;
		for(parent: parentModels) {
			if(parent instanceof ActivityArchitectureModel) {
				activityArch = parent
			}
		}
		
		val model = CauseEffectChainFactory.eINSTANCE.createCuaseEffectChainModel();
		model.setName(projectName);
		if(activityArch !== null) {
			model.setActArch(activityArch);
		}
		return model;
	}
}
