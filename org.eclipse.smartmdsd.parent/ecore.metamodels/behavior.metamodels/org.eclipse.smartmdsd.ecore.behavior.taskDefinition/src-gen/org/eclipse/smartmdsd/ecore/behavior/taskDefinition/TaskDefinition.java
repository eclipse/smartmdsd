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
 *    Matthias Lutz, Alex Lotz, Dennis Stampfer
 */
package org.eclipse.smartmdsd.ecore.behavior.taskDefinition;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.smartmdsd.ecore.base.basicAttributes.AttributeDefinition;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Task Definition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.smartmdsd.ecore.behavior.taskDefinition.TaskDefinition#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.smartmdsd.ecore.behavior.taskDefinition.TaskDefinition#getResults <em>Results</em>}</li>
 *   <li>{@link org.eclipse.smartmdsd.ecore.behavior.taskDefinition.TaskDefinition#getInAttribute <em>In Attribute</em>}</li>
 *   <li>{@link org.eclipse.smartmdsd.ecore.behavior.taskDefinition.TaskDefinition#getOutAttribute <em>Out Attribute</em>}</li>
 * </ul>
 *
 * @see org.eclipse.smartmdsd.ecore.behavior.taskDefinition.TaskDefinitionPackage#getTaskDefinition()
 * @model
 * @generated
 */
public interface TaskDefinition extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.eclipse.smartmdsd.ecore.behavior.taskDefinition.TaskDefinitionPackage#getTaskDefinition_Name()
	 * @model required="true"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.eclipse.smartmdsd.ecore.behavior.taskDefinition.TaskDefinition#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Results</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.smartmdsd.ecore.behavior.taskDefinition.TaskResult}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Results</em>' containment reference list.
	 * @see org.eclipse.smartmdsd.ecore.behavior.taskDefinition.TaskDefinitionPackage#getTaskDefinition_Results()
	 * @model containment="true" required="true"
	 * @generated
	 */
	EList<TaskResult> getResults();

	/**
	 * Returns the value of the '<em><b>In Attribute</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.smartmdsd.ecore.base.basicAttributes.AttributeDefinition}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>In Attribute</em>' containment reference list.
	 * @see org.eclipse.smartmdsd.ecore.behavior.taskDefinition.TaskDefinitionPackage#getTaskDefinition_InAttribute()
	 * @model containment="true"
	 * @generated
	 */
	EList<AttributeDefinition> getInAttribute();

	/**
	 * Returns the value of the '<em><b>Out Attribute</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.smartmdsd.ecore.base.basicAttributes.AttributeDefinition}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Out Attribute</em>' containment reference list.
	 * @see org.eclipse.smartmdsd.ecore.behavior.taskDefinition.TaskDefinitionPackage#getTaskDefinition_OutAttribute()
	 * @model containment="true"
	 * @generated
	 */
	EList<AttributeDefinition> getOutAttribute();

} // TaskDefinition
