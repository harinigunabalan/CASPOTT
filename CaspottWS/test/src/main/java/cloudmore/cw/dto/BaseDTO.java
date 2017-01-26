package cloudmore.cw.dto;

import java.time.*;
import com.fasterxml.jackson.annotation.*;

@JsonIgnoreProperties(ignoreUnknown=true)
public class BaseDTO{
	private String id;
	private long timestamp; // UNIX time

	public BaseDTO(){
		setTimestamp(Instant.now().getEpochSecond());
	}

	public BaseDTO(String nid){
		this();
		setId(nid);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	
	
}
