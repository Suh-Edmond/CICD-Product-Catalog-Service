package com.catalogprojectservice.catalogprojectservice.model.security;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.catalogprojectservice.catalogprojectservice.model.User;

public class CustomizedUserDetails implements UserDetails {

	private Long id;
	private String firstName;
	private String lastName;
	private String username;
	private String email;
	private String telephone;
	private String password;
	private List<GrantedAuthority> authorities;

	public CustomizedUserDetails(User user) {
		super();
		this.id = user.getId();
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.username = user.getUsername();
		this.email = user.getEmail();
		this.telephone = user.getTelephone();
		this.password = user.getPassword();
		this.authorities = Arrays.stream(user.getRole().split(",")).map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());
	}

	/**
	 * 
	 */
	public CustomizedUserDetails() {
		super();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		 
		return this.authorities;
	}

	@Override
	public String getPassword() {
		 
		return this.password;
	}

	@Override
	public String getUsername() {
		 
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		 
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		 
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		 
		return true;
	}

	@Override
	public boolean isEnabled() {
		 
		return true;
	}

	public String getFirstName() {
		return this.getFirstName();
	}
	
	public String getLastName() {
		return this.getLastName();
	}
	
	public String getEmail() {
		return this.getEmail();
	}
	
	public String getTelephone() {
		return this.getTelephone();
	}
	
	public Long getId() {
		return this.id;
	}
	
}
