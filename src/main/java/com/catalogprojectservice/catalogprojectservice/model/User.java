package com.catalogprojectservice.catalogprojectservice.model;


import java.util.List;



import java.util.List;


import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;



import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@Entity
@ApiModel("Description of user model")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long Id;
	
	
	@ApiModelProperty(notes = "first name must have at least 3 characters")
	private String firstName;
	
	
	@ApiModelProperty(notes = "last name must have at least 3 characters")
	private String lastName;
	
	
	@Column(unique=true)
	@ApiModelProperty(notes = "username must be unique")
	private String username;
	
	
	@Column(unique=true)
	@ApiModelProperty(notes = "email must be unique")
	private String email;
	
	
	@ApiModelProperty(notes = "telephone must be unique")
	@Column(unique=true)
	private String telephone;
	
	
	@ApiModelProperty(notes = "password must have at least 8 characters")
	private String password;
	
	
	private String role;
	@OneToMany(mappedBy="user")
	private List<Category> category;

	public List<Category> getCategory() {
		return category;
	}

	public void setCategory(List<Category> category) {
		this.category = category;
	}

	/**
	 * @param id
	 * @param firstName
	 * @param lastName
	 * @param username
	 * @param email
	 * @param telephone
	 * @param password
	 */
	public User(Long id, String firstName, String lastName, String username, String email, String telephone,
			String role, String password) {
		super();
		Id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.email = email;
		this.telephone = telephone;
		this.role = role;
		this.password = password;
	}

	/**
	 * 
	 */
	public User() {
		super();
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return Id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		Id = id;
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
	 * @return the role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(String role) {
		this.role = role;
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
