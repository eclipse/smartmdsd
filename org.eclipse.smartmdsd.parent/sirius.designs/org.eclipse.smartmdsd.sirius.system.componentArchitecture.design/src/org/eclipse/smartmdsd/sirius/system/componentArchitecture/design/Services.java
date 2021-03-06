/********************************************************************************
 * Copyright (c) 2017 Technische Hochschule Ulm, Servicerobotics Ulm, Germany
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
package org.eclipse.smartmdsd.sirius.system.componentArchitecture.design;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtext.resource.IContainer;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.resource.IResourceDescription;
import org.eclipse.xtext.resource.IResourceDescriptions;
import org.eclipse.xtext.resource.SaveOptions;
import org.eclipse.xtext.resource.impl.ResourceDescriptionsProvider;
import org.eclipse.smartmdsd.ecore.component.componentDefinition.AbstractComponentElement;
import org.eclipse.smartmdsd.ecore.component.componentDefinition.AnswerPort;
import org.eclipse.smartmdsd.ecore.component.componentDefinition.ComponentDefinitionPackage;
import org.eclipse.smartmdsd.ecore.component.componentDefinition.ComponentPort;
import org.eclipse.smartmdsd.ecore.component.componentDefinition.InputPort;
import org.eclipse.smartmdsd.ecore.component.componentDefinition.OutputPort;
import org.eclipse.smartmdsd.ecore.component.componentDefinition.RequestPort;
import org.eclipse.smartmdsd.ecore.component.componentParameter.ComponentParameter;
import org.eclipse.smartmdsd.ecore.component.componentParameter.ComponentParametersRef;
import org.eclipse.smartmdsd.ecore.component.seronetExtension.OpcUaReadServer;
import org.eclipse.smartmdsd.ecore.service.communicationPattern.EventPattern;
import org.eclipse.smartmdsd.ecore.service.communicationPattern.PushPattern;
import org.eclipse.smartmdsd.ecore.service.communicationPattern.QueryPattern;
import org.eclipse.smartmdsd.ecore.service.communicationPattern.SendPattern;
import org.eclipse.smartmdsd.ecore.service.serviceDefinition.ForkingServiceDefinition;
import org.eclipse.smartmdsd.ecore.service.serviceDefinition.JoiningServiceDefinition;
import org.eclipse.smartmdsd.ecore.service.serviceDefinition.RequestAnswerServiceDefinition;
import org.eclipse.smartmdsd.ecore.system.compArchSeronetExtension.OpcUaReadServerInstance;
import org.eclipse.smartmdsd.ecore.system.componentArchitecture.ComponentArchitectureFactory;
import org.eclipse.smartmdsd.ecore.system.componentArchitecture.ComponentArchitectureModelUtility;
import org.eclipse.smartmdsd.ecore.system.componentArchitecture.ComponentInstance;
import org.eclipse.smartmdsd.ecore.system.componentArchitecture.Connection;
import org.eclipse.smartmdsd.ecore.system.componentArchitecture.ProvidedService;
import org.eclipse.smartmdsd.ecore.system.componentArchitecture.RequiredService;
import org.eclipse.smartmdsd.ecore.system.componentArchitecture.ServiceInstance;
import org.eclipse.smartmdsd.ecore.system.componentArchitecture.SystemComponentArchitecture;
import org.eclipse.smartmdsd.ecore.system.systemParameter.ComponentParameterInstance;
import org.eclipse.smartmdsd.ecore.system.systemParameter.SystemParamModel;
import org.eclipse.smartmdsd.ecore.system.systemParameter.SystemParameterFactory;
import org.eclipse.smartmdsd.ecore.system.systemParameter.SystemParameterPackage;
import org.eclipse.smartmdsd.sirius.utils.DiagramHelperServices;
import org.eclipse.smartmdsd.xtext.component.componentDefinition.ui.internal.ComponentDefinitionActivator;
import org.eclipse.smartmdsd.xtext.system.systemParameter.ui.internal.SystemParameterActivator;

import com.google.inject.Injector;

/**
 * The services class used by VSM.
 */
public class Services {
    
    public String getProjectName(EObject obj) {
    	return DiagramHelperServices.getProjectName(obj);
    }
    
	public EObject importModels(EObject context) {
		DiagramHelperServices.triggerModelImportCommand();
		return context;
	}
    
