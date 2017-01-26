package cloudmore.cw.dto;


public class ExtProfileDTO extends ProfileDTO{
	public String SkypeName;
	public String TwitterName;
	public String LinkedInProfile;
	public String FacebookProfile;

	public ExtProfileDTO(String nid){
		super(nid);
	}

	public String getSkypeName() {
		return SkypeName;
	}

	public void setSkypeName(String skypeName) {
		SkypeName = skypeName;
	}

	public String getTwitterName() {
		return TwitterName;
	}

	public void setTwitterName(String twitterName) {
		TwitterName = twitterName;
	}

	public String getLinkedInProfile() {
		return LinkedInProfile;
	}

	public void setLinkedInProfile(String linkedInProfile) {
		LinkedInProfile = linkedInProfile;
	}

	public String getFacebookProfile() {
		return FacebookProfile;
	}

	public void setFacebookProfile(String facebookProfile) {
		FacebookProfile = facebookProfile;
	}
	
	
}
