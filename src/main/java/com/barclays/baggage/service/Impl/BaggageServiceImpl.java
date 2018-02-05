/**
 * 
 */
package com.barclays.baggage.service.Impl;

import java.util.Map;

import com.barclays.baggage.exception.BaggageServiceException;
import com.barclays.baggage.handler.BaggageHandler;
import com.barclays.baggage.service.BaggageService;
import com.barclays.baggage.vo.BaggageVO;

/**
 * Feb 3, 2018, 7:58:18 PM
 *
 * BaggageServiceImpl.java
 *
 * @author shenais
 *
 *  
 */
public class BaggageServiceImpl implements BaggageService{

	/*
	 * 	(non-Javadoc)
	 * @see com.barclays.baggage.service.BaggageService#getBaggageService()
	 */
	@Override
	public Map<String, BaggageVO> getBaggageService() throws BaggageServiceException{
		BaggageHandler bagHandler = new BaggageHandler();
		bagHandler.process();
        return bagHandler.getBagIdToBagMap();
	}

}
