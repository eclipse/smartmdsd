/**
 * Copyright (c) 2018 Technische Hochschule Ulm, Servicerobotics Ulm, Germany
 * headed by Prof. Dr. Christian Schlegel
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *  
 * SPDX-License-Identifier: EPL-2.0
 *  
 * Contributors:
 *    Alex Lotz
 */
package org.eclipse.smartmdsd.ecore.system.causeEffectChain.provider;

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

import org.eclipse.smartmdsd.ecore.system.causeEffectChain.CauseEffectChainPackage;

import org.eclipse.smartmdsd.ecore.system.causeEffectChain.util.CauseEffectChainAdapterFactory;

/**
 * This is the factory that is used to provide the interfaces needed to support Viewers.
 * The adapters generated by this factory convert EMF adapter notifications into calls to {@link #fireNotifyChanged fireNotifyChanged}.
 * The adapters also support Eclipse property sheets.
 * Note that most of the adapters are shared among multiple instances.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class CauseEffectChainItemProviderAdapterFactory extends CauseEffectChainAdapterFactory
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
			CauseEffectChainEditPlugin.INSTANCE, CauseEffectChainPackage.eNS_URI);

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
	public CauseEffectChainItemProviderAdapterFactory() {
		supportedTypes.add(IEditingDomainItemProvider.class);
		supportedTypes.add(IStructuredItemContentProvider.class);
		supportedTypes.add(ITreeItemContentProvider.class);
		supportedTypes.add(IItemLabelProvider.class);
		supportedTypes.add(IItemPropertySource.class);
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.smartmdsd.ecore.system.causeEffectChain.CuaseEffectChainModel} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CuaseEffectChainModelItemProvider cuaseEffectChainModelItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.smartmdsd.ecore.system.causeEffectChain.CuaseEffectChainModel}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createCuaseEffectChainModelAdapter() {
		if (cuaseEffectChainModelItemProvider == null) {
			cuaseEffectChainModelItemProvider = new CuaseEffectChainModelItemProvider(this);
		}

		return cuaseEffectChainModelItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.smartmdsd.ecore.system.causeEffectChain.ActivityChain} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ActivityChainItemProvider activityChainItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.smartmdsd.ecore.system.causeEffectChain.ActivityChain}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createActivityChainAdapter() {
		if (activityChainItemProvider == null) {
			activityChainItemProvider = new ActivityChainItemProvider(this);
		}

		return activityChainItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.smartmdsd.ecore.system.causeEffectChain.ActivityLink} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ActivityLinkItemProvider activityLinkItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.smartmdsd.ecore.system.causeEffectChain.ActivityLink}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createActivityLinkAdapter() {
		if (activityLinkItemProvider == null) {
			activityLinkItemProvider = new ActivityLinkItemProvider(this);
		}

		return activityLinkItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.smartmdsd.ecore.system.causeEffectChain.InputHandlerLink} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected InputHandlerLinkItemProvider inputHandlerLinkItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.smartmdsd.ecore.system.causeEffectChain.InputHandlerLink}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createInputHandlerLinkAdapter() {
		if (inputHandlerLinkItemProvider == null) {
			inputHandlerLinkItemProvider = new InputHandlerLinkItemProvider(this);
		}

		return inputHandlerLinkItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.smartmdsd.ecore.system.causeEffectChain.MaxResponseTime} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MaxResponseTimeItemProvider maxResponseTimeItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.smartmdsd.ecore.system.causeEffectChain.MaxResponseTime}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createMaxResponseTimeAdapter() {
		if (maxResponseTimeItemProvider == null) {
			maxResponseTimeItemProvider = new MaxResponseTimeItemProvider(this);
		}

		return maxResponseTimeItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.smartmdsd.ecore.system.causeEffectChain.MinResponseTime} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MinResponseTimeItemProvider minResponseTimeItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.smartmdsd.ecore.system.causeEffectChain.MinResponseTime}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createMinResponseTimeAdapter() {
		if (minResponseTimeItemProvider == null) {
			minResponseTimeItemProvider = new MinResponseTimeItemProvider(this);
		}

		return minResponseTimeItemProvider;
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
		if (cuaseEffectChainModelItemProvider != null)
			cuaseEffectChainModelItemProvider.dispose();
		if (activityChainItemProvider != null)
			activityChainItemProvider.dispose();
		if (activityLinkItemProvider != null)
			activityLinkItemProvider.dispose();
		if (inputHandlerLinkItemProvider != null)
			inputHandlerLinkItemProvider.dispose();
		if (maxResponseTimeItemProvider != null)
			maxResponseTimeItemProvider.dispose();
		if (minResponseTimeItemProvider != null)
			minResponseTimeItemProvider.dispose();
	}

}
