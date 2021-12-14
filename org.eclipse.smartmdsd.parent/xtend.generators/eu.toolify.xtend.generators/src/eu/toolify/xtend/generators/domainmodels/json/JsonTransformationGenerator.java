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
package eu.toolify.xtend.generators.domainmodels.json;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.smartmdsd.xtend.smartsoft.generator.GeneratorHelper;
import org.eclipse.xtext.generator.AbstractGenerator;
import org.eclipse.xtext.generator.IFileSystemAccess2;
import org.eclipse.xtext.generator.IGeneratorContext;

import com.google.inject.Guice;
import com.google.inject.Injector;

import eu.toolify.xtend.generators.ExtendedOutputConfigurationProvider;

public class JsonTransformationGenerator extends AbstractGenerator {
	@Override
	public void doGenerate(Resource resource, IFileSystemAccess2 fsa, IGeneratorContext context) 
	{
		// use google Guice to resolve all injected Xtend classes!
		Injector injector = Guice.createInjector(new JsonTransformationGeneratorModule());
		JsonTransformationGeneratorImpl gen = injector.getInstance(JsonTransformationGeneratorImpl.class);
		
		// use the generator-helper class
		GeneratorHelper genHelper = new GeneratorHelper(injector,resource);
		
		// create the smartsoft folder (if not already created)
		genHelper.createFolder(ExtendedOutputConfigurationProvider.TOOLIFY_OUTPUT);
		genHelper.createFolder(IFileSystemAccess2.DEFAULT_OUTPUT);
		
		// clean-up the "src-gen" directory
		genHelper.invokeDirectoryCleaner(IFileSystemAccess2.DEFAULT_OUTPUT);
		
		// execute generator using a configured FileSystemAccess
		gen.doGenerate(resource, genHelper.getConfiguredFileSystemAccess(), context);
		
		// refresh the source-folder (and its subfolders down to depth 3) for making changes visible
		genHelper.refreshFolder(ExtendedOutputConfigurationProvider.TOOLIFY_OUTPUT, 3);
	}
}
