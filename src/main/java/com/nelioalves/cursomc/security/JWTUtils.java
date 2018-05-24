package com.nelioalves.cursomc.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTUtils {

	@Value("${jwt.secret}")
	private String secret;
	
	@Value("${jwt.expiration}")
	private Long expiration;
	
	public String gerarToken(String username) {
		return Jwts.builder()
				.setSubject(username)
				.setExpiration(new Date(System.currentTimeMillis() + expiration))
				.signWith(SignatureAlgorithm.HS512, secret.getBytes())
				.compact();
	}

	public String getUsername(String token) {
		Claims claims = getClaims(token);
		String usernameRetorno = null;
		if (claims!=null) {
			usernameRetorno = claims.getSubject();
		}
		return usernameRetorno;
	}

	public boolean isTokenValido(String token) {
		Claims claims = getClaims(token);
		if (claims!=null) {
			String username = claims.getSubject();
			Date dataExpiration = claims.getExpiration();
			Date now = new Date(System.currentTimeMillis());
			if (username!=null && dataExpiration!=null && now.before(dataExpiration)) {
				return true;
			}
		}
		return false;
	}

	private Claims getClaims(String token) {
		Claims claims = null;
		try {
			claims = Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return claims;
	}
	
}
