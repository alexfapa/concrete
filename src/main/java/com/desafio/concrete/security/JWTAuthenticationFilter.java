package com.desafio.concrete.security;


import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.desafio.concrete.dto.CredenciaisDto;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	

private AuthenticationManager authenticationManager;
	
	private JWTUtil jwtUtil;
	
	
		
	public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil) {
		this.authenticationManager = authenticationManager;
		this.jwtUtil = jwtUtil;
	}
	
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, 
			HttpServletResponse res) throws AuthenticationException{

		try {
			CredenciaisDto creds = new ObjectMapper()
					.readValue(req.getInputStream(), CredenciaisDto.class);
			
			UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(creds.getEmail(), 
					creds.getSenha(), new ArrayList<>());
			
			Authentication auth = authenticationManager.authenticate(authToken);
			return auth;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public void successfulAuthentication(HttpServletRequest req, 
			HttpServletResponse res,
			FilterChain chain,
			Authentication auth) throws IOException, ServletException{
		
		String email = ((UserSS) auth.getPrincipal()).getUsername();
		String token = jwtUtil.generateToken(email);
		Integer userId = jwtUtil.getUserId(email);
		
		
		res.addHeader("Authorization", "Bearer " + token);
		res.addHeader("access-control-expose-headers","Authorization");
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/usuarios/{id}").buildAndExpand(userId).toUri();
		
		String location = uri.toString().replace("/login", "");
		res.addHeader("Location", location);
		
	}
}
