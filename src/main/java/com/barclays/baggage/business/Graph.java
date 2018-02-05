package com.barclays.baggage.business;

/**
 * Feb 1, 2018, 5:54:07 PM
 *
 * Graph.java
 *
 * @author shenais
 */
public interface Graph<V, E> {
    
	/**
	 * 
	 * @return
	 */
    Iterable<V> getNodes();
    
    /**
     * 
     * @return
     */
    int getOrder();
    
    /**
     * 
     * @return
     */
    Iterable<E> getLinks();
    
    /**
     * 
     * @return
     */
    int getSize();
    /**
     * 
     * @param source
     * @param target
     * @return
     */
    E getLink(V source, V target);
    
    /**
     * 
     * @param v
     * @return
     */
    boolean containsNode(V target);
    
    /**
     * 
     * @param element
     * @return
     */
    boolean containsLink(E element);
}
