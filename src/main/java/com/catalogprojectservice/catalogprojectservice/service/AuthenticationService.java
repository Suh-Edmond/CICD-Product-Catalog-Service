package com.catalogprojectservice.catalogprojectservice.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.catalogprojectservice.catalogprojectservice.exception.CustomDataIntegrityViolationException;
import com.catalogprojectservice.catalogprojectservice.exception.NotFoundException;
import com.catalogprojectservice.catalogprojectservice.model.User;
import com.catalogprojectservice.catalogprojectservice.model.security.CustomizedUserDetails;
import com.catalogprojectservice.catalogprojectservice.repository.UserRepository;

@Service
public class AuthenticationService  implements UserDetailsService {

	@Autowired
	UserRepository userRepository;
	
	/**
	 * hash and save user user
	 * @param user
	*/
	public void addNewUser(User user) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		try {
			userRepository.save(user);
		}catch(DataIntegrityViolationException e) {
			throw new CustomDataIntegrityViolationException("username, email, telephone must be unique");
		}
		
		
	}
	
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = userRepository.findByUsername(username);
		user.orElseThrow(()-> new NotFoundException("No user found with username "+ username));
		return new CustomizedUserDetails(user.get());
	}
	
}

