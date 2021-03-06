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
package org.eclipse.smartmdsd.ui.factories;

import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.ui.business.api.viewpoint.ViewpointSelection;
import org.eclipse.sirius.ui.business.api.viewpoint.ViewpointSelectionCallback;
import org.eclipse.sirius.ui.business.api.viewpoint.ViewpointSelection.Callback;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

public class SelectViewpointCommand extends RecordingCommand {

    private Session session;

    private final Callback callback;

	final String modelFileExtension;
	final String viewpointName;
    private boolean createNewRepresentations;

    private IProgressMonitor monitor;
	
	public SelectViewpointCommand(Session session, final String modelFileExtension, final String viewpointName, IProgressMonitor monitor) {
		super(session.getTransactionalEditingDomain(), "Select a given viewpoint");
		this.session = session;
		this.callback = new ViewpointSelectionCallback();
		this.modelFileExtension = modelFileExtension;
		this.viewpointName = viewpointName;
		this.createNewRepresentations = true;
		this.monitor = monitor;
	}
	
	public SelectViewpointCommand(Session session, final String modelFileExtension, final String viewpointName, boolean createNewRepresentations, IProgressMonitor monitor) {
		super(session.getTransactionalEditingDomain(), "Select a given viewpoint");
		this.session = session;
		this.callback = new ViewpointSelectionCallback();
		this.modelFileExtension = modelFileExtension;
		this.viewpointName = viewpointName;
		this.createNewRepresentations = createNewRepresentations;
		this.monitor = monitor;
	}

	@Override
	protected void doExecute() {
        if (callback == null || session == null) {
            return;
        }
        try {
            monitor.beginTask("Apply new viewpoints selection...", 1);

            // get all viewpoints according to given file extension
            Set<Viewpoint> viewpoints = ViewpointSelection.getViewpoints(modelFileExtension);

            for (final Viewpoint viewpoint : viewpoints) {
                if(viewpoint.getName().equals(viewpointName)) {
                    try {
                        callback.selectViewpoint(viewpoint, session, createNewRepresentations, monitor);
                    } catch (SecurityException e) {
                        // If permission were not sufficient to select the
                        // viewpoint on the main or one of the referenced
                        // DAnalysis

                        // Provide a meaningful error message to the end-user
                        String errorMessage = "Unable to activate viewpoint '" + viewpoint.getName() + "' because of insufficient rights.";

                        // And re-throw the security exception with the previous
                        // as cause
                        throw new SecurityException(errorMessage, e);
                    }	
                }
            }
        } finally {
            monitor.done();
        }
	}

}
