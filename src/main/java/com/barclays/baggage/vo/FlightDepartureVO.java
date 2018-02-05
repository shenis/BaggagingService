package com.barclays.baggage.vo;

import java.io.Serializable;

import com.barclays.baggage.constants.Gate;

/**
 * 
 * Feb 3, 2018, 11:08:02 AM
 *
 * FlightDeparture.java
 *
 * @author shenais
 * 
 */
public class FlightDepartureVO extends BaseVO implements Serializable{
	
    /**
	 * serial version userId
	 */
	private static final long serialVersionUID = 152655928039308644L;
	
	private String flightId;
    private Gate departureGate;
    private String destination;
    private String departureTime;

    public FlightDepartureVO(){
    }

    public FlightDepartureVO(String flightId, Gate departureGate, String destination, String departureTime){
        this.flightId = flightId;
        this.departureGate = departureGate;
        this.destination = destination;
        this.departureTime = departureTime;
    }

    public String getFlightId() {
        return flightId;
    }

    public void setFlightId(String flightId) {
        this.flightId = flightId;
    }

    public Gate getDepartureGate() {
        return departureGate;
    }

    public void setDepartureGate(Gate departureGate) {
        this.departureGate = departureGate;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    @Override
    public String toString() {
        return "FlightDeparture{" +
                "flightId='" + flightId + '\'' +
                ", departureGate=" + departureGate +
                ", destination='" + destination + '\'' +
                ", departureTime='" + departureTime + '\'' +
                '}';
    }
}
