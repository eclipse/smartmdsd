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
package org.eclipse.smartmdsd.ecore.base.basicAttributes.provider;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.ResourceLocator;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;

import org.eclipse.smartmdsd.ecore.base.basicAttributes.AttributeRefinement;
import org.eclipse.smartmdsd.ecore.base.basicAttributes.BasicAttributesFactory;
import org.eclipse.smartmdsd.ecore.base.basicAttributes.BasicAttributesPackage;

import org.eclipse.smartmdsd.ecore.base.documentation.provider.AbstractDocumentationElementItemProvider;

/**
 * This is the item provider adapter for a {@link org.eclipse.smartmdsd.ecore.base.basicAttributes.AttributeRefinement} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class AttributeRefinementItemProvider extends AbstractDocumentationElementItemProvider {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AttributeRefinementItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns the property descriptors for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

			addAttributePropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Attribute feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addAttributePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(
				((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
				getString("_UI_AttributeRefinement_attribute_feature"),
				getString("_UI_PropertyDescriptor_description", "_UI_AttributeRefinement_attribute_feature",
						"_UI_AttributeRefinement_type"),
				BasicAttributesPackage.Literals.ATTRIBUTE_REFINEMENT__ATTRIBUTE, true, false, true, null, null, null));
	}

	/**
	 * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate feature for an
	 * {@link org.eclipse.emf.edit.command.AddCommand}, {@link org.eclipse.emf.edit.command.RemoveCommand} or
	 * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object) {
		if (childrenFeatures == null) {
			super.getChildrenFeatures(object);
			childrenFeatures.add(BasicAttributesPackage.Literals.ATTRIBUTE_REFINEMENT__VALUE);
		}
		return childrenFeatures;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EStructuralFeature getChildFeature(Object object, Object child) {
		// Check the type of the specified child object and return the proper feature to use for
		// adding (see {@link AddCommand}) it as a child.

		return super.getChildFeature(object, child);
	}

	/**
	 * This returns AttributeRefinement.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/AttributeRefinement"));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected boolean shouldComposeCreationImage() {
		return true;
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		String label = crop(((AttributeRefinement) object).getDocumentation());
		return label == null || label.length() == 0 ? getString("_UI_AttributeRefinement_type")
				: getString("_UI_AttributeRefinement_type") + " " + label;
	}

	/**
	 * This handles model notifications by calling {@link #updateChildren} to update any cached
	 * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void notifyChanged(Notification notification) {
		updateChildren(notification);

		switch (notification.getFeatureID(AttributeRefinement.class)) {
		case BasicAttributesPackage.ATTRIBUTE_REFINEMENT__VALUE:
			fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
			return;
		}
		super.notifyChanged(notification);
	}

	/**
	 * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children
	 * that can be created under this object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);

		newChildDescriptors.add(createChildParameter(BasicAttributesPackage.Literals.ATTRIBUTE_REFINEMENT__VALUE,
				BasicAttributesFactory.eINSTANCE.createSingleValue()));

		newChildDescriptors.add(createChildParameter(BasicAttributesPackage.Literals.ATTRIBUTE_REFINEMENT__VALUE,
				BasicAttributesFactory.eINSTANCE.createArrayValue()));

		newChildDescriptors.add(createChildParameter(BasicAttributesPackage.Literals.ATTRIBUTE_REFINEMENT__VALUE,
				BasicAttributesFactory.eINSTANCE.createFloatingPointValue()));

		newChildDescriptors.add(createChildParameter(BasicAttributesPackage.Literals.ATTRIBUTE_REFINEMENT__VALUE,
				BasicAttributesFactory.eINSTANCE.createIntValue()));

		newChildDescriptors.add(createChildParameter(BasicAttributesPackage.Literals.ATTRIBUTE_REFINEMENT__VALUE,
				BasicAttributesFactory.eINSTANCE.createStringValue()));

		newChildDescriptors.add(createChildParameter(BasicAttributesPackage.Literals.ATTRIBUTE_REFINEMENT__VALUE,
				BasicAttributesFactory.eINSTANCE.createBoolValue()));

		newChildDescriptors.add(createChildParameter(BasicAttributesPackage.Literals.ATTRIBUTE_REFINEMENT__VALUE,
				BasicAttributesFactory.eINSTANCE.createEnumerationValue()));
	}

	/**
	 * Return the resource locator for this item provider's resources.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		return BasicAttributesEditPlugin.INSTANCE;
	}

}
