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
package org.eclipse.smartmdsd.xtext.system.deployment.validation

import org.eclipse.xtext.validation.Check
import org.eclipse.smartmdsd.ecore.system.deployment.NamingService
import org.eclipse.smartmdsd.ecore.system.deployment.DeploymentModel
import org.eclipse.smartmdsd.ecore.system.deployment.TargetPlatformReference
import org.eclipse.smartmdsd.ecore.system.deployment.DeploymentPackage
import org.eclipse.smartmdsd.ecore.system.deployment.ComponentArtefact

/**
 * This class contains custom validation rules. 
 *
 * See https://www.eclipse.org/Xtext/documentation/303_runtime_concepts.html#validation
 */
class DeploymentValidator extends AbstractDeploymentValidator {
	
	protected static val DEPLOYMENT_ISSUE_PREFIX = "org.xtext.system.deployment."
	public static val MULTIPLE_NAMING_SERVICES = DEPLOYMENT_ISSUE_PREFIX + "MultipleNamingServices"
	public static val MISSING_NETWORK_INTERFACE = DEPLOYMENT_ISSUE_PREFIX + "MissingNetworkInterface"
	public static val MISSING_UPLOAD_DIRECTORY = DEPLOYMENT_ISSUE_PREFIX + "MissingUploadDirectory"
	public static val NO_TARGET_PLATFORMS_DEFINED = DEPLOYMENT_ISSUE_PREFIX + "NoTargetPlatformsDefined"
	public static val MISSING_COMPONENT_ARTEFACT = DEPLOYMENT_ISSUE_PREFIX + "MissingComponentArtefact"
	
	@Check
	def checkSingleNamingService(NamingService ns) {
		val parent = ns.eContainer
		if(parent instanceof DeploymentModel) {
			if(parent.elements.filter(NamingService).size > 1) {
				error("Multiple NamingService-elements found, but (at most) one is allowed.",
					null, MULTIPLE_NAMING_SERVICES
				)
			}
		}
	}
	
	@Check
	def checkMissingElements(TargetPlatformReference ref) {
		if(ref.host === null) {
			warning("Missing NetworkInterfaceInstance.",
				null, MISSING_NETWORK_INTERFACE
			)
		}
		if(ref.directory === null) {
			warning("Missing UploadDirectory.",
				null, MISSING_UPLOAD_DIRECTORY
			)
		}
//		if(ref.login === null) {
//			warning("Missing LoginAccount.",
//				null, MISSING_LOGIN_ACCOUNT
//			)
//		}
	}
	
	@Check
	def hasTargetPlatforms(DeploymentModel model) {
		if(!model.elements.exists[it instanceof TargetPlatformReference]) {
			warning("At least one TargetPlatformReference has to be defined.", DeploymentPackage.Literals.DEPLOYMENT_MODEL__NAME, NO_TARGET_PLATFORMS_DEFINED)
		}
	}
	
	@Check
	def allComponentArtefactsAvailable(DeploymentModel model) {
		for(component: model.componentArch.components) {
			if(!model.elements.filter(ComponentArtefact).exists[it.component == component]) {
				warning("ComponentInstance "+component.name+" is not used in the deployment model",
					DeploymentPackage.Literals.DEPLOYMENT_MODEL__NAME, 
					MISSING_COMPONENT_ARTEFACT,
					component.name
				)
			}
		}
	}
}
