/**
 * 
 */
package com.barclays.test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.barclays.baggage.business.ConveyorGraph;
import com.barclays.baggage.service.ConveyorService;
import com.barclays.baggage.service.Impl.ConveyorServiceImpl;


/**
 * Feb 3, 2018, 9:38:52 PM
 *
 * ConveyorServiceTest.java
 *
 * @author shenais
 *
 *  
 */
public class ConveyorServiceTest {

	private ConveyorService conveyorService;
	@Before
	public void setUp() throws Exception {
		conveyorService = new ConveyorServiceImpl();
	}

	@After
	public void tearDown() throws Exception {
		conveyorService = null;
	}
	
	@Test
	public void testConveyorService() throws Exception{
		ConveyorGraph conveyorGraph = conveyorService.getConveyor();
		Assert.assertNotNull(conveyorGraph);
	}
}
