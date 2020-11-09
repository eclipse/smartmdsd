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
package org.eclipse.smartmdsd.ecore.component.performanceExtension.provider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.util.ResourceLocator;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.edit.command.CommandParameter;

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

import org.eclipse.smartmdsd.ecore.component.componentDefinition.Activity;
import org.eclipse.smartmdsd.ecore.component.componentDefinition.ComponentDefinitionPackage;
import org.eclipse.smartmdsd.ecore.component.componentDefinition.ComponentSubNode;

import org.eclipse.smartmdsd.ecore.component.componentDefinition.util.ComponentDefinitionSwitch;

import org.eclipse.smartmdsd.ecore.component.performanceExtension.PerformanceExtensionFactory;
import org.eclipse.smartmdsd.ecore.component.performanceExtension.PerformanceExtensionPackage;

import org.eclipse.smartmdsd.ecore.component.performanceExtension.util.PerformanceExtensionAdapterFactory;

/**
 * This is the factory that is used to provide the interfaces needed to support Viewers.
 * The adapters generated by this factory convert EMF adapter notifications into calls to {@link #fireNotifyChanged fireNotifyChanged}.
 * The adapters also support Eclipse property sheets.
 * Note that most of the adapters are shared among multiple instances.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class PerformanceExtensionItemProviderAdapterFactory extends PerformanceExtensionAdapterFactory
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
			PerformanceExtensionEditPlugin.INSTANCE, PerformanceExtensionPackage.eNS_URI);

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
	public PerformanceExtensionItemProviderAdapterFactory() {
		supportedTypes.add(IEditingDomainItemProvider.class);
		supportedTypes.add(IStructuredItemContentProvider.class);
		supportedTypes.add(ITreeItemContentProvider.class);
		supportedTypes.add(IItemLabelProvider.class);
		supportedTypes.add(IItemPropertySource.class);
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.smartmdsd.ecore.component.performanceExtension.ActivationConstraints} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ActivationConstraintsItemProvider activationConstraintsItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.smartmdsd.ecore.component.performanceExtension.ActivationConstraints}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createActivationConstraintsAdapter() {
		if (activationConstraintsItemProvider == null) {
			activationConstraintsItemProvider = new ActivationConstraintsItemProvider(this);
		}

		return activationConstraintsItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.smartmdsd.ecore.component.performanceExtension.InputLinkExtension} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected InputLinkExtensionItemProvider inputLinkExtensionItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.smartmdsd.ecore.component.performanceExtension.InputLinkExtension}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createInputLinkExtensionAdapter() {
		if (inputLinkExtensionItemProvider == null) {
			inputLinkExtensionItemProvider = new InputLinkExtensionItemProvider(this);
		}

		return inputLinkExtensionItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.smartmdsd.ecore.component.performanceExtension.DefaultInputTrigger} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DefaultInputTriggerItemProvider defaultInputTriggerItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.smartmdsd.ecore.component.performanceExtension.DefaultInputTrigger}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createDefaultInputTriggerAdapter() {
		if (defaultInputTriggerItemProvider == null) {
			defaultInputTriggerItemProvider = new DefaultInputTriggerItemProvider(this);
		}

		return defaultInputTriggerItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.smartmdsd.ecore.component.performanceExtension.DefaultPeriodicTimer} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DefaultPeriodicTimerItemProvider defaultPeriodicTimerItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.smartmdsd.ecore.component.performanceExtension.DefaultPeriodicTimer}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createDefaultPeriodicTimerAdapter() {
		if (defaultPeriodicTimerItemProvider == null) {
			defaultPeriodicTimerItemProvider = new DefaultPeriodicTimerItemProvider(this);
		}

		return defaultPeriodicTimerItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.smartmdsd.ecore.component.performanceExtension.DefaultObservedElementTrigger} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DefaultObservedElementTriggerItemProvider defaultObservedElementTriggerItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.smartmdsd.ecore.component.performanceExtension.DefaultObservedElementTrigger}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createDefaultObservedElementTriggerAdapter() {
		if (defaultObservedElementTriggerItemProvider == null) {
			defaultObservedElementTriggerItemProvider = new DefaultObservedElementTriggerItemProvider(this);
		}

		return defaultObservedElementTriggerItemProvider;
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
		if (activationConstraintsItemProvider != null)
			activationConstraintsItemProvider.dispose();
		if (inputLinkExtensionItemProvider != null)
			inputLinkExtensionItemProvider.dispose();
		if (defaultInputTriggerItemProvider != null)
			defaultInputTriggerItemProvider.dispose();
		if (defaultPeriodicTimerItemProvider != null)
			defaultPeriodicTimerItemProvider.dispose();
		if (defaultObservedElementTriggerItemProvider != null)
			defaultObservedElementTriggerItemProvider.dispose();
	}

	/**
	 * A child creation extender for the {@link ComponentDefinitionPackage}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static class ComponentDefinitionChildCreationExtender implements IChildCreationExtender {
		/**
		 * The switch for creating child descriptors specific to each extended class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		protected static class CreationSwitch extends ComponentDefinitionSwitch<Object> {
			/**
			 * The child descriptors being populated.
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected List<Object> newChildDescriptors;

			/**
			 * The domain in which to create the children.
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected EditingDomain editingDomain;

			/**
			 * Creates the a switch for populating child descriptors in the given domain.
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			CreationSwitch(List<Object> newChildDescriptors, EditingDomain editingDomain) {
				this.newChildDescriptors = newChildDescriptors;
				this.editingDomain = editingDomain;
			}

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public Object caseActivity(Activity object) {
				newChildDescriptors.add(createChildParameter(ComponentDefinitionPackage.Literals.ACTIVITY__EXTENSIONS,
						PerformanceExtensionFactory.eINSTANCE.createActivationConstraints()));

				newChildDescriptors.add(createChildParameter(ComponentDefinitionPackage.Literals.ACTIVITY__EXTENSIONS,
						PerformanceExtensionFactory.eINSTANCE.createDefaultInputTrigger()));

				newChildDescriptors.add(createChildParameter(ComponentDefinitionPackage.Literals.ACTIVITY__EXTENSIONS,
						PerformanceExtensionFactory.eINSTANCE.createDefaultPeriodicTimer()));

				newChildDescriptors.add(createChildParameter(ComponentDefinitionPackage.Literals.ACTIVITY__EXTENSIONS,
						PerformanceExtensionFactory.eINSTANCE.createDefaultObservedElementTrigger()));

				return null;
			}

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public Object caseComponentSubNode(ComponentSubNode object) {
				newChildDescriptors
						.add(createChildParameter(ComponentDefinitionPackage.Literals.COMPONENT_SUB_NODE__LINKS,
								PerformanceExtensionFactory.eINSTANCE.createInputLinkExtension()));

				return null;
			}

			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected CommandParameter createChildParameter(Object feature, Object child) {
				return new CommandParameter(null, feature, child);
			}

		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public Collection<Object> getNewChildDescriptors(Object object, EditingDomain editingDomain) {
			ArrayList<Object> result = new ArrayList<Object>();
			new CreationSwitch(result, editingDomain).doSwitch((EObject) object);
			return result;
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public ResourceLocator getResourceLocator() {
			return PerformanceExtensionEditPlugin.INSTANCE;
		}
	}

}
