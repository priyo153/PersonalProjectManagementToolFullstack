package com.demoProject.security;

import static com.demoProject.security.SecurityConstants.EXPIRATION_TIME;
import static com.demoProject.security.SecurityConstants.SECRET;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.demoProject.entities.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtTokenProvider {
	
	public String generateToken(Authentication authentication) {
		User user=(User) authentication.getPrincipal();
		
		Date now=new Date(System.currentTimeMillis());
		Date expiryDate=new Date(now.getTime()+EXPIRATION_TIME);
		System.out.println(expiryDate);
		String userId=Long.toString(user.getId());
		
		
		Map<String,Object> claims=new HashMap<>();
		claims.put("id",userId);
		claims.put("username", user.getUsername());
		claims.put("name", user.getName());
		
		return Jwts.builder()
				.setSubject(userId)
				.setClaims(claims)
				.setIssuedAt(now)
				.setExpiration(expiryDate)
				.signWith(SignatureAlgorithm.HS512, SECRET)
				.compact();
		
	}
	
	public boolean validateToken(String token) {
		
		try {
			Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
			return true;
		}
		catch(SignatureException e) {
			System.out.println("Invalid JWT Signature");
		}
		catch(MalformedJwtException e) {
			System.out.println("Invalid JWT token");
		}
		catch(ExpiredJwtException e) {
			System.out.println("Expired jwt token");
		}
		catch(UnsupportedJwtException e) {
			System.out.println("unsupported JWT Token");
		}
		catch(IllegalArgumentException e) {
			System.out.println("jwt class string is empty");
			
		}
		return false;
	}
	
	public Long getUserIdFromJWT(String token) {
		Claims claims=Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
		String id=(String)claims.get("id");
		return Long.parseLong(id);
	}

}
