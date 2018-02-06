/**
 * 
 */
package com.barclays.test;

import static org.hamcrest.CoreMatchers.is;

import java.util.Map;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.barclays.baggage.service.FlightService;
import com.barclays.baggage.service.Impl.FlightServiceImpl;
import com.barclays.baggage.vo.FlightDepartureVO;

/**
 * Feb 3, 2018, 9:38:03 PM
 *
 * FlightServiceTest.java
 *
 * @author shenais
 *
 *  
 */
public class FlightServiceTest {

	private FlightService flightService;
	@Before
	public void setUp() throws Exception {
		flightService = new FlightServiceImpl();
	}

	@After
	public void tearDown() throws Exception {
		flightService = null;
	}
	
	@Test
	public void testFlightService() throws Exception{
		
		 Map<String, FlightDepartureVO> flightMap = flightService.getFlightService();
		 Assert.assertNotNull(flightMap);
		 Assert.assertThat(flightMap.size(), is(9));
	}
}
