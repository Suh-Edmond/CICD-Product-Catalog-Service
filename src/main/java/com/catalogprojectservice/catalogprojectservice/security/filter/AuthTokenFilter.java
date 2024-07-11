package com.catalogprojectservice.catalogprojectservice.security.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.catalogprojectservice.catalogprojectservice.security.config.JWTUtil;
import com.catalogprojectservice.catalogprojectservice.service.AuthenticationService;

public class AuthTokenFilter extends OncePerRequestFilter{

	@Autowired
	AuthenticationService authenticationService;
	
	@Autowired
	JWTUtil jwtUtil;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			String jwt = this.parseJwt(request);
			if(jwt != null && jwtUtil.validateToken(jwt)) {
				String username = jwtUtil.getUserName(jwt);
				UserDetails userDetails = authenticationService.loadUserByUsername(username);
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		filterChain.doFilter(request, response);
		
	}
	
	
	private String parseJwt(HttpServletRequest request) {
		String authHeader = request.getHeader("Authorization");
		if (StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer ")) {
			return authHeader.substring(7, authHeader.length());
		}
		return null;
	}

}
