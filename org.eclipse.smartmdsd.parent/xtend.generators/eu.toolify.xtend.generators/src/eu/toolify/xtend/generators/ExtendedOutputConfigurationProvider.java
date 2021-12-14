/********************************************************************************
 * Copyright (c) 2021 Toolify Robotics GmbH
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *   Matthias Lutz, Alex Lotz, Dennis Stampfer
 ********************************************************************************/
package eu.toolify.xtend.generators;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.xtext.generator.IFileSystemAccess;
import org.eclipse.xtext.generator.IOutputConfigurationProvider;
import org.eclipse.xtext.generator.OutputConfiguration;

public class ExtendedOutputConfigurationProvider implements IOutputConfigurationProvider {

	public final static String TOOLIFY_OUTPUT = "TOOLIFY_OUTPUT";
	public final static String ROS1_ROOT_OUTPUT = "ROS1_ROOT_OUTPUT";
	public final static String ROS1_SRC_OUTPUT = "ROS1_SRC_OUTPUT";
	public final static String ROS1_SRC_GEN_OUTPUT = "ROS1_SRC_GEN_OUTPUT";
	public final static String REST_ROOT_OUTPUT = "REST_ROOT_OUTPUT";
	public final static String REST_SRC_OUTPUT = "REST_SRC_OUTPUT";
	public final static String REST_SRC_GEN_OUTPUT = "REST_SRC_GEN_OUTPUT";
	public final static String JAVA_ROOT_OUTPUT = "JAVA_ROOT_OUTPUT";
	public final static String JAVA_SRC_OUTPUT = "JAVA_SRC_OUTPUT";
	public final static String JAVA_SRC_GEN_OUTPUT = "JAVA_SRC_GEN_OUTPUT";
	public final static String PROJECT_ROOT_FOLDER = "PROJECT_ROOT_FOLDER";
	public final static String SCRIPTS_GEN_OUTPUT = "SCRIPTS_GEN_OUTPUT";

