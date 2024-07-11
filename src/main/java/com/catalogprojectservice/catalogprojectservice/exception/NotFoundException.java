package com.catalogprojectservice.catalogprojectservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException  extends RuntimeException{

	/**
	 * @param message
	 */
	public NotFoundException(String message) {
		super(message);
		 
	}

	
	
}
