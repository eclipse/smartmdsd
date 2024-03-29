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

import org.eclipse.smartmdsd.ui.wizards.WizardNewSmartMDSDModel;
import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;
import org.eclipse.ui.console.IConsoleConstants;

public abstract class AbstractSmartMDSDPerspective implements IPerspectiveFactory, ISmartMDSDPerspectiveCustomization {

	public static final String NAVIGATOR_VIEW_ID = "org.eclipse.smartmdsd.navigator.view";
	
	@Override
	public void createInitialLayout(IPageLayout layout) {
		defineBaseActions(layout);
		defineBaseLayout(layout);
	}

    /**
     * add items and actions set to the window
     * 
     * @param layout
     *            layout of the perspective
     */
    protected void defineBaseActions(final IPageLayout layout) {
        // wizards
        layout.addNewWizardShortcut("org.eclipse.ui.wizards.new.folder"); //$NON-NLS-1$ 
        layout.addNewWizardShortcut("org.eclipse.ui.wizards.new.file"); //$NON-NLS-1$

        layout.addNewWizardShortcut(WizardNewSmartMDSDModel.WIZARD_ID);
        
        // show view shortcuts
        layout.addShowViewShortcut(NAVIGATOR_VIEW_ID);
        layout.addShowViewShortcut(IPageLayout.ID_PROP_SHEET);
        layout.addShowViewShortcut(IPageLayout.ID_OUTLINE);
        layout.addShowViewShortcut(IPageLayout.ID_PROBLEM_VIEW);
        
        addCustomActions(layout);
        
        for(ISmartMDSDPerspectiveCustomization external_customization: SmartMDSDPerspectiveCustomizationsRegistry.getCustomizationsFor(getSmartMDSDPerspective())) {
        	external_customization.addCustomActions(layout);
        }
    }

    /**
     * add views to the layout
     * 
     * @param layout
     *            layout of the perspective
     */
    protected void defineBaseLayout(final IPageLayout layout) {
        final String editorArea = layout.getEditorArea();
        layout.addView(NAVIGATOR_VIEW_ID, IPageLayout.LEFT, (float) 0.25, editorArea);
        layout.addView(IPageLayout.ID_OUTLINE, IPageLayout.BOTTOM, 0.50f, NAVIGATOR_VIEW_ID);

        adjustBaseLayout(layout);
        
        for(ISmartMDSDPerspectiveCustomization external_customization: SmartMDSDPerspectiveCustomizationsRegistry.getCustomizationsFor(getSmartMDSDPerspective())) {
        	external_customization.adjustBaseLayout(layout);
        }
        
        // Place problem, properties and advance views to bottom of editor area.
        final IFolderLayout bottom = layout.createFolder("bottom", IPageLayout.BOTTOM, (float) 0.65, editorArea); //$NON-NLS-1$
        bottom.addView(IPageLayout.ID_PROP_SHEET);
        addBottomViews(bottom);
        bottom.addView(IPageLayout.ID_PROBLEM_VIEW);
        bottom.addView(IConsoleConstants.ID_CONSOLE_VIEW);
        bottom.addView(IPageLayout.ID_PROGRESS_VIEW);
        
        for(ISmartMDSDPerspectiveCustomization external_customization: SmartMDSDPerspectiveCustomizationsRegistry.getCustomizationsFor(getSmartMDSDPerspective())) {
        	external_customization.addBottomViews(bottom);
        }
    }
}
