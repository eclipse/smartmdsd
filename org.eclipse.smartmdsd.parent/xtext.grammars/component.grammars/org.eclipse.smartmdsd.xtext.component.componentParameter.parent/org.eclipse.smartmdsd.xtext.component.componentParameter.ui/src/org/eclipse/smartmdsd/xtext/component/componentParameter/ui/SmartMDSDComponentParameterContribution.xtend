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
package org.eclipse.smartmdsd.xtext.component.componentParameter.ui

import org.eclipse.smartmdsd.ui.models.ISmartMDSDXtextContribution
import org.eclipse.smartmdsd.ui.natures.SmartMDSDNatureEnum
import org.eclipse.smartmdsd.ecore.component.componentParameter.ComponentParameterPackage
import org.eclipse.smartmdsd.xtext.component.componentParameter.ui.internal.ComponentParameterActivator

class SmartMDSDComponentParameterContribution implements ISmartMDSDXtextContribution {
	override getEPackage() {
		return ComponentParameterPackage.eINSTANCE
	}
	
	override getSmartMDSDNatureEnum() {
		return SmartMDSDNatureEnum.ComponentNature
	}

	override getXtextEditorID() {
		return ComponentParameterActivator.ORG_ECLIPSE_SMARTMDSD_XTEXT_COMPONENT_COMPONENTPARAMETER_COMPONENTPARAMETER
	}
	
	override getXtextInjector() {
		return ComponentParameterActivator.instance.getInjector(xtextEditorID)
	}
	
	override isDefaultLanguage() {
		return true
	}
}