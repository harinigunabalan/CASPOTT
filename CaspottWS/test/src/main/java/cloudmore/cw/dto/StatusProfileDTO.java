package cloudmore.cw.dto;

import org.jose4j.jwt.*;

public class StatusProfileDTO extends BaseDTO{
	private String status;
	private boolean isBlocked;
	private boolean isLimited;
	private NumericDate validFrom; // UNIX time

	public StatusProfileDTO(String nid){
		super(nid);
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public boolean isBlocked() {
		return isBlocked;
	}

	public void setBlocked(boolean isBlocked) {
		this.isBlocked = isBlocked;
	}

	public boolean isLimited() {
		return isLimited;
	}

	public void setLimited(boolean isLimited) {
		this.isLimited = isLimited;
	}

	public NumericDate getValidFrom() {
		return validFrom;
	}

	public void setValidFrom(NumericDate validFrom) {
		this.validFrom = validFrom;
	}
	
	
}
