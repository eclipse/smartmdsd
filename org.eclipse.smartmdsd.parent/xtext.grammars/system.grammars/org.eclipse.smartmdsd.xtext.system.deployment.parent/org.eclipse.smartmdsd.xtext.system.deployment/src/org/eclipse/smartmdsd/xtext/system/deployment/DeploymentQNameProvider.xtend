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
package org.eclipse.smartmdsd.xtext.system.deployment

import org.eclipse.xtext.naming.DefaultDeclarativeQualifiedNameProvider
import org.eclipse.xtext.naming.QualifiedName
import org.eclipse.smartmdsd.ecore.system.deployment.ComponentArtefact
import org.eclipse.xtext.nodemodel.util.NodeModelUtils
import org.eclipse.smartmdsd.ecore.system.deployment.DeploymentPackage
import org.eclipse.smartmdsd.ecore.system.deployment.NetworkInterfaceSelection
import org.eclipse.smartmdsd.ecore.system.deployment.LoginAccountSelection

class DeploymentQNameProvider extends DefaultDeclarativeQualifiedNameProvider {
	def QualifiedName qualifiedName(ComponentArtefact artefact) {
		val parent = super.getFullyQualifiedName(artefact.eContainer());
		val nodes = NodeModelUtils.findNodesForFeature(artefact, DeploymentPackage.Literals.COMPONENT_ARTEFACT__COMPONENT)
		if(!nodes.isEmpty()) {
			return parent.append(NodeModelUtils.getTokenText(nodes.get(0)));
		} else {
			if(artefact.eIsSet(DeploymentPackage.Literals.COMPONENT_ARTEFACT__COMPONENT)) {
				return parent.append(artefact.component.name);
			}
		}
        return QualifiedName.EMPTY;
    }
    
   	def QualifiedName qualifiedName(NetworkInterfaceSelection selection) {
		val parent = super.getFullyQualifiedName(selection.eContainer());
		val nodes = NodeModelUtils.findNodesForFeature(selection, DeploymentPackage.Literals.NETWORK_INTERFACE_SELECTION__NETWORK)
		if(!nodes.isEmpty()) {
			return parent.append(NodeModelUtils.getTokenText(nodes.get(0)));
		} else {
			if(selection.eIsSet(DeploymentPackage.Literals.NETWORK_INTERFACE_SELECTION__NETWORK)) {
				return parent.append(selection.network.name);
			}
		}
        return QualifiedName.EMPTY;
    }
    
    def QualifiedName qualifiedName(LoginAccountSelection selection) {
		val parent = super.getFullyQualifiedName(selection.eContainer());
		val nodes = NodeModelUtils.findNodesForFeature(selection, DeploymentPackage.Literals.LOGIN_ACCOUNT_SELECTION__LOGIN)
		if(!nodes.isEmpty()) {
			return parent.append(NodeModelUtils.getTokenText(nodes.get(0)));
		} else {
			if(selection.eIsSet(DeploymentPackage.Literals.LOGIN_ACCOUNT_SELECTION__LOGIN)) {
				return parent.append(selection.login.name);
			}
		}
        return QualifiedName.EMPTY;
    }
    
//   	def QualifiedName qualifiedName(TargetPlatformReference platform) {
//		val parent = super.getFullyQualifiedName(platform.eContainer());
//		val platformNodes = NodeModelUtils.findNodesForFeature(platform, DeploymentPackage.Literals.TARGET_PLATFORM_REFERENCE__PLATFORM)
//		if(!platformNodes.empty) {
//			return parent.append(NodeModelUtils.getTokenText(platformNodes.get(0)))				
//		} else {
//			if(platform.eIsSet(DeploymentPackage.Literals.TARGET_PLATFORM_REFERENCE__PLATFORM)) {
//				return parent.append(platform.name);
//			}
//		}
//        return QualifiedName.EMPTY;
//    }
}