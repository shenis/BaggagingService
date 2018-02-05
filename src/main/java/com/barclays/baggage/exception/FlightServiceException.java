package com.barclays.baggage.exception;

/**
 * 
 * Feb 3, 2018, 7:12:01 PM
 *
 * AirportServiceException.java
 *
 * @author shenais
 *
 *
 */
public class FlightServiceException extends Exception{

	

	/**
	 * serial version userId
	 */
	private static final long serialVersionUID = 1L;

	public FlightServiceException() {
		super();
	}
    
	/**
	 * 
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public FlightServiceException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
    
	/**
	 * 
	 * @param message
	 * @param cause
	 */
	public FlightServiceException(String message, Throwable cause) {
		super(message, cause);
	}
    
	/**
	 * 
	 * @param message
	 */
	public FlightServiceException(String message) {
		super(message);
	}
    
	/**
	 * 
	 * @param cause
	 */
	public FlightServiceException(Throwable cause) {
		super(cause);
	}

}
