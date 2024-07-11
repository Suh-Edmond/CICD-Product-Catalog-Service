package com.catalogprojectservice.catalogprojectservice.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class LoginRequest {

	@NotNull
	@NotBlank(message = "username must not be null")
	private String username;

	@NotNull
	@NotBlank(message = "passsword must not be null")
	@Size(min = 8)
	private String password;

	/**
	 * @param username
	 * @param password
	 */
	public LoginRequest(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	/**
	 * 
	 */
	public LoginRequest() {
		super();
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

}
