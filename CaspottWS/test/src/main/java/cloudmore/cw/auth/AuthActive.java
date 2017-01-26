package cloudmore.cw.auth;

import java.util.UUID;
import org.slf4j.*;

public class AuthActive implements Runnable {
	private final Logger log=LoggerFactory.getLogger(this.getClass());
	private UUID uid=UUID.randomUUID(); 
	
	/*
	 * FDD: execution time can be measured here
	*/
	public void run(){
		log.info("Request handler["+uid+"] start.");
		try{
			synchronized(uid){
				uid.wait();
			}
		}catch(InterruptedException e){}
		log.info("Request handler["+uid+"] stop.");
	}
	
	public void stop(){
		synchronized(uid){
			uid.notifyAll();
		}
	}
}
