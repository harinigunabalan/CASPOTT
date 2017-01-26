package com.sap.cloudwave.caspott.DataObjectsHandler;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class CodeArtifact {

	private String projectName;
	private String artifactName;
	private String procedureName;
	private Integer lineNo;
	private Long responseTime;
	private Long noOfCalls;
	private Long noOfErrors;
	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

	public CodeArtifact() {
	}

	public CodeArtifact(String projectName, String artifactName, String procedureName, Integer lineNo, Long responseTime,
			Long noOfCalls, Long noOfErrors) {
		super();
		this.projectName = projectName;
		this.artifactName = artifactName;
		this.procedureName = procedureName;
		this.lineNo = lineNo;
		this.responseTime = responseTime;
		this.noOfCalls = noOfCalls;
		this.noOfErrors = noOfErrors;
	}

	public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		propertyChangeSupport.removePropertyChangeListener(listener);
	}

	public String getProjectName() {
		return projectName;
	}

	public String getartifactName() {
		return artifactName;
	}

	public Integer getLineNo() {
		return lineNo;
	}

	public Long getresponseTime() {
		return responseTime;
	}

	public void setProjectName(String projectName) {
		propertyChangeSupport.firePropertyChange("projectName", this.projectName, this.projectName = projectName);
	}

	public void setartifactName(String artifactName) {
		propertyChangeSupport.firePropertyChange("artifactName", this.artifactName, this.artifactName = artifactName);
	}

	public void setLineNo(Integer lineNo) {
		propertyChangeSupport.firePropertyChange("lineNo", this.lineNo, this.lineNo = lineNo);
	}

	public void setresponseTime(Long responseTime) {
		propertyChangeSupport.firePropertyChange("responseTime", this.responseTime, this.responseTime = responseTime);
	}

	public Long getnoOfCalls() {
		return noOfCalls;
	}
	
	public Long getnoOfErrors() {
		return noOfErrors;
	}

	public void setnoOfCalls(Long noOfCalls) {
		propertyChangeSupport.firePropertyChange("noOfCalls", this.noOfCalls, this.noOfCalls = noOfCalls);
	}
	
	public void setnoOfErrors(Long noOfErrors) {
		propertyChangeSupport.firePropertyChange("noOfErrors", this.noOfErrors, this.noOfErrors = noOfErrors);
	}

	@Override
	public String toString() {
		return artifactName + " " + responseTime;
	}

	public String getProcedureName() {
		return procedureName;
	}

	public void setProcedureName(String procedureName) {
		this.procedureName = procedureName;
	}

}
