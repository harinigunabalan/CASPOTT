package cloudmore.cw.conf;

import cloudmore.cw.auth.ApplicationContextProvider;
import cloudmore.cw.auth.JwtHelper;
import cloudmore.cw.dto.AuthConfDTO;
import cloudmore.cw.dto.JwtConfDTO;

public class ConfLoader {

	public static JwtConfDTO getJwtConf(){
		return new JwtConfDTO(ApplicationContextProvider.getApplicationContext().getBean("jwtConf", JwtConf.class));
	}
	
	public static AuthConfDTO getAuthConf(){
		return  new AuthConfDTO(ApplicationContextProvider.getApplicationContext().getBean("authConf", AuthConf.class));
	}
	
	public static JwtConf getJwtConfPlain(){
		return ApplicationContextProvider.getApplicationContext().getBean("jwtConf", JwtConf.class);
	}
	
	public static JwtHelper getJwtHelper(){
		return ApplicationContextProvider.getApplicationContext().getBean("jwtHelper", JwtHelper.class);

	}
	

}
