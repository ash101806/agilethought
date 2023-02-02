package com.agilethought.springboot.utils;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
/**
 * Util class to perform actions with JWT
 * @author Manuel Ashley Sanchez Zapien <mailto: manuel.zapien>
 */
@Component
public class JwtUtil implements Serializable {

	private static final long serialVersionUID = -2550185165626007488L;

	public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

	@Value("${agilethought.config.jwt.secret}")
	private String secret;
	/**
	 * Methos to get username from token
	 * @param token to get username
	 * @return the user name
	 */
	public String getUsernameFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}
	/**
	 * Method to get Expiration claim
	 * @param token  to get expiration date
	 * @return expiration date
	 */
	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}

	/**
	 * Method to get claim and apply it
	 * @param token to check
	 * @return Claims of token
	 */
	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}
	/**
	 * Method to get All claims of a token
	 * @param token to check
	 * @return Claims of token
	 */
	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(secret.getBytes())).build().parseClaimsJws(token)
				.getBody();
	}
	/**
	 * Methos to check if a JWT is expired or not
	 * @param token to check
	 * @return true if is valid else false
	 */
	private Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}
	/**
	 * Passthroght methos to generate a JWT
	 * @param userDetails details of authenticated user
	 * @return Generated JWT
	 */
	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();
		return doGenerateToken(claims, userDetails.getUsername());
	}

	/**
	 * Method to generate token with the parametrized secret
	 * @param claims Claims of authenticated user
	 * @param subject Goal (if exist) for token
	 * @return generated JWT
	 */
	private String doGenerateToken(Map<String, Object> claims, String subject) {

		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
				.signWith(Keys.hmacShaKeyFor(secret.getBytes())  , SignatureAlgorithm.HS512).compact();
	}
	/**
	 * Method to validate an JWT with the parametrized secret
	 * @param token
	 * @param userDetails
	 * @return
	 */
	public Boolean validateToken(String token, UserDetails userDetails) {
		final String username = getUsernameFromToken(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}
}