package cloudmore.cw.err;

@SuppressWarnings("serial")
public class HttpUnauthorizedException extends RuntimeException{
	public HttpUnauthorizedException(){
		super();
	}

	public HttpUnauthorizedException(String msg){
		super(msg);
	}
}
