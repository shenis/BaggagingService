package com.barclays.baggage.handler;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Logger;

import com.barclays.baggage.business.ConveyorGraph;
import com.barclays.baggage.constants.Gate;
import com.barclays.baggage.exception.ConveyorServiceException;
import com.barclays.baggage.vo.ConveyorNodeVO;

/**
 * 
 * Feb 4, 2018, 5:39:06 PM
 *
 * ConveyorHandler.java
 *
 * @author shenais
 *
 *
 */
public class ConveyorHandler implements InputHandler {
    
	private static final Logger LOGGER = Logger.getLogger(ConveyorHandler.class.getName());
    private ConveyorGraph conveyorGraph;

    private Map<Gate, ConveyorNodeVO> gateNodeMap = new HashMap<>();
    
    /*
     * (non-Javadoc)
     * @see com.barclays.baggage.handler.InputHandler#process()
     */
    @Override
    public void process() throws ConveyorServiceException {
    	LOGGER.info("Inside process() method");
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("input.txt").getFile());

        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
            boolean startGraphSection = false;
            boolean endGraphSection = false;
            //This section is only intented to read the input from the text file-- hence while and scanner is used
            while (scanner.hasNextLine() && !endGraphSection) {
                String line = scanner.nextLine();

                if (line.trim().equals("")){
                    continue;
                }

                if (line.startsWith("# Section:")) { //start of a new section
                    if (!line.endsWith("Conveyor System")) { //skip
                        startGraphSection = false;
                        continue;
                    } else if (line.endsWith("Conveyor System")) {
                    	LOGGER.info("Inside Conveyor Section");
                        startGraphSection = true;
                        conveyorGraph = new ConveyorGraph();
                        continue;
                    } else if (startGraphSection && !line.endsWith("Conveyor System")) {
                        endGraphSection = true;
                    }
                }

                if (startGraphSection && !endGraphSection) {
                	assignValues(line);
                }

            }
        } catch(Exception e){
        	LOGGER.severe("Error in ConveyorHandler process() method"+e.getMessage());
        	throw new ConveyorServiceException("Exception in processing the Conveyor Service");
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
    	 //format :: <Node 1> <Node 2> <travel_time>
        String tokens[] = line.split(" ");
        if (tokens.length != 3) {
            throw new IOException("BAD INPUT FORMAT...");
        }
        String from = tokens[0];
        String to = tokens[1];
        int cost = Integer.parseInt(tokens[2]);

        Gate fromGate = Gate.getGate(from);
        Gate toGate = Gate.getGate(to);

        if (fromGate == null || toGate == null) {
            throw new ConveyorServiceException("INVALID GATE FOUND...");
        }

        //add the bi-directional link in the barclays
        conveyorGraph.addLink(createNode(fromGate, gateNodeMap), createNode(toGate, gateNodeMap), cost);
        conveyorGraph.addLink(createNode(toGate, gateNodeMap), createNode(fromGate, gateNodeMap), cost);
    }
    
    /**
     * 
     * @return
     */
    public ConveyorGraph getConveyorGraph() {
        return conveyorGraph;
    }
    
    /**
     * 
     * @param conveyorGraph
     */
    public void setConveyorGraph(ConveyorGraph conveyorGraph) {
        this.conveyorGraph = conveyorGraph;
    }
    
    /**
     * Method tocreate node ans assign values
     * @param gate
     * @param nodeMap
     * @return
     */
    private ConveyorNodeVO createNode(Gate gate, Map<Gate, ConveyorNodeVO> nodeMap) {
        if (nodeMap.containsKey(gate)) {
            return nodeMap.get(gate);
        }
        ConveyorNodeVO conveyorNode = new ConveyorNodeVO(gate, gate.getValue());
        nodeMap.put(gate, conveyorNode);
        return conveyorNode;
    }
    
}
