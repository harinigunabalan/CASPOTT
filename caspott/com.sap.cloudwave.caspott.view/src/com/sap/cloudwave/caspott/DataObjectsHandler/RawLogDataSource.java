package com.sap.cloudwave.caspott.DataObjectsHandler;

import java.util.List;

public class RawLogDataSource {

	private String message;
	private String version;
	private String timestamp;
	private String type;
	private String file;
	private String host;
	private String offset;
	private List<String> tags;
	private String syslog_severity_code;
	private String syslog_facility_code;
	private String syslog_facility;
	private String syslog_severity;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getOffset() {
		return offset;
	}

	public void setOffset(String offset) {
		this.offset = offset;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	public String getSyslog_severity_code() {
		return syslog_severity_code;
	}

	public void setSyslog_severity_code(String syslog_severity_code) {
		this.syslog_severity_code = syslog_severity_code;
	}

	public String getSyslog_facility_code() {
		return syslog_facility_code;
	}

	public void setSyslog_facility_code(String syslog_facility_code) {
		this.syslog_facility_code = syslog_facility_code;
	}

	public String getSyslog_facility() {
		return syslog_facility;
	}

	public void setSyslog_facility(String syslog_facility) {
		this.syslog_facility = syslog_facility;
	}

	public String getSyslog_severity() {
		return syslog_severity;
	}

	public void setSyslog_severity(String syslog_severity) {
		this.syslog_severity = syslog_severity;
	}

	@Override
	public String toString() {
		String source = "\n Message:" + this.message + "\n version:" + this.version + "\n timestamp:" + this.timestamp
				+ "\n type:" + this.type + "\n file:" + this.file + "\n host:" + this.host + "\n offset:" + this.offset
				+ "\n tags:" + this.tags + "\n syslog_severity_code:" + this.syslog_severity_code
				+ "\n syslog_facility_code:" + this.syslog_facility_code + "\n syslog_facility:" + this.syslog_facility
				+ "\n syslog_severity:" + this.syslog_severity;
		return source;

	}
}
