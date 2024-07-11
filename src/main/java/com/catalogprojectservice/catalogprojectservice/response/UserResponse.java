package com.catalogprojectservice.catalogprojectservice.response;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.sis.internal.simple.CitationConstant.Authority;

import com.catalogprojectservice.catalogprojectservice.model.User;
import com.catalogprojectservice.catalogprojectservice.model.security.CustomizedUserDetails;

public class UserResponse {

	private Long id;
	private String firstName;
	private String lastName;
	private String username;
	private String email;
	private String telephone;
	private String roles;

	/**
	 * @param id
	 * @param firstName
	 * @param lastName
	 * @param username
	 * @param email
	 * @param telephone
	 * @param roles
	 */
	public UserResponse(Long id, String firstName, String lastName, String username, String email, String telephone,
			String roles) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.email = email;
		this.telephone = telephone;
		this.roles = roles;
	}

	/**
	 * 
	 */
	public UserResponse() {
		super();
	}

	/**
	 * 
	 * convert user entity to user response
	 * 
	 * @param user
	 * @return
	 */
	public UserResponse convertUserModelToUserResponse(User user) {
		UserResponse userResponse = new UserResponse();
		userResponse.setId(user.getId());
		userResponse.setFirstName(user.getFirstName());
		userResponse.setLastName(user.getLastName());
		userResponse.setEmail(user.getEmail());
		userResponse.setTelephone(user.getTelephone());
		userResponse.setUsername(user.getUsername());
		userResponse.setRoles(user.getRole());
		return userResponse;
	}

	 

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
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
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the telephone
	 */
	public String getTelephone() {
		return telephone;
	}

	/**
	 * @param telephone the telephone to set
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	/**
	 * @return the roles
	 */
	public String getRoles() {
		return roles;
	}

	/**
	 * @param roles the roles to set
	 */
	public void setRoles(String roles) {
		this.roles = roles;
	}

}
