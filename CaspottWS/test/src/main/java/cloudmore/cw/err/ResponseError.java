package cloudmore.cw.err;

import org.springframework.http.*;

public class ResponseError{
	private long timestamp;
	private int status;
	private String error;
	private String exception;
	private String message;
	private String path;

	public ResponseError(Exception ex, HttpStatus status, String path){
		setTimestamp(System.currentTimeMillis());
		setException(ex.getClass().getName());
		setStatus(status.value());
		setError(status.getReasonPhrase());
		setPath(path);
		setMessage(ex.getMessage());
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
}
