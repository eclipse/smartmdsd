/**
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
 *    Alex Lotz, Dennis Stampfer, Matthias Lutz
 */
package org.eclipse.smartmdsd.ecore.service.skillDefinition.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.eclipse.smartmdsd.ecore.service.skillDefinition.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class SkillDefinitionFactoryImpl extends EFactoryImpl implements SkillDefinitionFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static SkillDefinitionFactory init() {
		try {
			SkillDefinitionFactory theSkillDefinitionFactory = (SkillDefinitionFactory) EPackage.Registry.INSTANCE
					.getEFactory(SkillDefinitionPackage.eNS_URI);
			if (theSkillDefinitionFactory != null) {
				return theSkillDefinitionFactory;
			}
		} catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new SkillDefinitionFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SkillDefinitionFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
		case SkillDefinitionPackage.SKILL_DEFINITION_MODEL:
			return createSkillDefinitionModel();
		case SkillDefinitionPackage.SKILL_DEFINITION_REPOSITORY:
			return createSkillDefinitionRepository();
		case SkillDefinitionPackage.COORDINATION_MODULE_DEFINITION:
			return createCoordinationModuleDefinition();
		case SkillDefinitionPackage.SKILL_DEFINITION:
			return createSkillDefinition();
		case SkillDefinitionPackage.SKILL_RESULT:
			return createSkillResult();
		default:
			throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
		case SkillDefinitionPackage.SKILL_RESULT_TYPES:
			return createSKILL_RESULT_TYPESFromString(eDataType, initialValue);
		default:
			throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
		case SkillDefinitionPackage.SKILL_RESULT_TYPES:
			return convertSKILL_RESULT_TYPESToString(eDataType, instanceValue);
		default:
			throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public SkillDefinitionModel createSkillDefinitionModel() {
		SkillDefinitionModelImpl skillDefinitionModel = new SkillDefinitionModelImpl();
		return skillDefinitionModel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public SkillDefinitionRepository createSkillDefinitionRepository() {
		SkillDefinitionRepositoryImpl skillDefinitionRepository = new SkillDefinitionRepositoryImpl();
		return skillDefinitionRepository;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public CoordinationModuleDefinition createCoordinationModuleDefinition() {
		CoordinationModuleDefinitionImpl coordinationModuleDefinition = new CoordinationModuleDefinitionImpl();
		return coordinationModuleDefinition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public SkillDefinition createSkillDefinition() {
		SkillDefinitionImpl skillDefinition = new SkillDefinitionImpl();
		return skillDefinition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public SkillResult createSkillResult() {
		SkillResultImpl skillResult = new SkillResultImpl();
		return skillResult;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SKILL_RESULT_TYPES createSKILL_RESULT_TYPESFromString(EDataType eDataType, String initialValue) {
		SKILL_RESULT_TYPES result = SKILL_RESULT_TYPES.get(initialValue);
		if (result == null)
			throw new IllegalArgumentException(
					"The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertSKILL_RESULT_TYPESToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public SkillDefinitionPackage getSkillDefinitionPackage() {
		return (SkillDefinitionPackage) getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static SkillDefinitionPackage getPackage() {
		return SkillDefinitionPackage.eINSTANCE;
	}

} //SkillDefinitionFactoryImpl
