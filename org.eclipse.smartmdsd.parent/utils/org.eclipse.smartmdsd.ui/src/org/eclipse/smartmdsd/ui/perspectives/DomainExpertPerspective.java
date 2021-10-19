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
package org.eclipse.smartmdsd.ui.perspectives;

import org.eclipse.smartmdsd.ui.wizards.WizardNewDomainModelsProject;
import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;

public class DomainExpertPerspective extends AbstractSmartMDSDPerspective {

	public static final String PERSPECTIVE_ID = "org.eclipse.smartmdsd.ui.perspectives.domainExpert";
	
	@Override
	public void addCustomActions(IPageLayout layout) {
		layout.addNewWizardShortcut(WizardNewDomainModelsProject.WIZARD_ID);
	}

	@Override
	public void adjustBaseLayout(IPageLayout layout) {
		
	}

	@Override
	public void addBottomViews(IFolderLayout bottom) {
		
	}

	@Override
	public SmartMDSDPerspectiveEnum getSmartMDSDPerspective() {
		return SmartMDSDPerspectiveEnum.getFromID(PERSPECTIVE_ID);
	}
}
