package com.catalogprojectservice.catalogprojectservice.request;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.catalogprojectservice.catalogprojectservice.model.User;

public class RegisterRequest {

	@NotNull
	@NotBlank(message = "firstName cannot be null")
	@Size(min = 3, max = 20)
	private String firstName;

	@NotNull
	@NotBlank(message = "lastName cannot be null")
	@Size(min = 3, max = 20)
	private String lastName;

	
	@NotNull
	@NotBlank(message = "username cannot be null")
	@Size(min = 3, max = 20)
	private String userName;

	
	@NotNull
	@NotBlank(message = "email cannot be null")
	@Size(max = 255)
	@Email(message = "Invalid email")
	private String email;

	
	@NotNull
	@NotBlank(message = "telephone cannot be null")
	@Size(max = 20)
	@Pattern(regexp = "^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{3})[- ]?(\\d{3})$", message = "Invalid phonenumber")
	private String telephone;

	@NotNull
	@NotBlank(message = "password cannot be null")
	@Size(min = 8, max = 255)
	private String password;

	/**
	 * constructor
	 * @param firstName
	 * @param lastName
	 * @param userName
	 * @param email
	 * @param telephone
	 * @param password
	 */
	public RegisterRequest( String firstName, String lastName, String username,
			String email, String telephone, String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = username;
		this.email = email;
		this.telephone = telephone;
		this.password = password;
	}

	/**
	 * constructor
	 */
	public RegisterRequest() {
		super();
	}
	
	/**
	 * convert a user request to a user model
	 * @param request
	 * @return
	 */
	public User convertFromUserRequestToUserEntity(RegisterRequest request) {
		User user = new User();
		user.setFirstName(request.getFirstName());
		user.setLastName(request.getLastName());
		user.setUsername(request.getUsername());
		user.setEmail(request.getEmail());
		user.setTelephone(request.getTelephone());
		user.setPassword(request.getPassword());
		user.setRole("vendor");
		return user;
		
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
	 * @return the userName
	 */
	public String getUsername() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String username) {
		this.userName = username;
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
