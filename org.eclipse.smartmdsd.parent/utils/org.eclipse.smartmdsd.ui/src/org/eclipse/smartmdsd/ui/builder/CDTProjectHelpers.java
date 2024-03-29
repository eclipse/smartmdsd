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
package org.eclipse.smartmdsd.ui.builder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.cdt.core.CCProjectNature;
import org.eclipse.cdt.core.CCorePlugin;
import org.eclipse.cdt.core.CProjectNature;
import org.eclipse.cdt.core.model.CoreModel;
import org.eclipse.cdt.core.model.ICProject;
import org.eclipse.cdt.core.settings.model.ACSettingEntry;
import org.eclipse.cdt.core.settings.model.CIncludePathEntry;
import org.eclipse.cdt.core.settings.model.ICConfigurationDescription;
import org.eclipse.cdt.core.settings.model.ICFolderDescription;
import org.eclipse.cdt.core.settings.model.ICLanguageSetting;
import org.eclipse.cdt.core.settings.model.ICLanguageSettingEntry;
import org.eclipse.cdt.core.settings.model.ICProjectDescription;
import org.eclipse.cdt.core.settings.model.ICProjectDescriptionManager;
import org.eclipse.cdt.core.settings.model.ICSettingEntry;
import org.eclipse.cdt.managedbuilder.buildproperties.IBuildPropertyValue;
import org.eclipse.cdt.managedbuilder.core.BuildException;
import org.eclipse.cdt.managedbuilder.core.IBuilder;
import org.eclipse.cdt.managedbuilder.core.IConfiguration;
import org.eclipse.cdt.managedbuilder.core.IManagedBuildInfo;
import org.eclipse.cdt.managedbuilder.core.IManagedProject;
import org.eclipse.cdt.managedbuilder.core.IToolChain;
import org.eclipse.cdt.managedbuilder.core.ManagedBuildManager;
import org.eclipse.cdt.ui.CUIPlugin;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubMonitor;

public class CDTProjectHelpers {
	// code inspired from http://cdt-devel-faq.wikidot.com/
	public static List<IConfiguration> getSupportedConfigurations() {
		List<IConfiguration> cfgs = new ArrayList<IConfiguration>();
		for (IConfiguration cfg : ManagedBuildManager.getExtensionConfigurations()) {
		    IToolChain tc = cfg.getToolChain();
		    if (tc != null && ManagedBuildManager.isPlatformOk(tc) && tc.isSupported()) {
		        IBuildPropertyValue value = cfg.getBuildArtefactType();
		        if (value != null) {
		            if (ManagedBuildManager.BUILD_ARTEFACT_TYPE_PROPERTY_EXE.equals(value.getId())) {
		                cfgs.add(cfg);
		            }
		        }
		    }
		}
		return cfgs;
	}
	
    public static boolean isSmartMDSDBuilderActiveFor(IProject project) {
    	boolean hasSmartMDSDBuilder = false;
		IManagedBuildInfo buildInfo = ManagedBuildManager.getBuildInfo(project);
		IConfiguration configs[] = buildInfo.getManagedProject().getConfigurations();
		for(IConfiguration config: configs) {
			// we check for the name instead of ID as the ID was not set correctly for older projects
			if(config.getBuilder().getName().startsWith("SmartMDSD")) {
				hasSmartMDSDBuilder = true;
			}
		}
    	return hasSmartMDSDBuilder;
    }
	
    public static IBuilder getSmartMDSDBuilderOf(IProject project) {
		IManagedBuildInfo buildInfo = ManagedBuildManager.getBuildInfo(project);
		IConfiguration configs[] = buildInfo.getManagedProject().getConfigurations();
		for(IConfiguration config: configs) {
			// we check for the name instead of ID as the ID was not set correctly for older projects
			if(config.getBuilder().getName().startsWith("SmartMDSD")) {
				return config.getBuilder();
			}
		}
		return null;
    }
    
	public static IBuilder getSmartMDSDBuilder() {
	    for(IBuilder builder: ManagedBuildManager.getRealBuilders()) {
	    	if(builder.getId().equals(SmartMDSDManagedBuildConfigurator.BUILDER_ID)) {
	    		return builder;
	    	}
	    }
		return null;
	}
	
	public static IBuilder getDefaultGNUBuilder() {
		for(IBuilder builder: ManagedBuildManager.getRealBuilders()) {
			// we use the first CDT managed builder that appears in the real-builders list
			if(builder.getId().startsWith("cdt.managedbuild.")) {
				return builder;
			}
		}
		return null;
	}
	
