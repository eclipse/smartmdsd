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
package org.eclipse.smartmdsd.ecore.service.communicationPattern.impl;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.smartmdsd.ecore.service.communicationPattern.CommunicationPatternPackage;
import org.eclipse.smartmdsd.ecore.service.communicationPattern.RequestAnswerPattern;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Request Answer Pattern</b></em>'.
 * <!-- end-user-doc -->
 *
 * @generated
 */
public abstract class RequestAnswerPatternImpl extends TwoWayCommunicationPatternImpl implements RequestAnswerPattern {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RequestAnswerPatternImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CommunicationPatternPackage.Literals.REQUEST_ANSWER_PATTERN;
	}

} //RequestAnswerPatternImpl
