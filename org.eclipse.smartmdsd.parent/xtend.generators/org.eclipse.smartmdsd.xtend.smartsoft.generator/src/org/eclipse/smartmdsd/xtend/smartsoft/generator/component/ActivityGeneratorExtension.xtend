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
package org.eclipse.smartmdsd.xtend.smartsoft.generator.component

import org.eclipse.smartmdsd.ecore.component.componentDefinition.Activity

/**
 * This interface is used to attach ActivityGenerator extensions in a non-intrusive way.
 * The ActivityGenerator Extension-Point is specified in this plugin at "schema/ComponentGeneratorExtension.exsd".
 * 
 * With this extension it is possible to extend the generation of the provided activity's C++ class
 * by external code generators. As the activity's C++ class has a certain structure,
 * this class allows to provide specific code snipplets for the individual code-places
 * of the activity's class implementation (header and source). 
 */
interface ActivityGeneratorExtension {
	/**
	 * This is the minimally required method that needs to be implemented for a specific extension.
	 * The extension name is used to document the code parts that are provided by this extension.
	 * 
	 * @result the String name of this extension
	 */
	def String getExtensionName();
	
	/**
	 * This optional method places specific includes into the <b>header file</b>
	 * of the activity's main class.
	 * 
	 * @param activity the calling Activity definition resource
	 * @result the includes as a character stream 
	 */
	def CharSequence getHeaderIncludes(Activity activity) ''''''
	
	/**
	 * This optional method places specific includes into the <b>source file</b>
	 * of the activity's main class.
	 * 
	 * @param activity the calling Activity definition resource
	 * @result the includes as a character stream 
	 */
	def CharSequence getSourceIncludes(Activity activity) ''''''
	
	/**
	 * This optional method places specific class <b>member definitions</b> into the 
	 * <b>private</b> part of the class body definition (i.e. the class header) of the activity's
	 * main class implementation. Typically, such member definitions
	 * might be member variable definitions and/or additional method specifications.
	 * 
	 * @param activity the calling Activity definition resource
	 * @result the definitions as a character stream 
	 */
	def CharSequence getClassMemberPrivateDefinition(Activity activity) ''''''
	
	/**
	 * This optional method places specific class <b>member definitions</b> into the 
	 * <b>protected</b> part of the class body definition (i.e. the class header) of the activity's
	 * main class implementation. Typically, such member definitions
	 * might be member variable definitions and/or additional method specifications.
	 * 
	 * @param activity the calling Activity definition resource
	 * @result the definitions as a character stream 
	 */
	def CharSequence getClassMemberProtectedDefinition(Activity activity) ''''''
	
	/**
	 * This optional method places specific class <b>member definitions</b> into the 
	 * <b>public</b> part of the class body definition (i.e. the class header) of the activity's
	 * main class implementation. Typically, such member definitions
	 * might be member variable definitions and/or additional method specifications.
	 * 
	 * @param activity the calling Activity definition resource
	 * @result the definitions as a character stream 
	 */
	def CharSequence getClassMemberPublicDefinition(Activity activity) ''''''
	
	/**
	 * This optional method places specific class <b>member construction</b> into the 
	 * class constructor (i.e. in the class source file) of the activity's
	 * main class implementation. Typically, such member construction
	 * consist of calling the member's constructors and/or initializing
	 * default values.
	 * 
	 * @param activity the calling Activity definition resource
	 * @result the constructions as a character stream 
	 */
	def CharSequence getClassMemberConstruction(Activity activity) ''''''
	
	/**
	 * This optional method places specific class <b>member destruction</b> into the 
	 * class destructor (i.e. in the class source file) of the activity's
	 * main class implementation. Typically, such member destruction
	 * consist of deleting the dynamically allocated member memory.
	 * 
	 * @param activity the calling Activity definition resource
	 * @result the destructions as a character stream
	 * @sa getClassMemberConstruction()
	 */
	def CharSequence getClassMemberDestruction(Activity activity) ''''''
	
	/**
	 * This optional method places specific class <b>implementations</b> into the 
	 * the class source file the activity's class implementation. 
	 * These are all the implementations that are not in any other specified
	 * class places and that are placed directly into the source-file (as is).
	 * 
	 * @param activity the calling Activity definition resource
	 * @result the implementations as a character stream 
	 */
	def CharSequence getSourceImplementation(Activity activity) ''''''
	
	/**
	 * This optional method places provided sniplet into the <b>update method</b> 
	 * of the activity that is called at the beginning of every activity's
	 * task cycle and is supposed to update the values of all the input
	 * variables (i.e. class members). 
	 * 
	 * @param activity the calling Activity definition resource
	 * @result the update code as a character stream 
	 */
	def CharSequence getUpdateValuesImplementation(Activity activity) ''''''
	
	/**
	 * This optional method allows adding further includes to the user header file of
	 * the respective Activity. Please note this will only be added if the file
	 * is created for the first time (i.e. it does not yet exist).
	 * 
	 * @param activity the calling Activity definition resource
	 * @result the includes for the user-header file 
	 */
	def CharSequence getUserHeaderIncludes(Activity activity) ''''''
	
	/**
	 * This optional method allows adding further member specifications of the
	 * Activity's user class in the header file. 
	 * Please note this will only be added if the file
	 * is created for the first time (i.e. it does not yet exist).
	 * 
	 * @param activity the calling Activity definition resource
	 * @result the member specifications for the user-header file 
	 */
	def CharSequence getUserClassMemberPublicDefinition(Activity activity) ''''''
			
	/**
	 * This optional method allows adding further member implementations of the
	 * Activity's user class in the source file. 
	 * Please note this will only be added if the file
	 * is created for the first time (i.e. it does not yet exist).
	 * 
	 * @param activity the calling Activity definition resource
	 * @result the member implementations for the user-source file 
	 */	
	def CharSequence getUserSourceImplementation(Activity activity) ''''''
		
}
