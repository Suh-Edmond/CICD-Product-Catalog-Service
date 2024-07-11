package com.catalogprojectservice.catalogprojectservice.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UpdateUserRequest {

	@NotNull
	@NotBlank(message = "firstName must not be null")
	@Size(min = 3, max = 20)
	private String firstName;
	
	@NotNull
	@NotBlank(message = "lastName must not be null")
	@Size(min = 3, max = 20)
	private String lastName;
	
	@NotNull
	@NotBlank(message = "email must not be null")
	@Email(message = "Invalid email")
	@Size(max = 255)
	private String email;
	
	@NotNull
	@NotBlank(message = "telephone must not be null")
	@Size(max = 20)
	@Pattern(regexp = "^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{3})[- ]?(\\d{3})$", message = "Invalid phonenumber")
	private String telephone;
	/**
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param telephone
	 */
	public UpdateUserRequest(String firstName, String lastName, String email, String telephone) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.telephone = telephone;
	}
	/**
	 * 
	 */
	public UpdateUserRequest() {
		super();
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
	
	
	
}
