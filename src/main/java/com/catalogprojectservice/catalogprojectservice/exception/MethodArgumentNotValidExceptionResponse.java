package com.catalogprojectservice.catalogprojectservice.exception;

import java.util.Date;
import java.util.HashMap;

public class MethodArgumentNotValidExceptionResponse {

	private Date timeStamp;
	private String message;
	private HashMap<String, String> errors;
	/**
	 * @param timeStamp
	 * @param message
	 * @param errors
	 */
	public MethodArgumentNotValidExceptionResponse(Date timeStamp, String message, HashMap<String, String> errors) {
		super();
		this.timeStamp = timeStamp;
		this.message = message;
		this.errors = errors;
	}
	/**
	 * 
	 */
	public MethodArgumentNotValidExceptionResponse() {
		super();
	}
	/**
	 * @return the timeStamp
	 */
	public Date getTimeStamp() {
		return timeStamp;
	}
	/**
	 * @param timeStamp the timeStamp to set
	 */
	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 * @return the errors
	 */
	public HashMap<String, String> getErrors() {
		return errors;
	}
	/**
	 * @param errors the errors to set
	 */
	public void setErrors(HashMap<String, String> errors) {
		this.errors = errors;
	}
	
	
	
	
	
}
