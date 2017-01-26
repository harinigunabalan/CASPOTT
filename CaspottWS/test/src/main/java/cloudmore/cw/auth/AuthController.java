package cloudmore.cw.auth;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsAsyncClientHttpRequestFactory;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.context.request.async.DeferredResult;

import cloudmore.cw.conf.AuthConf;
import cloudmore.cw.conf.ConfLoader;
import cloudmore.cw.conf.JwtConf;
import cloudmore.cw.conf.ResponseObject;
import cloudmore.cw.dto.AuthConfDTO;
import cloudmore.cw.dto.AuthorizeReqDTO;
import cloudmore.cw.dto.AuthorizeResDTO;
import cloudmore.cw.dto.JwtConfDTO;
import cloudmore.cw.dto.StatusProfileDTO;
import cloudmore.cw.err.HttpUnauthorizedException;
import cloudmore.cw.err.ResponseError;

@RestController
@ComponentScan("cloudmore.cw.conf")
public class AuthController{
	
	@Autowired
	public AuthConf conf;

	
	private final AsyncRestTemplate asyncClient=new AsyncRestTemplate(new HttpComponentsAsyncClientHttpRequestFactory());	
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public @ResponseBody ResponseObject processRequest(HttpServletRequest request){
 
		ResponseObject response = new ResponseObject();
		
		response.setIpAddress(request.getRemoteAddr());
		
		response.setTimestamp(System.currentTimeMillis()); 
		
		response.setAuthConf(ConfLoader.getAuthConf());
		response.setJwtConf(ConfLoader.getJwtConf());
		
		return response; 
	}
	
	//private JwtConfDTO jwtConf = ConfLoader.getJwtConf();
	//private AuthConfDTO authConf = ConfLoader.getAuthConf();
	//response.setAuthConf(this.authConf); 
	//response.setJwtConf(this.jwtConf);	
	
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ExceptionHandler(HttpUnauthorizedException.class)
	public ResponseError handleRequestException(HttpServletRequest request, HttpUnauthorizedException ex){
		return new ResponseError(ex, HttpStatus.UNAUTHORIZED, request.getRequestURI());
	}

	@RequestMapping(value="/auth", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE, headers="Authorization")
	public DeferredResult<AuthorizeResDTO> authorize(@RequestHeader("Authorization") String authHdr, @RequestBody AuthorizeReqDTO authReq) throws Exception{
		return subrequestAndIssueToken(new AuthorizeReq(authReq), authHdr, new AuthDeferredResult<AuthorizeResDTO>(conf.getTimeout()));
	}

	@SuppressWarnings({ "serial", "unused" })
	private DeferredResult<AuthorizeResDTO> subrequestAndIssueToken(AuthorizeReq authReq, String authHdr, AuthDeferredResult<AuthorizeResDTO> deferredResult) throws Exception{

		
		AuthorizeRes response = new AuthorizeRes(authReq);

		
		deferredResult.setResult(response.getDto());
		
		
		if(true)return deferredResult;
				
		asyncClient.postForEntity(conf.getApiUrl(), new HttpEntity<AuthorizeReqDTO>(new AuthorizeReqDTO(authReq.getId()){
			{
				setEp(Arrays.asList(new String[]{conf.getApiUrl().getHost()}));
			}
		}, new HttpHeaders(){
			{
				set("Authorization", authHdr);
				setContentType(MediaType.APPLICATION_JSON);
			}
		}), AuthorizeResDTO.class).addCallback(new ListenableFutureCallback<ResponseEntity<AuthorizeResDTO>>(){
			@Override
			public void onFailure(Throwable t){
				deferredResult.setErrorResult(t);
			}

			@Override
			public void onSuccess(ResponseEntity<AuthorizeResDTO> res){
				if(!conf.isServiceLimited()||authReq.isAudioOnly()) try{
					deferredResult.setResult(new AuthorizeRes(authReq,ConfLoader.getJwtHelper(),ConfLoader.getJwtConfPlain()).getDto());
				}catch(Exception e){
					deferredResult.setErrorResult(e);
				}
				else asyncClient.exchange(conf.getPayUrl()+"/{id}", HttpMethod.GET, new HttpEntity<>(new HttpHeaders(){
					{
						set("Authorization", "Bearer "+res.getBody().getToken());
					}
				}), StatusProfileDTO.class, authReq.getId()).addCallback(new ListenableFutureCallback<ResponseEntity<StatusProfileDTO>>(){
					@Override
					public void onFailure(Throwable t){
						deferredResult.setErrorResult(t);
					}

					@Override
					public void onSuccess(ResponseEntity<StatusProfileDTO> result){
						if(result.getBody().isLimited()&&authReq.isVideoOnly()) deferredResult.setErrorResult(new HttpUnauthorizedException("service is limited for free users"));
						else{
							authReq.setServiceLimited(result.getBody().isLimited());
							try{
								deferredResult.setResult(new AuthorizeRes(authReq,ConfLoader.getJwtHelper(),ConfLoader.getJwtConfPlain()).getDto());
							}catch(Exception e){
								deferredResult.setErrorResult(e);
							}
						}
					}
				});
			}
		});
		return deferredResult;
	}
}
