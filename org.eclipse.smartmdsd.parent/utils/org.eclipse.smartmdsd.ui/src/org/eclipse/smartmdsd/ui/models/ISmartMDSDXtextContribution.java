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
package org.eclipse.smartmdsd.ui.models;

import com.google.inject.Injector;

/**
 * This interface can be used to add an Xtext grammar 
 * specification to a certain SmartMDSD project nature 
 * (see also the base interface specification 
 * ISmartMDSDModelContribution).
 * 
 * Implement this interface in your Xtext UI plugin and
 * add the implementation to the extension point named
 * SmartMDSDModelContribution.
 * 
 * @author Alex Lotz
 * 
 * @see ISmartMDSDModelContribution
 */
public interface ISmartMDSDXtextContribution extends ISmartMDSDModelContribution {
	/**
	 * The return value of this method specifies whether this model 
	 * type will be preselected by default when creating a new 
	 * project where this model type is registered.
	 * 
	 * @return true to specify this model as default type (or false otherwise)
	 */
	boolean isDefaultLanguage();
	
	/**
	 * This methid returns the Xtext editor ID.
	 * 
	 * The Xtext editor ID is implemented in the
	 * Xtext UI plugin within the src-gen folder
	 * and the sub-package named "internal".
	 * The class is named using this schema:
	 * "<YourXtextName>Activator"
	 * 
	 * This class has a generated static member with the
	 * respective editor id. A typical implementation
	 * looks as follows:
	 * 
	 * "return MyXtextModelActivator.MY_XTEXT_MODEL;"
	 * 
	 * It is always recommended to use the generated static
	 * member instead of directly returning a plain string.
	 * This ensures that the compiler will detect relevant 
	 * changes in the Xtext grammar implementation.
	 * 
	 * @return the generated Xtext editor ID
	 */
	String getXtextEditorID();
	
	/**
	 * This method returns the Xtext's Injector from the
	 * Xtext UI Module definition and is used to get the
	 * Xtext infrastructure for handling related models.
	 * 
	 * You can implement this interface in your Xtext
	 * UI plugin using the generated class named
	 * "<YourXtextName>Activator". The Activator class
	 * is generated in the src-gen folder (of your Xtext UI Plugin)
	 * within the sub-package named "internal". The typical 
	 * implementation looks like this:
	 * 
	 * "return MyXtextModelActivator.getInstance().getInjector(getXtextEditorID());"
	 * 
	 * Please notice the usage of the previous method getXtextEditorID().
	 * This ensures that the injection remains correct even if the Xtext specification
	 * changes (which will be detected by the compiler).
	 * 
	 * @return the Xtext Module Injector
	 * @see getXtextEditorID()
	 */
	Injector getXtextInjector();
}
