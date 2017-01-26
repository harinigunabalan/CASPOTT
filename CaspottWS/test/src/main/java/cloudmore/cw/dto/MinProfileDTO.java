package cloudmore.cw.dto;


public class MinProfileDTO extends BaseDTO{
	public String loginName;
	public String displayName;

	public MinProfileDTO(String nid){
		super(nid);
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	
	
}
