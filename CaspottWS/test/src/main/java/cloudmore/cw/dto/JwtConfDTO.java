package cloudmore.cw.dto;

import cloudmore.cw.conf.JwtConf;


public class JwtConfDTO {
	private String issuer;
	private String audience;
	private String subject;
	private String type;
	private int validMinutes;
	private int notBefore; // MinutesInThePast
	private String kid;
	private String secret;	
	private String audioEp;
	private String videoEp;
	
	public JwtConfDTO(JwtConf conf){
		this.issuer = conf.getIssuer();
		this.audience = conf.getAudience();
		this.subject = conf.getType();
		this.validMinutes = conf.getValidMinutes();
		this.notBefore = conf.getNotBefore();
		this.kid = conf.getKid();
		this.secret = conf.getSecret();
		this.audioEp = conf.getAudioEp();
		this.videoEp = conf.getVideoEp();
	}

	public JwtConfDTO(){
		super();
	}
	
	public JwtConfDTO jwtConfDto(){
		return this;
	}

	public String getIssuer() {
		return issuer;
	}

	public void setIssuer(String issuer) {
		this.issuer = issuer;
	}

	public String getAudience() {
		return audience;
	}

	public void setAudience(String audience) {
		this.audience = audience;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getValidMinutes() {
		return validMinutes;
	}

	public void setValidMinutes(int validMinutes) {
		this.validMinutes = validMinutes;
	}

	public int getNotBefore() {
		return notBefore;
	}

	public void setNotBefore(int notBefore) {
		this.notBefore = notBefore;
	}

	public String getKid() {
		return kid;
	}

	public void setKid(String kid) {
		this.kid = kid;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public String getAudioEp() {
		return audioEp;
	}

	public void setAudioEp(String audioEp) {
		this.audioEp = audioEp;
	}

	public String getVideoEp() {
		return videoEp;
	}

	public void setVideoEp(String videoEp) {
		this.videoEp = videoEp;
	}
}