    public EObject selectOpcUaReadServerPortReference(EObject context) {
    	if(context instanceof OpcUaReadServerInstance) {
    		OpcUaReadServerInstance readServerInstance = (OpcUaReadServerInstance)context;
    		EObject parent = context.eContainer();
    		if(parent instanceof ComponentInstance) {
    			ComponentInstance compInstance = (ComponentInstance)parent;
    			for(EObject element: compInstance.getComponent().getElements()) {
    				if(element instanceof OpcUaReadServer) {
    					OpcUaReadServer readServer = (OpcUaReadServer)element;
    					if(readServerInstance.getReadServer().equals(readServer)) {
    						for(ServiceInstance serviceInstance: compInstance.getPorts()) {
    							if(serviceInstance.getPort().equals(readServer.getOutPort())) {
    								return serviceInstance;
    							}
    						}
    					}
    				}
    			}
    		}
    	}
    	return null;
    }
     
    public EObject deleteComponentAndRelatedConnections(EObject context)
    {
    	if(context instanceof ComponentInstance) {
    		ComponentInstance componentInstance = (ComponentInstance)context;
    		EObject parent = context.eContainer();
    		if(parent instanceof SystemComponentArchitecture) {
    			SystemComponentArchitecture system = (SystemComponentArchitecture)parent;
    			Collection<Connection> relatedConnections = new ArrayList<Connection>();
    			for(Connection connection: system.getConnections()) {
    				for(ServiceInstance port: componentInstance.getPorts()) {
    					if(port instanceof ProvidedService) {
    						if(connection.getTo().equals(port)) {
    							relatedConnections.add(connection);
    						}
    					} else {
    						if(connection.getFrom().equals(port)) {
    							relatedConnections.add(connection);
    						}
    					}
    				}
    			}
    			for(Connection connection: relatedConnections) {
    				// delete related connection
    				EcoreUtil.delete(connection);
    			}
    		}
    		// delete component instance
    		EcoreUtil.delete(componentInstance);
    	}
    	return context;
    }
    
	public Boolean hasLogo(EObject context) {
		if(context instanceof ComponentInstance) {
			ComponentInstance component = (ComponentInstance)context;
			if(component.getComponent() != null && component.getComponent().getLogo() != null) {
				String logoPathString = component.getComponent().getLogo();
				if(!logoPathString.startsWith(System.getProperty("file.separator"))) {
					URI uri = component.getComponent().eResource().getURI();
					logoPathString = uri.trimSegments(1).appendSegment(logoPathString).toPlatformString(true);
				}
				IPath logoPath = new Path(logoPathString);
				IFile logoFile = ResourcesPlugin.getWorkspace().getRoot().getFile(logoPath);
				if(logoFile.exists()) {
					return true;
				}
			}
		}
		return false;
	}
	
	public String getLogoPath(EObject context) {
		if(context instanceof ComponentInstance) {
			ComponentInstance component = (ComponentInstance)context;
			if(component.getComponent() != null && component.getComponent().getLogo() != null) {
				String logoPathString = component.getComponent().getLogo();
				if(!logoPathString.startsWith(System.getProperty("file.separator"))) {
					URI uri = component.getComponent().eResource().getURI();
					logoPathString = uri.trimSegments(1).appendSegment(logoPathString).toPlatformString(true);
				}
				IPath logoPath = new Path(logoPathString);
				IFile logoFile = ResourcesPlugin.getWorkspace().getRoot().getFile(logoPath);
				if(logoFile.exists()) {
					return logoPathString;
				}
			}
		}
		return "";
	}
    
    public EObject getComponentParameter(EObject obj) {
    	if(obj instanceof ComponentInstance) {
    		ComponentInstance inst = (ComponentInstance)obj;
    		for(AbstractComponentElement compElem: inst.getComponent().getElements()) {
    			if(compElem instanceof ComponentParametersRef) {
    				return ((ComponentParametersRef) compElem).getParameter();
    			}
    		}
    	}
    	return null;
    }
    
    public EObject createAndGetComponentParameterInstance(EObject context) {
    	if(context instanceof ComponentInstance) {
    		ComponentInstance compInstance = (ComponentInstance)context;
    		EObject parent = context.eContainer();
    		if(parent instanceof SystemComponentArchitecture) {
    			SystemComponentArchitecture system = (SystemComponentArchitecture)parent;
    			
    	    	Injector injector = getSystemParameterInjector();
    	    	EClass type = SystemParameterPackage.eINSTANCE.getSystemParamModel();
    	    	Collection<EObject> objects = geXtextIndexEObjetcsByType(injector, context, type);
    			for(EObject obj: objects) {
    				if(obj instanceof SystemParamModel) {
    					SystemParamModel paramModel = (SystemParamModel)obj;
    					if(paramModel.getSystem().equals(system)) {
    						for(ComponentParameterInstance paramInstance: paramModel.getComponents()) {
    							if(paramInstance.getComponentInstance().equals(compInstance)) {
    								// paramInstance found, just return it
//    								System.out.println("return existing ComponentParameterInstance: "+paramInstance);
    								return paramInstance;
    							}
    						}
    						// parmaInstance not found => create a new one
    						ComponentParameterInstance paramInstance = SystemParameterFactory.eINSTANCE.createComponentParameterInstance();
    						paramInstance.setComponentInstance(compInstance);
    						EObject compParam = getComponentParameter(compInstance);
    						if(compParam != null) {
    							paramInstance.setComponentParam((ComponentParameter)compParam);
    						}
    						paramModel.getComponents().add(paramInstance);
    						try {
								paramModel.eResource().save(SaveOptions.defaultOptions().toOptionsMap());
							} catch (IOException e) {
								e.printStackTrace();
							}
//    						System.out.println("return a new ComponentParameterInstance: "+paramInstance);
    						return paramInstance; 
    					}
    				}
    			}
    		}
    	}
    	return null;
    }
    
