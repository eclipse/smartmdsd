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
package org.eclipse.smartmdsd.xtext.system.deployment.ui.quickfix

import org.eclipse.xtext.ui.editor.quickfix.DefaultQuickfixProvider
import org.eclipse.xtext.ui.editor.quickfix.Fix
import org.eclipse.xtext.validation.Issue
import org.eclipse.xtext.ui.editor.quickfix.IssueResolutionAcceptor
import org.eclipse.smartmdsd.ecore.system.deployment.DeploymentModel
import org.eclipse.smartmdsd.ecore.system.deployment.TargetPlatformReference
import org.eclipse.smartmdsd.ecore.system.deployment.DeploymentFactory
import org.eclipse.xtext.util.concurrent.IUnitOfWork
import org.eclipse.xtext.resource.XtextResource
import org.eclipse.emf.ecore.EObject
import org.eclipse.smartmdsd.ecore.system.targetPlatform.NetworkInterface
import org.eclipse.smartmdsd.xtext.system.deployment.validation.DeploymentValidator
import com.google.inject.Inject
import org.eclipse.smartmdsd.xtext.indexer.XtextResourceIndex
import org.eclipse.smartmdsd.ecore.system.targetPlatform.TargetPlatformPackage
import org.eclipse.smartmdsd.ecore.system.targetPlatform.TargetPlatformDefinition
import org.eclipse.emf.ecore.util.EcoreUtil
import org.eclipse.smartmdsd.ecore.system.deployment.DeploymentUtility

/**
 * Custom quickfixes.
 *
 * See https://www.eclipse.org/Xtext/documentation/310_eclipse_support.html#quick-fixes
 */
class DeploymentQuickfixProvider extends DefaultQuickfixProvider {
	@Inject XtextResourceIndex index;
	
	@Fix(DeploymentValidator.MULTIPLE_NAMING_SERVICES)
	def fixDuplicateOperationModeBinding(Issue issue, IssueResolutionAcceptor acceptor) {
		acceptor.accept(issue, 
			'Remove duplicate NamingService', 
			'Remove duplicate NamingService.', '') [
			element, context |
			val model = (element.eContainer as DeploymentModel)
			model.elements.remove(element)
		]
	}
	
	@Fix(DeploymentValidator.MISSING_UPLOAD_DIRECTORY)
	def fixMissingUploadDirectoy(Issue issue, IssueResolutionAcceptor acceptor) {
		acceptor.accept(issue, 
			'Create a default UploadDirectory', 
			'Create a default UploadDirectory', '') [
			element, context |
			val platform = (element as TargetPlatformReference)
			platform.directory = DeploymentFactory.eINSTANCE.createUploadDirectory
			platform.directory.path = "/tmp"
		]
	}
	
	@Fix(DeploymentValidator.MISSING_NETWORK_INTERFACE)
	def fixMissingNetworkInterface(Issue issue, IssueResolutionAcceptor acceptor) {
		val object = issue.EObject
		if(object instanceof TargetPlatformReference) {
			for(network: object.platform.elements.filter(NetworkInterface)) {
				acceptor.accept(issue, 
					'Use the NetworkInterface '+network.name, 
					'Use the NetworkInterface '+network.name, '') [
					element, context |
					val platform = (element as TargetPlatformReference)
					platform.host = DeploymentFactory.eINSTANCE.createNetworkInterfaceSelection
					platform.host.network = network
				]
			}
		}
	}
	
//	@Fix(DeploymentValidator.MISSING_LOGIN_ACCOUNT)
//	def fixMissingLoginAccount(Issue issue, IssueResolutionAcceptor acceptor) {
//		val object = issue.EObject
//		if(object instanceof TargetPlatformReference) {
//			for(login: object.platform.elements.filter(LoginAccount)) {
//				acceptor.accept(issue, 
//					'Use the LoginAccount '+login.name, 
//					'Use the LoginAccount '+login.name, '') [
//					element, context |
//					val platform = (element as TargetPlatformReference)
//					platform.login = DeploymentFactory.eINSTANCE.createLoginAccountSelection
//					platform.login.login = login
//				]
//			}
//		}
//	}
	@Fix(DeploymentValidator.NO_TARGET_PLATFORMS_DEFINED)
	def addDefaultDeployment(Issue issue, IssueResolutionAcceptor acceptor) {
		acceptor.accept(issue, 
			'Add default deployment target', 
			'Add default deployment target', '') [
			element, context |
			val model = (element as DeploymentModel)
			val targets = index.getVisibleEObjectDescriptions(model, TargetPlatformPackage.eINSTANCE.targetPlatformDefinition)
			if(!targets.empty) {
				val object = targets.head.EObjectOrProxy
				if(object.eIsProxy) {
					val target = EcoreUtil.resolve(object, model);
					if(target instanceof TargetPlatformDefinition) {
						// create a new default target reference
						val ref = DeploymentUtility.addDefaultTargetReference(model, target)
						// add all missing component artefacts
						DeploymentUtility.addAllMissingComponentArtefacts(model, ref)
					}
				}
			}
			
		]
	}
	
	def private EObject getEObject(Issue issue) {
		val modificationContext = modificationContextFactory.createModificationContext(issue);
		val xtextDocument = modificationContext.getXtextDocument();
		val object = xtextDocument.readOnly(new IUnitOfWork<EObject, XtextResource>() {
				override exec(XtextResource state) throws Exception {
					return state.getEObject(issue.uriToProblem.fragment());
				}
			});
		return object;
	}
	
	@Fix(DeploymentValidator.MISSING_COMPONENT_ARTEFACT)
	def createMissingComponentArtefact(Issue issue, IssueResolutionAcceptor acceptor) {
		acceptor.accept(issue, 
			'Create missing component-artefact '+issue.data.get(0), 
			'Create missing component-artefact '+issue.data.get(0), '') [
			element, context |
				val model = (element as DeploymentModel)
				val target = model.elements.findFirst[it instanceof TargetPlatformReference]
				if(target instanceof TargetPlatformReference) {
					val component = model.componentArch.components.findFirst[it.name == issue.data.get(0)]
					if(component !== null) {
						DeploymentUtility.addComponentArtefact(model, component, target)
					}
				}
			]
	}
}
