package com.barclays.baggage.handler;

import com.barclays.baggage.constants.Gate;
import com.barclays.baggage.exception.FlightServiceException;
import com.barclays.baggage.vo.FlightDepartureVO;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Logger;

/**
 * Feb 2, 2018, 5:15:02 PM
 *
 * FlightDepartureHandler.java
 *
 * @author shenais
 * 
 * 
 */
public class FlightDepartureHandler implements InputHandler {
    
	private static final Logger LOGGER = Logger.getLogger(FlightDepartureHandler.class.getName());
    Map<String, FlightDepartureVO> flightIdToDepartureMap;
    
    /*
     * (non-Javadoc)
     * @see com.barclays.baggage.handler.InputHandler#process()
     */
    @Override
    public void process() throws FlightServiceException {
    	LOGGER.info("Inside process() method");
    	
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("input.txt").getFile());

        Scanner scanner = null;
        try{
            scanner = new Scanner(file);
            boolean startDepartureSection = false;
            boolean endDepartureSection = false;
            //This section is only intented to read the input from the text file-- hence while and scanner is used
            while (scanner.hasNextLine() && !endDepartureSection) {
                String line = scanner.nextLine();

                if (line.trim().equals("")){
                    continue;
                }

                if (line.startsWith("# Section:")) { //start of a new section
                    if (!line.endsWith("Departures")) { //skip
                        startDepartureSection = false;
                        continue;
                    } else if (line.endsWith("Departures")) {
                    	LOGGER.info("Inside FlightDeparture Section");
                        startDepartureSection = true;
                        flightIdToDepartureMap = new HashMap<>();
                        continue;
                    } else if (startDepartureSection && !line.endsWith("Departures")) {
                        endDepartureSection = true;
                    }
                }

                if (startDepartureSection && !endDepartureSection) {
                	assignValues(line);
                }

            }
        }catch(Exception e){
        	LOGGER.severe("Error in FlightDepartureHandler process() method"+e.getMessage());
        	throw new FlightServiceException("Exception in processing the flight service");
        }finally {
        	if(scanner != null){
        		scanner.close();
        	}
        }
    }
    
    /**
     * Method to assign values
     * @param line
     * @throws Exception
     */
    private void assignValues(String line) throws Exception{
    	//Format: <flight_id> <flight_gate> <destination> <flight_time>
        String tokens[] = line.split(" ");
        if (tokens.length != 4) {
            throw new IOException("BAD INPUT FORMAT...");
        }
        String flightId = tokens[0];
        String flightGate = tokens[1];
        String destination = tokens[2];
        String departureTime = tokens[3];

        Gate departureGate = Gate.getGate(flightGate);

        if (departureGate == null) {
            throw new FlightServiceException("INVALID GATE FOUND...");
        }

        flightIdToDepartureMap.put(flightId, new FlightDepartureVO(flightId, departureGate, destination, departureTime));
    }
    
    /**
     * 
     * @return
     */
    public Map<String, FlightDepartureVO> getFlightIdToDepartureMap() {
        return flightIdToDepartureMap;
    }
    
    /**
     * 
     * @param flightIdToDepartureMap
     */
    public void setFlightIdToDepartureMap(Map<String, FlightDepartureVO> flightIdToDepartureMap) {
        this.flightIdToDepartureMap = flightIdToDepartureMap;
    }
    
}