    public boolean isForkingConnection(EObject obj) {
    	if(obj instanceof Connection) {
    		Connection connection = (Connection)obj;
    		// getServiceProvider->getPort()
    		ComponentPort service = connection.getTo().getPort();
    		if(service instanceof OutputPort) {
    			//ProvidedService + OutputFort = ForkingService
    			return true;
    		}
    	}
    	return false;
    }
    
    public boolean isJoyningService(EObject obj) {
    	if(obj instanceof ServiceInstance) {
    		ServiceInstance svcInstance = (ServiceInstance)obj;
    		ComponentPort service = svcInstance.getPort();
    		if(service instanceof InputPort) {
    			InputPort port = (InputPort)service;
    			if(port.getService() instanceof JoiningServiceDefinition) {
    				return true;
    			}
    		} else if(service instanceof OutputPort) {
    			OutputPort port = (OutputPort)service;
    			if(port.getService() instanceof JoiningServiceDefinition) {
    				return true;
    			}
    		}
    	}
    	return false;
    }
    
    public boolean is2WayService(EObject obj) {
    	if(obj instanceof ServiceInstance) {
    		ServiceInstance svcInstance = (ServiceInstance)obj;
    		ComponentPort service = svcInstance.getPort();
    		if(service instanceof AnswerPort || service instanceof RequestPort) {
    			return true;
    		}
    	}
    	return false;
    }
    
    public boolean isRequestAnswerConnection(EObject obj) {
    	if(obj instanceof Connection) {
    		Connection connection = (Connection)obj;
    		// getServiceProvider->getPort()
    		ComponentPort service = connection.getTo().getPort();
    		if(service instanceof AnswerPort) {
    			//ProvidedService + AnswerPort = ForkingService
    			return true;
    		}
    	}
    	return false;
    }
    
    public String getCommObjectName(EObject obj) {
    	if(obj instanceof Connection) {
    		Connection connection = (Connection)obj;
    		ComponentPort service = connection.getTo().getPort();
    		if(service instanceof OutputPort) {
    			OutputPort port = (OutputPort)service;
    			if(port.getService() instanceof ForkingServiceDefinition) {
    				ForkingServiceDefinition sdef = (ForkingServiceDefinition)(port.getService());
    				if(sdef.getPattern() instanceof PushPattern) {
    					PushPattern push = (PushPattern)(sdef.getPattern());
    					return push.getDataType().getName();
    				}
    				if(sdef.getPattern() instanceof EventPattern) {
    					EventPattern event = (EventPattern)(sdef.getPattern());
    					return event.getEventType().getName();
    				}
    			}
    		} else if(service instanceof InputPort) {
    			InputPort port = (InputPort)service;
    			if(port.getService() instanceof JoiningServiceDefinition) {
    				JoiningServiceDefinition sdef = (JoiningServiceDefinition)(port.getService());
    				if(sdef.getPattern() instanceof SendPattern) {
    					SendPattern send = (SendPattern)(sdef.getPattern());
    					return send.getDataType().getName();
    				}
    			}
    		} else if(service instanceof AnswerPort) {
    			AnswerPort port = (AnswerPort)service;
    			RequestAnswerServiceDefinition ra = (RequestAnswerServiceDefinition)(port.getService());
    			QueryPattern query = (QueryPattern)(ra.getPattern());
    			return "R="+query.getRequestType().getName() + " / A="+query.getAnswerType().getName();
    		}
    	}
    	return "";
    }
    
    
    public Iterable<ComponentPort> getAllServiceUses(EObject context) {
    	if(context instanceof ComponentInstance) {
    		ComponentInstance component = (ComponentInstance)context;
    		Iterable<ComponentPort> allPorts = ComponentArchitectureModelUtility.getAllClientPorts(component);
    		Collection<ComponentPort> newPortsOnly = new ArrayList<ComponentPort>();
    		for(ComponentPort port: allPorts) {
    			boolean portExists = false;
    			for(ServiceInstance inst: component.getPorts()) {
    				if(inst.getPort().equals(port)) {
    					portExists = true;
    					break;
    				}
    			}
    			if(portExists == false) {
    				newPortsOnly.add(port);
    			}
    		}
    		return newPortsOnly;
    	}
    	return new ArrayList<ComponentPort>();
    }
    
