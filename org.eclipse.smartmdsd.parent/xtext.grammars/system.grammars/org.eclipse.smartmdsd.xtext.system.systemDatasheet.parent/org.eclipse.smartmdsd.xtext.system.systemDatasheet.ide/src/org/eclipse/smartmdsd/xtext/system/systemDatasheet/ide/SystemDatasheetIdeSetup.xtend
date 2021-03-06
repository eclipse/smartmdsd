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
package org.eclipse.smartmdsd.xtext.system.systemDatasheet.ide

import com.google.inject.Guice
import org.eclipse.smartmdsd.xtext.system.systemDatasheet.SystemDatasheetRuntimeModule
import org.eclipse.smartmdsd.xtext.system.systemDatasheet.SystemDatasheetStandaloneSetup
import org.eclipse.xtext.util.Modules2

/**
 * Initialization support for running Xtext languages as language servers.
 */
class SystemDatasheetIdeSetup extends SystemDatasheetStandaloneSetup {

	override createInjector() {
		Guice.createInjector(Modules2.mixin(new SystemDatasheetRuntimeModule, new SystemDatasheetIdeModule))
	}
	
}
