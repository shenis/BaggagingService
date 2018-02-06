/**
 * 
 */
package com.barclays.test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Map;

import org.hamcrest.Matchers;
import org.hamcrest.collection.IsMapContaining;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.barclays.baggage.service.BaggageService;
import com.barclays.baggage.service.Impl.BaggageServiceImpl;
import com.barclays.baggage.vo.BaggageVO;


/**
 * Feb 3, 2018, 9:37:48 PM
 *
 * BaggageServiceTest.java
 *
 * @author shenais
 *
 *  
 */
public class BaggageServiceTest {

 private BaggageService baggageService;
	
	@Before
	public void setUp() throws Exception {
		baggageService = new BaggageServiceImpl();
	}

	@After
	public void tearDown() throws Exception {
		baggageService = null;
	}
	
	@Test
	public void testBaggageService() throws Exception{
		
		Map<String, BaggageVO> baggageMap = baggageService.getBaggageService();
		Assert.assertNotNull(baggageMap);
		Assert.assertThat(baggageMap.size(), is(5));
		
	}
}
