package org.eclipse.smartmdsd.ui.perspectives;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.ISafeRunnable;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.SafeRunner;

public class SmartMDSDPerspectiveCustomizationsRegistry {
	private static Map<SmartMDSDPerspectiveEnum, Collection<ISmartMDSDPerspectiveCustomization>> external_customizations;
	
	public static void initialize() {
		external_customizations = new HashMap<>();
		for(SmartMDSDPerspectiveEnum perspective: SmartMDSDPerspectiveEnum.values()) {
			external_customizations.put(perspective, new ArrayList<>());
		}
		IConfigurationElement[] config = Platform.getExtensionRegistry().getConfigurationElementsFor("org.eclipse.smartmdsd.ui.SmartMDSDPerspectiveCustomizationEP");
		try {
			// for each extension
			for(IConfigurationElement ext: config) {
				// get the "class" object from the extension (which should implement the AbstractGenerator interface)
				Object obj = ext.createExecutableExtension("class");
				if(obj instanceof ISmartMDSDPerspectiveCustomization) {
					ISmartMDSDPerspectiveCustomization perspective_customizer = (ISmartMDSDPerspectiveCustomization)obj;
					ISafeRunnable runnable = new ISafeRunnable() {
						@Override
						public void handleException(Throwable exception) {
							exception.printStackTrace();
						}
						@Override
						public void run() throws Exception {
							external_customizations.get(perspective_customizer.getSmartMDSDPerspective()).add(perspective_customizer);
						}
					};
					// execute generator
					SafeRunner.run(runnable);
				}
			}
		} catch (CoreException ex) {
			System.out.println(ex.getMessage());
		}
		
	}
	
	public static Collection<ISmartMDSDPerspectiveCustomization> getCustomizationsFor(SmartMDSDPerspectiveEnum perspective) {
		return external_customizations.get(perspective);
	}
}
