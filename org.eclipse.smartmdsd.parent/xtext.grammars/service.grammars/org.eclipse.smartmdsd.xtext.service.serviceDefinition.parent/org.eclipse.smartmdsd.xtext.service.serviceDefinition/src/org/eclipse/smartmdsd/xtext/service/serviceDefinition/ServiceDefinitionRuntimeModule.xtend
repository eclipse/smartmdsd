/********************************************************************************
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
 *   Alex Lotz, Dennis Stampfer, Matthias Lutz
 ********************************************************************************/
package org.eclipse.smartmdsd.xtext.service.serviceDefinition

import com.google.inject.Binder
import org.eclipse.xtext.scoping.IScopeProvider
import com.google.inject.name.Names
import org.eclipse.xtext.scoping.impl.AbstractDeclarativeScopeProvider
import org.eclipse.smartmdsd.xtext.service.serviceDefinition.scoping.ServiceDefinitionImportedNamespaceAwareLocalScopeProvider

/**
 * Use this class to register components to be used at runtime / without the Equinox extension registry.
 */
class ServiceDefinitionRuntimeModule extends AbstractServiceDefinitionRuntimeModule {
	override configureIScopeProviderDelegate(Binder binder) {
		binder.bind(IScopeProvider)
			.annotatedWith(Names.named(AbstractDeclarativeScopeProvider.NAMED_DELEGATE))
			.to(ServiceDefinitionImportedNamespaceAwareLocalScopeProvider)
//		super.configureIScopeProviderDelegate(binder)
	}
}
