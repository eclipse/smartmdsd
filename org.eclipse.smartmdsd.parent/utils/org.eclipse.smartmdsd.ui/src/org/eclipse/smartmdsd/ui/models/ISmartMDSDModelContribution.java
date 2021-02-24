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

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.smartmdsd.ui.natures.SmartMDSDNatureEnum;

/**
 * This interface links an Ecore Metamodel with
 * a related SmartMDSD project nature. In this way, all
 * refinements of this interface (such as the Xtext and Sirius
 * contributions) will have to specify the metamodel that
 * they are using and to which project nature they are
 * contributing).
 *  
 * @author Alex Lotz
 */
public interface ISmartMDSDModelContribution {
	/**
	 * This method returns the EMF-Package (EPackage).
	 * Each Ecore metamodel generates a class named
	 * "<MetamodelName>Package", which specifies
	 * the package name, the package URI and other
	 * meta information related to identifying the
	 * specific Ecore metamodel.
	 * 
	 * You can easily implement this method by 
	 * returning the static eINSTANCE member from your 
	 * generated <MetamodelName>Package class.
	 * A typical implementation looks like this: 
	 * 
	 * "return MyModelPackage.eINSTANCE;"
	 * 
	 * @return the EMF Package
	 */
	EPackage getEPackage();
	
	/**
	 * This method returns the SmartMDSD project nature enum
	 * with which this model type will be linked.
	 * In other words, models and project types are linked by 
	 * linking the metamodel and the project nature within
	 * the implementation of this interface.
	 * 
	 * @return the SmartMDSD project nature enum value
	 */
	SmartMDSDNatureEnum getSmartMDSDNatureEnum();
}
