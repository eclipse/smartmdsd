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
package org.eclipse.smartmdsd.ecore.behavior.taskRealization;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.smartmdsd.ecore.behavior.taskRealization.TaskRealizationPackage
 * @generated
 */
public interface TaskRealizationFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	TaskRealizationFactory eINSTANCE = org.eclipse.smartmdsd.ecore.behavior.taskRealization.impl.TaskRealizationFactoryImpl
			.init();

	/**
	 * Returns a new object of class '<em>Model</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Model</em>'.
	 * @generated
	 */
	TaskRealizationModel createTaskRealizationModel();

	/**
	 * Returns a new object of class '<em>Task Realization</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Task Realization</em>'.
	 * @generated
	 */
	TaskRealization createTaskRealization();

	/**
	 * Returns a new object of class '<em>Abstract Coordination Module Instance</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Abstract Coordination Module Instance</em>'.
	 * @generated
	 */
	AbstractCoordinationModuleInstance createAbstractCoordinationModuleInstance();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	TaskRealizationPackage getTaskRealizationPackage();

} //TaskRealizationFactory
