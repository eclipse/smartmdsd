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
package org.eclipse.smartmdsd.xtext.base.genericDatasheet


/**
 * Initialization support for running Xtext languages without Equinox extension registry.
 */
class GenericDatasheetStandaloneSetup extends GenericDatasheetStandaloneSetupGenerated {

	def static void doSetup() {
		new GenericDatasheetStandaloneSetup().createInjectorAndDoEMFRegistration()
	}
}
