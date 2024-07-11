package com.catalogprojectservice.catalogprojectservice.security.config;

import java.io.Serializable;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.catalogprojectservice.catalogprojectservice.exception.CustomizedBadCredentialsException;
import com.catalogprojectservice.catalogprojectservice.model.security.CustomizedUserDetails;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JWTUtil implements Serializable {

	@Value("${jwt.secret}")
	private String secret;

	@Value("${jwt.expiration}")
	private int expirationDate;

	/**
	 * get user name
	 * 
	 * @param token
	 * @return
	 */
	public String getUserName(String token) {
		return Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody().getSubject();
	}

	/**
	 * generate token
	 * 
	 * @param authentication
	 * @return
	 */
	 
	public String generateToken(Authentication authentication) {
		 
		CustomizedUserDetails userDetails = (CustomizedUserDetails) authentication.getPrincipal();
		return Jwts.builder().setSubject(userDetails.getUsername()).setIssuedAt(new Date())
				.setExpiration(new Date((new Date().getTime()) + this.expirationDate))
				.signWith(SignatureAlgorithm.HS256, this.secret).compact();
	}

	/**
	 * validate user jwt
	 * 
	 * @param jwt
	 * @return
	 */
	public Boolean validateToken(String jwt) {
		try {
			Jwts.parser().setSigningKey(this.secret).parseClaimsJws(jwt);
			return true;
		} catch (SignatureException signatureEx) {
			throw new CustomizedBadCredentialsException("Invalid authentication token");
		} catch (MalformedJwtException ex) {
			throw new CustomizedBadCredentialsException("Invalid authentication token");
		} catch (ExpiredJwtException ex) {
			throw new CustomizedBadCredentialsException("Authentication token has expired");
		} catch (UnsupportedJwtException e) {
			throw new CustomizedBadCredentialsException("Invalid authentication token");
		}
	}

}
