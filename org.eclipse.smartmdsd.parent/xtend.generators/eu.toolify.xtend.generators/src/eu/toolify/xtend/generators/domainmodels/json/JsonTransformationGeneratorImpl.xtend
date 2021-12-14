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

import org.eclipse.xtext.generator.AbstractGenerator
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.xtext.generator.IFileSystemAccess2
import org.eclipse.xtext.generator.IGeneratorContext
import org.eclipse.smartmdsd.ecore.service.communicationObject.CommObjectsRepository
import java.util.Collection
import java.util.HashSet
import com.google.inject.Inject
import org.eclipse.smartmdsd.ecore.service.communicationObject.CommunicationObjectUtility
import org.eclipse.smartmdsd.ecore.service.communicationObject.CommElementReference
import org.eclipse.smartmdsd.ecore.service.communicationObject.CommunicationObject
import org.eclipse.smartmdsd.xtend.smartsoft.generator.commObj.CommObjectGenHelpers

class JsonTransformationGeneratorImpl extends AbstractGenerator {
	
	@Inject extension CommunicationObjectUtility;
	@Inject extension JsonTransformation
	@Inject extension CommObjectGenHelpers;
	
	override doGenerate(Resource input, IFileSystemAccess2 fsa, IGeneratorContext context) {
		for(repo: input.allContents.toIterable.filter(typeof(CommObjectsRepository))) {
			fsa.generateFile("CMakeLists.txt", repo.compileCMakeFile)
			fsa.generateFile(repo.name+"JSONConfig.cmake.in", repo.compileCMakeConfigFile)
			
			for(obj: repo.elements.filter(CommunicationObject)) {
				fsa.generateFile(obj.repoNamespace+"JSON/"+obj.jsonHeaderFileName, obj.generateJsonHeaderFile)
				fsa.generateFile(obj.repoNamespace+"JSON/"+obj.jsonSourceFileName, obj.generateJsonSourceFile)
			}
		}
	}
	
	def compileCMakeFile(CommObjectsRepository repo) 
	'''
	CMAKE_MINIMUM_REQUIRED(VERSION 3.5)
	
	PROJECT("«repo.name»JSON" VERSION 1.0)
	
	FIND_PACKAGE(nlohmann_json PATHS $ENV{SMART_ROOT_ACE} PATH_SUFFIXES modules QUIET)

	IF(TARGET nlohmann_json::nlohmann_json)
		SET(CMAKE_CXX_STANDARD 14)
		
		FILE(GLOB JSON_SRCS ${PROJECT_SOURCE_DIR}/${PROJECT_NAME}/*.cc)
		FILE(GLOB JSON_HDRS ${PROJECT_SOURCE_DIR}/${PROJECT_NAME}/*.hh)
		«FOR ext: repo.externalRepositories»
		# find depndency «repo.name»JSON
		FIND_PACKAGE(«ext.name»JSON PATHS $ENV{SMART_ROOT_ACE}/modules)
		«ENDFOR»
		
		IF(WIN32)
			ADD_LIBRARY(${PROJECT_NAME} STATIC ${JSON_SRCS})
		ELSE(WIN32)
			ADD_LIBRARY(${PROJECT_NAME} SHARED ${JSON_SRCS})
		ENDIF(WIN32)
		
		TARGET_LINK_LIBRARIES(${PROJECT_NAME} «repo.name» nlohmann_json::nlohmann_json)
		«FOR ext: repo.externalRepositories»
		# link depndency «ext.name»
		TARGET_LINK_LIBRARIES(${PROJECT_NAME} «ext.name»JSON)
		«ENDFOR»
		TARGET_INCLUDE_DIRECTORIES(${PROJECT_NAME} PUBLIC
		$<BUILD_INTERFACE:${CMAKE_CURRENT_SOURCE_DIR}>
		$<INSTALL_INTERFACE:include>
		)
		
		ADD_DEPENDENCIES(autoinstall ${PROJECT_NAME})
		
		CONFIGURE_FILE(${CMAKE_CURRENT_SOURCE_DIR}/${PROJECT_NAME}Config.cmake.in ${CMAKE_CURRENT_BINARY_DIR}/${PROJECT_NAME}Config.cmake @ONLY)
		INSTALL(FILES ${CMAKE_CURRENT_BINARY_DIR}/${PROJECT_NAME}Config.cmake DESTINATION modules)
		
		IF(DEFINED ${PROJECT_NAME}_VERSION)
			SET_TARGET_PROPERTIES(${PROJECT_NAME} PROPERTIES VERSION ${${PROJECT_NAME}_VERSION} SOVERSION ${${PROJECT_NAME}_VERSION_MAJOR})
			INCLUDE(CMakePackageConfigHelpers)
			write_basic_package_version_file(${PROJECT_NAME}ConfigVersion.cmake COMPATIBILITY SameMajorVersion)
			IF(EXISTS ${PROJECT_BINARY_DIR}/${PROJECT_NAME}ConfigVersion.cmake)
				INSTALL(FILES ${PROJECT_BINARY_DIR}/${PROJECT_NAME}ConfigVersion.cmake DESTINATION modules)
				SMART_TRACE_GENERATED_FILE(${PROJECT_BINARY_DIR}/${PROJECT_NAME}ConfigVersion.cmake)
			ENDIF(EXISTS ${PROJECT_BINARY_DIR}/${PROJECT_NAME}ConfigVersion.cmake)
		ENDIF(DEFINED ${PROJECT_NAME}_VERSION)
		
		INSTALL(TARGETS ${PROJECT_NAME} EXPORT ${PROJECT_NAME}Targets DESTINATION lib)
		EXPORT(EXPORT ${PROJECT_NAME}Targets)
		INSTALL(EXPORT ${PROJECT_NAME}Targets DESTINATION modules)
		INSTALL(FILES ${JSON_HDRS} DESTINATION include/${PROJECT_NAME})
	ENDIF(TARGET nlohmann_json::nlohmann_json)
	'''
	
