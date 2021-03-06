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
package org.eclipse.smartmdsd.ecore.system.deployment;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.smartmdsd.ecore.system.targetPlatform.LoginAccount;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Login Account Selection</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.smartmdsd.ecore.system.deployment.LoginAccountSelection#getLogin <em>Login</em>}</li>
 *   <li>{@link org.eclipse.smartmdsd.ecore.system.deployment.LoginAccountSelection#getName <em>Name</em>}</li>
 * </ul>
 *
 * @see org.eclipse.smartmdsd.ecore.system.deployment.DeploymentPackage#getLoginAccountSelection()
 * @model
 * @generated
 */
public interface LoginAccountSelection extends EObject {
	/**
	 * Returns the value of the '<em><b>Login</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Login</em>' reference.
	 * @see #setLogin(LoginAccount)
	 * @see org.eclipse.smartmdsd.ecore.system.deployment.DeploymentPackage#getLoginAccountSelection_Login()
	 * @model required="true"
	 * @generated
	 */
	LoginAccount getLogin();

	/**
	 * Sets the value of the '{@link org.eclipse.smartmdsd.ecore.system.deployment.LoginAccountSelection#getLogin <em>Login</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Login</em>' reference.
	 * @see #getLogin()
	 * @generated
	 */
	void setLogin(LoginAccount value);

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #isSetName()
	 * @see org.eclipse.smartmdsd.ecore.system.deployment.DeploymentPackage#getLoginAccountSelection_Name()
	 * @model unsettable="true" required="true" transient="true" changeable="false" volatile="true" derived="true"
	 * @generated
	 */
	String getName();

	/**
	 * Returns whether the value of the '{@link org.eclipse.smartmdsd.ecore.system.deployment.LoginAccountSelection#getName <em>Name</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Name</em>' attribute is set.
	 * @see #getName()
	 * @generated
	 */
	boolean isSetName();

} // LoginAccountSelection
