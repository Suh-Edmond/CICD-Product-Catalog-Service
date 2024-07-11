package com.catalogprojectservice.catalogprojectservice.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.catalogprojectservice.catalogprojectservice.exception.NotFoundException;
import com.catalogprojectservice.catalogprojectservice.model.User;
import com.catalogprojectservice.catalogprojectservice.repository.UserRepository;
import com.catalogprojectservice.catalogprojectservice.request.UpdateUserRequest;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private AuthenticationService authService;
	
	
	/**
	 * get user details;
	 * @param userId
	 * @return
	 */
	public User getUserDetails() {
		Authentication authentication  = SecurityContextHolder.getContext().getAuthentication();
		String userName = authentication.getName();
		Optional<User> user = userRepository.findByUsername(userName);
		user.orElseThrow(() -> new NotFoundException("No user found with username "+ userName));
		return user.get();
	}
	
	/**
	 * updated user details
	 * @param request
	 * @param userId
	 */
	public void updateUserDetails(UpdateUserRequest request) {
		User  updatedUser = this.getUserDetails();
		updatedUser.setFirstName(request.getFirstName());
		updatedUser.setLastName(request.getLastName());
		updatedUser.setEmail(request.getEmail());
		updatedUser.setTelephone(request.getTelephone());
		userRepository.save(updatedUser);
	}
	
}
