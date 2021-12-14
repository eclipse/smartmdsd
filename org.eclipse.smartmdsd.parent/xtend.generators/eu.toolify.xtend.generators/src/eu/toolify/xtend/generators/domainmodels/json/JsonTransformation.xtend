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

import com.google.inject.Inject
import org.eclipse.smartmdsd.ecore.service.communicationObject.CommunicationObjectUtility
import eu.toolify.xtend.generators.CopyrightHelpers
import org.eclipse.smartmdsd.ecore.service.communicationObject.CommunicationObject
import org.eclipse.smartmdsd.xtend.smartsoft.generator.commObj.CommObjectGenHelpers
import org.eclipse.smartmdsd.ecore.base.basicAttributes.AttributeDefinition
import org.eclipse.smartmdsd.ecore.base.basicAttributes.AbstractAttributeType
import org.eclipse.smartmdsd.ecore.base.basicAttributes.PrimitiveType
import org.eclipse.smartmdsd.ecore.base.basicAttributes.PRIMITIVE_TYPE_NAME
import org.eclipse.smartmdsd.ecore.base.basicAttributes.InlineEnumerationType
import org.eclipse.smartmdsd.ecore.service.communicationObject.CommElementReference
import org.eclipse.smartmdsd.ecore.service.communicationObject.Enumeration

class JsonTransformation {
	@Inject extension CopyrightHelpers;
	@Inject extension CommObjectGenHelpers;
	@Inject extension CommunicationObjectUtility;
	
	def getJsonHeaderFileName(CommunicationObject co) {
		return co.name.toFirstUpper + "JSON.hh"
	}
	def getJsonSourceFileName(CommunicationObject co) {
		return co.name.toFirstUpper + "JSON.cc"
	}
	
	def getUserClassHeaderFileName(CommunicationObject co) {
		return co.name.toFirstUpper + ".hh"
	}
	
	// helper methods to unify the repository namespace definition
//	def dispatch getRepoNamespace(CommObjectsRepository repo) '''«repo.name.toFirstUpper»'''
//	def dispatch getRepoNamespace(CommunicationObject co) '''«(co.eContainer as CommObjectsRepository).name.toFirstUpper»'''
	
	def generateJsonHeaderFile(CommunicationObject co)
	'''
	«copyright»
	
	#ifndef «co.name.toUpperCase»_JSON_TRANSFORMATION_H_
	#define «co.name.toUpperCase»_JSON_TRANSFORMATION_H_
	
	#include <nlohmann/json.hpp>

«««	#include "«co.repoNamespace»/«co.userClassHeaderFileName»"
	#include <«co.repoNamespace»/«co.idlHeaderFileName»>
	
	namespace «co.repoNamespace»IDL {
	
		void to_json(const «co.repoNamespace»IDL::«co.userClassName»& obj, nlohmann::json& j);
		void from_json(const nlohmann::json& j, «co.repoNamespace»IDL::«co.userClassName»& obj);
		
	} // end namespace «co.repoNamespace»IDL

	#endif // «co.name.toUpperCase»_JSON_TRANSFORMATION_H_
	'''
	
	def getEnumerationInclude(AbstractAttributeType attrType) {
		if(attrType instanceof CommElementReference) {
			val typeName = attrType.typeName
			if(typeName instanceof Enumeration) {
				return "#include <"+typeName.repoNamespace+"/"+typeName.enumClassFileName+">"
			}
		}
		return ""
	}
	
