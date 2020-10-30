/********************************************************************************
 * Copyright (c) 2017 Technische Hochschule Ulm, Servicerobotics Ulm, Germany
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
package org.eclipse.smartmdsd.ecore.system.targetPlatform

class TargetPlatformUtility {
	static def addDefaultLocalhostTarget(TargetPlatformModel model) {
		// create new localhost target platform definition
		val targetPlatformDef = TargetPlatformFactory.eINSTANCE.createTargetPlatformDefinition;
		targetPlatformDef.name = "LocalhostTarget"
		
		// create a default localhiost network interface
		val ni = TargetPlatformFactory.eINSTANCE.createNetworkInterface
		ni.name = "localhost"
		ni.hostAddress = "127.0.0.1"
		// add the new network interface to the target-definition
		targetPlatformDef.elements.add(ni)
		// add the new target-definition to the target model
		model.elements.add(targetPlatformDef)
		// return the newly created target-definition
		return targetPlatformDef
	}
}
