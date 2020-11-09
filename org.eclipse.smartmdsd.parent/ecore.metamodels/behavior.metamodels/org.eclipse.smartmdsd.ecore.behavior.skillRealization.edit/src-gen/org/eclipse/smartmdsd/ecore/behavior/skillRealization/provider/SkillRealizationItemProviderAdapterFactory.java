/**
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
 *    Matthias Lutz, Alex Lotz, Dennis Stampfer
 */
package org.eclipse.smartmdsd.ecore.behavior.skillRealization.provider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.util.ResourceLocator;

import org.eclipse.emf.edit.domain.EditingDomain;

import org.eclipse.emf.edit.provider.ChangeNotifier;
import org.eclipse.emf.edit.provider.ChildCreationExtenderManager;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.IChangeNotifier;
import org.eclipse.emf.edit.provider.IChildCreationExtender;
import org.eclipse.emf.edit.provider.IDisposable;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.INotifyChangedListener;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;

import org.eclipse.smartmdsd.ecore.behavior.skillRealization.SkillRealizationPackage;

import org.eclipse.smartmdsd.ecore.behavior.skillRealization.util.SkillRealizationAdapterFactory;

/**
 * This is the factory that is used to provide the interfaces needed to support Viewers.
 * The adapters generated by this factory convert EMF adapter notifications into calls to {@link #fireNotifyChanged fireNotifyChanged}.
 * The adapters also support Eclipse property sheets.
 * Note that most of the adapters are shared among multiple instances.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class SkillRealizationItemProviderAdapterFactory extends SkillRealizationAdapterFactory
		implements ComposeableAdapterFactory, IChangeNotifier, IDisposable, IChildCreationExtender {
	/**
	 * This keeps track of the root adapter factory that delegates to this adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ComposedAdapterFactory parentAdapterFactory;

	/**
	 * This is used to implement {@link org.eclipse.emf.edit.provider.IChangeNotifier}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IChangeNotifier changeNotifier = new ChangeNotifier();

	/**
	 * This helps manage the child creation extenders.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ChildCreationExtenderManager childCreationExtenderManager = new ChildCreationExtenderManager(
			SkillRealizationEditPlugin.INSTANCE, SkillRealizationPackage.eNS_URI);

	/**
	 * This keeps track of all the supported types checked by {@link #isFactoryForType isFactoryForType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected Collection<Object> supportedTypes = new ArrayList<Object>();

	/**
	 * This constructs an instance.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SkillRealizationItemProviderAdapterFactory() {
		supportedTypes.add(IEditingDomainItemProvider.class);
		supportedTypes.add(IStructuredItemContentProvider.class);
		supportedTypes.add(ITreeItemContentProvider.class);
		supportedTypes.add(IItemLabelProvider.class);
		supportedTypes.add(IItemPropertySource.class);
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.smartmdsd.ecore.behavior.skillRealization.SkillRealizationModel} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SkillRealizationModelItemProvider skillRealizationModelItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.smartmdsd.ecore.behavior.skillRealization.SkillRealizationModel}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createSkillRealizationModelAdapter() {
		if (skillRealizationModelItemProvider == null) {
			skillRealizationModelItemProvider = new SkillRealizationModelItemProvider(this);
		}

		return skillRealizationModelItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.smartmdsd.ecore.behavior.skillRealization.CoordinationModuleRealization} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CoordinationModuleRealizationItemProvider coordinationModuleRealizationItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.smartmdsd.ecore.behavior.skillRealization.CoordinationModuleRealization}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createCoordinationModuleRealizationAdapter() {
		if (coordinationModuleRealizationItemProvider == null) {
			coordinationModuleRealizationItemProvider = new CoordinationModuleRealizationItemProvider(this);
		}

		return coordinationModuleRealizationItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.smartmdsd.ecore.behavior.skillRealization.SkillRealization} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SkillRealizationItemProvider skillRealizationItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.smartmdsd.ecore.behavior.skillRealization.SkillRealization}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createSkillRealizationAdapter() {
		if (skillRealizationItemProvider == null) {
			skillRealizationItemProvider = new SkillRealizationItemProvider(this);
		}

		return skillRealizationItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.smartmdsd.ecore.behavior.skillRealization.ComponentCoordinationActionEvent} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ComponentCoordinationActionEventItemProvider componentCoordinationActionEventItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.smartmdsd.ecore.behavior.skillRealization.ComponentCoordinationActionEvent}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createComponentCoordinationActionEventAdapter() {
		if (componentCoordinationActionEventItemProvider == null) {
			componentCoordinationActionEventItemProvider = new ComponentCoordinationActionEventItemProvider(this);
		}

		return componentCoordinationActionEventItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.smartmdsd.ecore.behavior.skillRealization.ComponentCoordinationActionWiring} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ComponentCoordinationActionWiringItemProvider componentCoordinationActionWiringItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.smartmdsd.ecore.behavior.skillRealization.ComponentCoordinationActionWiring}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createComponentCoordinationActionWiringAdapter() {
		if (componentCoordinationActionWiringItemProvider == null) {
			componentCoordinationActionWiringItemProvider = new ComponentCoordinationActionWiringItemProvider(this);
		}

		return componentCoordinationActionWiringItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.smartmdsd.ecore.behavior.skillRealization.ComponentCoordinationActionParameter} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ComponentCoordinationActionParameterItemProvider componentCoordinationActionParameterItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.smartmdsd.ecore.behavior.skillRealization.ComponentCoordinationActionParameter}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createComponentCoordinationActionParameterAdapter() {
		if (componentCoordinationActionParameterItemProvider == null) {
			componentCoordinationActionParameterItemProvider = new ComponentCoordinationActionParameterItemProvider(
					this);
		}

		return componentCoordinationActionParameterItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.smartmdsd.ecore.behavior.skillRealization.ComponentCoordinationActionActivation} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ComponentCoordinationActionActivationItemProvider componentCoordinationActionActivationItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.smartmdsd.ecore.behavior.skillRealization.ComponentCoordinationActionActivation}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createComponentCoordinationActionActivationAdapter() {
		if (componentCoordinationActionActivationItemProvider == null) {
			componentCoordinationActionActivationItemProvider = new ComponentCoordinationActionActivationItemProvider(
					this);
		}

		return componentCoordinationActionActivationItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.smartmdsd.ecore.behavior.skillRealization.CoordinationActionBlock} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CoordinationActionBlockItemProvider coordinationActionBlockItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.smartmdsd.ecore.behavior.skillRealization.CoordinationActionBlock}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createCoordinationActionBlockAdapter() {
		if (coordinationActionBlockItemProvider == null) {
			coordinationActionBlockItemProvider = new CoordinationActionBlockItemProvider(this);
		}

		return coordinationActionBlockItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.smartmdsd.ecore.behavior.skillRealization.EventHandler} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EventHandlerItemProvider eventHandlerItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.smartmdsd.ecore.behavior.skillRealization.EventHandler}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createEventHandlerAdapter() {
		if (eventHandlerItemProvider == null) {
			eventHandlerItemProvider = new EventHandlerItemProvider(this);
		}

		return eventHandlerItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.smartmdsd.ecore.behavior.skillRealization.CoordinationInterfaceInstance} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CoordinationInterfaceInstanceItemProvider coordinationInterfaceInstanceItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.smartmdsd.ecore.behavior.skillRealization.CoordinationInterfaceInstance}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createCoordinationInterfaceInstanceAdapter() {
		if (coordinationInterfaceInstanceItemProvider == null) {
			coordinationInterfaceInstanceItemProvider = new CoordinationInterfaceInstanceItemProvider(this);
		}

		return coordinationInterfaceInstanceItemProvider;
	}

	/**
	 * This returns the root adapter factory that contains this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ComposeableAdapterFactory getRootAdapterFactory() {
		return parentAdapterFactory == null ? this : parentAdapterFactory.getRootAdapterFactory();
	}

	/**
	 * This sets the composed adapter factory that contains this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setParentAdapterFactory(ComposedAdapterFactory parentAdapterFactory) {
		this.parentAdapterFactory = parentAdapterFactory;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object type) {
		return supportedTypes.contains(type) || super.isFactoryForType(type);
	}

	/**
	 * This implementation substitutes the factory itself as the key for the adapter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter adapt(Notifier notifier, Object type) {
		return super.adapt(notifier, this);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object adapt(Object object, Object type) {
		if (isFactoryForType(type)) {
			Object adapter = super.adapt(object, type);
			if (!(type instanceof Class<?>) || (((Class<?>) type).isInstance(adapter))) {
				return adapter;
			}
		}

		return null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<IChildCreationExtender> getChildCreationExtenders() {
		return childCreationExtenderManager.getChildCreationExtenders();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Collection<?> getNewChildDescriptors(Object object, EditingDomain editingDomain) {
		return childCreationExtenderManager.getNewChildDescriptors(object, editingDomain);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ResourceLocator getResourceLocator() {
		return childCreationExtenderManager;
	}

	/**
	 * This adds a listener.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void addListener(INotifyChangedListener notifyChangedListener) {
		changeNotifier.addListener(notifyChangedListener);
	}

	/**
	 * This removes a listener.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void removeListener(INotifyChangedListener notifyChangedListener) {
		changeNotifier.removeListener(notifyChangedListener);
	}

	/**
	 * This delegates to {@link #changeNotifier} and to {@link #parentAdapterFactory}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void fireNotifyChanged(Notification notification) {
		changeNotifier.fireNotifyChanged(notification);

		if (parentAdapterFactory != null) {
			parentAdapterFactory.fireNotifyChanged(notification);
		}
	}

	/**
	 * This disposes all of the item providers created by this factory. 
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void dispose() {
		if (skillRealizationModelItemProvider != null)
			skillRealizationModelItemProvider.dispose();
		if (coordinationModuleRealizationItemProvider != null)
			coordinationModuleRealizationItemProvider.dispose();
		if (skillRealizationItemProvider != null)
			skillRealizationItemProvider.dispose();
		if (componentCoordinationActionEventItemProvider != null)
			componentCoordinationActionEventItemProvider.dispose();
		if (componentCoordinationActionWiringItemProvider != null)
			componentCoordinationActionWiringItemProvider.dispose();
		if (componentCoordinationActionParameterItemProvider != null)
			componentCoordinationActionParameterItemProvider.dispose();
		if (componentCoordinationActionActivationItemProvider != null)
			componentCoordinationActionActivationItemProvider.dispose();
		if (coordinationActionBlockItemProvider != null)
			coordinationActionBlockItemProvider.dispose();
		if (eventHandlerItemProvider != null)
			eventHandlerItemProvider.dispose();
		if (coordinationInterfaceInstanceItemProvider != null)
			coordinationInterfaceInstanceItemProvider.dispose();
	}

}
