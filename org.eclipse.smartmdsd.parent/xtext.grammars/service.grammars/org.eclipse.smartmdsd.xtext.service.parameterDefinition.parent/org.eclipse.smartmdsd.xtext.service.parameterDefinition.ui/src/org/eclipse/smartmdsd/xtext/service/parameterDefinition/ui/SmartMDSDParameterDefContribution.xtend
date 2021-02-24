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
package org.eclipse.smartmdsd.xtext.service.parameterDefinition.ui

import org.eclipse.smartmdsd.ui.models.ISmartMDSDXtextContribution
import org.eclipse.smartmdsd.ui.natures.SmartMDSDNatureEnum
import org.eclipse.smartmdsd.ecore.service.parameterDefinition.ParameterDefinitionPackage
import org.eclipse.smartmdsd.xtext.service.parameterDefinition.ui.internal.ParameterDefinitionActivator

class SmartMDSDParameterDefContribution implements ISmartMDSDXtextContribution {
	override getEPackage() {
		return ParameterDefinitionPackage.eINSTANCE
	}
	
	override getSmartMDSDNatureEnum() {
		return SmartMDSDNatureEnum.DomainModelsNature
	}

	override getXtextEditorID() {
		return ParameterDefinitionActivator.ORG_ECLIPSE_SMARTMDSD_XTEXT_SERVICE_PARAMETERDEFINITION_PARAMETERDEFINITION
	}
	
	override getXtextInjector() {
		return ParameterDefinitionActivator.instance.getInjector(xtextEditorID)
	}
	
	override isDefaultLanguage() {
		return true
	}
}