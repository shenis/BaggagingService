package com.barclays.baggage.business;

import java.util.*;

import com.barclays.baggage.vo.ConveyorLinkVO;
import com.barclays.baggage.vo.ConveyorNodeVO;

/**
 * Feb 3, 2018, 5:53:02 PM
 *
 * ConveyorGraph.java
 *
 * @author shenais
 * 
 * 
 */
public class ConveyorGraph implements Graph<ConveyorNodeVO, ConveyorLinkVO> {

    //adjacency list representation of graph
    private Map<ConveyorNodeVO, List<ConveyorLinkVO>> neighbors = new HashMap<>();

    private Set<ConveyorNodeVO> nodes = new HashSet<>();

    private Set<ConveyorLinkVO> links = new HashSet<>();

    /**
     * Add a node to the graph.
     *
     * @param node
     */
    public void addNode(ConveyorNodeVO node) {
        if (!neighbors.containsKey(node)) {
            neighbors.put(node, new ArrayList<ConveyorLinkVO>());
            nodes.add(node);
        }
    }

    /**
     * Check if link exists between two nodes.
     *
     * @param from
     * @param to
     * @return
     */
    public boolean isLink(ConveyorNodeVO from, ConveyorNodeVO to) {
        List<ConveyorLinkVO> links = neighbors.get(from);
        if (links != null && !links.isEmpty()) {
            for (ConveyorLinkVO conveyorLink : links) {
                if (conveyorLink.getTo().equals(to)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Add a link to the Graph.
     *
     * @param from
     * @param to
     * @param cost
     */
    public void addLink(ConveyorNodeVO from, ConveyorNodeVO to, double cost) {
        addNode(from);
        addNode(to);

        if (!isLink(from, to)) {
            ConveyorLinkVO link = new ConveyorLinkVO(from, to, cost);
            neighbors.get(from).add(link);
            links.add(link);
        }
    }

    @Override
    public ConveyorLinkVO getLink(ConveyorNodeVO source, ConveyorNodeVO target) {
        List<ConveyorLinkVO> links = neighbors.get(source);
        for (ConveyorLinkVO link : links) {
            if (link.getTo().equals(target)) {
                return link;
            }
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * @see com.barclays.baggage.business.Graph#getNodes()
     */
    @Override
    public Iterable<ConveyorNodeVO> getNodes() {
        return nodes;
    }
    
    /*
     * (non-Javadoc)
     * @see com.barclays.baggage.business.Graph#getOrder()
     */
    @Override
    public int getOrder() {
        return neighbors.size();
    }
    
    /*
     * (non-Javadoc)
     * @see com.barclays.baggage.business.Graph#getLinks()
     */
    @Override
    public Iterable<ConveyorLinkVO> getLinks() {
        return links;
    }
    
    /*
     * (non-Javadoc)
     * @see com.barclays.baggage.business.Graph#getSize()
     */
    @Override
    public int getSize() {
        return links.size();
    }
    
    /*
     * (non-Javadoc)
     * @see com.barclays.baggage.business.Graph#containsNode(java.lang.Object)
     */
    @Override
    public boolean containsNode(ConveyorNodeVO conveyorNode) {
        return neighbors.containsKey(conveyorNode);
    }
    
    /*
     * (non-Javadoc)
     * @see com.barclays.baggage.business.Graph#containsLink(java.lang.Object)
     */
    @Override
    public boolean containsLink(ConveyorLinkVO conveyorLink) {
        return links.contains(conveyorLink);
    }

    /**
     * Dijkstra's shortest path implementation
     * http://en.wikipedia.org/wiki/Dijkstra%27s_algorithm
     *
     * @param source
     * @param target
     * @return
     */
    public List<ConveyorNodeVO> findShortestPath(ConveyorNodeVO source, ConveyorNodeVO target) {
        List<ConveyorNodeVO> shortestPath = new ArrayList<>();

        source.setMinDistance(0D);

        PriorityQueue<ConveyorNodeVO> vertexQueue = new PriorityQueue<>();

        for (ConveyorNodeVO vertex : nodes) {
            if (!vertex.equals(source)) {
                vertex.setMinDistance(Double.POSITIVE_INFINITY);
                vertex.setPrevious(null);
            } else {
                vertex = source;
            }
            vertexQueue.add(vertex);
        }

        while (!vertexQueue.isEmpty()) {
            ConveyorNodeVO u = vertexQueue.poll();

            if (u.equals(target)) {
                while (u.getPrevious() != null) {
                    shortestPath.add(u);
                    u = u.getPrevious();
                }
                break;
            }

            vertexQueue.remove(u);

            List<ConveyorLinkVO> edges = neighbors.get(u);

            for (ConveyorLinkVO edge : edges) {
                ConveyorNodeVO v = edge.getTo();

                double weight = edge.getCost();
                double distanceThroughU = u.getMinDistance() + weight;

                if (distanceThroughU < v.getMinDistance()) {
                    v.setMinDistance(distanceThroughU);
                    v.setPrevious(u);
                    vertexQueue.remove(v);
                    vertexQueue.add(v);
                }
            }
        }

        Collections.reverse(shortestPath);

        return shortestPath;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (ConveyorNodeVO node : neighbors.keySet()) {
            sb.append("\n    " + node.getNodeId().getValue() + " -> " + neighbors.get(node));
        }
        return sb.toString();
    }

    public Map<ConveyorNodeVO, List<ConveyorLinkVO>> getNeighbors() {
        return neighbors;
    }

}
