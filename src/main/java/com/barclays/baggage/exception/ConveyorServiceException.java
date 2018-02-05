package com.barclays.baggage.exception;

/**
 * 
 * Feb 3, 2018, 7:12:30 PM
 *
 * DepartureServiceException.java
 *
 * @author shenais
 *
 *
 */
public class ConveyorServiceException extends Exception{

	/**
	 * serial version userId
	 */
	private static final long serialVersionUID = 1434633165390160711L;

	public ConveyorServiceException() {
		super();
	}
    
	/**
	 * 
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public ConveyorServiceException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
    
	/**
	 * 
	 * @param message
	 * @param cause
	 */
	public ConveyorServiceException(String message, Throwable cause) {
		super(message, cause);
	}
    
	/**
	 * 
	 * @param message
	 */
	public ConveyorServiceException(String message) {
		super(message);
	}
    
	/**
	 * 
	 * @param cause
	 */
	public ConveyorServiceException(Throwable cause) {
		super(cause);
	}

}
