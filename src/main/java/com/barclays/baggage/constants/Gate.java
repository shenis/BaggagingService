package com.barclays.baggage.constants;

import java.util.HashMap;
import java.util.Map;

/**
 * Feb 1, 2018, 5:54:01 PM
 *
 * Gate.java
 *
 * @author shenais
 * 
 * 
 */
public enum Gate {
    A1("A1"),
    A2("A2"),
    A3("A3"),
    A4("A4"),
    A5("A5"),
    A6("A6"),
    A7("A7"),
    A8("A8"),
    A9("A9"),
    A10("A10"),
    CONCOURSE_A_TICKETING("Concourse_A_Ticketing"),
    BAGGAGE_CLAIM("BaggageClaim");

    public final String value;
    
    private static final Map<String, Gate> GATE_MAP = new HashMap<String, Gate>();

    private Gate(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    static {
        for (Gate gate : Gate.values()) {
            GATE_MAP.put(gate.getValue(), gate);
        }
    }

    public static Gate getGate(String value) {
        return GATE_MAP.get(value);
    }
}
