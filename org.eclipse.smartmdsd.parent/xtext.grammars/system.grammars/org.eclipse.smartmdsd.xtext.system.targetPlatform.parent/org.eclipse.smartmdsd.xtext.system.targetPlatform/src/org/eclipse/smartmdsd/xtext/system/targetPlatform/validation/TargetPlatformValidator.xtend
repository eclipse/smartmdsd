/********************************************************************************
 * Copyright (c) 2017 Technische Hochschule Ulm, Servicerobotics Ulm, Germany
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
package org.eclipse.smartmdsd.xtext.system.targetPlatform.validation

import org.eclipse.xtext.validation.Check
import org.eclipse.smartmdsd.ecore.system.targetPlatform.TargetPlatformModel
import org.eclipse.smartmdsd.ecore.system.targetPlatform.TargetPlatformPackage

/**
 * This class contains custom validation rules. 
 *
 * See https://www.eclipse.org/Xtext/documentation/303_runtime_concepts.html#validation
 */
class TargetPlatformValidator extends AbstractTargetPlatformValidator {
	
	public static val NO_TARGET_PLATFORMS = "no_target_platforms"
	
	@Check
	def hasATargetPlatformDefinition(TargetPlatformModel model) {
		if(model.elements.empty) {
			warning("At least one TargetPlatform should be defined", TargetPlatformPackage.Literals.TARGET_PLATFORM_MODEL__NAME, NO_TARGET_PLATFORMS)
		}
	}
	
}
