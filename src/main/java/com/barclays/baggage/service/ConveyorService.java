/**
 * 
 */
package com.barclays.baggage.service;

import com.barclays.baggage.business.ConveyorGraph;
import com.barclays.baggage.exception.ConveyorServiceException;

/**
 * Feb 3, 2018, 7:57:21 PM
 *
 * ConveyorService.java
 *
 * @author shenais
 *
 *  
 */
public interface ConveyorService extends BaseService {
    
	/**
	 * Method to retrieve conveyor details
	 * @return
	 * @throws ConveyorServiceException
	 */
	public ConveyorGraph getConveyor() throws ConveyorServiceException;
}
