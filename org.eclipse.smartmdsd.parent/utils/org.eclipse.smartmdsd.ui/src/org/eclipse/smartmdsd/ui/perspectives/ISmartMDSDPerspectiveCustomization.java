package org.eclipse.smartmdsd.ui.perspectives;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;

public interface ISmartMDSDPerspectiveCustomization {
	SmartMDSDPerspectiveEnum getSmartMDSDPerspective();
	void addCustomActions(final IPageLayout layout);
	void adjustBaseLayout(final IPageLayout layout);
	void addBottomViews(final IFolderLayout bottom);
}
