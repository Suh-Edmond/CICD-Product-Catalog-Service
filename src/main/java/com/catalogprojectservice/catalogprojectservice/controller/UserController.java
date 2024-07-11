package com.catalogprojectservice.catalogprojectservice.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.catalogprojectservice.catalogprojectservice.request.UpdateUserRequest;
import com.catalogprojectservice.catalogprojectservice.response.UserResponse;
import com.catalogprojectservice.catalogprojectservice.service.UserService;

@CrossOrigin()
@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	UserService userService;

	private UserResponse userResponse;

	public UserController() {
		this.userResponse = new UserResponse();
	}

	/**
	 * get user details
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/profile")
	public UserResponse getUserDetails() {
		return this.userResponse.convertUserModelToUserResponse(userService.getUserDetails());
	}

	/**
	 * update user details
	 * 
	 * @param request
	 * @param id
	 * @return
	 */
	@PutMapping("/profile/update")
	public ResponseEntity<UserResponse> updateUserDetails(@Valid @RequestBody UpdateUserRequest request) {
		userService.updateUserDetails(request);
		UserResponse response = this.userResponse.convertUserModelToUserResponse(userService.getUserDetails());
		return new ResponseEntity<UserResponse>(response, HttpStatus.ACCEPTED);
	}
}
