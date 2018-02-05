package com.barclays.baggage.exception;

/**
 * 
 * Feb 3, 2018, 7:12:21 PM
 *
 * BaggageServiceException.java
 *
 * @author shenais
 *
 *
 */
public class BaggageServiceException extends Exception {

	

	/**
	 * serial version userId
	 */
	private static final long serialVersionUID = 1290914474511749386L;

	public BaggageServiceException() {
		super();
	}
    
	/**
	 * 
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public BaggageServiceException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
    
	/**
	 * 
	 * @param message
	 * @param cause
	 */
	public BaggageServiceException(String message, Throwable cause) {
		super(message, cause);
	}
    
	/**
	 * 
	 * @param message
	 */
	public BaggageServiceException(String message) {
		super(message);
	}
    
	/**
	 * 
	 * @param cause
	 */
	public BaggageServiceException(Throwable cause) {
		super(cause);
	}
}
