package cloudmore.cw.dto;

import java.util.*;

public class AuthorizeReqDTO extends BaseDTO{
	private String scope;
	private String grantType;
	private List<String> ep;

	public AuthorizeReqDTO(){
		super();
	}
	public AuthorizeReqDTO(String nid){
		super(nid);
	}
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	public String getGrantType() {
		return grantType;
	}
	public void setGrantType(String grantType) {
		this.grantType = grantType;
	}
	public List<String> getEp() {
		return ep;
	}
	public void setEp(List<String> ep) {
		this.ep = ep;
	}
	
	
	
}
