package cloudmore.cw.auth;

import org.slf4j.*;
import org.springframework.web.context.request.async.*;

public class AuthDeferredResult<T> extends DeferredResult<T> implements Runnable{
	private final Logger log=LoggerFactory.getLogger(this.getClass());
	protected Runnable authActive=new AuthActive();
	
	protected void start(){
		new Thread(authActive).start();
		onCompletion(this);
	}

	public void run(){
		((AuthActive)authActive).stop();
	}

	public AuthDeferredResult(){
		super();
		start();
	}

	public AuthDeferredResult(long timeout){
		super(timeout);
		start();
	}

	public AuthDeferredResult(Long timeout, Object timeoutResult){
		super(timeout, timeoutResult);
		start();
	}

}
