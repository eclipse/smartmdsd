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
package org.eclipse.smartmdsd.ecore.service.serviceDefinition.impl;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.smartmdsd.ecore.service.serviceDefinition.ServiceDefinitionPackage;
import org.eclipse.smartmdsd.ecore.service.serviceDefinition.TwoWayCommunicationService;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Two Way Communication Service</b></em>'.
 * <!-- end-user-doc -->
 *
 * @generated
 */
public abstract class TwoWayCommunicationServiceImpl extends CommunicationServiceDefinitionImpl
		implements TwoWayCommunicationService {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TwoWayCommunicationServiceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ServiceDefinitionPackage.Literals.TWO_WAY_COMMUNICATION_SERVICE;
	}

} //TwoWayCommunicationServiceImpl
