package cloudmore.cw.auth;

import org.slf4j.*;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;

@SpringBootApplication
public class Application{
	private final Logger log=LoggerFactory.getLogger(this.getClass());
	public static void main(String[] args){
		SpringApplication.run(Application.class, args);
	}
}
