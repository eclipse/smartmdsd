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
package org.eclipse.smartmdsd.xtext.service.domainModelsDatasheet.formatting2

import org.eclipse.smartmdsd.xtext.base.genericDatasheet.formatting2.GenericDatasheetFormatter
import org.eclipse.smartmdsd.ecore.service.domainModelsDatasheet.DomainModelsDatasheet
import org.eclipse.xtext.formatting2.IFormattableDocument

class DomainModelsDatasheetFormatter extends GenericDatasheetFormatter {
	
//	@Inject extension DomainModelsDatasheetGrammarAccess
	
	def dispatch void format(DomainModelsDatasheet domainModelsDatasheet, extension IFormattableDocument document) {
		val ropen = domainModelsDatasheet.regionFor.keyword("{")
		val rclose = domainModelsDatasheet.regionFor.keyword("}")
		ropen.prepend[newLine]
		ropen.append[newLine]
		interior(ropen, rclose)[indent]
		rclose.prepend[newLine]
		
		for(element: domainModelsDatasheet.elements) {
			element.format
			element.append[newLine]
		}
	}
}
