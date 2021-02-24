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
package org.eclipse.smartmdsd.xtext.behavior.taskDefinition.ui

import org.eclipse.smartmdsd.ui.models.ISmartMDSDXtextContribution
import org.eclipse.smartmdsd.ui.natures.SmartMDSDNatureEnum
import org.eclipse.smartmdsd.ecore.behavior.taskDefinition.TaskDefinitionPackage
import org.eclipse.smartmdsd.xtext.behavior.taskDefinition.ui.internal.TaskDefinitionActivator

class SmartMDSDTaskDefContribution implements ISmartMDSDXtextContribution {
	override getEPackage() {
		return TaskDefinitionPackage.eINSTANCE
	}
	
	override getSmartMDSDNatureEnum() {
		return SmartMDSDNatureEnum.DomainModelsNature
	}

	override getXtextEditorID() {
		return TaskDefinitionActivator.ORG_ECLIPSE_SMARTMDSD_XTEXT_BEHAVIOR_TASKDEFINITION_TASKDEFINITION
	}
	
	override getXtextInjector() {
		return TaskDefinitionActivator.instance.getInjector(xtextEditorID)
	}
	
	override isDefaultLanguage() {
		return false
	}
}