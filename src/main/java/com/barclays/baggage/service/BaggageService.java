/**
 * 
 */
package com.barclays.baggage.service;

import java.util.Map;

import com.barclays.baggage.exception.BaggageServiceException;
import com.barclays.baggage.vo.BaggageVO;

/**
 * Feb 3, 2018, 7:59:00 PM
 *
 * BaggageService.java
 *
 * @author shenais
 *
 *  
 */
public interface BaggageService extends BaseService{
    
	/**
	 * Method to retrieve Baggage details
	 * 
	 * @return
	 * @throws BaggageServiceException
	 */
	public Map<String, BaggageVO> getBaggageService() throws BaggageServiceException;
}
