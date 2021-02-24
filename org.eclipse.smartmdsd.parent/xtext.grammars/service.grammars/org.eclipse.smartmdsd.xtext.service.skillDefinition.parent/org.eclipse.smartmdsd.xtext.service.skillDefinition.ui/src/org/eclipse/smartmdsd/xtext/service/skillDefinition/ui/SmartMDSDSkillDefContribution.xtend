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
package org.eclipse.smartmdsd.xtext.service.skillDefinition.ui

import org.eclipse.smartmdsd.ui.models.ISmartMDSDXtextContribution
import org.eclipse.smartmdsd.ui.natures.SmartMDSDNatureEnum
import org.eclipse.smartmdsd.ecore.service.skillDefinition.SkillDefinitionPackage
import org.eclipse.smartmdsd.xtext.service.skillDefinition.ui.internal.SkillDefinitionActivator

class SmartMDSDSkillDefContribution implements ISmartMDSDXtextContribution {
	override getEPackage() {
		return SkillDefinitionPackage.eINSTANCE
	}
	
	override getSmartMDSDNatureEnum() {
		return SmartMDSDNatureEnum.DomainModelsNature
	}

	override getXtextEditorID() {
		return SkillDefinitionActivator.ORG_ECLIPSE_SMARTMDSD_XTEXT_SERVICE_SKILLDEFINITION_SKILLDEFINITION
	}
	
	override getXtextInjector() {
		return SkillDefinitionActivator.instance.getInjector(xtextEditorID)
	}
	
	override isDefaultLanguage() {
		return false
	}
}