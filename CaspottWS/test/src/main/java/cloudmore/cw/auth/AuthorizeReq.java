package cloudmore.cw.auth;

import java.util.*;
import org.slf4j.*;
import cloudmore.cw.conf.*;
import cloudmore.cw.dto.*;

public class AuthorizeReq{
	private final Logger log=LoggerFactory.getLogger(this.getClass());
	
	private AuthorizeReqDTO dto;
	private boolean isServiceLimited;
	private AuthConf conf=ApplicationContextProvider.getApplicationContext().getBean("authConf", AuthConf.class);

	public AuthorizeReq(AuthorizeReqDTO ndto){
		setDto(ndto);
		if(ndto.getEp()==null||getEp().isEmpty()) throw new IllegalArgumentException("Audio and Video endpoins are not set.");
	}

	
	
	public AuthorizeReqDTO getDto() {
		return dto;
	}



	public void setDto(AuthorizeReqDTO dto) {
		this.dto = dto;
	}



	public boolean isServiceLimited() {
		return isServiceLimited;
	}



	public void setServiceLimited(boolean isServiceLimited) {
		this.isServiceLimited = isServiceLimited;
	}



	public AuthConf getConf() {
		return conf;
	}



	public void setConf(AuthConf conf) {
		this.conf = conf;
	}



	public Logger getLog() {
		return log;
	}



	@SuppressWarnings("serial")
	public List<String> getEp(){
		return new ArrayList<String>(getDto().getEp()){
			{
				removeIf(p->!p.equalsIgnoreCase(getConf().getAudioUrl().getHost())&&(!p.equalsIgnoreCase(getConf().getVideoUrl().getHost())||isServiceLimited()));
			}
		};
	}

	public String getId(){
		return getDto().getId();
	}
	
	public String getGrantType(){
		return getDto().getGrantType();
	}

	public int getEpCount(){
		return getEp().size();
	}

	public boolean hasAudio(){
		return getEp().contains(getConf().getAudioUrl().getHost());
	}

	public boolean hasVideo(){
		return getEp().contains(getConf().getVideoUrl().getHost());
	}

	public boolean isAudioOnly(){
		return getEpCount()==1&&hasAudio();
	}

	public boolean isVideoOnly(){
		return getEpCount()==1&&hasVideo();
	}
}
