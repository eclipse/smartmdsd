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
package org.eclipse.smartmdsd.ecore.system.componentArchitecture.impl;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.smartmdsd.ecore.system.componentArchitecture.ComponentArchitecturePackage;
import org.eclipse.smartmdsd.ecore.system.componentArchitecture.RequiredService;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Required Service</b></em>'.
 * <!-- end-user-doc -->
 *
 * @generated
 */
public class RequiredServiceImpl extends ServiceInstanceImpl implements RequiredService {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RequiredServiceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ComponentArchitecturePackage.Literals.REQUIRED_SERVICE;
	}

} //RequiredServiceImpl
