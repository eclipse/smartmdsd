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
package org.eclipse.smartmdsd.xtext.behavior.taskRealization.ui

import org.eclipse.smartmdsd.ui.models.ISmartMDSDXtextContribution
import org.eclipse.smartmdsd.ui.natures.SmartMDSDNatureEnum
import org.eclipse.smartmdsd.ecore.behavior.taskRealization.TaskRealizationPackage
import org.eclipse.smartmdsd.xtext.behavior.taskRealization.ui.internal.TaskRealizationActivator

class SmartMDSDTaskRealizationContribution implements ISmartMDSDXtextContribution {
	override getEPackage() {
		return TaskRealizationPackage.eINSTANCE
	}
	
	override getSmartMDSDNatureEnum() {
		return SmartMDSDNatureEnum.BehaviorNature
	}

	override getXtextEditorID() {
		return TaskRealizationActivator.ORG_ECLIPSE_SMARTMDSD_XTEXT_BEHAVIOR_TASKREALIZATION_TASKREALIZATION
	}
	
	override getXtextInjector() {
		return TaskRealizationActivator.instance.getInjector(xtextEditorID)
	}
	
	override isDefaultLanguage() {
		return true
	}
}