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
package org.eclipse.smartmdsd.ecore.service.serviceDefinition;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Service Repo Version</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.smartmdsd.ecore.service.serviceDefinition.ServiceRepoVersion#getMajor <em>Major</em>}</li>
 *   <li>{@link org.eclipse.smartmdsd.ecore.service.serviceDefinition.ServiceRepoVersion#getMinor <em>Minor</em>}</li>
 *   <li>{@link org.eclipse.smartmdsd.ecore.service.serviceDefinition.ServiceRepoVersion#getPatch <em>Patch</em>}</li>
 * </ul>
 *
 * @see org.eclipse.smartmdsd.ecore.service.serviceDefinition.ServiceDefinitionPackage#getServiceRepoVersion()
 * @model
 * @generated
 */
public interface ServiceRepoVersion extends EObject {
	/**
	 * Returns the value of the '<em><b>Major</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Major</em>' attribute.
	 * @see #setMajor(int)
	 * @see org.eclipse.smartmdsd.ecore.service.serviceDefinition.ServiceDefinitionPackage#getServiceRepoVersion_Major()
	 * @model required="true"
	 * @generated
	 */
	int getMajor();

	/**
	 * Sets the value of the '{@link org.eclipse.smartmdsd.ecore.service.serviceDefinition.ServiceRepoVersion#getMajor <em>Major</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Major</em>' attribute.
	 * @see #getMajor()
	 * @generated
	 */
	void setMajor(int value);

	/**
	 * Returns the value of the '<em><b>Minor</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Minor</em>' attribute.
	 * @see #setMinor(int)
	 * @see org.eclipse.smartmdsd.ecore.service.serviceDefinition.ServiceDefinitionPackage#getServiceRepoVersion_Minor()
	 * @model required="true"
	 * @generated
	 */
	int getMinor();

	/**
	 * Sets the value of the '{@link org.eclipse.smartmdsd.ecore.service.serviceDefinition.ServiceRepoVersion#getMinor <em>Minor</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Minor</em>' attribute.
	 * @see #getMinor()
	 * @generated
	 */
	void setMinor(int value);

	/**
	 * Returns the value of the '<em><b>Patch</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Patch</em>' attribute.
	 * @see #setPatch(int)
	 * @see org.eclipse.smartmdsd.ecore.service.serviceDefinition.ServiceDefinitionPackage#getServiceRepoVersion_Patch()
	 * @model
	 * @generated
	 */
	int getPatch();

	/**
	 * Sets the value of the '{@link org.eclipse.smartmdsd.ecore.service.serviceDefinition.ServiceRepoVersion#getPatch <em>Patch</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Patch</em>' attribute.
	 * @see #getPatch()
	 * @generated
	 */
	void setPatch(int value);

} // ServiceRepoVersion
