package cloudmore.cw.dto;

import java.util.*;

public class AuthorizeResDTO extends AuthorizeReqDTO{
	private long expire;
	private List<String> ep;
	private String token;

	public AuthorizeResDTO(){
		super();
	}
	
	public AuthorizeResDTO(String nid){
		super(nid);
	}

	public long getExpire() {
		return expire;
	}

	public void setExpire(long expire) {
		this.expire = expire;
	}

	public List<String> getEp() {
		return ep;
	}

	public void setEp(List<String> ep) {
		this.ep = ep;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
}
