/**
 * Copyright (c) 2019 Technische Hochschule Ulm, Servicerobotics Ulm, Germany
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
package org.eclipse.smartmdsd.ecore.component.componentDependencyGraphExtension.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.smartmdsd.ecore.base.basicAttributes.BasicAttributesPackage;

import org.eclipse.smartmdsd.ecore.base.documentation.DocumentationPackage;

import org.eclipse.smartmdsd.ecore.base.stateMachine.StateMachinePackage;

import org.eclipse.smartmdsd.ecore.component.componentDefinition.ComponentDefinitionPackage;

import org.eclipse.smartmdsd.ecore.component.componentDependencyGraphExtension.ComponentDependencyGraphExtensionFactory;
import org.eclipse.smartmdsd.ecore.component.componentDependencyGraphExtension.ComponentDependencyGraphExtensionPackage;
import org.eclipse.smartmdsd.ecore.component.componentDependencyGraphExtension.ComponentDependencyObject;

import org.eclipse.smartmdsd.ecore.service.communicationObject.CommunicationObjectPackage;

import org.eclipse.smartmdsd.ecore.service.communicationPattern.CommunicationPatternPackage;

import org.eclipse.smartmdsd.ecore.service.componentMode.ComponentModePackage;

import org.eclipse.smartmdsd.ecore.service.coordinationPattern.CoordinationPatternPackage;

import org.eclipse.smartmdsd.ecore.service.parameterDefinition.ParameterDefinitionPackage;

import org.eclipse.smartmdsd.ecore.service.serviceDefinition.ServiceDefinitionPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ComponentDependencyGraphExtensionPackageImpl extends EPackageImpl
		implements ComponentDependencyGraphExtensionPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass componentDependencyObjectEClass = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see org.eclipse.smartmdsd.ecore.component.componentDependencyGraphExtension.ComponentDependencyGraphExtensionPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private ComponentDependencyGraphExtensionPackageImpl() {
		super(eNS_URI, ComponentDependencyGraphExtensionFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 *
	 * <p>This method is used to initialize {@link ComponentDependencyGraphExtensionPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static ComponentDependencyGraphExtensionPackage init() {
		if (isInited)
			return (ComponentDependencyGraphExtensionPackage) EPackage.Registry.INSTANCE
					.getEPackage(ComponentDependencyGraphExtensionPackage.eNS_URI);

		// Obtain or create and register package
		Object registeredComponentDependencyGraphExtensionPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
		ComponentDependencyGraphExtensionPackageImpl theComponentDependencyGraphExtensionPackage = registeredComponentDependencyGraphExtensionPackage instanceof ComponentDependencyGraphExtensionPackageImpl
				? (ComponentDependencyGraphExtensionPackageImpl) registeredComponentDependencyGraphExtensionPackage
				: new ComponentDependencyGraphExtensionPackageImpl();

		isInited = true;

		// Initialize simple dependencies
		BasicAttributesPackage.eINSTANCE.eClass();
		CommunicationObjectPackage.eINSTANCE.eClass();
		CommunicationPatternPackage.eINSTANCE.eClass();
		ComponentDefinitionPackage.eINSTANCE.eClass();
		ComponentModePackage.eINSTANCE.eClass();
		CoordinationPatternPackage.eINSTANCE.eClass();
		DocumentationPackage.eINSTANCE.eClass();
		ParameterDefinitionPackage.eINSTANCE.eClass();
		ServiceDefinitionPackage.eINSTANCE.eClass();
		StateMachinePackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theComponentDependencyGraphExtensionPackage.createPackageContents();

		// Initialize created meta-data
		theComponentDependencyGraphExtensionPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theComponentDependencyGraphExtensionPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(ComponentDependencyGraphExtensionPackage.eNS_URI,
				theComponentDependencyGraphExtensionPackage);
		return theComponentDependencyGraphExtensionPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getComponentDependencyObject() {
		return componentDependencyObjectEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getComponentDependencyObject_Name() {
		return (EAttribute) componentDependencyObjectEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ComponentDependencyGraphExtensionFactory getComponentDependencyGraphExtensionFactory() {
		return (ComponentDependencyGraphExtensionFactory) getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated)
			return;
		isCreated = true;

		// Create classes and their features
		componentDependencyObjectEClass = createEClass(COMPONENT_DEPENDENCY_OBJECT);
		createEAttribute(componentDependencyObjectEClass, COMPONENT_DEPENDENCY_OBJECT__NAME);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized)
			return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		ComponentDefinitionPackage theComponentDefinitionPackage = (ComponentDefinitionPackage) EPackage.Registry.INSTANCE
				.getEPackage(ComponentDefinitionPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		componentDependencyObjectEClass.getESuperTypes().add(theComponentDefinitionPackage.getComponentPortExtension());

		// Initialize classes, features, and operations; add parameters
		initEClass(componentDependencyObjectEClass, ComponentDependencyObject.class, "ComponentDependencyObject",
				!IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getComponentDependencyObject_Name(), ecorePackage.getEString(), "name", null, 1, 1,
				ComponentDependencyObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);
	}

} //ComponentDependencyGraphExtensionPackageImpl
