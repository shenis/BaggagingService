package com.barclays.baggage.handler;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Logger;

import com.barclays.baggage.constants.Gate;
import com.barclays.baggage.exception.BaggageServiceException;
import com.barclays.baggage.vo.BaggageVO;

/**
 * 
 * Feb 4, 2018, 5:38:52 PM
 *
 * BaggageHandler.java
 *
 * @author shenais
 *
 *
 */
public class BaggageHandler implements InputHandler {
    
	private static final Logger LOGGER = Logger.getLogger(BaggageHandler.class.getName());

    private Map<String, BaggageVO> bagIdToBagMap;
    
    /*
     * (non-Javadoc)
     * @see com.barclays.baggage.handler.InputHandler#process()
     */
    @Override
    public void process() throws BaggageServiceException {
    	LOGGER.info("Inside process() method");
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("input.txt").getFile());

        Scanner scanner = null;
        try{
            scanner = new Scanner(file);
            boolean startBagSection = false;
            boolean endBagSection = false;
            //This section is only intented to read the input from the text file-- hence while and scanner is used
            while (scanner.hasNextLine() && !endBagSection) {
                String line = scanner.nextLine();
                if (line.trim().equals("")){
                    continue;
                }
                
                if (line.startsWith("# Section:")) { //start of a new section
                    if (!line.endsWith("Bags")) { //skip
                    	startBagSection = false;
                        continue;
                    } else if (line.endsWith("Bags")) {
                    	LOGGER.info("Inside Bags Section");
                        startBagSection = true;
                        bagIdToBagMap = new LinkedHashMap<>();
                        continue;
                    } else if (startBagSection && !line.endsWith("Bags")) {
                        endBagSection = true;
                    }
                }

                if (startBagSection && !endBagSection) {
                	assignValues(line);
                }
            }
        }catch(Exception exception){
        	LOGGER.severe("Error in BaggageHandler process() method"+exception.getMessage());
        	throw new BaggageServiceException("Exception in Processing the Baggage Service");
        }
        finally {
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
    	//Format: <bag_number> <entry_point> <flight_id>
        String tokens[] = line.split(" ");
        if (tokens.length != 3) {
        	LOGGER.severe("BAD INPUT FORMAT...Error in BaggageHandler process() method");
            throw new IOException("BAD INPUT FORMAT...");
        }
        String bagId = tokens[0];
        Gate entryGate = Gate.getGate(tokens[1]);
        String flightId = tokens[2];


        if (entryGate == null) {
            throw new BaggageServiceException("INVALID ENTRY GATE FOUND...");
        }

        bagIdToBagMap.put(bagId, new BaggageVO(bagId, entryGate, flightId));
    }
    
    /**
     * 
     * @return
     */
    public Map<String, BaggageVO> getBagIdToBagMap() {
        return bagIdToBagMap;
    }
    
    /**
     * 
     * @param bagIdToBagMap
     */
    public void setBagIdToBagMap(Map<String, BaggageVO> bagIdToBagMap) {
        this.bagIdToBagMap = bagIdToBagMap;
    }
    
}
