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

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.eclipse.smartmdsd.ecore.component.componentDependencyGraphExtension.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ComponentDependencyGraphExtensionFactoryImpl extends EFactoryImpl
		implements ComponentDependencyGraphExtensionFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ComponentDependencyGraphExtensionFactory init() {
		try {
			ComponentDependencyGraphExtensionFactory theComponentDependencyGraphExtensionFactory = (ComponentDependencyGraphExtensionFactory) EPackage.Registry.INSTANCE
					.getEFactory(ComponentDependencyGraphExtensionPackage.eNS_URI);
			if (theComponentDependencyGraphExtensionFactory != null) {
				return theComponentDependencyGraphExtensionFactory;
			}
		} catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new ComponentDependencyGraphExtensionFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComponentDependencyGraphExtensionFactoryImpl() {
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
		case ComponentDependencyGraphExtensionPackage.COMPONENT_DEPENDENCY_OBJECT:
			return createComponentDependencyObject();
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
	public ComponentDependencyObject createComponentDependencyObject() {
		ComponentDependencyObjectImpl componentDependencyObject = new ComponentDependencyObjectImpl();
		return componentDependencyObject;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ComponentDependencyGraphExtensionPackage getComponentDependencyGraphExtensionPackage() {
		return (ComponentDependencyGraphExtensionPackage) getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static ComponentDependencyGraphExtensionPackage getPackage() {
		return ComponentDependencyGraphExtensionPackage.eINSTANCE;
	}

} //ComponentDependencyGraphExtensionFactoryImpl
