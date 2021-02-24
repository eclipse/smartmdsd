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
package org.eclipse.smartmdsd.ui.factories;

import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

/**
 * This interface can be used to provide a custom
 * default model creation factory that is registered
 * over the extension point SmartMDSDModelFactoryEP.
 * 
 * @author Alex Lotz
 */
public interface ISmartMDSDModelFactory {
	/**
	 * This method returns the EMF-Package (EPackage).
	 * Each Ecore meta-model generates a class named
	 * "<MetamodelName>Package", which specifies
	 * the package name, the package URI and other
	 * meta information related to identifying the
	 * specific Ecore meta-model.
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
	 * This method returns a list of related EPackes
	 * that this model uses specifically for 
	 * creating a default model (see also the 
	 * createDefaultModel() method). More specifically,
	 * if the creation of the default model requires
	 * a reference to another existing model, then the
	 * EPackage of that model has to be returned 
	 * in this method. If the default model does not
	 * depend on any other models, then a null or an
	 * empty list can be returned as well.
	 * 
	 * @see createDefaultModel()
	 *  
	 * @return a list of related EPackages or null
	 */
	Collection<EPackage> getParentEPackages();
	
	/**
	 * This is the actual factory method for creating a
	 * default model. You can use the generated class
	 * named "<MetamodelName>Foctory" with its methods
	 * to create a valid initial default model. A default
	 * model should consist of the typical elements that
	 * are often created for new models, but it does
	 * not have to be too detailed, as the details will
	 * be modeled individually. At the minimum, the model's
	 * root object should be created. The typical 
	 * implementation looks like this:
	 * 
	 * MyRootObject object = MyModelFactory.eINSTANCE.createMyRootObject();
	 * object.setName("SomeDefaultName");
	 * ...
	 * return object;
	 * 
	 * If the creation of the default model requires other
	 * models as reference, then these external models
	 * will be automatically loaded in the background and
	 * provided in the parameter "parentModels".
	 * You can specify the required model-types by
	 * returning their respective EPackages in the method
	 * getParentEPackages(). Please notice, the parent models
	 * have to be located in the same project as the current
	 * model (this is the default search strategy).
	 * 
	 * @see getParentEPackages()
	 * 
	 * @param parentModels the related models required for the creation of the default model
	 * @return the default model's root object
	 */
	EObject createDefaultModel(Collection<EObject> parentModels);
}
