import java.util.Base64;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtUtil {
	
	//generate token
	
	public String generateToken(String id,
	String subject,
	String key
	)
	{
		
		return Jwts.builder().setId(id) //id
				.setSubject(subject)  //subject
				.setIssuer("TECTORO")   //provider
				.setIssuedAt(new Date(System.currentTimeMillis()))  //token generation date
				.setExpiration(new Date(System.currentTimeMillis()+TimeUnit.MINUTES.toMillis(1)))
				.signWith(SignatureAlgorithm.HS256,  //sign Alog
						Base64.getEncoder().encode(key.getBytes()))  //secretKey
				.compact();  //converts into string
	}

	//get claims
	public Claims getClaims(String key,String token)
	{
		return Jwts.parser().setSigningKey(Base64.getEncoder().encode(key.getBytes()))//secret key
				.parseClaimsJws(token)
				.getBody();
	}
	
	//get subject
	
	public String getSubject(String key,String token)
	{
		return getClaims(key, token).getSubject();
	}
	
	//check whether the token is valid
	
	public boolean isValidToken(String key,String token)
	{
		//expiration date>current date
		return getClaims(key, token)
				.getExpiration().after(new Date(System.currentTimeMillis()));
	}
}
