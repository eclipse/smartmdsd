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
package org.eclipse.smartmdsd.xtext.system.componentArchitecture.formatting2

import org.eclipse.smartmdsd.ecore.system.componentArchitecture.Connection
import org.eclipse.smartmdsd.ecore.system.componentArchitecture.SystemComponentArchitecture
import org.eclipse.smartmdsd.xtext.service.roboticMiddleware.formatting2.RoboticMiddlewareFormatter
import org.eclipse.xtext.formatting2.IFormattableDocument
import org.eclipse.smartmdsd.ecore.system.componentArchitecture.ComponentInstance

class ComponentArchitectureFormatter extends RoboticMiddlewareFormatter {
	
//	@Inject extension ComponentArchitectureGrammarAccess

	def dispatch void format(SystemComponentArchitecture systemModel, extension IFormattableDocument document) {
		val open = systemModel.regionFor.keyword("{")
		val close = systemModel.regionFor.keyword("}")
		open.prepend[newLine]
		open.append[newLine]
		interior(open, close)[indent] 
		
		for (ComponentInstance componentInstance : systemModel.getComponents()) 
		{
			componentInstance.format;
		}
		for (Connection conn : systemModel.connections) 
		{
			conn.format;
		}
	}

	def dispatch void format(ComponentInstance componentInstance, extension IFormattableDocument document) {
		val ropen = componentInstance.regionFor.keyword("{")
		val rclose = componentInstance.regionFor.keyword("}")
		ropen.prepend[newLine]
		ropen.append[newLine]
		interior(ropen, rclose)[indent]
		componentInstance.append[setNewLines(1,1,2)]
			
		for(ex: componentInstance.extensions) {
			ex.format
			ex.append[setNewLines(1,1,2)]
		}
		
		for (port : componentInstance.ports) {
			port.format;
			port.append[setNewLines(1,1,2)]
		}
	}
	
	def dispatch void format(Connection connection, extension IFormattableDocument document) {
		connection.append[setNewLines(1,1,2)]
	}
}
