package com.sap.cloudwave.caspott.DataObjectsHandler;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.swt.SWT;

public class TableSorter extends ViewerComparator {
	private int propertyIndex;
	private static final int DESCENDING = 1;
	private int direction = DESCENDING;

	public TableSorter() {
		this.propertyIndex = 0;
		direction = DESCENDING;
	}

	public int getDirection() {
		return direction == 1 ? SWT.DOWN : SWT.UP;
	}

	public void setColumn(int column) {
		if (column == this.propertyIndex) {
			// Same column as last sort; toggle the direction
			direction = 1 - direction;
		} else {
			// New column; do an ascending sort
			this.propertyIndex = column;
			direction = DESCENDING;
		}
	}

	@Override
	public int compare(Viewer viewer, Object e1, Object e2) {
		CodeArtifact ca1 = (CodeArtifact) e1;
		CodeArtifact ca2 = (CodeArtifact) e2;
		int rc = 0;
		switch (propertyIndex) {
		case 0:
			rc = ca1.getProjectName().compareTo(ca2.getProjectName());
			break;
		case 1:
			rc = ca1.getartifactName().compareTo(ca2.getartifactName());
			break;
		case 2:
			rc = ca1.getProcedureName().compareTo(ca2.getProcedureName());
			break;
		case 3:
			rc = ca1.getresponseTime().compareTo(ca2.getresponseTime());
			break;
		case 4:
			rc = ca1.getnoOfCalls().compareTo(ca2.getnoOfCalls());
			break;
		case 5:
			rc = ca1.getnoOfErrors().compareTo(ca2.getnoOfErrors());
			break;
		default:
			rc = 0;
		}
		// If descending order, flip the direction
		if (direction == DESCENDING) {
			rc = -rc;
		}
		return rc;
	}

}
