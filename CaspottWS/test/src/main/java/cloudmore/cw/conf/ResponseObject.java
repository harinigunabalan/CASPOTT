package cloudmore.cw.conf;

import cloudmore.cw.dto.AuthConfDTO;
import cloudmore.cw.dto.JwtConfDTO;

public class ResponseObject {

	private JwtConfDTO jwtConfDto;
	
	private String ipAddress;
	
	private AuthConfDTO authConfDto;
	
	private long timestamp;

	public JwtConfDTO getJwtConf() {
		return jwtConfDto;
	}

	public void setJwtConf(JwtConfDTO jwtConfDto) {
		this.jwtConfDto = jwtConfDto;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public AuthConfDTO getAuthConf() {
		return authConfDto;
	}

	public void setAuthConf(AuthConfDTO authConf) {
		this.authConfDto = authConf;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	
}
