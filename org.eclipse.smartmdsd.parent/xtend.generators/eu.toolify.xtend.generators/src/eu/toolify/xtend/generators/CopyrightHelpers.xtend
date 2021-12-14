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
package eu.toolify.xtend.generators

import org.eclipse.core.runtime.Platform

class CopyrightHelpers {
	def private String getVersion() {
		return Platform.getBundle("eu.toolify.xtend.generators").version.toString
	}
	
	def String getToolchainVersionFileString()
	'''
	«copyright»
	
	// Generated with Toolify Robotics GmbH extension version «version»
	'''
	
	
	def String copyrightHelper() '''
		//-----------------------------------------------------------------------------------
		// Code generated by the Toolify Robotics GmbH extension for the SmartMDSD Toolchain 
		// 
		// Toolify Robotics GmbH
		// Weinbergweg 216
		// 89075 Ulm 
		//
		// Information about the SmartSoft MDSD Toolchain is available at:
		// www.servicerobotik-ulm.de
		//
	'''

	// returns copyright for source files
	def String getCopyright() '''
		«copyrightHelper()»
		// Please do not modify this file. It will be re-generated
		// running the code generator.
		//-----------------------------------------------------------------------------------
	'''

	// returns copyright for source files which are generated once
	def String getCopyrightWriteOnce() '''
		«copyrightHelper()» 
		// This file is generated once. Modify this file to your needs. 
		// If you want the toolchain to re-generate this file, please 
		// delete it before running the code generator.
		//-----------------------------------------------------------------------------------
	'''
	
	
	// returns copyright for files which need #
	def String getCopyrightHash() {
		return getCopyright().replaceAll("//","#");
	}
	
	
	// returns copyright for files which need # and are generated once
	def String getCopyrightWriteOnceHash() {
		getCopyrightWriteOnce().replaceAll("//","#");
	}
}