	// code inspired from http://cdt-devel-faq.wikidot.com/
	public static void addDefaultSettingsTo(IProject project, String activeBuildType, IProgressMonitor monitor) throws CoreException, BuildException {
		SubMonitor subMonitor = SubMonitor.convert(monitor, "Set default CDT Settings", 100);
		CProjectNature.addCNature(project, subMonitor.split(20));
		CCProjectNature.addCCNature(project, subMonitor.split(20));
		ICProjectDescription cProjectDescription = CoreModel.getDefault().createProjectDescription(project, false);

		// create build info and managed project
		List<IConfiguration> supportedConfigurations = getSupportedConfigurations();
		ManagedBuildManager.createBuildInfo(project);
		IManagedProject managedProject = ManagedBuildManager.createManagedProject(project,
				supportedConfigurations.get(0).getProjectType());

		// the registered SmartMDSD builder
		IBuilder smartmdsdBuilder = getSmartMDSDBuilder();

		for (IConfiguration configuration : supportedConfigurations) {
			subMonitor.split(20);
			// builder-suffix is either "debug" or "release"
			String builderSuffix = configuration.getName().toLowerCase();
			// builder ID is constructed from the builder base ID and the builder suffix
			String builderID = ManagedBuildManager.calculateChildId(SmartMDSDManagedBuildConfigurator.BUILDER_ID, builderSuffix);

			// clone the configuration and set the artifact name
			IConfiguration configurationClone = managedProject.createConfiguration(configuration, builderID);
			configurationClone.setArtifactName("${ProjName}");

			if (smartmdsdBuilder != null) {
				// configure smartmdsd builder
				configurationClone.changeBuilder(smartmdsdBuilder, builderID, smartmdsdBuilder.getName());
			}

			// activate parallel build
			configurationClone.getEditableBuilder().setParallelBuildOn(true);

			// creates/add the configuration to the project description
			cProjectDescription.createConfiguration(ManagedBuildManager.CFG_DATA_PROVIDER_ID,
					configurationClone.getConfigurationData());
		}
		// activate the specified build type, i.e. Debug/Release
		ICConfigurationDescription[] configs = cProjectDescription.getConfigurations();
		if (configs != null && configs.length > 0) {
			for (ICConfigurationDescription config : configs) {
				if (config.getName().equals(activeBuildType)) {
					config.setActive();
					break;
				}
			}
		}
		subMonitor.split(20);
		CoreModel.getDefault().setProjectDescription(project, cProjectDescription);
	}
	
    public static void setSmartMDSDCdtBuilderFor(IProject project) {
		IManagedBuildInfo buildInfo = ManagedBuildManager.getBuildInfo(project);
		IConfiguration configs[] = buildInfo.getManagedProject().getConfigurations();
		boolean hasChanges = false;
		IBuilder smartmdsdBuilder = getSmartMDSDBuilder();
		if(smartmdsdBuilder != null) {
			for(IConfiguration config: configs) {
				// builder-suffix is either "debug" or "release"
				String builderSuffix = config.getName().toLowerCase();
				// builder ID is constructed from the builder base ID and the builder suffix
				String builderID = ManagedBuildManager.calculateChildId(smartmdsdBuilder.getId(), builderSuffix);
				config.changeBuilder(smartmdsdBuilder, builderID, smartmdsdBuilder.getName());
				try {
					// activate parallel build (just in case it was deactivated before)
					config.getEditableBuilder().setParallelBuildOn(true);
					hasChanges = true;
				} catch (CoreException e) {
					e.printStackTrace();
				}
			}
		}
		if(hasChanges == true) {
			// change things in the buildInfo
			ManagedBuildManager.saveBuildInfo(project, true);
		}
    }
    
    public static void resetDefaultCdtBuilderFor(IProject project) {
		IManagedBuildInfo buildInfo = ManagedBuildManager.getBuildInfo(project);
		IConfiguration configs[] = buildInfo.getManagedProject().getConfigurations();
		boolean hasChanges = false;
		IBuilder gnuBuilder = getDefaultGNUBuilder();
		if(gnuBuilder != null) {
			for(IConfiguration config: configs) {
				String builderID = ManagedBuildManager.calculateChildId(gnuBuilder.getId(), null);
				config.changeBuilder(gnuBuilder, builderID, gnuBuilder.getName());
				try {
					// reset parallel build flag to false
					config.getEditableBuilder().setParallelBuildOn(false);
					hasChanges = true;
				} catch (CoreException e) {
					e.printStackTrace();
				}
			}
		}
		if(hasChanges == true) {
			// change things in the buildInfo
			ManagedBuildManager.saveBuildInfo(project, true);
		}
    }
    
