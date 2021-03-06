/**
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
 *    Alex Lotz, Dennis Stampfer, Matthias Lutz
 */
package org.eclipse.smartmdsd.ecore.component.componentDefinition.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.eclipse.smartmdsd.ecore.component.componentDefinition.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ComponentDefinitionFactoryImpl extends EFactoryImpl implements ComponentDefinitionFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ComponentDefinitionFactory init() {
		try {
			ComponentDefinitionFactory theComponentDefinitionFactory = (ComponentDefinitionFactory) EPackage.Registry.INSTANCE
					.getEFactory(ComponentDefinitionPackage.eNS_URI);
			if (theComponentDefinitionFactory != null) {
				return theComponentDefinitionFactory;
			}
		} catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new ComponentDefinitionFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComponentDefinitionFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
		case ComponentDefinitionPackage.COMPONENT_DEF_MODEL:
			return createComponentDefModel();
		case ComponentDefinitionPackage.COMPONENT_DEFINITION:
			return createComponentDefinition();
		case ComponentDefinitionPackage.ACTIVITY:
			return createActivity();
		case ComponentDefinitionPackage.INPUT_HANDLER:
			return createInputHandler();
		case ComponentDefinitionPackage.SERVICE_REPO_IMPORT:
			return createServiceRepoImport();
		case ComponentDefinitionPackage.OUTPUT_PORT:
			return createOutputPort();
		case ComponentDefinitionPackage.REQUEST_PORT:
			return createRequestPort();
		case ComponentDefinitionPackage.INPUT_PORT:
			return createInputPort();
		case ComponentDefinitionPackage.ANSWER_PORT:
			return createAnswerPort();
		case ComponentDefinitionPackage.REQUEST_HANDLER:
			return createRequestHandler();
		case ComponentDefinitionPackage.COMPONENT_SUB_NODE_OBSERVER:
			return createComponentSubNodeObserver();
		case ComponentDefinitionPackage.INPUT_PORT_LINK:
			return createInputPortLink();
		case ComponentDefinitionPackage.REQUEST_PORT_LINK:
			return createRequestPortLink();
		default:
			throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ComponentDefModel createComponentDefModel() {
		ComponentDefModelImpl componentDefModel = new ComponentDefModelImpl();
		return componentDefModel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ComponentDefinition createComponentDefinition() {
		ComponentDefinitionImpl componentDefinition = new ComponentDefinitionImpl();
		return componentDefinition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Activity createActivity() {
		ActivityImpl activity = new ActivityImpl();
		return activity;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public InputHandler createInputHandler() {
		InputHandlerImpl inputHandler = new InputHandlerImpl();
		return inputHandler;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ServiceRepoImport createServiceRepoImport() {
		ServiceRepoImportImpl serviceRepoImport = new ServiceRepoImportImpl();
		return serviceRepoImport;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public OutputPort createOutputPort() {
		OutputPortImpl outputPort = new OutputPortImpl();
		return outputPort;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public RequestPort createRequestPort() {
		RequestPortImpl requestPort = new RequestPortImpl();
		return requestPort;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public InputPort createInputPort() {
		InputPortImpl inputPort = new InputPortImpl();
		return inputPort;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public AnswerPort createAnswerPort() {
		AnswerPortImpl answerPort = new AnswerPortImpl();
		return answerPort;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public RequestHandler createRequestHandler() {
		RequestHandlerImpl requestHandler = new RequestHandlerImpl();
		return requestHandler;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ComponentSubNodeObserver createComponentSubNodeObserver() {
		ComponentSubNodeObserverImpl componentSubNodeObserver = new ComponentSubNodeObserverImpl();
		return componentSubNodeObserver;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public InputPortLink createInputPortLink() {
		InputPortLinkImpl inputPortLink = new InputPortLinkImpl();
		return inputPortLink;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public RequestPortLink createRequestPortLink() {
		RequestPortLinkImpl requestPortLink = new RequestPortLinkImpl();
		return requestPortLink;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ComponentDefinitionPackage getComponentDefinitionPackage() {
		return (ComponentDefinitionPackage) getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static ComponentDefinitionPackage getPackage() {
		return ComponentDefinitionPackage.eINSTANCE;
	}

} //ComponentDefinitionFactoryImpl