    public boolean isCompatibleConnection(EObject left, EObject right) {
    	return ComponentArchitectureModelUtility.servicePortsCompatible((ServiceInstance)left, (ServiceInstance)right);
    }
    
    public Iterable<ComponentPort> getAllServiceRealizations(EObject context) {
    	if(context instanceof ComponentInstance) {
    		ComponentInstance component = (ComponentInstance)context;
    		Iterable<ComponentPort> allPorts = ComponentArchitectureModelUtility.getAllServerPorts(component);
    		Collection<ComponentPort> newPortsOnly = new ArrayList<ComponentPort>();
    		for(ComponentPort port: allPorts) {
    			boolean portExists = false;
    			for(ServiceInstance inst: component.getPorts()) {
    				if(inst.getPort().equals(port)) {
    					portExists = true;
    					break;
    				}
    			}
    			if(portExists == false) {
    				newPortsOnly.add(port);
    			}
    		}
    		return newPortsOnly;
    	}
    	return new ArrayList<ComponentPort>();
    }
    
    public EObject instantiateAllComponentPorts(EObject context) {
    	if(context instanceof ComponentInstance) {
    		ComponentInstance component = (ComponentInstance)context;
    		for(ComponentPort port: ComponentArchitectureModelUtility.getAllClientPorts(component)) {
    			RequiredService service = ComponentArchitectureFactory.eINSTANCE.createRequiredService();
    			service.setPort(port);
    			component.getPorts().add(service);
    		}
    		for(ComponentPort port: ComponentArchitectureModelUtility.getAllServerPorts(component)) {
    			ProvidedService service = ComponentArchitectureFactory.eINSTANCE.createProvidedService();
    			service.setPort(port);
    			component.getPorts().add(service);
    		}
    	}
    	return context;
    }
    
    public Collection<EObject> getAllVisibleComponentDefinitions(EObject context) {
    	Injector injector = getComponentDefinitionInjector();
    	EClass type = ComponentDefinitionPackage.eINSTANCE.getComponentDefinition();
    	return geXtextIndexEObjetcsByType(injector, context, type);
    }
    
    private Injector getComponentDefinitionInjector() {
    	return ComponentDefinitionActivator.getInstance().getInjector(ComponentDefinitionActivator.ORG_ECLIPSE_SMARTMDSD_XTEXT_COMPONENT_COMPONENTDEFINITION_COMPONENTDEFINITION);
    }
    
    private Injector getSystemParameterInjector() {
    	return SystemParameterActivator.getInstance().getInjector(SystemParameterActivator.ORG_ECLIPSE_SMARTMDSD_XTEXT_SYSTEM_SYSTEMPARAMETER_SYSTEMPARAMETER);
    }
    
    private Collection<EObject> geXtextIndexEObjetcsByType(Injector injector, EObject context, EClass type) {
    	Collection<EObject> objects = new ArrayList<EObject>();
    	// get resource description from current "context"
    	ResourceDescriptionsProvider rdp = injector.getInstance(ResourceDescriptionsProvider.class);
    	IResourceDescriptions descriptions = rdp.getResourceDescriptions(context.eResource());
    	IResourceDescription description = descriptions.getResourceDescription(context.eResource().getURI());
    	// get all visible containers
    	IContainer.Manager manager = injector.getInstance(IContainer.Manager.class);
    	List<IContainer> containters = manager.getVisibleContainers(description, descriptions);
    	for(IContainer container: containters) {
    		// get object descriptions filtered by EClass type
    		Iterable<IEObjectDescription> objectDescriptions = container.getExportedObjectsByType(type);
    		for(IEObjectDescription objectDescription: objectDescriptions) {
    			EObject serviceRepoProxy = objectDescription.getEObjectOrProxy();
    			if(serviceRepoProxy.eIsProxy()) {
    				EObject serviceRepo = context.eResource().getResourceSet().getEObject(objectDescription.getEObjectURI(), true);
    				objects.add(serviceRepo);
    			} else {
    				objects.add(serviceRepoProxy);
    			}
    		}
    	}
    	return objects;
    }
}
