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
 *   Alex Lotz, Vineet Nagrath, Dennis Stampfer, Matthias Lutz
 ********************************************************************************/
package org.eclipse.smartmdsd.xtend.plainOPCUA.generator.component

import com.google.inject.Inject
import org.eclipse.xtext.generator.AbstractGenerator
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.xtext.generator.IFileSystemAccess2
import org.eclipse.xtext.generator.IGeneratorContext
import org.eclipse.smartmdsd.ecore.component.componentDefinition.ComponentDefinition
import org.eclipse.smartmdsd.ecore.component.seronetExtension.OpcUaDeviceClient
import org.eclipse.smartmdsd.ecore.component.seronetExtension.OpcUaReadServer

class PlainOpcUaGenerator2Impl extends AbstractGenerator {
	// Plain OPC UA extensions
	@Inject extension PlainOpcUaDeviceClient
	@Inject extension PlainOpcUaStatusServer
	@Inject extension PlainOpcUaComponentExtension
	
	override doGenerate(Resource input, IFileSystemAccess2 fsa, IGeneratorContext context) {
		for(comp: input.allContents.toIterable.filter(typeof(ComponentDefinition))) 
		{
			fsa.generateFile(
				comp.plainOpcUaExtensionHeaderFilename,
				comp.compilePlainOpcUaExtensionHeader
			)
			fsa.generateFile(
				comp.plainOpcUaExtensionSourceFilename,
				comp.compilePlainOpcUaExtensionSource
			)
			
			fsa.generateFile(
				comp.name+"PlainOpcUa.cmake",
				comp.compileCMakeFile
			)
			
			// generate related classes for Plain OPC UA Ports
			for(opcUaDevClient: comp.elements.filter(OpcUaDeviceClient)) {
				opcUaDevClient.compilePlainOpcUaDeviceClient(fsa)
			}
			for(opcUaStatusServer: comp.elements.filter(OpcUaReadServer)) {
				opcUaStatusServer.compilePlainOpcUaReadServer(fsa)
			}
		}
	}
	
	def compileCMakeFile(ComponentDefinition component) 
	'''
	CMAKE_MINIMUM_REQUIRED(VERSION 3.5)
	
	FIND_PACKAGE(Open62541CppWrapper 1.0 QUIET)

	IF(Open62541CppWrapper_FOUND)
		SET(CMAKE_CXX_STANDARD 14)
		INCLUDE_DIRECTORIES(${CMAKE_CURRENT_LIST_DIR})
		FILE(GLOB PLAIN_OPCUA_SRCS ${CMAKE_CURRENT_LIST_DIR}/*.cc)
	ENDIF(Open62541CppWrapper_FOUND)
	'''
	
}