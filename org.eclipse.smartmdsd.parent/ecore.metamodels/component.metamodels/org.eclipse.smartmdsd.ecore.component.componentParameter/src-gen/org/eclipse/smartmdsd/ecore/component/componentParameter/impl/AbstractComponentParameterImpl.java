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
package org.eclipse.smartmdsd.ecore.component.componentParameter.impl;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.smartmdsd.ecore.base.documentation.impl.AbstractDocumentationElementImpl;

import org.eclipse.smartmdsd.ecore.component.componentParameter.AbstractComponentParameter;
import org.eclipse.smartmdsd.ecore.component.componentParameter.ComponentParameterPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Abstract Component Parameter</b></em>'.
 * <!-- end-user-doc -->
 *
 * @generated
 */
public abstract class AbstractComponentParameterImpl extends AbstractDocumentationElementImpl
		implements AbstractComponentParameter {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AbstractComponentParameterImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ComponentParameterPackage.Literals.ABSTRACT_COMPONENT_PARAMETER;
	}

} //AbstractComponentParameterImpl
