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
package org.eclipse.smartmdsd.xtext.system.targetPlatform


/**
 * Initialization support for running Xtext languages without Equinox extension registry.
 */
class TargetPlatformStandaloneSetup extends TargetPlatformStandaloneSetupGenerated {

	def static void doSetup() {
		new TargetPlatformStandaloneSetup().createInjectorAndDoEMFRegistration()
	}
}