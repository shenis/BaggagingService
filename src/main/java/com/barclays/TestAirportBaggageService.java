package com.barclays;

import java.util.List;
import java.util.Map;

import com.barclays.baggage.business.ConveyorGraph;
import com.barclays.baggage.constants.Gate;
import com.barclays.baggage.service.BaggageService;
import com.barclays.baggage.service.ConveyorService;
import com.barclays.baggage.service.FlightService;
import com.barclays.baggage.service.Impl.BaggageServiceImpl;
import com.barclays.baggage.service.Impl.ConveyorServiceImpl;
import com.barclays.baggage.service.Impl.FlightServiceImpl;
import com.barclays.baggage.vo.BaggageVO;
import com.barclays.baggage.vo.ConveyorNodeVO;
import com.barclays.baggage.vo.FlightDepartureVO;

/**
 * 
 * Feb 3, 2018, 6:24:50 PM
 *
 * TestAirportBaggageService.java
 *
 * @author shenais
 * 
 */
public class TestAirportBaggageService {
    
	/**
	 * Main method to test the Airport Baggage Service functionality
	 * @param args
	 */
    public static void main(String[] args) {

        ConveyorGraph conveyorGraph = null;
        Map<String, FlightDepartureVO> flightIdToDepartureMap = null;
        Map<String, BaggageVO> bagIdToBagMap = null;
       
        try {
        	//Get Conveyor Service
        	ConveyorService conveyorService = new ConveyorServiceImpl();        	
            conveyorGraph = conveyorService.getConveyor();
            
            //Get Flight Service
            FlightService flightService = new FlightServiceImpl();
        	flightIdToDepartureMap = flightService.getFlightService();
            
        	//Get Baggage Service
        	BaggageService baggageService = new BaggageServiceImpl();
            bagIdToBagMap = baggageService.getBaggageService();
            displayValues(conveyorGraph,flightIdToDepartureMap,bagIdToBagMap);
           
        } catch (Exception ex) {
        	//print stack trace added intentionally because this is a test class with main method
            ex.printStackTrace();
        }
   }
    
    /**
     * Method for constructing output in a structured format as expected in the problem
     * 
     * @param conveyorGraph
     * @param flightIdToDepartureMap
     * @param bagIdToBagMap
     * @throws Exception
     */
    private static void displayValues(ConveyorGraph conveyorGraph,Map<String, FlightDepartureVO> flightIdToDepartureMap,Map<String, BaggageVO> bagIdToBagMap){
    	StringBuilder output = new StringBuilder();
    	for (Map.Entry<String, BaggageVO> entry : bagIdToBagMap.entrySet()) {
            BaggageVO bag = entry.getValue();
            String bagId = bag.getId();
            String flightId = bag.getFlightId();
            Gate sourceGate = bag.getEntryPoint();

            output.append(bagId + " ");


            Gate departureGate = null;
            if (flightId.equals("ARRIVAL")) {
                departureGate = Gate.BAGGAGE_CLAIM;
            } else {
                departureGate = flightIdToDepartureMap.get(flightId).getDepartureGate();
            }

            ConveyorNodeVO sourceNode = new ConveyorNodeVO(sourceGate, sourceGate.getValue());
            ConveyorNodeVO targetNode = new ConveyorNodeVO(departureGate, departureGate.getValue());
            List<ConveyorNodeVO> shortestPath = conveyorGraph.findShortestPath(sourceNode, targetNode);
            displayShortestPath(output,shortestPath,sourceGate);
           
        }
       //Sysout is added intentionally to see the output in the console
       System.out.println(output.toString());
    }
    
    /**
     * Method to construct space and adding appropriate values in the string builder
     * 
     * @param output
     * @param shortestPath
     * @param sourceGate
     * @throws Exception
     */
    private static void displayShortestPath(StringBuilder output,List<ConveyorNodeVO> shortestPath,Gate sourceGate) {
    	 if (!shortestPath.isEmpty()) {
             output.append(sourceGate.getValue());
             output.append(" ");
             ConveyorNodeVO prevNode = shortestPath.get(0);
             output.append(prevNode.getNodeId().getValue());
             output.append(" ");
             for (int i = 1; i < shortestPath.size(); i++) {
                 ConveyorNodeVO current = shortestPath.get(i);
                 prevNode = current;
                 output.append(current.getNodeId().getValue());
                 output.append(" ");
             }
             output.append(": ");
             output.append(prevNode.getMinDistance());
             output.append(System.lineSeparator());
         } else { //PATH NOT FOUND
             output.append("PATH_NOT_EXISTS");
             output.append(System.lineSeparator());
         }
    }
    
}
