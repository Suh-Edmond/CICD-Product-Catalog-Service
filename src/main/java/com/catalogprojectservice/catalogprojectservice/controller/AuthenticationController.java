package com.catalogprojectservice.catalogprojectservice.controller;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.catalogprojectservice.catalogprojectservice.exception.CustomizedBadCredentialsException;
import com.catalogprojectservice.catalogprojectservice.model.User;
import com.catalogprojectservice.catalogprojectservice.model.security.CustomizedUserDetails;
import com.catalogprojectservice.catalogprojectservice.request.LoginRequest;
import com.catalogprojectservice.catalogprojectservice.request.RegisterRequest;
import com.catalogprojectservice.catalogprojectservice.response.JwtResponse;
import com.catalogprojectservice.catalogprojectservice.response.SuccessResponse;
import com.catalogprojectservice.catalogprojectservice.security.config.JWTUtil;
import com.catalogprojectservice.catalogprojectservice.service.AuthenticationService;

@CrossOrigin()
@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

	@Autowired
	private AuthenticationService authService;
	@Autowired
	private AuthenticationManager authManager;
	@Autowired
	private JWTUtil jwtUtil;
	private RegisterRequest userRequest;

	/**
	 * constructor
	 */
	public AuthenticationController() {
		this.userRequest = new RegisterRequest();
	}

	/**
	 * create user
	 * 
	 * @param request
	 * @return
	 */
	@PostMapping("/register")
	public ResponseEntity<Object> createUserAccount(@Valid @RequestBody RegisterRequest request) {
		User user = userRequest.convertFromUserRequestToUserEntity(request);
		authService.addNewUser(user);
		return new ResponseEntity<Object>((new SuccessResponse(new Date(), "Successfully created user")),
				HttpStatus.CREATED);
	}

	/**
	 * login user
	 * 
	 * @param request
	 * @return
	 */
	@PostMapping("/login")
	public ResponseEntity<Object> loginUser(@Valid @RequestBody LoginRequest request) {
		Authentication authentication;
		try {
			authentication = authManager.authenticate(
					new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		} catch (BadCredentialsException e) {
			 throw new CustomizedBadCredentialsException("Invalid username or password");
		}
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtil.generateToken(authentication);
		CustomizedUserDetails userDetails = (CustomizedUserDetails) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream().map(role -> role.getAuthority())
				.collect(Collectors.toList());
		return new ResponseEntity<Object>(new JwtResponse(jwt, roles), HttpStatus.OK);
	}

}
