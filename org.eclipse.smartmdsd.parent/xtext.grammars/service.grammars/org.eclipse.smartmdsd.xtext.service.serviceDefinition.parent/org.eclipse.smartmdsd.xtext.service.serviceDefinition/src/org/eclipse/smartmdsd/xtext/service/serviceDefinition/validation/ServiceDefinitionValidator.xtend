/********************************************************************************
 * Copyright (c) 2018 Technische Hochschule Ulm, Servicerobotics Ulm, Germany
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
package org.eclipse.smartmdsd.xtext.service.serviceDefinition.validation

import org.eclipse.xtext.validation.Check
import org.eclipse.smartmdsd.ecore.service.serviceDefinition.ServiceDefRepository
import org.eclipse.smartmdsd.ecore.service.serviceDefinition.ServiceDefinitionPackage
import org.eclipse.smartmdsd.ecore.service.serviceDefinition.AbstractServiceDefinition
import org.eclipse.smartmdsd.ecore.service.serviceDefinition.CoordinationServiceDefinition

/**
 * This class contains custom validation rules. 
 *
 * See https://www.eclipse.org/Xtext/documentation/303_runtime_concepts.html#validation
 */
class ServiceDefinitionValidator extends AbstractServiceDefinitionValidator {
	
	protected static val ISSUE_PREFIX = "org.xtext.service.serviceDefinition."
	public static val MISSING_PARAMETER_PATTERN = ISSUE_PREFIX + "MissingParameterPattern"
	public static val MISSING_STATE_PATTERN = ISSUE_PREFIX + "MissingStatePattern"
	public static val INVALID_REPO_NAME = ISSUE_PREFIX + "InvalidRepoName"
	public static val CAPITAL_SERVICE_NAME = ISSUE_PREFIX + "CapitalServiceName"
	
	@Check
	def void checkMissingCoordinationPatterns(CoordinationServiceDefinition service) {
		if(service.parameterPattern === null) {
			error("Missing ParameterPattern definition",
				ServiceDefinitionPackage.Literals.ABSTRACT_SERVICE_DEFINITION__NAME,
				MISSING_PARAMETER_PATTERN
			)
		}
		if(service.statePattern === null) {
			error("Missing StatePattern definition",
				ServiceDefinitionPackage.Literals.ABSTRACT_SERVICE_DEFINITION__NAME,
				MISSING_STATE_PATTERN
			)
		}
	}
	
	@Check
	def void checkRepositoryNameIsProjectName(ServiceDefRepository repo) {
		val uri = repo.eResource.URI
		// platform URI typically indicates a real (not virtual) resource 
		if(uri.platform) {
			// first segment is the root resource-name which is the project-name (within a workspace)
			val projectName = uri.segment(1)
			if(repo.name != projectName) {
				warning("Repository name should match project name.",
					ServiceDefinitionPackage.Literals.SERVICE_DEF_REPOSITORY__NAME,
					INVALID_REPO_NAME,
					projectName
				)
			}
		}
	}
	
	@Check
	def void checkServicedDefNameStartsWithCapital(AbstractServiceDefinition service) {
		if(!Character.isUpperCase(service.name.charAt(0))) {
			warning("ServiceDefinition name should start with a capital!", 
				ServiceDefinitionPackage.Literals.ABSTRACT_SERVICE_DEFINITION__NAME,
				CAPITAL_SERVICE_NAME
			);
		}
	}
	
}
