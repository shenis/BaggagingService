/**
 * 
 */
package com.barclays.baggage.service.Impl;

import java.util.Map;

import com.barclays.baggage.exception.FlightServiceException;
import com.barclays.baggage.handler.FlightDepartureHandler;
import com.barclays.baggage.service.FlightService;
import com.barclays.baggage.vo.FlightDepartureVO;

/**
 * Feb 3, 2018, 7:59:17 PM
 *
 * FlightServiceImpl.java
 *
 * @author shenais
 *
 * 
 */
public class FlightServiceImpl implements FlightService {
    
	/*
	 * (non-Javadoc)
	 * @see com.barclays.baggage.service.FlightService#getFlightService()
	 */
	@Override
	public Map<String, FlightDepartureVO> getFlightService() throws FlightServiceException {
		FlightDepartureHandler flightDepartureHandler = new FlightDepartureHandler();
		flightDepartureHandler.process();
		return flightDepartureHandler.getFlightIdToDepartureMap();
	}

}
