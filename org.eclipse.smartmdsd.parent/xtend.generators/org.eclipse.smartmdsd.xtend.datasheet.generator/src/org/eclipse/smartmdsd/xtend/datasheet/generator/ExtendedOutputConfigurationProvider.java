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
package org.eclipse.smartmdsd.xtend.datasheet.generator;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.xtext.generator.IFileSystemAccess;
import org.eclipse.xtext.generator.IOutputConfigurationProvider;
import org.eclipse.xtext.generator.OutputConfiguration;

public class ExtendedOutputConfigurationProvider implements IOutputConfigurationProvider {
	public final static String PROJECT_ROOT_FOLDER = "PROJECT_ROOT_FOLDER";

	/**
	 * @return a set of {@link OutputConfiguration} available for the generator
	 */
	public Set<OutputConfiguration> getOutputConfigurations() {
		OutputConfiguration defaultOutput = new OutputConfiguration(
				IFileSystemAccess.DEFAULT_OUTPUT);
		defaultOutput.setDescription("Output Folder");
		defaultOutput.setOutputDirectory("./datasheet-gen");
		defaultOutput.setOverrideExistingResources(true);
		defaultOutput.setCreateOutputDirectory(true);
		defaultOutput.setCleanUpDerivedResources(true);
		defaultOutput.setSetDerivedProperty(true);
		
		
		OutputConfiguration rootConfigOutput = new OutputConfiguration(
				PROJECT_ROOT_FOLDER);
		rootConfigOutput.setDescription("Project Root Output Folder");
		rootConfigOutput.setOutputDirectory("./");
		rootConfigOutput.setOverrideExistingResources(false);
		rootConfigOutput.setCreateOutputDirectory(false);
		rootConfigOutput.setCleanUpDerivedResources(false);
		rootConfigOutput.setSetDerivedProperty(false);
		
		HashSet<OutputConfiguration> configurations = new HashSet<OutputConfiguration>();
		configurations.add(defaultOutput);
		configurations.add(rootConfigOutput);
		return configurations;
	}
}