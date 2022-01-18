/********************************************************************************
 * Copyright (c) 2022 Technische Hochschule Ulm, Servicerobotics Ulm, Germany
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
package org.eclipse.smartmdsd.ui;

import java.util.Collection;

/**
 * This interface can be implemented to extend the default SmartMDSD Project import types.
 * 
 * @author Alex Lotz
 */
public interface ISmartMDSDProjectImportContribution {
	/**
	 * Get the SmartMDSD project nature (e.g. ComponentNature.NATURE_ID)
	 * whose import relation should be extended.
	 * 
	 * @return the parent SmartMDSD project nature ID
	 */
	String getParentProjectNature();
	
	/**
	 * Get the list of related project nature IDs that will be used to
	 * filter related project types in the project import wizard.
	 * 
	 * @return a list of related project nature IDs for filtering imported project types
	 */
	Collection<String> getImportedProjectNatures();
}
