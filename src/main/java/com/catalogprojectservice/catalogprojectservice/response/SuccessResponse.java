package com.catalogprojectservice.catalogprojectservice.response;

import java.util.Date;

public class SuccessResponse {

	private  Date timeStamp;
	private String message;
	/**
	 * @param timeStamp
	 * @param message
	 */
	public SuccessResponse(Date timeStamp, String message) {
		super();
		this.timeStamp = timeStamp;
		this.message = message;
	}
	/**
	 * 
	 */
	public SuccessResponse() {
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
	
	
	
	
}
