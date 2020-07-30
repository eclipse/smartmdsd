/********************************************************************************
 * Copyright (c) 2019 Technische Hochschule Ulm, Servicerobotics Ulm, Germany
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *   Matthias Lutz, Alex Lotz, Dennis Stampfer
 ********************************************************************************/
package org.eclipse.smartmdsd.xtext.behavior.skillRealization


/**
 * Initialization support for running Xtext languages without Equinox extension registry.
 */
class SkillRealizationStandaloneSetup extends SkillRealizationStandaloneSetupGenerated {

	def static void doSetup() {
		new SkillRealizationStandaloneSetup().createInjectorAndDoEMFRegistration()
	}
}