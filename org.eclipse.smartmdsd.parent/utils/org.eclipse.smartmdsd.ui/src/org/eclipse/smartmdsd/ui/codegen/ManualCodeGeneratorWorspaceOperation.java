/********************************************************************************
 * Copyright (c) 2021 Technische Hochschule Ulm, Servicerobotics Ulm, Germany
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
package org.eclipse.smartmdsd.ui.codegen;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.smartmdsd.ui.Activator;
import org.eclipse.smartmdsd.ui.factories.JavaProjectFactory;
import org.eclipse.smartmdsd.ui.models.SmartMDSDModelingLanguage;
import org.eclipse.smartmdsd.ui.natures.AbstractSmartMDSDNature;
import org.eclipse.smartmdsd.ui.natures.SmartMDSDNatureHelpers;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.IConsoleManager;
import org.eclipse.ui.console.MessageConsole;
import org.eclipse.ui.console.MessageConsoleStream;
import org.eclipse.xtext.diagnostics.Severity;
import org.eclipse.xtext.generator.GeneratorDelegate;
import org.eclipse.xtext.generator.JavaIoFileSystemAccess;
import org.eclipse.xtext.resource.XtextResourceSet;
import org.eclipse.xtext.util.CancelIndicator;
import org.eclipse.xtext.validation.CheckMode;
import org.eclipse.xtext.validation.IResourceValidator;
import org.eclipse.xtext.validation.Issue;
import org.osgi.framework.Bundle;

import com.google.inject.Injector;

public class ManualCodeGeneratorWorspaceOperation extends WorkspaceModifyOperation {
	private IResource resource;
	private MessageConsole messageConsole;
	
	public static final String CONSOLE_NAME = "SmarMDSD Code-Generator";
	
	public ManualCodeGeneratorWorspaceOperation(IResource resource) {
		this.resource = resource;
		this.messageConsole = findCodeGeneratorConsole();
	}
	
	private MessageConsole findCodeGeneratorConsole() {
		ConsolePlugin plugin = ConsolePlugin.getDefault();
		IConsoleManager consoleManager = plugin.getConsoleManager();
		IConsole[] existing = consoleManager.getConsoles();
		for (int i = 0; i < existing.length; i++) {
			if (existing[i].getName().equals(CONSOLE_NAME))
				return (MessageConsole) existing[i];
		}
		
		// no console found, so create a new one
		Bundle bundle = Platform.getBundle(Activator.PLUGIN_ID);
		ImageDescriptor codeGeneratorIcon = ImageDescriptor.createFromURL(bundle.getEntry("icons/manual-gear.png"));
		MessageConsole codeGeneratorConsole = new MessageConsole(CONSOLE_NAME, codeGeneratorIcon);
		consoleManager.addConsoles(new IConsole[]{codeGeneratorConsole});
		return codeGeneratorConsole;
	}
	
	@Override
	protected void execute(IProgressMonitor monitor)
			throws CoreException, InvocationTargetException, InterruptedException {
		final AbstractSmartMDSDNature smartMDSDnature = SmartMDSDNatureHelpers.getFirstSmartMDSDNatureFrom(resource.getProject());
		if(smartMDSDnature == null) {
			// this project does not have a valid SmartMDSDNature (we skip building it)
			return;
		}
		
		// use a message console to print-out info messages
		messageConsole.clearConsole();
		
		// bring the console window tab to foreground
		ConsolePlugin.getDefault().getConsoleManager().showConsoleView(messageConsole);
		
		// create new console-message stream
		MessageConsoleStream out = messageConsole.newMessageStream();
		out.println("Run SmartMDSD code generator for: "+resource.getName());
		
		// get a list of all files (potentially model-files) located in the "model" subfolder of the current project
		final List<IResource> modelFiles = JavaProjectFactory.getContainingJavaSources(resource);
		final int numberModels = modelFiles.size();

		// convert to SubMonitor and set total number of work units
		SubMonitor subMonitor = SubMonitor.convert(monitor, numberModels*10);
		
		for(IResource modelResource: modelFiles) {
			if (monitor.isCanceled()) {
				// cancel requested -> stop generating code
				out.print("code genearion cancelled!");
				return;
			}
			
			// advance the submonitor by 10 ticks
			subMonitor.split(10);

			// the default editor for a specific model file will give us the associated language-ID
			SmartMDSDModelingLanguage language = smartMDSDnature.getLanguageFrom(modelResource);
			// the editor can be null if the file extension is unknown
			if(language != null) {
				Injector injector = language.getInjector();
				// this XtextResourceSet can parse and load our model file
				XtextResourceSet resourceSet = injector.getInstance(XtextResourceSet.class);
				
				// load the main (i.e. currently used) model into an EMF resource using the related XtextResourceSet
				String modelUriString = "platform:/resource/" + resource.getProject().getName() + "/" + modelResource.getProjectRelativePath();
				out.println("Load model: "+modelResource.getName());
				Resource resource = resourceSet.getResource(URI.createURI(modelUriString), true);
				
				if(resource.isLoaded()) {
			    	// inject validator and validate the main resource 
			    	IResourceValidator validator = injector.getInstance(IResourceValidator.class);
			    	boolean hasErrors = false;
			    	List<Issue> issues = validator.validate(resource, CheckMode.ALL, CancelIndicator.NullImpl);
		    		for(Issue issue: issues) {
		    			if(issue.getSeverity() == Severity.ERROR) {
		    				hasErrors = true;
		    				out.println("ERROR: "+issue.getMessage());
		    			} else if(issue.getSeverity() == Severity.WARNING) {
		    				out.println("WARNING: "+issue.getMessage());
		    			}
		    		}
		    		
		    		// check if meanwhile cancel has been requested
		    		if (monitor.isCanceled()) {
						// cancel requested -> stop generating code
		    			out.print("code genearion cancelled!");
						return;
					}
			    	
		    		if(hasErrors == true) {
		    			out.println("code generation skipped due to errors.");
		    		} else {
		    			// Use the language-specific code-generator
		    			GeneratorDelegate generator = injector.getInstance(GeneratorDelegate.class);
		    			JavaIoFileSystemAccess fsa = injector.getInstance(JavaIoFileSystemAccess.class);
		    			// finally call code generator for our current resource
		    			out.println("Run code generator...");
		    			generator.doGenerate(resource, fsa);
		    			out.println("done!");
		    		}
				} // end if(resource.isLoaded())
			} // end if(editor != null) {
		} // end for(modelResource: modelFiles)
	}
}