	public static void setDefaultCppIncludesFor(IProject project, boolean useKernelIncludes) throws CoreException 
	{
		ICProjectDescriptionManager mngr = CoreModel.getDefault().getProjectDescriptionManager();
		ICProjectDescription projectDescription = mngr.getProjectDescription(project);

		for(ICConfigurationDescription configDescription : projectDescription.getConfigurations()) {
			ICFolderDescription projectRoot = configDescription.getRootFolderDescription();
			for (ICLanguageSetting setting : projectRoot.getLanguageSettings()) {
				String languageId = setting.getLanguageId();
				if(languageId != null && languageId.equals("org.eclipse.cdt.core.g++")) {
					List<ICLanguageSettingEntry> includePathSettings = setting.getSettingEntriesList(ICSettingEntry.INCLUDE_PATH);
					
					String aceInclude = "${ACE_ROOT}";
					includePathSettings.add(new CIncludePathEntry(aceInclude, ICSettingEntry.LOCAL));
					String smartInclude = "${SMART_ROOT_ACE}/include";
					includePathSettings.add(new CIncludePathEntry(smartInclude, ICSettingEntry.LOCAL));
					
					if(useKernelIncludes == true) {
						String apiInclude = "/usr/include/SmartSoft_CD_API";
						includePathSettings.add(new CIncludePathEntry(apiInclude, ICSettingEntry.LOCAL));
						String kernelInclude = "/usr/include/AceSmartSoftKernel";
						includePathSettings.add(new CIncludePathEntry(kernelInclude, ICSettingEntry.LOCAL));
						String kernelMWInclude = "/usr/include/AceSmartSoftKernel/middlewareMapping";
						includePathSettings.add(new CIncludePathEntry(kernelMWInclude, ICSettingEntry.LOCAL));
					}
					
					includePathSettings.add(new CIncludePathEntry("smartsoft/src", ICSettingEntry.VALUE_WORKSPACE_PATH));
					includePathSettings.add(new CIncludePathEntry("smartsoft/src-gen", ICSettingEntry.VALUE_WORKSPACE_PATH));
					
					setting.setSettingEntries(ICSettingEntry.INCLUDE_PATH, includePathSettings);
				}
			}
		}
		
		CoreModel.getDefault().setProjectDescription(project, projectDescription);
	}
	
	public static boolean addFurtherCdtIncludesTo(IProject project, Map<String,Boolean> includes) throws CoreException 
	{
		boolean settings_changed = false;
		ICProjectDescriptionManager mngr = CoreModel.getDefault().getProjectDescriptionManager();
		ICProjectDescription projectDescription = mngr.getProjectDescription(project);

		for(ICConfigurationDescription configDescription : projectDescription.getConfigurations()) {
			ICFolderDescription projectRoot = configDescription.getRootFolderDescription();
			for (ICLanguageSetting setting : projectRoot.getLanguageSettings()) {
				String languageId = setting.getLanguageId();
				if(languageId != null && languageId.equals("org.eclipse.cdt.core.g++")) {
					List<ICLanguageSettingEntry> includePathSettings = setting.getSettingEntriesList(ICSettingEntry.INCLUDE_PATH);

					for(Map.Entry<String, Boolean> includeEntry: includes.entrySet()) {
						String includeString = includeEntry.getKey();
						boolean already_included = false;
						for(ICLanguageSettingEntry entry: includePathSettings) {
							if(entry instanceof ACSettingEntry) {
								ACSettingEntry settingEntry = (ACSettingEntry)entry;
								if(settingEntry.getName().contentEquals(includeString)) {
									already_included = true;
								}
							}
						}
						// we only add new includes
						// existing includes will not be changed because there might be settings manually added by the user
						// this implies that old settings must be manually cleaned up by the user (maybe this is not the best behavior)
						// if we would want to really update the settings, then we would need to track which settings have been set before
						// and compare those to the new ones
						if(!already_included) {
							settings_changed = true;
							if(includeEntry.getValue() == true) {
								includePathSettings.add(new CIncludePathEntry(includeString, ICSettingEntry.VALUE_WORKSPACE_PATH));
							} else {
								includePathSettings.add(new CIncludePathEntry(includeString, ICSettingEntry.LOCAL));
							}
						}
					}
					setting.setSettingEntries(ICSettingEntry.INCLUDE_PATH, includePathSettings);
				}
			}
		}
		if(settings_changed) {
			CoreModel.getDefault().setProjectDescription(project, projectDescription);
		}
		return settings_changed;
	}
	
	public static void triggerCdtIndexRebuildFor(String projectName) {
		ICProject cproject = CoreModel.getDefault().getCModel().getCProject(projectName);
		CCorePlugin.getIndexManager().reindex(cproject);
	}
	
	public static void setActiveBuildTypeFor(IProject project, String activeBuildType) throws CoreException {
		ICProjectDescriptionManager mngr = CoreModel.getDefault().getProjectDescriptionManager();
		ICProjectDescription cProjectDescription = mngr.getProjectDescription(project);
		ICConfigurationDescription[] configs = cProjectDescription.getConfigurations();
		boolean settings_changed = false;
		if (configs != null && configs.length > 0) {
			for (ICConfigurationDescription config : configs) {
				if (config.getName().equals(activeBuildType)) {
					if(!config.isActive()) {
						config.setActive();
						settings_changed = true;
						System.out.println("change active build type to '"+config.getName()+"' for project "+project.getName());
					}
					break;
				}
			}
		}
		if(settings_changed) {
			CoreModel.getDefault().setProjectDescription(project, cProjectDescription);
		}
	}
	
	public static String getCdtBuildMessages(IProject project) {
		return CUIPlugin.getDefault().getConsoleManager().getConsoleDocument(project).get();
	}
}
