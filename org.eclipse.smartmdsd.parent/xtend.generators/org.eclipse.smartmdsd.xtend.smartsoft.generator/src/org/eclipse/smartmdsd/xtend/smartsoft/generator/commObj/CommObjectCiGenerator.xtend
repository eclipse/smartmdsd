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
 *   Alex Lotz, Matthias Lutz, Dennis Stampfer
 ********************************************************************************/
package org.eclipse.smartmdsd.xtend.smartsoft.generator.commObj

import com.google.inject.Inject
import java.util.Collection
import java.util.HashSet
import org.eclipse.smartmdsd.ecore.service.communicationObject.CommObjectsRepository
import org.eclipse.smartmdsd.ecore.service.communicationObject.CommunicationObject
import org.eclipse.smartmdsd.ecore.service.communicationObject.CommElementReference

class CommObjectCiGenerator {
	@Inject extension CommObjectGenHelpers

	
	/////////////////////////////////////////////////////
	// config.xml for jenkins job
	/////////////////////////////////////////////////////
	def generateCiInfo(CommObjectsRepository repo) '''
	{
		"id" : "«repo.repoNamespace.toString»",
		"type" : "DomainModel",
		"dependprojects" : [ «FOR reponame: repo.referencedCommObjectsName.sortBy(x|x) BEFORE "" SEPARATOR "," AFTER ""»"«reponame»"«ENDFOR» ]
	}
	'''
		
		
		
	def getReferencedCommObjectsName(CommObjectsRepository repo) {
		var Collection<String> repoNames = new HashSet<String>
		for(abstrel: repo.elements) {
			if(abstrel instanceof CommunicationObject) {
				for(attr: abstrel.attributes) {
					val type = attr.type
					if(type instanceof CommElementReference) {
						val id = type.typeName
						
						if(id instanceof CommunicationObject) {
							if(id.repoNamespace.toString != repo.repoNamespace.toString) {
								repoNames.add(id.repoNamespace.toString)
							}
						}
					}
				}
			}
		}
		return repoNames
	}
	

}
