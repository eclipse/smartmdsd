/********************************************************************************
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
 *   Alex Lotz, Dennis Stampfer, Matthias Lutz
 ********************************************************************************/
package org.eclipse.smartmdsd.xtext.system.deployment.scoping

import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.EReference
import org.eclipse.smartmdsd.ecore.system.deployment.DeploymentPackage
import org.eclipse.xtext.scoping.Scopes
import org.eclipse.smartmdsd.ecore.system.targetPlatform.LoginAccount
import org.eclipse.smartmdsd.ecore.system.targetPlatform.NetworkInterface
import org.eclipse.smartmdsd.ecore.system.deployment.TargetPlatformReference
import org.eclipse.smartmdsd.ecore.system.deployment.DeploymentModel

/**
 * This class contains custom scoping description.
 * 
 * See https://www.eclipse.org/Xtext/documentation/303_runtime_concepts.html#scoping
 * on how and when to use it.
 */
class DeploymentScopeProvider extends AbstractDeploymentScopeProvider {
	
	override getScope(EObject context, EReference reference) {
		val parent = context.eContainer
		if(reference == DeploymentPackage.Literals.LOGIN_ACCOUNT_SELECTION__LOGIN) {
			if(parent instanceof TargetPlatformReference) {
				if(parent.platform !== null) {
					return Scopes.scopeFor(parent.platform.elements.filter(LoginAccount))
				}
			}
		} else if(reference == DeploymentPackage.Literals.NETWORK_INTERFACE_SELECTION__NETWORK) {
			if(parent instanceof TargetPlatformReference) {
				if(parent.platform !== null) {
					return Scopes.scopeFor(parent.platform.elements.filter(NetworkInterface))
				}
			}
		} else if(reference == DeploymentPackage.Literals.COMPONENT_ARTEFACT__COMPONENT) {
			if(parent instanceof DeploymentModel) {
				if(parent.componentArch !== null) {
					return Scopes.scopeFor(parent.componentArch.components)
				}
			}
		}
		super.getScope(context, reference)
	}
	
}
