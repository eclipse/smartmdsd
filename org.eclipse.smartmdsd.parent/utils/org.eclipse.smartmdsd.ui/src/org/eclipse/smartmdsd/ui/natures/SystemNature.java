/********************************************************************************
 * Copyright (c) 2019 Technische Hochschule Ulm, Servicerobotics Ulm, Germany
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
package org.eclipse.smartmdsd.ui.natures;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.cdt.core.CCProjectNature;
import org.eclipse.cdt.managedbuilder.core.BuildException;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IEditorRegistry;
import org.eclipse.ui.PlatformUI;
import org.eclipse.xtext.resource.FileExtensionProvider;
import org.eclipse.smartmdsd.ui.Activator;
import org.eclipse.smartmdsd.ui.preferences.SmartMDSDPreferencesPage;
import org.eclipse.smartmdsd.ui.builder.CDTProjectHelpers;
import org.eclipse.smartmdsd.xtext.system.activityArchitecture.ui.internal.ActivityArchitectureActivator;
import org.eclipse.smartmdsd.xtext.system.causeEffectChain.ui.internal.CauseEffectChainActivator;
import org.eclipse.smartmdsd.xtext.system.componentArchitecture.ui.internal.ComponentArchitectureActivator;
import org.eclipse.smartmdsd.xtext.system.deployment.ui.internal.DeploymentActivator;
import org.eclipse.smartmdsd.xtext.system.systemParameter.ui.internal.SystemParameterActivator;
import org.eclipse.smartmdsd.xtext.system.targetPlatform.ui.internal.TargetPlatformActivator;
import org.eclipse.smartmdsd.xtext.system.systemDatasheet.ui.internal.SystemDatasheetActivator;

import com.google.inject.Injector;

public class SystemNature extends AbstractSmartMDSDNature {
	public static final String NATURE_ID = "org.eclipse.smartmdsd.ui.natures.SystemNature";

	@Override
	public void configure() throws CoreException {
		if(project != null && project.isOpen()) {
			if(!project.hasNature(CCProjectNature.CC_NATURE_ID)) {
				// for backwards compatibility, we will add a CPP settings to the project,
				// just in case it has been created with an old toolchain that did not yet add the CDT settings by default
				WorkspaceJob addCppSettingsJob = new WorkspaceJob("Add C++ project configuration to "+project.getName()) {
					@Override
					public IStatus runInWorkspace(IProgressMonitor monitor) throws CoreException {
						try {
							String activeBuildType = Activator.getDefault().getPreferenceStore().getString(SmartMDSDPreferencesPage.PROP_CMAKE_BUILD_TYPE);
							CDTProjectHelpers.addDefaultSettingsTo(project, activeBuildType, monitor);
						} catch (BuildException e) {
							e.printStackTrace();
							return Status.CANCEL_STATUS;
						}
						return Status.OK_STATUS;
					}
				};
				addCppSettingsJob.setUser(true);
				addCppSettingsJob.schedule();
			} else {
				super.configure();
			}
		}
	}
	
	public enum DSL implements LanguageInterface {
		SystemComponentArchitecture (ComponentArchitectureActivator.ORG_ECLIPSE_SMARTMDSD_XTEXT_SYSTEM_COMPONENTARCHITECTURE_COMPONENTARCHITECTURE),
		SystemActivityArchitecture (ActivityArchitectureActivator.ORG_ECLIPSE_SMARTMDSD_XTEXT_SYSTEM_ACTIVITYARCHITECTURE_ACTIVITYARCHITECTURE),
		CauseEffectChains (CauseEffectChainActivator.ORG_ECLIPSE_SMARTMDSD_XTEXT_SYSTEM_CAUSEEFFECTCHAIN_CAUSEEFFECTCHAIN),
		SystemParameters (SystemParameterActivator.ORG_ECLIPSE_SMARTMDSD_XTEXT_SYSTEM_SYSTEMPARAMETER_SYSTEMPARAMETER),
		SystemDatasheet (SystemDatasheetActivator.ORG_ECLIPSE_SMARTMDSD_XTEXT_SYSTEM_SYSTEMDATASHEET_SYSTEMDATASHEET),
		TargetPlatform (TargetPlatformActivator.ORG_ECLIPSE_SMARTMDSD_XTEXT_SYSTEM_TARGETPLATFORM_TARGETPLATFORM),
		Deployment (DeploymentActivator.ORG_ECLIPSE_SMARTMDSD_XTEXT_SYSTEM_DEPLOYMENT_DEPLOYMENT);
		
		// Reverse-lookup map for getting a language enum from a String languageId
		private static final Map<String, DSL> lookup = new HashMap<String, DSL>();
		static {
	        for (DSL lang : DSL.values()) {
	            lookup.put(lang.getLanguageID(), lang);
	        }
	    }
		// static factory method to construct a LANGUAGES enum element from a full languageID
		public static DSL getFromID(String languageID) {
	        return lookup.get(languageID);
	    }
		
		// private internal value representation as String
		private String languageID;
		// private constructor only used by the enum itself
		private DSL(String languageID) {
			this.languageID = languageID;
		}
		
		
		@Override
		public String getLanguageID() {
			return languageID;
		}
		
		@Override
		public String getKey() {
			return this.name();
		}
		
		@Override
		public Injector getInjector() {
			switch (this) {
			case SystemComponentArchitecture:
				return ComponentArchitectureActivator.getInstance().getInjector(languageID);
			case SystemActivityArchitecture:
				return ActivityArchitectureActivator.getInstance().getInjector(languageID);
			case CauseEffectChains:
				return CauseEffectChainActivator.getInstance().getInjector(languageID);
			case SystemParameters:
				return SystemParameterActivator.getInstance().getInjector(languageID);
			case SystemDatasheet:
				return SystemDatasheetActivator.getInstance().getInjector(languageID);
			case TargetPlatform:
				return TargetPlatformActivator.getInstance().getInjector(languageID);
			case Deployment:
				return DeploymentActivator.getInstance().getInjector(languageID);
			// add further cases when new languages appear
			default:
				return null;	
			}
		}
		
		@Override
		public String getModelFileExtension() {
			return getInjector().getInstance(FileExtensionProvider.class).getPrimaryFileExtension();
		}
		
		// this map collects the Sirius Diagram Viewpoint names for the related languages
		private static final Map<String, String> siriusViewpoints = new HashMap<String, String>();
		static {
			siriusViewpoints.put(SystemComponentArchitecture.languageID, "SystemArchitectureViewpoint");
			siriusViewpoints.put(TargetPlatform.languageID, "TargetPlatformViewpoint");
			siriusViewpoints.put(Deployment.languageID, "DeploymentViewpoint");
		}
		
		@Override
		public String getSiriusViewpointName() {
			return siriusViewpoints.get(languageID);
		}
		
		@Override
		public boolean isDefaultLanguage() {
			switch (this) {
			case SystemComponentArchitecture:
			case TargetPlatform:
			case Deployment:
				return true;
			default:
				return false;
			}
		}
	}

	@Override
	public LanguageInterface getLanguageInterfaceOf(String languageName) {
		return DSL.valueOf(languageName);
	}
	
	@Override
	public LanguageInterface getLanguageInterfaceFrom(IResource modelResource) {
		try {
			IProject project = modelResource.getProject();
			if(project.hasNature(NATURE_ID)) {
				IEditorRegistry editorRegistry = PlatformUI.getWorkbench().getEditorRegistry();
				IEditorDescriptor editor = editorRegistry.getDefaultEditor(modelResource.getName());
				if(editor != null) {
					// this can be null if resource-type is unknown
					return DSL.getFromID(editor.getId());
				}
			}
		} catch (CoreException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public LanguageInterface[] getAllSupportedLanguages() {
		return DSL.values();
	}
	
	@Override
	public List<String> getImportedProjectNatureIds() {
		return Arrays.asList(ComponentNature.NATURE_ID, BehaviorNature.NATURE_ID);
	}
}
