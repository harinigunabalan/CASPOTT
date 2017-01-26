package com.sap.cloudwave.caspott.view;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

public class Perspective implements IPerspectiveFactory {

	private static final String FAVORITES_VIEW_ID = "com.sap.cloudwave.caspott.view.CaspottView";
	// private static final String FAVORITES_ACTION_ID =
	// "com.qualityeclipse.favorites.workbenchActionSet";

	public void createInitialLayout(IPageLayout layout) {
		// Get the editor area.
		String editorArea = layout.getEditorArea();

		// Put the Favorites view on the bottom with
		// the Tasks view.
		IFolderLayout bottom = layout.createFolder("bottom", IPageLayout.BOTTOM, 0.66f, editorArea);
		bottom.addView(FAVORITES_VIEW_ID);
		bottom.addView(IPageLayout.ID_TASK_LIST);
		bottom.addPlaceholder(IPageLayout.ID_PROBLEM_VIEW);

		// Add the Favorites action set.
		// layout.addActionSet(FAVORITES_ACTION_ID);

		layout.setEditorAreaVisible(false);
		layout.setFixed(true);
	}

	/////////////////////////////
	/*
	 * public void createInitialLayout(IPageLayout layout) {
	 * layout.setEditorAreaVisible(false); layout.setFixed(true);
	 * 
	 * }
	 */

}
