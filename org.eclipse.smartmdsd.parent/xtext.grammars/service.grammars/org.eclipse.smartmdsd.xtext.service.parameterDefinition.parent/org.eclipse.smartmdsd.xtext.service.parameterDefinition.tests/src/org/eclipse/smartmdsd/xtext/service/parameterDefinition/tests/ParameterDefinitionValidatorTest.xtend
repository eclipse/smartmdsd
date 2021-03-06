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
package org.eclipse.smartmdsd.xtext.service.parameterDefinition.tests

import com.google.inject.Inject
import org.eclipse.smartmdsd.ecore.service.parameterDefinition.ParamDefModel
import org.eclipse.xtext.testing.InjectWith
import org.eclipse.xtext.testing.extensions.InjectionExtension
import org.eclipse.xtext.testing.util.ParseHelper
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.^extension.ExtendWith
import org.eclipse.smartmdsd.ecore.base.basicAttributes.BasicAttributesPackage
import org.eclipse.xtext.testing.validation.ValidationTestHelper
import org.eclipse.emf.ecore.EClass
import org.eclipse.smartmdsd.ecore.service.parameterDefinition.ParameterDefinitionPackage
import org.eclipse.smartmdsd.xtext.service.parameterDefinition.validation.ParameterDefinitionValidator
import org.eclipse.smartmdsd.xtext.base.basicAttributes.validation.BasicAttributesValidator

@ExtendWith(InjectionExtension)
@InjectWith(ParameterDefinitionInjectorProvider)
class ParameterDefinitionValidatorTest {
	@Inject extension ParseHelper<ParamDefModel>
	@Inject extension ValidationTestHelper
	
	static val DEF_VAL = BasicAttributesPackage.eINSTANCE.abstractValue
	
	def private attributeTestModel(CharSequence attrBody)
	'''
		ParameterSetRepository MyRepo {
			ParameterSet S1 {
				Parameter P1 {
					attr: «attrBody»
				}
			}
		}
	'''
	
	def private void assertAttributeIncompatibleTypes(CharSequence attrBody, 
		EClass c, String checkType) 
	{
		attrBody.attributeTestModel.parse.assertError(c, checkType)
	}
	def private void assertAttributeCompatibleTypes(CharSequence attrBody) 
	{
		attrBody.attributeTestModel.parse.assertNoErrors
	}
	
	@Test def void testStringType() {
		val checkType = BasicAttributesValidator.INCOMPATIBLE_TYPES
		'''String = 100'''.assertAttributeIncompatibleTypes(DEF_VAL, checkType)
		'''String = 100.0'''.assertAttributeIncompatibleTypes(DEF_VAL, checkType)
		'''String = true'''.assertAttributeIncompatibleTypes(DEF_VAL, checkType)
		'''String = "Hello"'''.assertAttributeCompatibleTypes
		'''String[] = ["Hello", "World"]'''.assertAttributeCompatibleTypes
	}
	@Test def void testDoubleType() {
		val checkType = BasicAttributesValidator.INCOMPATIBLE_TYPES
		'''Double = "Hello"'''.assertAttributeIncompatibleTypes(DEF_VAL, checkType)
		'''Double = true'''.assertAttributeIncompatibleTypes(DEF_VAL, checkType)
		'''Double = 100'''.assertAttributeCompatibleTypes
		'''Double = 1.0'''.assertAttributeCompatibleTypes
		'''Double[] = [1.0, .1]'''.assertAttributeCompatibleTypes
		'''Double = -1.0'''.assertAttributeCompatibleTypes
		'''Double = .0'''.assertAttributeCompatibleTypes
		'''Double = 1.0e-10'''.assertAttributeCompatibleTypes
	}
	@Test def void testInt32Type() {
		val checkType = BasicAttributesValidator.INCOMPATIBLE_TYPES
		'''Int32 = 1.0'''.assertAttributeIncompatibleTypes(DEF_VAL, checkType)
		'''Int32 = "Hello"'''.assertAttributeIncompatibleTypes(DEF_VAL, checkType)
		'''Int32 = true'''.assertAttributeIncompatibleTypes(DEF_VAL, checkType)
		'''Int32 = 1'''.assertAttributeCompatibleTypes
		'''Int32[] = [1, -1]'''.assertAttributeCompatibleTypes
	}
	@Test def void testUInt32Type() {
		val checkType = BasicAttributesValidator.INCOMPATIBLE_TYPES
		'''UInt32 = 1.0'''.assertAttributeIncompatibleTypes(DEF_VAL, checkType)
		'''UInt32 = "Hello"'''.assertAttributeIncompatibleTypes(DEF_VAL, checkType)
		'''UInt32 = true'''.assertAttributeIncompatibleTypes(DEF_VAL, checkType)
		'''UInt32 = -1'''.assertAttributeIncompatibleTypes(DEF_VAL, checkType)
		'''UInt32 = 1'''.assertAttributeCompatibleTypes
		'''UInt32[] = [1, 2, 3]'''.assertAttributeCompatibleTypes
	}
	@Test def void testBooleanType() {
		val checkType = BasicAttributesValidator.INCOMPATIBLE_TYPES
		'''Boolean = 1.0'''.assertAttributeIncompatibleTypes(DEF_VAL, checkType)
		'''Boolean = "Hello"'''.assertAttributeIncompatibleTypes(DEF_VAL, checkType)
		'''Boolean = 1'''.assertAttributeIncompatibleTypes(DEF_VAL, checkType)
		'''Boolean = true'''.assertAttributeCompatibleTypes
		'''Boolean = false'''.assertAttributeCompatibleTypes
		'''Boolean[] = [false, true]'''.assertAttributeCompatibleTypes
	}
	
