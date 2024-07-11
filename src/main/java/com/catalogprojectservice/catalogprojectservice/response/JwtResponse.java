package com.catalogprojectservice.catalogprojectservice.response;

import java.util.List;

public class JwtResponse {
	
	private String jwt;
	private List<String> roles;
	/**
	 * @param token
	 * @param id
	 * @param roles
	 */
	public JwtResponse(String jwt,  List<String> roles) {
		super();
		this.jwt = jwt;
		this.roles = roles;
	}
	/**
	 * 
	 */
	public JwtResponse() {
		super();
	}
	/**
	 * @return the token
	 */
	public String getJwt() {
		return jwt;
	}
	/**
	 * @param token the token to set
	 */
	public void setJwt(String jwt) {
		this.jwt = jwt;
	}
	
	/**
	 * @return the roles
	 */
	public List<String> getRoles() {
		return roles;
	}
	/**
	 * @param roles the roles to set
	 */
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	
	
}
