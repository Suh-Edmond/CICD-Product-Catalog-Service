package com.catalogprojectservice.catalogprojectservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UnAuthorizedException  extends RuntimeException {

	/**
	 * @param message
	 */
	public UnAuthorizedException(String message) {
		super(message);
		 
	}
		
}
