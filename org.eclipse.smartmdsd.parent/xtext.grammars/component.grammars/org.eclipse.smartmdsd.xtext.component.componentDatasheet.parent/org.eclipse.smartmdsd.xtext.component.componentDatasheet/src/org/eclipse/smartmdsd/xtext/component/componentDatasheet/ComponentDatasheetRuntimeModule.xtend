/********************************************************************************
 * Copyright (c) 2019 Technische Hochschule Ulm, Servicerobotics Ulm, Germany
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
package org.eclipse.smartmdsd.xtext.component.componentDatasheet

import org.eclipse.xtext.naming.IQualifiedNameProvider

/**
 * Use this class to register components to be used at runtime / without the Equinox extension registry.
 */
class ComponentDatasheetRuntimeModule extends AbstractComponentDatasheetRuntimeModule {
	override Class<? extends IQualifiedNameProvider> bindIQualifiedNameProvider() {
		return ComponentDatasheetQNameProvider
	}
}
