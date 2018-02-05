/**
 * 
 */
package com.barclays.test;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Feb 3, 2018, 10:23:38 PM
 *
 * BaggageTestSuite.java
 *
 * @author shenais
 *
 *  
 */

@RunWith(Suite.class)

@Suite.SuiteClasses({
   BaggageServiceTest.class,
   ConveyorServiceTest.class,
   FlightServiceTest.class
})
public class BaggageTestSuite {

	
}
