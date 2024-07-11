package com.catalogprojectservice.catalogprojectservice.exception;

import java.util.Date;
import java.util.HashMap;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
 


@ControllerAdvice
@RestController
public class CustomizedExceptionHandler extends ResponseEntityExceptionHandler{
	
	/**
	 * handle not found resource exception
	 * 
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<Object> handleNotFoundException(Exception ex, WebRequest request) {
		ExceptionResponse response = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<Object>(response, HttpStatus.NOT_FOUND);
	}

	/**
	 * handle unauthorized exception
	 * 
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler(UnAuthorizedException.class)
	public ResponseEntity<Object> handleUnAuthorizedException(Exception ex, WebRequest request) {
		ExceptionResponse response = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));

		return new ResponseEntity<Object>(response, HttpStatus.UNAUTHORIZED);
	}

	/**
	 * handle forbidden exception
	 * 
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler(ForbiddenException.class)
	public ResponseEntity<Object> handleForbiddenException(Exception ex, WebRequest request) {
		ExceptionResponse response = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));

		return new ResponseEntity<Object>(response, HttpStatus.FORBIDDEN);
	}
	
	/**
	 * handle bad credentials exception
	 * 
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler(CustomizedBadCredentialsException.class)
	public ResponseEntity<Object> handleBadCredentialException(Exception ex, WebRequest request) {
		ExceptionResponse response = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
	}
	
	
	/**
	 * handle constraint voilation exception
	 * 
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler(CustomDataIntegrityViolationException.class)
	public ResponseEntity<Object> handleConstraintViolationException(Exception ex, WebRequest request) {
		ExceptionResponse response = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
	}


	/**
	 * handle any other error
	 * 
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleAnyOtherException(Exception ex, WebRequest request) {
		ExceptionResponse response = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<Object>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	
	/**
	 * handle bad requests 
	 */
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		HashMap<String, String> errors = new HashMap<String, String>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String errorField = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(errorField, errorMessage);
		});
		MethodArgumentNotValidExceptionResponse response = new MethodArgumentNotValidExceptionResponse(new Date(),
				"Invalid Arguments", errors);
		return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
	}
	   
	
	
	
	
}
