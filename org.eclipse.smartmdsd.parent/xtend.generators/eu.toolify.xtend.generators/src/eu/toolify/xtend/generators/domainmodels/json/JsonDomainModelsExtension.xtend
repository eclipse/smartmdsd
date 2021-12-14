/********************************************************************************
 * Copyright (c) 2021 Toolify Robotics GmbH
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *   Matthias Lutz, Alex Lotz, Dennis Stampfer
 ********************************************************************************/
package eu.toolify.xtend.generators.domainmodels.json

import org.eclipse.smartmdsd.xtend.smartsoft.generator.commObj.DomainModelsGeneratorExtension
import org.eclipse.smartmdsd.ecore.service.communicationObject.CommObjectsRepository

class JsonDomainModelsExtension implements DomainModelsGeneratorExtension {
	
	override getExtensionName(CommObjectsRepository repo) {
		repo.name + "JsonTransformation"
	}
	
	override getCMakeExtension(CommObjectsRepository repo) 
	'''
	GET_FILENAME_COMPONENT(TOOLIFY_DIR "${PROJECT_SOURCE_DIR}/../toolify" REALPATH)
	IF(EXISTS ${TOOLIFY_DIR})
		ADD_SUBDIRECTORY(${TOOLIFY_DIR}/src-gen ${CMAKE_CURRENT_BINARY_DIR}/toolify)
	ENDIF(EXISTS ${TOOLIFY_DIR})
	'''
	
}