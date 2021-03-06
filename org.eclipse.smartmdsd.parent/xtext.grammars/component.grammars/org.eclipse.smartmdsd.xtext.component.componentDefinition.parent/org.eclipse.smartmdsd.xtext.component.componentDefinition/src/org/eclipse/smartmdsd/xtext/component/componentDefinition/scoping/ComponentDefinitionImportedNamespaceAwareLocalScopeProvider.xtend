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
package org.eclipse.smartmdsd.xtext.component.componentDefinition.scoping

import org.eclipse.xtext.scoping.impl.ImportedNamespaceAwareLocalScopeProvider
import org.eclipse.xtext.scoping.impl.ImportNormalizer
import org.eclipse.xtext.naming.QualifiedName
import org.eclipse.smartmdsd.xtext.service.parameterDefinition.ParameterDefinitionDefaultLib

class ComponentDefinitionImportedNamespaceAwareLocalScopeProvider 
	extends ImportedNamespaceAwareLocalScopeProvider {
		
	override protected getImplicitImports(boolean ignoreCase) {
		newArrayList(
			new ImportNormalizer(
				QualifiedName.create(ParameterDefinitionDefaultLib.DEFAULT_PARAM_PACKAGE),
				true, // wildcard
				ignoreCase
			)
		)
	}	
	
}