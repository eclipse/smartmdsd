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
package org.eclipse.smartmdsd.xtext.system.targetPlatform.ui

import org.eclipse.smartmdsd.ui.models.ISmartMDSDXtextContribution
import org.eclipse.smartmdsd.ui.natures.SmartMDSDNatureEnum
import org.eclipse.smartmdsd.ecore.system.targetPlatform.TargetPlatformPackage
import org.eclipse.smartmdsd.xtext.system.targetPlatform.ui.internal.TargetPlatformActivator

class SmartMDSDTargetPlatformContribution implements ISmartMDSDXtextContribution {
	override getEPackage() {
		return TargetPlatformPackage.eINSTANCE
	}
	
	override getSmartMDSDNatureEnum() {
		return SmartMDSDNatureEnum.SystemNature
	}

	override getXtextEditorID() {
		return TargetPlatformActivator.ORG_ECLIPSE_SMARTMDSD_XTEXT_SYSTEM_TARGETPLATFORM_TARGETPLATFORM
	}
	
	override getXtextInjector() {
		return TargetPlatformActivator.instance.getInjector(xtextEditorID)
	}
	
	override isDefaultLanguage() {
		return true
	}
}