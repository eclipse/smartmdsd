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
package org.eclipse.smartmdsd.ecore.component.coordinationExtension.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.smartmdsd.ecore.component.componentDefinition.impl.NamedComponentElementImpl;

import org.eclipse.smartmdsd.ecore.component.coordinationExtension.CoordinationExtensionPackage;
import org.eclipse.smartmdsd.ecore.component.coordinationExtension.CoordinationMasterPort;

import org.eclipse.smartmdsd.ecore.service.serviceDefinition.CoordinationServiceDefinition;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Coordination Master Port</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.smartmdsd.ecore.component.coordinationExtension.impl.CoordinationMasterPortImpl#getService <em>Service</em>}</li>
 * </ul>
 *
 * @generated
 */
public class CoordinationMasterPortImpl extends NamedComponentElementImpl implements CoordinationMasterPort {
	/**
	 * The cached value of the '{@link #getService() <em>Service</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getService()
	 * @generated
	 * @ordered
	 */
	protected CoordinationServiceDefinition service;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CoordinationMasterPortImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CoordinationExtensionPackage.Literals.COORDINATION_MASTER_PORT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public CoordinationServiceDefinition getService() {
		if (service != null && service.eIsProxy()) {
			InternalEObject oldService = (InternalEObject) service;
			service = (CoordinationServiceDefinition) eResolveProxy(oldService);
			if (service != oldService) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE,
							CoordinationExtensionPackage.COORDINATION_MASTER_PORT__SERVICE, oldService, service));
			}
		}
		return service;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CoordinationServiceDefinition basicGetService() {
		return service;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setService(CoordinationServiceDefinition newService) {
		CoordinationServiceDefinition oldService = service;
		service = newService;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					CoordinationExtensionPackage.COORDINATION_MASTER_PORT__SERVICE, oldService, service));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case CoordinationExtensionPackage.COORDINATION_MASTER_PORT__SERVICE:
			if (resolve)
				return getService();
			return basicGetService();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case CoordinationExtensionPackage.COORDINATION_MASTER_PORT__SERVICE:
			setService((CoordinationServiceDefinition) newValue);
			return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
		case CoordinationExtensionPackage.COORDINATION_MASTER_PORT__SERVICE:
			setService((CoordinationServiceDefinition) null);
			return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
		case CoordinationExtensionPackage.COORDINATION_MASTER_PORT__SERVICE:
			return service != null;
		}
		return super.eIsSet(featureID);
	}

} //CoordinationMasterPortImpl
