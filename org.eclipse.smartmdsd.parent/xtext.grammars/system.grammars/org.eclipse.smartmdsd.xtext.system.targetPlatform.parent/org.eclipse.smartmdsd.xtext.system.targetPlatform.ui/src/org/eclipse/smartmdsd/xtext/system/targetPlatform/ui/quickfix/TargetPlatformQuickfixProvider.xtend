/********************************************************************************
 * Copyright (c) 2017 Technische Hochschule Ulm, Servicerobotics Ulm, Germany
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
package org.eclipse.smartmdsd.xtext.system.targetPlatform.ui.quickfix

import org.eclipse.smartmdsd.xtext.service.roboticMiddleware.ui.quickfix.RoboticMiddlewareQuickfixProvider
import org.eclipse.xtext.ui.editor.quickfix.Fix
import org.eclipse.smartmdsd.xtext.system.targetPlatform.validation.TargetPlatformValidator
import org.eclipse.xtext.validation.Issue
import org.eclipse.xtext.ui.editor.quickfix.IssueResolutionAcceptor
import org.eclipse.smartmdsd.ecore.system.targetPlatform.TargetPlatformModel
import org.eclipse.smartmdsd.ecore.system.targetPlatform.TargetPlatformUtility

/**
 * Custom quickfixes.
 *
 * See https://www.eclipse.org/Xtext/documentation/310_eclipse_support.html#quick-fixes
 */
class TargetPlatformQuickfixProvider extends RoboticMiddlewareQuickfixProvider {

	@Fix(TargetPlatformValidator.NO_TARGET_PLATFORMS)
	def defineLocalhostTarget(Issue issue, IssueResolutionAcceptor acceptor) {
		acceptor.accept(issue, 'Define a default Localhost target', 'Define a default Localhost target', '') [
			element, context |
			val model = (element as TargetPlatformModel)
			TargetPlatformUtility.addDefaultLocalhostTarget(model)
		]
	}
}
