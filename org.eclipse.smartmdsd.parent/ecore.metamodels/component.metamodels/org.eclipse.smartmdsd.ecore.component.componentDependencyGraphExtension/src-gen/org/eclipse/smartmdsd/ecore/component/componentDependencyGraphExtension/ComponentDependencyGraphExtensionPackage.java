/**
 * Copyright (c) 2019 Technische Hochschule Ulm, Servicerobotics Ulm, Germany
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
package org.eclipse.smartmdsd.ecore.component.componentDependencyGraphExtension;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.smartmdsd.ecore.component.componentDefinition.ComponentDefinitionPackage;

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
 * @see org.eclipse.smartmdsd.ecore.component.componentDependencyGraphExtension.ComponentDependencyGraphExtensionFactory
 * @model kind="package"
 * @generated
 */
public interface ComponentDependencyGraphExtensionPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "componentDependencyGraphExtension";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.eclipse.org/smartmdsd/component/componentDependencyGraphExtension";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "componentDependencyGraphExtension";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ComponentDependencyGraphExtensionPackage eINSTANCE = org.eclipse.smartmdsd.ecore.component.componentDependencyGraphExtension.impl.ComponentDependencyGraphExtensionPackageImpl
			.init();

	/**
	 * The meta object id for the '{@link org.eclipse.smartmdsd.ecore.component.componentDependencyGraphExtension.impl.ComponentDependencyObjectImpl <em>Component Dependency Object</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.smartmdsd.ecore.component.componentDependencyGraphExtension.impl.ComponentDependencyObjectImpl
	 * @see org.eclipse.smartmdsd.ecore.component.componentDependencyGraphExtension.impl.ComponentDependencyGraphExtensionPackageImpl#getComponentDependencyObject()
	 * @generated
	 */
	int COMPONENT_DEPENDENCY_OBJECT = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_DEPENDENCY_OBJECT__NAME = ComponentDefinitionPackage.COMPONENT_PORT_EXTENSION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Component Dependency Object</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_DEPENDENCY_OBJECT_FEATURE_COUNT = ComponentDefinitionPackage.COMPONENT_PORT_EXTENSION_FEATURE_COUNT
			+ 1;

	/**
	 * The number of operations of the '<em>Component Dependency Object</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_DEPENDENCY_OBJECT_OPERATION_COUNT = ComponentDefinitionPackage.COMPONENT_PORT_EXTENSION_OPERATION_COUNT
			+ 0;

	/**
	 * Returns the meta object for class '{@link org.eclipse.smartmdsd.ecore.component.componentDependencyGraphExtension.ComponentDependencyObject <em>Component Dependency Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Component Dependency Object</em>'.
	 * @see org.eclipse.smartmdsd.ecore.component.componentDependencyGraphExtension.ComponentDependencyObject
	 * @generated
	 */
	EClass getComponentDependencyObject();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.smartmdsd.ecore.component.componentDependencyGraphExtension.ComponentDependencyObject#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.smartmdsd.ecore.component.componentDependencyGraphExtension.ComponentDependencyObject#getName()
	 * @see #getComponentDependencyObject()
	 * @generated
	 */
	EAttribute getComponentDependencyObject_Name();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	ComponentDependencyGraphExtensionFactory getComponentDependencyGraphExtensionFactory();

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
		 * The meta object literal for the '{@link org.eclipse.smartmdsd.ecore.component.componentDependencyGraphExtension.impl.ComponentDependencyObjectImpl <em>Component Dependency Object</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.smartmdsd.ecore.component.componentDependencyGraphExtension.impl.ComponentDependencyObjectImpl
		 * @see org.eclipse.smartmdsd.ecore.component.componentDependencyGraphExtension.impl.ComponentDependencyGraphExtensionPackageImpl#getComponentDependencyObject()
		 * @generated
		 */
		EClass COMPONENT_DEPENDENCY_OBJECT = eINSTANCE.getComponentDependencyObject();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPONENT_DEPENDENCY_OBJECT__NAME = eINSTANCE.getComponentDependencyObject_Name();

	}

} //ComponentDependencyGraphExtensionPackage
