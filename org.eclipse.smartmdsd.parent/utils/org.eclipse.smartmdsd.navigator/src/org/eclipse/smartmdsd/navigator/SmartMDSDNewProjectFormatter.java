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
package org.eclipse.smartmdsd.navigator;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.smartmdsd.ui.ISmartMDSDProjectCustomizer;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IViewReference;
import org.eclipse.ui.PlatformUI;

public class SmartMDSDNewProjectFormatter implements ISmartMDSDProjectCustomizer {

	@Override
	public void customizeProject(IProject project, IProgressMonitor monitor) throws CoreException {
		WorkspaceJob job = new WorkspaceJob("Expand newly created project "+project.getName()) {
			@Override
			public IStatus runInWorkspace(IProgressMonitor monitor) throws CoreException {
				project.refreshLocal(IResource.DEPTH_INFINITE, monitor);
				Display.getDefault().syncExec(new Runnable() {
					@Override
					public void run() {
						for(IViewReference ref: PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getViewReferences()) {
							if(ref.getId().contentEquals(SmartMDSDProjectExplorer.VIEWER_ID)) {
								IViewPart view = ref.getView(false);
								if(view instanceof SmartMDSDProjectExplorer) {
									SmartMDSDProjectExplorer explorer = (SmartMDSDProjectExplorer)view;
									explorer.getCommonViewer().expandToLevel(project, 2);
									explorer.getCommonViewer().refresh(project);
								}
							}
						}
					}
				});
				return Status.OK_STATUS;
			}
		};
		job.setRule(project);
		job.schedule();
	}

}
