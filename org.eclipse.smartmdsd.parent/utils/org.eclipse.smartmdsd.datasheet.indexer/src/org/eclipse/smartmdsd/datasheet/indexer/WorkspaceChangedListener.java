package org.eclipse.smartmdsd.datasheet.indexer;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.runtime.CoreException;

public class WorkspaceChangedListener implements IResourceChangeListener {

	@Override
	public void resourceChanged(IResourceChangeEvent event) {
		if(event.getType() == IResourceChangeEvent.POST_CHANGE) {
			try {
				event.getDelta().accept(new IResourceDeltaVisitor() {
					@Override
					public boolean visit(IResourceDelta delta) throws CoreException {
						IResource resource = (IResource)delta.getResource();
						if(resource instanceof IProject) {
							IProject project = (IProject)resource;
							// if project is being opened, newly created, closed, or removed, then in
							// all cases the datasheet indexer has to be updated for this project.
							// The datasheet indexer will check the isAccessible flag of the project 
							// to either create a new entry or delete an existing entry. The OPEN flag
							// is also set when a new project is created, or an existing project is closed.
							if( (delta.getFlags() & IResourceDelta.OPEN) == IResourceDelta.OPEN ) {
								WorkspaceDatasheetIndexer.getInstance().updateProjectIndex(project);
							} else if(delta.getKind() == IResourceDelta.REMOVED) {
								WorkspaceDatasheetIndexer.getInstance().updateProjectIndex(project);
							}
						}
						return true;
					}
				});
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}
	}

}
