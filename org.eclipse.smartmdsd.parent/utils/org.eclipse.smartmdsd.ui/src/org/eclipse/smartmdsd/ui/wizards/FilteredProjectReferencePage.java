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
package org.eclipse.smartmdsd.ui.wizards;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.ui.dialogs.WizardNewProjectReferencePage;
import org.eclipse.smartmdsd.ui.natures.SmartMDSDNatureEnum;

public class FilteredProjectReferencePage extends WizardNewProjectReferencePage {
	private SmartMDSDNatureEnum currentNatureEnum;
	
	public FilteredProjectReferencePage(String pageName, SmartMDSDNatureEnum currentNatureEnum) {
		super(pageName);
		this.currentNatureEnum = currentNatureEnum;
	}

    /**
     * Returns a content provider for the filtered project-list potentially to be referenced. 
     * It will return all projects in the workspace that have a certain project nature.
     *
     * @return the content provider
     */
	@Override
	protected IStructuredContentProvider getContentProvider() {
		return new SmartMDSDFilteredProjectImportContentProvider(currentNatureEnum);
    }
}
