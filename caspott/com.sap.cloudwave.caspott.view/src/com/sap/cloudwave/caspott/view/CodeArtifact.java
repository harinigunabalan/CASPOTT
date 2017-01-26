package com.sap.cloudwave.caspott.view;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class CodeArtifact {

	private String artifactName;
	private Integer responseTime;
	private Integer noOfCalls;
	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

	public CodeArtifact() {
	}

	public CodeArtifact(String artifactName, Integer responseTime, Integer noOfCalls) {
		super();
		this.artifactName = artifactName;
		this.responseTime = responseTime;
		this.noOfCalls = noOfCalls;
	}

	public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		propertyChangeSupport.removePropertyChangeListener(listener);
	}

	public String getartifactName() {
		return artifactName;
	}

	public Integer getresponseTime() {
		return responseTime;
	}

	public void setartifactName(String artifactName) {
		propertyChangeSupport.firePropertyChange("artifactName", this.artifactName, this.artifactName = artifactName);
	}

	public void setresponseTime(Integer responseTime) {
		propertyChangeSupport.firePropertyChange("responseTime", this.responseTime, this.responseTime = responseTime);
	}

	public Integer getnoOfCalls() {
		return noOfCalls;
	}

	public void setnoOfCalls(Integer noOfCalls) {
		propertyChangeSupport.firePropertyChange("noOfCalls", this.noOfCalls, this.noOfCalls = noOfCalls);
	}

	@Override
	public String toString() {
		return artifactName + " " + responseTime;
	}

}
