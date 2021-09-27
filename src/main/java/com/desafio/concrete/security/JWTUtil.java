package com.desafio.concrete.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.desafio.concrete.entities.Usuario;
import com.desafio.concrete.services.UsuarioService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTUtil {

	@Value("${jwt.secret}")
	private String secret;
	
	@Value("${jwt.expiration}")
	private Long expiration;
	
	@Autowired
	private UsuarioService serv;
	
	public String generateToken(String email) {
		String token;
		
		token = Jwts.builder()
				.setSubject(email)
				.setExpiration(new Date(System.currentTimeMillis() + expiration))
				.signWith(SignatureAlgorithm.HS512, secret.getBytes())
				.compact();
		
		Usuario userLogged = serv.findByEmailAuth(email);
		return serv.updateToken(token, userLogged);
	}

	public boolean tokenValido(String token) {
		Claims claims = getClaims(token);
		if(claims != null) {
			String username = claims.getSubject();
			Date expiratinoDate = claims.getExpiration();
			Date now = new Date(System.currentTimeMillis());
			
			if(username != null && expiratinoDate != null && now.before(expiratinoDate)) {
				return true;
			}
		}
		return false;
	}
	
	public String getUsername(String token) {
		Claims claims = getClaims(token);
		if(claims != null) {
			return claims.getSubject();
		}
		return null;
	}
	
	public Integer getUserId(String email) {
		return serv.findByEmailAuth(email).getId();
	}

	private Claims getClaims(String token) {
		try {
			return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody();
		}catch (Exception e) {
			return null;
		}
	}
}
