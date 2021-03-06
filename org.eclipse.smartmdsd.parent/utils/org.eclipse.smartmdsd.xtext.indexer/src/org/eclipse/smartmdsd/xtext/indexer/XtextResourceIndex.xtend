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
package org.eclipse.smartmdsd.xtext.indexer

import org.eclipse.xtext.resource.impl.ResourceDescriptionsProvider
import org.eclipse.xtext.resource.IContainer
import com.google.inject.Inject
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.EClass
import org.eclipse.xtext.resource.IResourceDescription

/**
 * This is a helper class to streamline access to the global Xtext indexer.
 * The implementation is inspired from the smalljava example available at: 
 * https://github.com/LorenzoBettini/packtpub-xtext-book-2nd-examples
 */
class XtextResourceIndex {
	@Inject ResourceDescriptionsProvider rdp
	@Inject IContainer.Manager cm

	/**
	 * Get IResourceDescription for the given EObject from the Xtext index.
	 */	
	def IResourceDescription getResourceDescription(EObject o) {
		val index = rdp.getResourceDescriptions(o.eResource)
		val description = index.getResourceDescription(o.eResource.URI)
		return description
	}
	
	/**
	 * Get a list of IEObjectDescription for all the exported
	 * EObject descriptions in the Xtext index
	 */
	def getExportedEObjectDescriptions(EObject o) {
		o.getResourceDescription.getExportedObjects
	}
	
	/**
	 * Get all exported IEObjectDescription from the Xtext index
	 * of a certain type.
	 */
	def getExportedEObjectDescriptionsByType(EObject o, EClass type) {
		o.getResourceDescription.getExportedObjectsByType(type)
	}
	
	/**
	 * Get visible IContainer resources for a given EObject
	 */
	def getVisibleContainers(EObject o) {
		val index = rdp.getResourceDescriptions(o.eResource)
		val rd = index.getResourceDescription(o.eResource.URI)
		cm.getVisibleContainers(rd, index)
	}
	
	/**
	 * Get IEObjectDescriptions from all visible IContainers
	 * for a given EClass type.
	 */
	def getVisibleEObjectDescriptions(EObject o, EClass type) {
		o.getVisibleContainers.map [ container |
			container.getExportedObjectsByType(type)
		].flatten
	}
	
	/**
	 * Get IEObjectDescriptions from all visible IContainers
	 * for a given EClass type, excluding those from the local resource.
	 */
	def getVisibleExternalDescriptions(EObject o, EClass type) {
		val allVisibleClasses =
			o.getVisibleEObjectDescriptions(type)
		val allExportedClasses =
			o.getExportedEObjectDescriptionsByType(type)
		val difference = allVisibleClasses.toSet
		difference.removeAll(allExportedClasses.toSet)
		return difference.toMap[qualifiedName]
	}
}