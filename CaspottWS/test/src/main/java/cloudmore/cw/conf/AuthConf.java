package cloudmore.cw.conf;

import java.net.*;
import org.springframework.boot.context.properties.*;
import org.springframework.context.annotation.*;

@Configuration
@ConfigurationProperties(prefix="auth")
public class AuthConf{
	private int timeout;
	private boolean isServiceLimited;
	private URI apiUrl;
	private URI payUrl;
	private URI audioUrl;
	private URI videoUrl;

	public AuthConf authConf(){
		return this;
	}

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	public boolean isServiceLimited() {
		return isServiceLimited;
	}

	public void setServiceLimited(boolean isServiceLimited) {
		this.isServiceLimited = isServiceLimited;
	}

	public URI getApiUrl() {
		return apiUrl;
	}

	public void setApiUrl(URI apiUrl) {
		this.apiUrl = apiUrl;
	}

	public URI getPayUrl() {
		return payUrl;
	}

	public void setPayUrl(URI payUrl) {
		this.payUrl = payUrl;
	}

	public URI getAudioUrl() {
		return audioUrl;
	}

	public void setAudioUrl(URI audioUrl) {
		this.audioUrl = audioUrl;
	}

	public URI getVideoUrl() {
		return videoUrl;
	}

	public void setVideoUrl(URI videoUrl) {
		this.videoUrl = videoUrl;
	}
	
	
}