	/**
	 * @return a set of {@link OutputConfiguration} available for the generator
	 */
	public Set<OutputConfiguration> getOutputConfigurations() {
		OutputConfiguration defaultOutput = new OutputConfiguration(
				IFileSystemAccess.DEFAULT_OUTPUT);
		defaultOutput.setDescription("Output Folder");
		defaultOutput.setOutputDirectory("./toolify/src-gen");
		defaultOutput.setOverrideExistingResources(true);
		defaultOutput.setCreateOutputDirectory(true);
		defaultOutput.setCleanUpDerivedResources(true);
		defaultOutput.setSetDerivedProperty(true);
		
		OutputConfiguration toolifyOutput = new OutputConfiguration(
				TOOLIFY_OUTPUT);
		toolifyOutput.setDescription("Output Folder for Custom Code");
		toolifyOutput.setOutputDirectory("./toolify");
		toolifyOutput.setOverrideExistingResources(false);
		toolifyOutput.setCreateOutputDirectory(true);
		toolifyOutput.setCleanUpDerivedResources(false);
		toolifyOutput.setSetDerivedProperty(false);
		
		OutputConfiguration ros1RootOutput = new OutputConfiguration(
				ROS1_ROOT_OUTPUT);
		ros1RootOutput.setDescription("Output Folder for Custom Code");
		ros1RootOutput.setOutputDirectory("./toolify/ROS1");
		ros1RootOutput.setOverrideExistingResources(false);
		ros1RootOutput.setCreateOutputDirectory(true);
		ros1RootOutput.setCleanUpDerivedResources(false);
		ros1RootOutput.setSetDerivedProperty(false);
		
		OutputConfiguration ros1SrcOutput = new OutputConfiguration(
				ROS1_SRC_OUTPUT);
		ros1SrcOutput.setDescription("Output Folder for Custom Code");
		ros1SrcOutput.setOutputDirectory("./toolify/ROS1/src");
		ros1SrcOutput.setOverrideExistingResources(false);
		ros1SrcOutput.setCreateOutputDirectory(true);
		ros1SrcOutput.setCleanUpDerivedResources(false);
		ros1SrcOutput.setSetDerivedProperty(false);
		
		OutputConfiguration ros1SrcGenOutput = new OutputConfiguration(
				ROS1_SRC_GEN_OUTPUT);
		ros1SrcGenOutput.setDescription("Output Folder");
		ros1SrcGenOutput.setOutputDirectory("./toolify/ROS1/src-gen");
		ros1SrcGenOutput.setOverrideExistingResources(true);
		ros1SrcGenOutput.setCreateOutputDirectory(true);
		ros1SrcGenOutput.setCleanUpDerivedResources(true);
		ros1SrcGenOutput.setSetDerivedProperty(true);
		
		OutputConfiguration restRootOutput = new OutputConfiguration(
				REST_ROOT_OUTPUT);
		restRootOutput.setDescription("Output Folder for Custom Code");
		restRootOutput.setOutputDirectory("./toolify/REST");
		restRootOutput.setOverrideExistingResources(false);
		restRootOutput.setCreateOutputDirectory(true);
		restRootOutput.setCleanUpDerivedResources(false);
		restRootOutput.setSetDerivedProperty(false);
		
		OutputConfiguration restSrcOutput = new OutputConfiguration(
				REST_SRC_OUTPUT);
		restSrcOutput.setDescription("Output Folder for Custom Code");
		restSrcOutput.setOutputDirectory("./toolify/REST/src");
		restSrcOutput.setOverrideExistingResources(false);
		restSrcOutput.setCreateOutputDirectory(true);
		restSrcOutput.setCleanUpDerivedResources(false);
		restSrcOutput.setSetDerivedProperty(false);

		OutputConfiguration restSrcGenOutput = new OutputConfiguration(
				REST_SRC_GEN_OUTPUT);
		restSrcGenOutput.setDescription("Output Folder");
		restSrcGenOutput.setOutputDirectory("./toolify/REST/src-gen");
		restSrcGenOutput.setOverrideExistingResources(true);
		restSrcGenOutput.setCreateOutputDirectory(true);
		restSrcGenOutput.setCleanUpDerivedResources(true);
		restSrcGenOutput.setSetDerivedProperty(true);
		
		OutputConfiguration javaRootOutput = new OutputConfiguration(
				JAVA_ROOT_OUTPUT);
		javaRootOutput.setDescription("Output Folder for Custom Code");
		javaRootOutput.setOutputDirectory("./toolify/java");
		javaRootOutput.setOverrideExistingResources(false);
		javaRootOutput.setCreateOutputDirectory(true);
		javaRootOutput.setCleanUpDerivedResources(false);
		javaRootOutput.setSetDerivedProperty(false);
		
		OutputConfiguration javaSrcOutput = new OutputConfiguration(
				JAVA_SRC_OUTPUT);
		javaSrcOutput.setDescription("Output Folder for Custom Code");
		javaSrcOutput.setOutputDirectory("./toolify/java/src");
		javaSrcOutput.setOverrideExistingResources(false);
		javaSrcOutput.setCreateOutputDirectory(true);
		javaSrcOutput.setCleanUpDerivedResources(false);
		javaSrcOutput.setSetDerivedProperty(false);
		
		OutputConfiguration javaSrcGenOutput = new OutputConfiguration(
				JAVA_SRC_GEN_OUTPUT);
		javaSrcGenOutput.setDescription("Output Folder");
		javaSrcGenOutput.setOutputDirectory("./toolify/java/src-gen");
		javaSrcGenOutput.setOverrideExistingResources(true);
		javaSrcGenOutput.setCreateOutputDirectory(true);
		javaSrcGenOutput.setCleanUpDerivedResources(true);
		javaSrcGenOutput.setSetDerivedProperty(true);
		
		OutputConfiguration rootConfigOutput = new OutputConfiguration(
				PROJECT_ROOT_FOLDER);
		rootConfigOutput.setDescription("Project Root Output Folder");
		rootConfigOutput.setOutputDirectory("./");
		rootConfigOutput.setOverrideExistingResources(false);
		rootConfigOutput.setCreateOutputDirectory(false);
		rootConfigOutput.setCleanUpDerivedResources(false);
		rootConfigOutput.setSetDerivedProperty(false);

		OutputConfiguration scriptsGenOutput = new OutputConfiguration(
				SCRIPTS_GEN_OUTPUT);
		scriptsGenOutput.setDescription("Output Folder");
		scriptsGenOutput.setOutputDirectory("./toolify/scripts-gen");
		scriptsGenOutput.setOverrideExistingResources(true);
		scriptsGenOutput.setCreateOutputDirectory(true);
		scriptsGenOutput.setCleanUpDerivedResources(true);
		scriptsGenOutput.setSetDerivedProperty(true);
		
		HashSet<OutputConfiguration> configurations = new HashSet<OutputConfiguration>();
		configurations.add(defaultOutput);
		configurations.add(toolifyOutput);
		configurations.add(ros1RootOutput);
		configurations.add(ros1SrcOutput);
		configurations.add(ros1SrcGenOutput);
		configurations.add(restRootOutput);
		configurations.add(restSrcOutput);
		configurations.add(restSrcGenOutput);
		configurations.add(javaRootOutput);
		configurations.add(javaSrcOutput);
		configurations.add(javaSrcGenOutput);
		configurations.add(rootConfigOutput);
		configurations.add(scriptsGenOutput);
		return configurations;
	}

}
