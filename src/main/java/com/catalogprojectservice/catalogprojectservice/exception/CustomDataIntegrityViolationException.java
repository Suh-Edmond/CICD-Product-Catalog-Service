package com.catalogprojectservice.catalogprojectservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CustomDataIntegrityViolationException extends RuntimeException {

	/**
	 * @param message
	 */
	public CustomDataIntegrityViolationException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}
