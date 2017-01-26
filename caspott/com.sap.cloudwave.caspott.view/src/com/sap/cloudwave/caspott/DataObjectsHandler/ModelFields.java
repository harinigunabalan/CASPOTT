package com.sap.cloudwave.caspott.DataObjectsHandler;

import java.util.List;

public class ModelFields {

	private List<String> timestamp;

	public ModelFields() {

	}

	public List<String> getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(List<String> timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		String fields = "\n Fields:" + this.timestamp;
		return fields;

	}

}
