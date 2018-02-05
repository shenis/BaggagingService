/**
 * 
 */
package com.barclays.baggage.service;

import java.util.Map;

import com.barclays.baggage.exception.FlightServiceException;
import com.barclays.baggage.vo.FlightDepartureVO;

/**
 * Feb 3, 2018, 7:59:43 PM
 *
 * FlightService.java
 *
 * @author shenais
 *
 *  
 */
public interface FlightService extends BaseService{
    
	/**
	 * Method to reterieve flight service details
	 * @return
	 * @throws FlightServiceException
	 */
	public Map<String, FlightDepartureVO> getFlightService() throws FlightServiceException;
}
