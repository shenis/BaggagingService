/**
 * 
 */
package com.barclays.baggage.service.Impl;

import com.barclays.baggage.business.ConveyorGraph;
import com.barclays.baggage.exception.ConveyorServiceException;
import com.barclays.baggage.handler.ConveyorHandler;
import com.barclays.baggage.service.ConveyorService;

/**
 * Feb 3, 2018, 7:56:32 PM
 *
 * ConveyorServiceImpl.java
 *
 * @author shenais
 *
 * 
 */
public class ConveyorServiceImpl implements ConveyorService {
    
	/*
	 * (non-Javadoc)
	 * @see com.barclays.baggage.service.ConveyorService#getConveyor()
	 */
	@Override
	public ConveyorGraph getConveyor() throws ConveyorServiceException {
		ConveyorHandler conveyorGraphHandler = new ConveyorHandler();
		conveyorGraphHandler.process();
		return conveyorGraphHandler.getConveyorGraph();
	}

}