	def generateJsonSourceFile(CommunicationObject obj)
	'''
		«copyright»
		
		#include "«obj.jsonHeaderFileName»"
		
		«FOR attr: obj.attributes»
			«IF attr.type.isCommunicationObject»
				#include "«attr.type.communicationObjectRef.repoNamespace»JSON/«attr.type.communicationObjectRef.jsonHeaderFileName»"
			«ELSEIF attr.type.isEnumeration»
				«attr.type.enumerationInclude»
			«ENDIF»
		«ENDFOR»
		
		namespace «obj.repoNamespace»IDL {
		
		void to_json(const «obj.repoNamespace»IDL::«obj.userClassName»& obj, nlohmann::json& j)
		{
			«FOR attribute: obj.attributes»
				// «attribute.name»: «attribute.type.typeName»«IF attribute.isArrayType»[]«ENDIF»
				«IF attribute.type.isComposedType»
					«IF attribute.isArrayType»
						for(size_t idx=0; idx < obj.«attribute.name».size(); idx++) {
							nlohmann::json array_element;
							to_json(obj.«attribute.name».at(idx), array_element);
							j["«attribute.name»"].emplace_back(array_element);
						}
					«ELSE»
						to_json(obj.«attribute.name», j["«attribute.name»"]);
					«ENDIF»
				«ELSEIF attribute.type.isEnumeration»
					«IF attribute.isArrayType»
						for(size_t idx=0; idx < obj.«attribute.name».size(); idx++) {
							nlohmann::json array_element = «attribute.type.referencedRepoNamespace»::«attribute.type.referencedClassName»(obj.«attribute.name».at(idx)).to_string(false);
							j["«attribute.name»"].emplace_back(array_element);
						}
					«ELSE»
						j["«attribute.name»"] = «attribute.type.referencedRepoNamespace»::«attribute.type.referencedClassName»(obj.«attribute.name»).to_string(false);
					«ENDIF»
				«ELSE»
					j["«attribute.name»"] = obj.«attribute.name»;
				«ENDIF»
			«ENDFOR»
		}

		/**
		 * this transformation handles missing values and checks for correct types:
		 * - missing primitive type values (including simple arrays) are set to zero values
		 * - missing object type values are skipped entirely
		 * - types are checked before performing the type-cast operation
		 */
		void from_json(const nlohmann::json& j, «obj.repoNamespace»IDL::«obj.userClassName»& obj)
		{
			«FOR attribute: obj.attributes»
				// «attribute.name»: «attribute.type.typeName»«IF attribute.isArrayType»[]«ENDIF»
				«IF attribute.type.isComposedType»
					«IF attribute.isArrayType»
						if(j.contains("«attribute.name»") && j["«attribute.name»"].is_array()) {
							auto j«attribute.name» = j["«attribute.name»"];
							obj.«attribute.name».resize(j«attribute.name».size());
							for(size_t idx=0; idx < j«attribute.name».size(); idx++) {
								// convert the json array values individually
								if(j«attribute.name».at(idx).is_object()) {
									// from_json(j«attribute.name».at(idx), obj.«attribute.name».at(idx));
									obj.«attribute.name»[idx] = j«attribute.name»[idx].get<«attribute.type.repoNamespace»IDL::«attribute.type.typeName»>();
								}
							}
						}
					«ELSE»
						if(j.contains("«attribute.name»") && j["«attribute.name»"].is_object()) {
							//from_json(j["«attribute.name»"], obj.«attribute.name»);
							obj.«attribute.name» = j["«attribute.name»"].get<«attribute.type.repoNamespace»IDL::«attribute.type.typeName»>();
						}
					«ENDIF»
				«ELSEIF attribute.type.isEnumeration»
					«IF attribute.isArrayType»
						if(j.contains("«attribute.name»") && j["«attribute.name»"].is_array()) {
							auto j«attribute.name» = j["«attribute.name»"];
							obj.«attribute.name».resize(j«attribute.name».size());
							if(j«attribute.name».at(idx).is_string()) {
								obj.«attribute.name»[idx] = «attribute.type.referencedRepoNamespace»::«attribute.type.referencedClassName»::from_string(j«attribute.name»[idx].get<std::string>());
							}
						}
					«ELSE»
						if(j.contains("«attribute.name»") && j["«attribute.name»"].is_string()) {
							obj.«attribute.name» = «attribute.type.referencedRepoNamespace»::«attribute.type.referencedClassName»::from_string(j["«attribute.name»"].get<std::string>());
						}
					«ENDIF»
				«ELSE»
					if(j.contains("«attribute.name»") && j["«attribute.name»"].«IF attribute.isArrayType»is_array«ELSE»«attribute.type.typeCheckName»«ENDIF»()) {
						obj.«attribute.name» = j["«attribute.name»"].get<«attribute.cppCastType»>();
					}
				«ENDIF»
			«ENDFOR»
		}
		
		} // end namespace «obj.repoNamespace»IDL
	'''
	
	def getCppCastType(AttributeDefinition attribute) {
		var result = ""
		if(attribute.isArrayType) {
			result = "std::vector<"
		}
		result = result + attribute.type.cppType
		if(attribute.isArrayType) {
			result = result + ">"
		}
		return result
	}
	
	def getCppType(AbstractAttributeType type) {
		if(type instanceof PrimitiveType) {
			switch(type.typeName) {
				case PRIMITIVE_TYPE_NAME::UINT8: "unsigned char"
				case PRIMITIVE_TYPE_NAME::UINT16: "unsigned short"
				case PRIMITIVE_TYPE_NAME::UINT32: "unsigned int"
				case PRIMITIVE_TYPE_NAME::UINT64: "unsigned long"
				case PRIMITIVE_TYPE_NAME::INT8: "char"
				case PRIMITIVE_TYPE_NAME::INT16: "short"
				case PRIMITIVE_TYPE_NAME::INT32: "int"
				case PRIMITIVE_TYPE_NAME::INT64: "long"
				case PRIMITIVE_TYPE_NAME::FLOAT: "float"
				case PRIMITIVE_TYPE_NAME::DOUBLE: "double"
				case PRIMITIVE_TYPE_NAME::BOOLEAN: "bool"
				case PRIMITIVE_TYPE_NAME::STRING: "std::string"
			}
		} else if(type instanceof InlineEnumerationType) {
			return "int"
		}
	}
	
	def getTypeCheckName(AbstractAttributeType type) {
		if(type instanceof PrimitiveType) {
			if(type.typeName <= PRIMITIVE_TYPE_NAME::UINT64) {
				return "is_number_unsigned"
			} else if(type.typeName <= PRIMITIVE_TYPE_NAME::INT64) {
				return "is_number_integer"
			} else if(type.typeName <= PRIMITIVE_TYPE_NAME::DOUBLE) {
				return "is_number_float"
			} else if(type.typeName == PRIMITIVE_TYPE_NAME::BOOLEAN) {
				return "is_boolean"
			} else if(type.typeName == PRIMITIVE_TYPE_NAME::STRING) {
				return "is_string"
			}
		} else if(type instanceof InlineEnumerationType) {
			return "is_number_integer"
		}
	}
}