	@Test def void testArraySize() {
		val checkType = BasicAttributesValidator.INCOMPATIBLE_CARDINALITY
		'''Int32[1] = [1, 2]'''.assertAttributeIncompatibleTypes(DEF_VAL, checkType)
		'''Int32[2] = [1, 2]'''.assertAttributeCompatibleTypes
		'''Int32[3] = [1, 2]'''.assertAttributeCompatibleTypes
		'''Int32[*] = [1, 2]'''.assertAttributeCompatibleTypes
		'''Int32[] = [1, 2]'''.assertAttributeCompatibleTypes
	}
	
	@Test def void testParamSetHierarchyCycle() {
		'''
		ParameterSetRepository MyRepo {
			ParameterSet S1 extends S3 { }
			ParameterSet S2 extends S1 { }
			ParameterSet S3 extends S2 { }
		}
		'''.parse => [
			assertHierarchyCycle("S1")
			assertHierarchyCycle("S2")
			assertHierarchyCycle("S3")
		]
	}
	
	@Test def void testParamSetSelfCycle() {
		'''
		ParameterSetRepository MyRepo {
			ParameterSet S1 extends S1 { }
		}
		'''.parse => [
			assertError(
				ParameterDefinitionPackage.eINSTANCE.parameterSetDefinition,
				ParameterDefinitionValidator.PARAM_SET_HIERARCHY,
				"Cycle in extensions, 'S1' cannot extend itself"
			)
		]
	}
	
	@Test def void testNoHierarchyCycle() {
		'''
		ParameterSetRepository MyRepo {
			ParameterSet S1 { }
			ParameterSet S2 extends S1 { }
			ParameterSet S3 extends S2 { }
		}
		'''.parse.assertNoErrors
	}
	
	@Test def void testDuplicateParameterInHierarchy() {
		'''
		ParameterSetRepository MyRepo {
			ParameterSet S1 {
				Parameter P1 { }
			}
			ParameterSet S2 extends S1 {
				Parameter P1 { }
			}
		}
		'''.parse => [
			assertWarning(
				ParameterDefinitionPackage.eINSTANCE.parameterDefinition,
				ParameterDefinitionValidator.PARAMETER_DUPLICATE
			)
		]
	}
	
	@Test def void testDuplicateTriggerInHierarchy() {
		'''
		ParameterSetRepository MyRepo {
			ParameterSet S1 {
				Trigger T1 { }
			}
			ParameterSet S2 extends S1 {
				Trigger T1 { }
			}
		}
		'''.parse => [
			assertWarning(
				ParameterDefinitionPackage.eINSTANCE.triggerDefinition,
				ParameterDefinitionValidator.PARAMETER_DUPLICATE
			)
		]
	}
	
	@Test def void testCppKeywordAttributeName() {
		val checkType = BasicAttributesValidator.CPP_KEYWORD_ATTR_NAME
		val ATTR = BasicAttributesPackage.eINSTANCE.attributeDefinition
		'''
		ParameterSetRepository MyRepo {
			ParameterSet MySet1 {
				Parameter MyParam {
					class: String
				}
			}
		}
		'''.parse.assertError(ATTR, checkType)
		'''
		ParameterSetRepository MyRepo {
			ParameterSet MySet1 {
				Trigger MyTrigger {
					this: Boolean
				}
			}
		}
		'''.parse.assertError(ATTR, checkType)
	}
	
	def private void assertHierarchyCycle(ParamDefModel p, String expectedClassName) {
		p.assertError(
			ParameterDefinitionPackage.eINSTANCE.parameterSetDefinition,
			ParameterDefinitionValidator.PARAM_SET_HIERARCHY,
			"Cycle in extensions hierarchy of ParameterSet '"+expectedClassName+"'"
		)
	}
}