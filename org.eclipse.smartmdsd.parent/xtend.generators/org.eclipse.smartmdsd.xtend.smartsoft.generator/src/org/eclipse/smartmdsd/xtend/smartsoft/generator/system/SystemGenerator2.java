/********************************************************************************
 * Copyright (c) 2018 Technische Hochschule Ulm, Servicerobotics Ulm, Germany
 * headed by Prof. Dr. Christian Schlegel
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *   Alex Lotz, Matthias Lutz, Dennis Stampfer
 ********************************************************************************/
package org.eclipse.smartmdsd.xtend.smartsoft.generator.system;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.generator.AbstractGenerator;
import org.eclipse.xtext.generator.IFileSystemAccess2;
import org.eclipse.xtext.generator.IGeneratorContext;
import org.eclipse.smartmdsd.xtend.smartsoft.generator.ExtendedOutputConfigurationProvider;
import org.eclipse.smartmdsd.xtend.smartsoft.generator.GeneratorHelper;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class SystemGenerator2 extends AbstractGenerator {

	@Override
	public void doGenerate(Resource resource, IFileSystemAccess2 fsa, IGeneratorContext context) 
	{
		// use google Guice to resolve all injected Xtend classes!
		Injector injector = Guice.createInjector(new SystemGenerator2Module());
		SystemGenerator2Impl gen = injector.getInstance(SystemGenerator2Impl.class);
		
		// use the generator-helper class
		GeneratorHelper genHelper = new GeneratorHelper(injector,resource);
		
		// create the smartsoft folder (if not already created)
		genHelper.createFolder(ExtendedOutputConfigurationProvider.SMARTSOFT_OUTPUT);
		genHelper.createFolder(IFileSystemAccess2.DEFAULT_OUTPUT);
		
		// clean-up the "smartsoft/src-gen/params" directory
		genHelper.invokeDirectoryCleaner(ExtendedOutputConfigurationProvider.SRC_GEN_SYS_CONFIG);
		
		// execute generator using a configured FileSystemAccess
		gen.doGenerate(resource, genHelper.getConfiguredFileSystemAccess(), context);
		
		// refresh the source-folder (and its subfolders down to depth 3) for making changes visible
		genHelper.refreshFolder(ExtendedOutputConfigurationProvider.SMARTSOFT_OUTPUT, 3);
	}

}
