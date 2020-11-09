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
 *    Alex Lotz
 */
package org.eclipse.smartmdsd.ecore.component.performanceExtension.impl;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.smartmdsd.ecore.component.componentDefinition.impl.ActivityExtensionImpl;

import org.eclipse.smartmdsd.ecore.component.performanceExtension.DefaultTrigger;
import org.eclipse.smartmdsd.ecore.component.performanceExtension.PerformanceExtensionPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Default Trigger</b></em>'.
 * <!-- end-user-doc -->
 *
 * @generated
 */
public abstract class DefaultTriggerImpl extends ActivityExtensionImpl implements DefaultTrigger {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DefaultTriggerImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PerformanceExtensionPackage.Literals.DEFAULT_TRIGGER;
	}

} //DefaultTriggerImpl
