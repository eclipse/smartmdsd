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
package org.eclipse.smartmdsd.ecore.component.componentDefinition;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Component Sub Node Observer</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.smartmdsd.ecore.component.componentDefinition.ComponentSubNodeObserver#getSubject <em>Subject</em>}</li>
 * </ul>
 *
 * @see org.eclipse.smartmdsd.ecore.component.componentDefinition.ComponentDefinitionPackage#getComponentSubNodeObserver()
 * @model
 * @generated
 */
public interface ComponentSubNodeObserver extends AbstractComponentLink {
	/**
	 * Returns the value of the '<em><b>Subject</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Subject</em>' reference.
	 * @see #setSubject(ComponentSubNode)
	 * @see org.eclipse.smartmdsd.ecore.component.componentDefinition.ComponentDefinitionPackage#getComponentSubNodeObserver_Subject()
	 * @model required="true"
	 * @generated
	 */
	ComponentSubNode getSubject();

	/**
	 * Sets the value of the '{@link org.eclipse.smartmdsd.ecore.component.componentDefinition.ComponentSubNodeObserver#getSubject <em>Subject</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Subject</em>' reference.
	 * @see #getSubject()
	 * @generated
	 */
	void setSubject(ComponentSubNode value);

} // ComponentSubNodeObserver
