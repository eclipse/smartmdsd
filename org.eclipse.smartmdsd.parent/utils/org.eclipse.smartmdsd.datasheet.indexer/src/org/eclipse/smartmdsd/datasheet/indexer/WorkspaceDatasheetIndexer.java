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
package org.eclipse.smartmdsd.datasheet.indexer;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;

/**
 * This class implements a Datasheet properties index (i.e. a persistent cache)
 * that provides a central access point for all projects in the workspace
 * to write/read all property-value entries of all projects in the workspace.
 * This indexer works independent of the Java classpath and can be used as a
 * persistent cache in parallel to other indexer types such as e.g. the
 * Xtext indexer. Please note, that this implementation might become rather
 * slow when the number of entries grows.
 * 
 * @author Alex Lotz
 *
 */
public class WorkspaceDatasheetIndexer {
	// the internal metadata index
	private Map<String, DatasheetProjectSettings> index = new HashMap<String, DatasheetProjectSettings>();

	// singleton instance
	private static WorkspaceDatasheetIndexer instance;

	/** returns singleton instance
	 * 
	 * This is a classical implementation of the Singleton design pattern.
	 *  
	 * @return singleton instance
	 */
	public static WorkspaceDatasheetIndexer getInstance() {
		if(instance == null) {
			instance = new WorkspaceDatasheetIndexer();
		}
		return instance;
	}
	
	/** creates the internal index for all currently open projects in the current workspace<br>
	 * 
	 * This method creates the internal project index for all currently open projects in the
	 * workspace. Be aware that this method might be rather time-consuming as it loads all 
	 * project-setting files for all projects in the workspace.
	 */
	public void reloadWorkspaceMetadataIndex() {
		for(IProject project: ResourcesPlugin.getWorkspace().getRoot().getProjects()) {
			updateProjectIndex(project);
		}
		// remove all invalid values
		index.values().removeIf(value -> !value.isValid());
	}
	
	/** creates or cleans-up the index for a given project depending on whether the project is accessible or not
	 * 
	 * If the provided project is accessible, and there is no index yet, then a new index entry is created.
	 * If the given project is not accessible, the index entry is removed thus cleaning up dangling references.
	 * 
	 * @param project the project to be added or removed depending on its isAccessible state 
	 */
	public void updateProjectIndex(IProject project) {
		String projectName = project.getName();
		if(project.isAccessible()) {
			// create a new entry if it is not yet indexed
			if(!index.containsKey(projectName)) {
				index.put(projectName, new DatasheetProjectSettings(project));
			}
		} else {
			// remove index as project is not accessible anymore
			if(index.containsKey(projectName)) {
				index.remove(projectName);
			}
		}
	}

	/**
	 * Clear all entries in the internal index.
	 */
	public void clearWorkspaceMetadataIndex() {
		index.clear();
	}
	
	/**
	 * This method returns a set of all currently indexed property names from all opened projects.
	 * 
	 * @return a set of unique property names
	 */
	public Set<String> getAllIndexedPropertyNames() {
		Set<String> unique_property_names = new HashSet<String>();
		for(DatasheetProjectSettings metadata: index.values()) {
			if(metadata.isValid()) {
				unique_property_names.addAll(metadata.getAllPropertyNames());
			}
		}
		return unique_property_names;
	}
	
	/** returns a list of all matching property values<br>
	 * 
	 * This method iterates the index and returns the 
	 * value of each property that matches the provided
	 * property name. The returned set has no duplicates and
	 * all empty values are removed.
	 * 
	 * @param propertyName the property name to be searched for in the indexer
	 * @return a list of values for all matching properties
	 */
	public Set<String> getAllMatchingPropertyValues(String propertyName) {
		Set<String> all_values = new HashSet<String>();
		for(DatasheetProjectSettings metadata: index.values()) {
			if(metadata.isValid()) {
				all_values.addAll(metadata.getAllPropertyValues(propertyName));
			}
		}
		return all_values;
	}
	
	/** returns a list of all matching property units<br>
	 * 
	 * This method iterates the index and returns the 
	 * unit of each property that matches the provided
	 * property name. The returned set has no duplicates and
	 * all empty units are removed.
	 * 
	 * @param propertyName the property name to be searched for in the indexer
	 * @return a list of units for all matching properties
	 */
	public Set<String> getAllMatchingPropertyUnits(String propertyName) {
		Set<String> all_units = new HashSet<String>();
		for(DatasheetProjectSettings metadata: index.values()) {
			if(metadata.isValid()) {
				all_units.addAll(metadata.getAllPropertyUnits(propertyName));
			}
		}
		return all_units;
	}
	
	/** returns a list of all matching property units<br>
	 * 
	 * This method iterates the index and returns the 
	 * unit of each property that matches the provided
	 * property name. The returned set has no duplicates and
	 * all empty units are removed.
	 * 
	 * @param propertyName the property name to be searched for in the indexer
	 * @return a list of units for all matching properties
	 */
	public Set<String> getAllMatchingPropertySemanticURIs(String propertyName) {
		Set<String> all_uris = new HashSet<String>();
		for(DatasheetProjectSettings metadata: index.values()) {
			if(metadata.isValid()) {
				all_uris.addAll(metadata.getAllPropertySemanticURIs(propertyName));
			}
		}
		return all_uris;
	}
	
	/** returns the project's metadata class<br>
	 * 
	 * This method returns the currently registered project's metadata
	 * entry for a given project name. In case when the current project
	 * is not (yet) indexed, a new index entry is automatically created
	 * and returned instead. This method can return null in case the given
	 * project name does not belong to any loaded projects in the current
	 * workspace.
	 * 
	 * @param projectName the name of the project to return its metadata properties
	 * @return an instance of the matching DatasheetProjectSettings properties (or null)
	 */
	public DatasheetProjectSettings getDatasheetProjectSettings(String projectName) {
		DatasheetProjectSettings metadata = index.get(projectName);
		if(metadata == null) {
			// create new metadata entry (if needed)
			IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);
			if(project.exists() && project.isOpen()) {
				metadata = index.put(projectName, new DatasheetProjectSettings(project));
			}
		}
		return metadata;
	}
	
	public DatasheetProjectSettings getDatasheetSettingsFor(IProject project) {
		DatasheetProjectSettings metadata = null;
		if(project != null && project.isAccessible()) {
			metadata = index.get(project.getName());
			if(metadata == null) {
				metadata = index.put(project.getName(), new DatasheetProjectSettings(project));
			}
		}
		return metadata;
	}
}
