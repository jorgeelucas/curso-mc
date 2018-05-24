package com.nelioalves.cursomc.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

	private JWTUtils jwtUtils;

	private UserDetailsService userDetailService;
	
	private static final String AUTHORIZATION = "Authorization";
	private static final String BEARER = "Bearer ";
	
	public JWTAuthorizationFilter(AuthenticationManager authenticationManager, JWTUtils jwtUtil, UserDetailsService userDetailService) {
		super(authenticationManager);
		this.jwtUtils = jwtUtil;
		this.userDetailService = userDetailService;
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String header = request.getHeader(AUTHORIZATION);
		if (header!=null && header.startsWith(BEARER)) {
			UsernamePasswordAuthenticationToken auth = getAuthentication(header.substring(7));
			if (auth!=null) {
				SecurityContextHolder.getContext().setAuthentication(auth);
				
			}
		}
		chain.doFilter(request, response);
	}

	private UsernamePasswordAuthenticationToken getAuthentication(String token) {
		if (jwtUtils.isTokenValido(token)) {
			String username = jwtUtils.getUsername(token);
			UserDetails user = userDetailService.loadUserByUsername(username);
			return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
		}
		return null;
	}

}
