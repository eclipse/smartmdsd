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

/**
 * This interface can be used to add a Sirius graphical diagram
 * specification to a certain SmartMDSD project nature (see also 
 * the base interface specification ISmartMDSDModelContribution).
 * 
 * Implement this interface in your Sirius diagram specification
 * plugin and add the implementation to the extension point
 * named SmartMDSDModelContribution.
 * 
 * @author Alex Lotz
 * 
 * @see ISmartMDSDModelContribution
 */
public interface ISmartMDSDSiriusContribution extends ISmartMDSDModelContribution {
	/**
	 * This method returns the Sirius Viewpoint name
	 * which is used to initialize a new graphical model
	 * representation during project/model creation.
	 * 
	 * @return the viewpoint name
	 */
	String getViewpointName();
}
