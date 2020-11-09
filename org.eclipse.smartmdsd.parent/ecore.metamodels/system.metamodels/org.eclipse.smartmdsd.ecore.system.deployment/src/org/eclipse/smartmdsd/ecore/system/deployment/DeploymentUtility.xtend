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
package org.eclipse.smartmdsd.ecore.system.deployment

import org.eclipse.smartmdsd.ecore.system.targetPlatform.TargetPlatformDefinition
import org.eclipse.smartmdsd.ecore.system.targetPlatform.NetworkInterface
import org.eclipse.smartmdsd.ecore.system.componentArchitecture.ComponentInstance

class DeploymentUtility {
	def static addDefaultTargetReference(DeploymentModel deploymentModel, TargetPlatformDefinition targetDef) {
		// create new target-platform-reference
		val targetRef = DeploymentFactory.eINSTANCE.createTargetPlatformReference()
		targetRef.name = targetDef.name
		targetRef.platform = targetDef

		// create a network-interface-selection using the first network-interface from the target-definition 
		val ni = targetDef.elements.findFirst[it instanceof NetworkInterface]
		if(ni instanceof NetworkInterface) {
			val nis = DeploymentFactory.eINSTANCE.createNetworkInterfaceSelection()
			nis.network = ni
			targetRef.host = nis
		}
		
		// create a default target directory
		val ud = DeploymentFactory.eINSTANCE.createUploadDirectory()
		ud.path = "/tmp"
		targetRef.directory = ud
		
		// add the new target-reference to the deployment model
		deploymentModel.elements.add(targetRef)
		
		// create a NamingService artefact
		val ns = DeploymentFactory.eINSTANCE.createNamingService()
		val ns_depl = DeploymentFactory.eINSTANCE.createDeployment()
		ns_depl.to = targetRef
		ns.deploy = ns_depl
		
		// add the new NamingService to the deployment model
		deploymentModel.elements.add(ns)
		
		return targetRef
	}
	
	def static addComponentArtefact(DeploymentModel deploymentModel, ComponentInstance component, TargetPlatformReference targetRef) {
		// create new component artefact
		val artefact = DeploymentFactory.eINSTANCE.createComponentArtefact()
		artefact.component = component;
		// create related artefact-deployment
		artefact.deploy = DeploymentFactory.eINSTANCE.createDeployment()
		artefact.deploy.to = targetRef
		// add the new artefact to the deployment-model
		deploymentModel.elements.add(artefact)
	}
	
	def static addAllMissingComponentArtefacts(DeploymentModel deploymentModel, TargetPlatformReference targetRef) {
		for(component: deploymentModel.componentArch.components) {
			if(!deploymentModel.elements.filter(ComponentArtefact).exists[it.component == component]) {
				deploymentModel.addComponentArtefact(component, targetRef)
			}
		}
	}
	
	def static areAllComponentsDeployed(DeploymentModel deploymentModel) {
		for(component: deploymentModel.componentArch.components) {
			if(!deploymentModel.elements.filter(ComponentArtefact).exists[it.component == component]) {
				return false;
			}
		}
		return true;
	}
}
