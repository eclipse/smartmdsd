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
package org.eclipse.smartmdsd.xtext.service.communicationObject.ui.quickfix

import org.eclipse.xtext.ui.editor.quickfix.Fix
import org.eclipse.smartmdsd.xtext.service.communicationObject.validation.CommunicationObjectValidator
import org.eclipse.xtext.diagnostics.Diagnostic
import org.eclipse.xtext.validation.Issue
import org.eclipse.xtext.ui.editor.quickfix.IssueResolutionAcceptor
import org.eclipse.smartmdsd.ecore.service.communicationObject.CommObjectsRepository
import org.eclipse.xtext.EcoreUtil2
import org.eclipse.smartmdsd.ecore.service.communicationObject.AbstractCommElement
import org.eclipse.smartmdsd.ecore.service.communicationObject.CommunicationObjectFactory
import org.eclipse.smartmdsd.xtext.base.basicAttributes.ui.quickfix.BasicAttributesQuickfixProvider

/**
 * Custom quickfixes.
 *
 * See https://www.eclipse.org/Xtext/documentation/310_eclipse_support.html#quick-fixes
 */
class CommunicationObjectQuickfixProvider extends BasicAttributesQuickfixProvider {

	@Fix(CommunicationObjectValidator.INVALID_REPO_NAME)
	def repoName(Issue issue, IssueResolutionAcceptor acceptor) {
		acceptor.accept(issue,
			'Fix repository name to match project name',
			'Repository name will be set to project name',
			'', // icon
			[
				element, context |
				(element as CommObjectsRepository).name = issue.data.get(0)
			]
		)
	}
	
	@Fix(CommunicationObjectValidator.CAPITAL_COMM_ELEM_NAME)
	def capitalizeName(Issue issue, IssueResolutionAcceptor acceptor) {
		acceptor.accept(issue, 'Capitalize name', 'Capitalize the name.', '') [
			context |
			val xtextDocument = context.xtextDocument
			val firstLetter = xtextDocument.get(issue.offset, 1)
			xtextDocument.replace(issue.offset, 1, firstLetter.toUpperCase)
		]
	}
	
	@Fix(Diagnostic.LINKING_DIAGNOSTIC)
	def void createMissingCommObject(Issue issue, IssueResolutionAcceptor acceptor) {
		acceptor.accept(issue,
			'Create missing CommunicationObject',
			'Create missing CommunicationObject',
			'',
			[
				element, context |
				val currEntity = EcoreUtil2.getContainerOfType(element, AbstractCommElement)
				val model = currEntity.eContainer as CommObjectsRepository
				model.elements.add(
					model.elements.indexOf(currEntity)+1,
					CommunicationObjectFactory.eINSTANCE.createCommunicationObject => [
						name = context.xtextDocument.get(issue.offset, issue.length)
					]
				)
			]
		)
	}
}
