/********************************************************************************
 * Copyright (c) 2017 Technische Hochschule Ulm, Servicerobotics Ulm, Germany
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
package org.eclipse.smartmdsd.xtext.service.componentMode

import com.google.inject.Injector
import org.eclipse.emf.ecore.EPackage
import org.eclipse.smartmdsd.ecore.service.componentMode.ComponentModePackage

/**
 * Initialization support for running Xtext languages without Equinox extension registry.
 */
class ComponentModeStandaloneSetup extends ComponentModeStandaloneSetupGenerated {

	def static void doSetup() {
		new ComponentModeStandaloneSetup().createInjectorAndDoEMFRegistration()
	}
	
	override register(Injector injector) {
		if(!EPackage.Registry.INSTANCE.containsKey(ComponentModePackage.eNS_URI)) {
			EPackage.Registry.INSTANCE.put(ComponentModePackage.eNS_URI,
				ComponentModePackage.eINSTANCE
			)
		}
		super.register(injector)
	}
}
