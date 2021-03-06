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
package org.eclipse.smartmdsd.sirius.component.design;

import org.eclipse.emf.common.util.URI;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.smartmdsd.ecore.component.componentDefinition.AbstractComponentElement;
import org.eclipse.smartmdsd.ecore.component.componentDefinition.ComponentDefinition;
import org.eclipse.smartmdsd.ecore.component.componentParameter.ComponentParametersRef;
import org.eclipse.smartmdsd.sirius.utils.xtext.AbstractOpenXtextEditorViewPartAction;


public class OpenComponentParameterEditorAction extends AbstractOpenXtextEditorViewPartAction {

	@Override
	public String getViewPartID() {
		return "org.eclipse.smartmdsd.sirius.component.design.ComponentParamView";
	}

	@Override
	public String getDefaultModelConent(URI xtextResourceUri) {
		String componentName = xtextResourceUri.segment(1);
		return "ComponentParameter "+componentName+"Paremeters component "+componentName+" {\n\n}";
	}

	@Override
	public boolean checkModelConstraints(DDiagramElement diagramElement) {
		for(DDiagramElement currElem: diagramElement.getParentDiagram().getOwnedDiagramElements()) {
			if(currElem.getTarget() instanceof ComponentDefinition) {
				ComponentDefinition component = (ComponentDefinition)currElem.getTarget();
				for(AbstractComponentElement compElem: component.getElements()) {
					if(compElem instanceof ComponentParametersRef) {
						return true;
					}
				}
			}
		}
		return false;
	}

}
