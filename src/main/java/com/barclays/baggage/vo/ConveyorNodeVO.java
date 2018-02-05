package com.barclays.baggage.vo;

import java.io.Serializable;

import com.barclays.baggage.constants.Gate;

/**
 * 
 * Feb 3, 2018, 11:07:07 AM
 *
 * ConveyorNode.java
 *
 * @author shenais
 * 
 */
public class ConveyorNodeVO extends BaseVO implements Comparable<ConveyorNodeVO>,Serializable{

    /**
	 * serial version userId
	 */
	private static final long serialVersionUID = 8307041486987179788L;
	
	private Gate nodeId;
    private String nodeName;
    private Double minDistance = 0D;
    private ConveyorNodeVO previous;

    public ConveyorNodeVO(Gate nodeId, String nodeName){
        this.nodeId = nodeId;
        this.nodeName = nodeName;
    }

    public Gate getNodeId() {
        return nodeId;
    }

    public String getNodeName() {
        return nodeName;
    }

    public Double getMinDistance() {
        return minDistance;
    }

    public void setMinDistance(Double minDistance) {
        this.minDistance = minDistance;
    }

    public ConveyorNodeVO getPrevious() {
        return previous;
    }

    public void setPrevious(ConveyorNodeVO previous) {
        this.previous = previous;
    }

    @Override
    public boolean equals(Object obj){
        if (this == obj){
            return true;
        }

        if (obj == null || !(obj instanceof ConveyorNodeVO)){
            return false;
        }

        ConveyorNodeVO other = (ConveyorNodeVO) obj;

        return (this.nodeId.getValue().equals(other.nodeId.getValue()));
    }

    @Override
    public int hashCode() {
        return nodeId.getValue().hashCode();
    }

    @Override
    public int compareTo(ConveyorNodeVO other) {
        return Double.compare(minDistance, other.minDistance);
    }
}
