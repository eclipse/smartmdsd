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
package org.eclipse.smartmdsd.xtext.component.componentParameter.validation

import com.google.inject.Inject
import org.eclipse.xtext.validation.Check
import org.eclipse.smartmdsd.ecore.component.componentParameter.ParameterInstance
import org.eclipse.smartmdsd.ecore.component.componentParameter.ComponentParameterPackage
import org.eclipse.smartmdsd.ecore.component.componentParameter.ComponentParameter
import org.eclipse.smartmdsd.ecore.component.componentParameter.ParameterSetInstance
import org.eclipse.xtext.naming.IQualifiedNameProvider
import org.eclipse.smartmdsd.ecore.base.basicAttributes.AttributeDefinition
import org.eclipse.smartmdsd.ecore.component.componentParameter.ComponentParameterBase
import org.eclipse.smartmdsd.ecore.component.coordinationExtension.CoordinationSlavePort

/**
 * This class contains custom validation rules. 
 *
 * See https://www.eclipse.org/Xtext/documentation/303_runtime_concepts.html#validation
 */
class ComponentParameterValidator extends AbstractComponentParameterValidator {
	@Inject IQualifiedNameProvider fqn;
	
	protected static val COMP_PARAM_ISSUE_PREFIX = "org.xtext.component.componentParameter."
	public static val MISSING_ATTRIBUTE_REFINEMENTS = COMP_PARAM_ISSUE_PREFIX + "MissingAttributeRefinements"
	public static val SINGLE_PARAM_SET_INSTANCE = COMP_PARAM_ISSUE_PREFIX + "SingleParamSetInstance"
	public static val PARAM_SET_INSTANCE_MATCH_BEHAVIOR_INTERFACE = COMP_PARAM_ISSUE_PREFIX + "ParameterSetInstanceMatchBehaviorInterface"
	public static val MISSING_ATTRIBUTE_VALUE = COMP_PARAM_ISSUE_PREFIX + "MissingAttributeValue"
	
	@Check
	def void checkAllAttributesRefined(ParameterInstance param) {
		if(!param.parameterDef.attributes.forall[attr|param.attributes.exists[ref|ref.attribute.equals(attr)]]) {
			warning("Missing attribute-refinements",
				ComponentParameterPackage.Literals.PARAMETER_INSTANCE__PARAMETER_DEF,
				MISSING_ATTRIBUTE_REFINEMENTS
			)
		}
	}
	
	@Check
	def void checkSingleParamSetInstance(ParameterSetInstance param) {
		val parent = param.eContainer
		if(parent instanceof ComponentParameter) {
			if(parent.parameters.filter(ParameterSetInstance).size > 1) {
				error("Multiple ParameterSetInstances are defined, only one (at most) is allowed.",
					ComponentParameterPackage.Literals.PARAMETER_SET_INSTANCE__PARAM_SET,
					SINGLE_PARAM_SET_INSTANCE,
					param.paramSet.name
				)
			}
		}
	}
	
	@Check
	def void checkParameterSetInstanceMatchComponentBehaviorSlaveInterface(ParameterSetInstance paramSetInstance) {
		val parent = paramSetInstance.eContainer
		if(parent instanceof ComponentParameter) {
			if(parent.component !== null) {
				val slavePort = parent.component.elements.filter(CoordinationSlavePort).head
				if(slavePort !== null) {
					val paramSet = slavePort.service?.parameterPattern?.parameterSet
					if(paramSet !== null) {
						if(!paramSet.equals(paramSetInstance.paramSet)) {
							warning("ParameterSetInstance "+paramSetInstance.paramSet.name+
								" does not match ParameterSet "+paramSet.name+" specified within BehaviorSlaveInterface of Component "+parent.component.name,
								ComponentParameterPackage.Literals.PARAMETER_SET_INSTANCE__PARAM_SET,
								PARAM_SET_INSTANCE_MATCH_BEHAVIOR_INTERFACE,
								fqn.getFullyQualifiedName(paramSet).toString
							)
						}
					}
				}
			}
		}
	}
	
	@Check
	def checkAttributeHasDefaultValue(AttributeDefinition attr) {
		val parent = attr.eContainer
		if(parent instanceof ComponentParameterBase) {
			if(attr.defaultvalue === null) {
				warning("You should assign a default value.",
					null,
					MISSING_ATTRIBUTE_VALUE
				)
			}
		}
	}
}
