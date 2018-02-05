package com.barclays.baggage.vo;

import java.io.Serializable;
import java.util.Objects;

/**
 * 
 * Feb 3, 2018, 11:05:02 AM
 *
 * ConveyorLink.java
 *
 * @author shenais
 * 
 */
public final class ConveyorLinkVO extends BaseVO implements Serializable{

    /**
	 * serial version userId
	 */
	private static final long serialVersionUID = -5925592259476222182L;
	
	private ConveyorNodeVO from;
    private ConveyorNodeVO to;
    private double cost;

    public ConveyorLinkVO(ConveyorNodeVO from, ConveyorNodeVO to, double cost) {
        this.from = from;
        this.to = to;
        this.cost = cost;
    }

    public ConveyorNodeVO getFrom() {
        return from;
    }

    public ConveyorNodeVO getTo() {
        return to;
    }

    public double getCost() {
        return cost;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || !(obj instanceof ConveyorNodeVO)) {
            return false;
        }

        ConveyorLinkVO other = (ConveyorLinkVO) obj;

        return (this.from.equals(other.from) && this.to.equals(other.to));
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to);
    }

    @Override
    public String toString() {
        return "Link [" + from.getNodeId().getValue() + "->" + to.getNodeId().getValue() + " : " + cost + "]";
    }
}
