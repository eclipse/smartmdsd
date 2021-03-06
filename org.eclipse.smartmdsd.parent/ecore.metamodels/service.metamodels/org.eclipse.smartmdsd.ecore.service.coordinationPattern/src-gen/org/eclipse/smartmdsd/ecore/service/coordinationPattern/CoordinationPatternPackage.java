/**
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
 *    Alex Lotz, Dennis Stampfer, Matthias Lutz
 */
package org.eclipse.smartmdsd.ecore.service.coordinationPattern;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.eclipse.smartmdsd.ecore.service.coordinationPattern.CoordinationPatternFactory
 * @model kind="package"
 * @generated
 */
public interface CoordinationPatternPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "coordinationPattern";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.eclipse.org/smartmdsd/service/coordinationPattern";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "coordinationPattern";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	CoordinationPatternPackage eINSTANCE = org.eclipse.smartmdsd.ecore.service.coordinationPattern.impl.CoordinationPatternPackageImpl
			.init();

	/**
	 * The meta object id for the '{@link org.eclipse.smartmdsd.ecore.service.coordinationPattern.impl.CoordinationPatternImpl <em>Coordination Pattern</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.smartmdsd.ecore.service.coordinationPattern.impl.CoordinationPatternImpl
	 * @see org.eclipse.smartmdsd.ecore.service.coordinationPattern.impl.CoordinationPatternPackageImpl#getCoordinationPattern()
	 * @generated
	 */
	int COORDINATION_PATTERN = 0;

	/**
	 * The number of structural features of the '<em>Coordination Pattern</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COORDINATION_PATTERN_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>Coordination Pattern</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COORDINATION_PATTERN_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.smartmdsd.ecore.service.coordinationPattern.impl.StatePatternImpl <em>State Pattern</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.smartmdsd.ecore.service.coordinationPattern.impl.StatePatternImpl
	 * @see org.eclipse.smartmdsd.ecore.service.coordinationPattern.impl.CoordinationPatternPackageImpl#getStatePattern()
	 * @generated
	 */
	int STATE_PATTERN = 1;

	/**
	 * The feature id for the '<em><b>Lifecycle</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE_PATTERN__LIFECYCLE = COORDINATION_PATTERN_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Modes</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE_PATTERN__MODES = COORDINATION_PATTERN_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>State Pattern</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE_PATTERN_FEATURE_COUNT = COORDINATION_PATTERN_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>State Pattern</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE_PATTERN_OPERATION_COUNT = COORDINATION_PATTERN_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.smartmdsd.ecore.service.coordinationPattern.impl.ParameterPatternImpl <em>Parameter Pattern</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.smartmdsd.ecore.service.coordinationPattern.impl.ParameterPatternImpl
	 * @see org.eclipse.smartmdsd.ecore.service.coordinationPattern.impl.CoordinationPatternPackageImpl#getParameterPattern()
	 * @generated
	 */
	int PARAMETER_PATTERN = 2;

	/**
	 * The feature id for the '<em><b>Parameter Set</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER_PATTERN__PARAMETER_SET = COORDINATION_PATTERN_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Parameter Pattern</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER_PATTERN_FEATURE_COUNT = COORDINATION_PATTERN_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Parameter Pattern</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER_PATTERN_OPERATION_COUNT = COORDINATION_PATTERN_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.smartmdsd.ecore.service.coordinationPattern.impl.DynamicWiringPatternImpl <em>Dynamic Wiring Pattern</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.smartmdsd.ecore.service.coordinationPattern.impl.DynamicWiringPatternImpl
	 * @see org.eclipse.smartmdsd.ecore.service.coordinationPattern.impl.CoordinationPatternPackageImpl#getDynamicWiringPattern()
	 * @generated
	 */
	int DYNAMIC_WIRING_PATTERN = 3;

	/**
	 * The number of structural features of the '<em>Dynamic Wiring Pattern</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DYNAMIC_WIRING_PATTERN_FEATURE_COUNT = COORDINATION_PATTERN_FEATURE_COUNT + 0;

	/**
	 * The operation id for the '<em>Connect</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DYNAMIC_WIRING_PATTERN___CONNECT__STRING_STRING_STRING_STRING = COORDINATION_PATTERN_OPERATION_COUNT + 0;

	/**
	 * The operation id for the '<em>Disconnect</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DYNAMIC_WIRING_PATTERN___DISCONNECT__STRING_STRING = COORDINATION_PATTERN_OPERATION_COUNT + 1;

	/**
	 * The number of operations of the '<em>Dynamic Wiring Pattern</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DYNAMIC_WIRING_PATTERN_OPERATION_COUNT = COORDINATION_PATTERN_OPERATION_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.eclipse.smartmdsd.ecore.service.coordinationPattern.impl.MonitoringPatternImpl <em>Monitoring Pattern</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.smartmdsd.ecore.service.coordinationPattern.impl.MonitoringPatternImpl
	 * @see org.eclipse.smartmdsd.ecore.service.coordinationPattern.impl.CoordinationPatternPackageImpl#getMonitoringPattern()
	 * @generated
	 */
	int MONITORING_PATTERN = 4;

	/**
	 * The number of structural features of the '<em>Monitoring Pattern</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MONITORING_PATTERN_FEATURE_COUNT = COORDINATION_PATTERN_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Monitoring Pattern</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MONITORING_PATTERN_OPERATION_COUNT = COORDINATION_PATTERN_OPERATION_COUNT + 0;

	/**
	 * Returns the meta object for class '{@link org.eclipse.smartmdsd.ecore.service.coordinationPattern.CoordinationPattern <em>Coordination Pattern</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Coordination Pattern</em>'.
	 * @see org.eclipse.smartmdsd.ecore.service.coordinationPattern.CoordinationPattern
	 * @generated
	 */
	EClass getCoordinationPattern();

	/**
	 * Returns the meta object for class '{@link org.eclipse.smartmdsd.ecore.service.coordinationPattern.StatePattern <em>State Pattern</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>State Pattern</em>'.
	 * @see org.eclipse.smartmdsd.ecore.service.coordinationPattern.StatePattern
	 * @generated
	 */
	EClass getStatePattern();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.smartmdsd.ecore.service.coordinationPattern.StatePattern#getLifecycle <em>Lifecycle</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Lifecycle</em>'.
	 * @see org.eclipse.smartmdsd.ecore.service.coordinationPattern.StatePattern#getLifecycle()
	 * @see #getStatePattern()
	 * @generated
	 */
	EReference getStatePattern_Lifecycle();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.smartmdsd.ecore.service.coordinationPattern.StatePattern#getModes <em>Modes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Modes</em>'.
	 * @see org.eclipse.smartmdsd.ecore.service.coordinationPattern.StatePattern#getModes()
	 * @see #getStatePattern()
	 * @generated
	 */
	EReference getStatePattern_Modes();

	/**
	 * Returns the meta object for class '{@link org.eclipse.smartmdsd.ecore.service.coordinationPattern.ParameterPattern <em>Parameter Pattern</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Parameter Pattern</em>'.
	 * @see org.eclipse.smartmdsd.ecore.service.coordinationPattern.ParameterPattern
	 * @generated
	 */
	EClass getParameterPattern();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.smartmdsd.ecore.service.coordinationPattern.ParameterPattern#getParameterSet <em>Parameter Set</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Parameter Set</em>'.
	 * @see org.eclipse.smartmdsd.ecore.service.coordinationPattern.ParameterPattern#getParameterSet()
	 * @see #getParameterPattern()
	 * @generated
	 */
	EReference getParameterPattern_ParameterSet();

	/**
	 * Returns the meta object for class '{@link org.eclipse.smartmdsd.ecore.service.coordinationPattern.DynamicWiringPattern <em>Dynamic Wiring Pattern</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Dynamic Wiring Pattern</em>'.
	 * @see org.eclipse.smartmdsd.ecore.service.coordinationPattern.DynamicWiringPattern
	 * @generated
	 */
	EClass getDynamicWiringPattern();

	/**
	 * Returns the meta object for the '{@link org.eclipse.smartmdsd.ecore.service.coordinationPattern.DynamicWiringPattern#connect(java.lang.String, java.lang.String, java.lang.String, java.lang.String) <em>Connect</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Connect</em>' operation.
	 * @see org.eclipse.smartmdsd.ecore.service.coordinationPattern.DynamicWiringPattern#connect(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 * @generated
	 */
	EOperation getDynamicWiringPattern__Connect__String_String_String_String();

	/**
	 * Returns the meta object for the '{@link org.eclipse.smartmdsd.ecore.service.coordinationPattern.DynamicWiringPattern#disconnect(java.lang.String, java.lang.String) <em>Disconnect</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Disconnect</em>' operation.
	 * @see org.eclipse.smartmdsd.ecore.service.coordinationPattern.DynamicWiringPattern#disconnect(java.lang.String, java.lang.String)
	 * @generated
	 */
	EOperation getDynamicWiringPattern__Disconnect__String_String();

	/**
	 * Returns the meta object for class '{@link org.eclipse.smartmdsd.ecore.service.coordinationPattern.MonitoringPattern <em>Monitoring Pattern</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Monitoring Pattern</em>'.
	 * @see org.eclipse.smartmdsd.ecore.service.coordinationPattern.MonitoringPattern
	 * @generated
	 */
	EClass getMonitoringPattern();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	CoordinationPatternFactory getCoordinationPatternFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link org.eclipse.smartmdsd.ecore.service.coordinationPattern.impl.CoordinationPatternImpl <em>Coordination Pattern</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.smartmdsd.ecore.service.coordinationPattern.impl.CoordinationPatternImpl
		 * @see org.eclipse.smartmdsd.ecore.service.coordinationPattern.impl.CoordinationPatternPackageImpl#getCoordinationPattern()
		 * @generated
		 */
		EClass COORDINATION_PATTERN = eINSTANCE.getCoordinationPattern();

		/**
		 * The meta object literal for the '{@link org.eclipse.smartmdsd.ecore.service.coordinationPattern.impl.StatePatternImpl <em>State Pattern</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.smartmdsd.ecore.service.coordinationPattern.impl.StatePatternImpl
		 * @see org.eclipse.smartmdsd.ecore.service.coordinationPattern.impl.CoordinationPatternPackageImpl#getStatePattern()
		 * @generated
		 */
		EClass STATE_PATTERN = eINSTANCE.getStatePattern();

		/**
		 * The meta object literal for the '<em><b>Lifecycle</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STATE_PATTERN__LIFECYCLE = eINSTANCE.getStatePattern_Lifecycle();

		/**
		 * The meta object literal for the '<em><b>Modes</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STATE_PATTERN__MODES = eINSTANCE.getStatePattern_Modes();

		/**
		 * The meta object literal for the '{@link org.eclipse.smartmdsd.ecore.service.coordinationPattern.impl.ParameterPatternImpl <em>Parameter Pattern</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.smartmdsd.ecore.service.coordinationPattern.impl.ParameterPatternImpl
		 * @see org.eclipse.smartmdsd.ecore.service.coordinationPattern.impl.CoordinationPatternPackageImpl#getParameterPattern()
		 * @generated
		 */
		EClass PARAMETER_PATTERN = eINSTANCE.getParameterPattern();

		/**
		 * The meta object literal for the '<em><b>Parameter Set</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PARAMETER_PATTERN__PARAMETER_SET = eINSTANCE.getParameterPattern_ParameterSet();

		/**
		 * The meta object literal for the '{@link org.eclipse.smartmdsd.ecore.service.coordinationPattern.impl.DynamicWiringPatternImpl <em>Dynamic Wiring Pattern</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.smartmdsd.ecore.service.coordinationPattern.impl.DynamicWiringPatternImpl
		 * @see org.eclipse.smartmdsd.ecore.service.coordinationPattern.impl.CoordinationPatternPackageImpl#getDynamicWiringPattern()
		 * @generated
		 */
		EClass DYNAMIC_WIRING_PATTERN = eINSTANCE.getDynamicWiringPattern();

		/**
		 * The meta object literal for the '<em><b>Connect</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation DYNAMIC_WIRING_PATTERN___CONNECT__STRING_STRING_STRING_STRING = eINSTANCE
				.getDynamicWiringPattern__Connect__String_String_String_String();

		/**
		 * The meta object literal for the '<em><b>Disconnect</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation DYNAMIC_WIRING_PATTERN___DISCONNECT__STRING_STRING = eINSTANCE
				.getDynamicWiringPattern__Disconnect__String_String();

		/**
		 * The meta object literal for the '{@link org.eclipse.smartmdsd.ecore.service.coordinationPattern.impl.MonitoringPatternImpl <em>Monitoring Pattern</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.smartmdsd.ecore.service.coordinationPattern.impl.MonitoringPatternImpl
		 * @see org.eclipse.smartmdsd.ecore.service.coordinationPattern.impl.CoordinationPatternPackageImpl#getMonitoringPattern()
		 * @generated
		 */
		EClass MONITORING_PATTERN = eINSTANCE.getMonitoringPattern();

	}

} //CoordinationPatternPackage
