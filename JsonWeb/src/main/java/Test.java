import java.util.Base64;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.management.RuntimeErrorException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class Test {
	public static void main(String[] args)
	{
		JwtUtil util=new JwtUtil();
		String token=util.generateToken("129", "sujatha", "tec");
		System.out.println(token);
		Claims c=util.getClaims("tec", token);
	
		System.out.println(c.getId());
	    System.out.println(c.getIssuer());
	    System.out.println(c.getIssuedAt());
	    
	    String subject = util.getSubject("tec", token);
	    System.out.println(subject);
	    
	    boolean validToken = util.isValidToken("tec", token);
	    System.out.println(validToken);
		
	}

}
