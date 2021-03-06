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

import org.eclipse.smartmdsd.ecore.behavior.skillRealization.CoordinationModuleRealization;

import org.eclipse.smartmdsd.ecore.component.coordinationExtension.CoordinationExtensionPackage;
import org.eclipse.smartmdsd.ecore.component.coordinationExtension.SkillRealizationsRef;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Skill Realizations Ref</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.smartmdsd.ecore.component.coordinationExtension.impl.SkillRealizationsRefImpl#getSkillRealizationCoordModuleRef <em>Skill Realization Coord Module Ref</em>}</li>
 * </ul>
 *
 * @generated
 */
public class SkillRealizationsRefImpl extends AbstractCoordinationElementImpl implements SkillRealizationsRef {
	/**
	 * The cached value of the '{@link #getSkillRealizationCoordModuleRef() <em>Skill Realization Coord Module Ref</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSkillRealizationCoordModuleRef()
	 * @generated
	 * @ordered
	 */
	protected CoordinationModuleRealization skillRealizationCoordModuleRef;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SkillRealizationsRefImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CoordinationExtensionPackage.Literals.SKILL_REALIZATIONS_REF;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public CoordinationModuleRealization getSkillRealizationCoordModuleRef() {
		if (skillRealizationCoordModuleRef != null && skillRealizationCoordModuleRef.eIsProxy()) {
			InternalEObject oldSkillRealizationCoordModuleRef = (InternalEObject) skillRealizationCoordModuleRef;
			skillRealizationCoordModuleRef = (CoordinationModuleRealization) eResolveProxy(
					oldSkillRealizationCoordModuleRef);
			if (skillRealizationCoordModuleRef != oldSkillRealizationCoordModuleRef) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE,
							CoordinationExtensionPackage.SKILL_REALIZATIONS_REF__SKILL_REALIZATION_COORD_MODULE_REF,
							oldSkillRealizationCoordModuleRef, skillRealizationCoordModuleRef));
			}
		}
		return skillRealizationCoordModuleRef;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CoordinationModuleRealization basicGetSkillRealizationCoordModuleRef() {
		return skillRealizationCoordModuleRef;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setSkillRealizationCoordModuleRef(CoordinationModuleRealization newSkillRealizationCoordModuleRef) {
		CoordinationModuleRealization oldSkillRealizationCoordModuleRef = skillRealizationCoordModuleRef;
		skillRealizationCoordModuleRef = newSkillRealizationCoordModuleRef;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					CoordinationExtensionPackage.SKILL_REALIZATIONS_REF__SKILL_REALIZATION_COORD_MODULE_REF,
					oldSkillRealizationCoordModuleRef, skillRealizationCoordModuleRef));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case CoordinationExtensionPackage.SKILL_REALIZATIONS_REF__SKILL_REALIZATION_COORD_MODULE_REF:
			if (resolve)
				return getSkillRealizationCoordModuleRef();
			return basicGetSkillRealizationCoordModuleRef();
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
		case CoordinationExtensionPackage.SKILL_REALIZATIONS_REF__SKILL_REALIZATION_COORD_MODULE_REF:
			setSkillRealizationCoordModuleRef((CoordinationModuleRealization) newValue);
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
		case CoordinationExtensionPackage.SKILL_REALIZATIONS_REF__SKILL_REALIZATION_COORD_MODULE_REF:
			setSkillRealizationCoordModuleRef((CoordinationModuleRealization) null);
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
		case CoordinationExtensionPackage.SKILL_REALIZATIONS_REF__SKILL_REALIZATION_COORD_MODULE_REF:
			return skillRealizationCoordModuleRef != null;
		}
		return super.eIsSet(featureID);
	}

} //SkillRealizationsRefImpl
