/********************************************************************************
 * Copyright (c) 2021 Technische Hochschule Ulm, Servicerobotics Ulm, Germany
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
package org.eclipse.smartmdsd.sirius.component.design;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.smartmdsd.ecore.component.componentDefinition.ComponentDefinitionPackage;
import org.eclipse.smartmdsd.ui.models.ISmartMDSDSiriusContribution;
import org.eclipse.smartmdsd.ui.natures.SmartMDSDNatureEnum;

public class SmartMDSDSiriusComponentDefinition implements ISmartMDSDSiriusContribution {
	@Override
	public EPackage getEPackage() {
		return ComponentDefinitionPackage.eINSTANCE;
	}

	@Override
	public SmartMDSDNatureEnum getSmartMDSDNatureEnum() {
		return SmartMDSDNatureEnum.ComponentNature;
	}

	@Override
	public String getViewpointName() {
		return "ComponentDefinitionViewpoint";
	}
}
