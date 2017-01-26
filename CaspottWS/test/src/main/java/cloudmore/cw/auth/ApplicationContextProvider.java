package cloudmore.cw.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextProvider implements ApplicationContextAware{
	private final Logger log=LoggerFactory.getLogger(this.getClass());
	
	private static ApplicationContext context;

	public static ApplicationContext getApplicationContext(){
		try{
			Thread.sleep(500);
		}catch(Exception e){
			
		}
		return context;
	}

	@Override
	public void setApplicationContext(ApplicationContext ctx) throws BeansException{
		context=ctx;
	}
}
