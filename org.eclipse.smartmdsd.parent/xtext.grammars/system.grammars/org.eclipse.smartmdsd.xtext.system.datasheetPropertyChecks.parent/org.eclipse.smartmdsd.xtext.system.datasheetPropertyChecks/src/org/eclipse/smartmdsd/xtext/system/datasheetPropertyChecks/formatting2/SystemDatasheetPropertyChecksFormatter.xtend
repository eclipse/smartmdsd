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
package org.eclipse.smartmdsd.xtext.system.datasheetPropertyChecks.formatting2

import org.eclipse.smartmdsd.ecore.system.datasheetPropertyChecks.SystemDatasheetPropertyChecksModel
import org.eclipse.xtext.formatting2.AbstractFormatter2
import org.eclipse.xtext.formatting2.IFormattableDocument

class SystemDatasheetPropertyChecksFormatter extends AbstractFormatter2 {
	
//	@Inject extension SystemDatasheetPropertyChecksGrammarAccess

	def dispatch void format(SystemDatasheetPropertyChecksModel systemDatasheetPropertyChecksModel, extension IFormattableDocument document) {
		// TODO: format HiddenRegions around keywords, attributes, cross references, etc. 
		for (datasheetPropertyCheck : systemDatasheetPropertyChecksModel.checks) {
			datasheetPropertyCheck.format
		}
	}
	
	// TODO: implement for 
}
