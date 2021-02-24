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
package org.eclipse.smartmdsd.xtext.system.datasheetPropertyChecks.ui

import org.eclipse.smartmdsd.ui.models.ISmartMDSDXtextContribution
import org.eclipse.smartmdsd.ui.natures.SmartMDSDNatureEnum
import org.eclipse.smartmdsd.ecore.system.datasheetPropertyChecks.DatasheetPropertyChecksPackage
import org.eclipse.smartmdsd.xtext.system.datasheetPropertyChecks.ui.internal.DatasheetPropertyChecksActivator

class SmartMDSDDSPropertyChecksContribution implements ISmartMDSDXtextContribution {
	override getEPackage() {
		return DatasheetPropertyChecksPackage.eINSTANCE
	}
	
	override getSmartMDSDNatureEnum() {
		return SmartMDSDNatureEnum.SystemNature
	}

	override getXtextEditorID() {
		return DatasheetPropertyChecksActivator.ORG_ECLIPSE_SMARTMDSD_XTEXT_SYSTEM_DATASHEETPROPERTYCHECKS_SYSTEMDATASHEETPROPERTYCHECKS
	}
	
	override getXtextInjector() {
		return DatasheetPropertyChecksActivator.instance.getInjector(xtextEditorID)
	}
	
	override isDefaultLanguage() {
		return false
	}
}