	def compileCMakeConfigFile(CommObjectsRepository repo)
	'''
	CMAKE_MINIMUM_REQUIRED(VERSION 3.5)
	
	FIND_FILE(SMART_MACROS SmartMacros2.cmake PATHS $ENV{SMART_ROOT_ACE} /opt/smartSoftAce /opt/smartsoft PATH_SUFFIXES CMakeMacros)
	INCLUDE(${SMART_MACROS})
	INTERNAL_IMPORT_PACKAGE(«repo.name»)
	
	IF(NOT TARGET nlohmann_json::nlohmann_json)
		FIND_PACKAGE(nlohmann_json PATHS $ENV{SMART_ROOT_ACE} PATH_SUFFIXES modules)
	ENDIF(NOT TARGET nlohmann_json::nlohmann_json)
	
	«FOR ext: repo.externalRepositories»
	# find depndency «ext.name»JSON
	FIND_PACKAGE(«ext.name»JSON PATHS $ENV{SMART_ROOT_ACE}/modules)
	«ENDFOR»
	
	# include generated target configurations
	INCLUDE(${CMAKE_CURRENT_LIST_DIR}/@PROJECT_NAME@Targets.cmake)
	
	# the following variables are depricated and should not be used anymore:
	# @PROJECT_NAME@_LIBRARIES
	# @PROJECT_NAME@_INCLUDES
	
	# instead, just directly link the library @PROJECT_NAME@ to your executable target like this:
	#
	# TARGET_LINK_LIBRARIES(YourExecutableTarget @PROJECT_NAME@)
	#
	# (all the includes and additional libraries are automatically determined from the target @PROJECT_NAME@)
	'''
	
	def getExternalRepositories(CommObjectsRepository repo) {
		var Collection<CommObjectsRepository> repos = new HashSet<CommObjectsRepository>();
		for(obj : repo.communicationObjects) {
			for(attr: obj.attributes) {
				val type = attr.type
				if(type instanceof CommElementReference) {
					val ref = type.typeName
					val other = (ref.eContainer as CommObjectsRepository)
					if(other != repo) {
						repos.add(other)
					}
				}
			}
		}
		return repos;
	}
}
