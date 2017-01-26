package com.sap.cloudwave.caspott.DataObjectsHandler;

public class ModelSource {
	
	private String projectName;
	private String className;
	private String procedureName;
	private int lineNumber = 1;
	private long responseTime;
	private long numberOfCalls;
	private long numberOfErrors;

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	
	public String getProjectName() {
		return this.projectName;
	}

	public void setClassName(String className) {
		this.className = className;
	}
	
	public String getClassName() {
		return this.className;
	}

	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}
	
	public int getLineNumber() {
		return this.lineNumber;
	}

	public void setResponseTime(long responseTime) {
		this.responseTime = responseTime;
	}
	
	public long getResponseTime() {
		return this.responseTime;
	}
	
	public void setNumberOfCalls(long numberOfCalls) {
		this.numberOfCalls = numberOfCalls;
	}
	
	public long getNumberOfCalls() {
		return this.numberOfCalls;
	}
	
	public void setNumberOfErrors(long numberOfErrors) {
		this.numberOfErrors = numberOfErrors;
	}
	
	public long getNumberOfErrors() {
		return this.numberOfErrors;
	}

	public String getProcedureName() {
		return procedureName;
	}

	public void setProcedureName(String procedureName) {
		this.procedureName = procedureName;
	}
}
