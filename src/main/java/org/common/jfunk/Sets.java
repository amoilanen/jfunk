package org.common.jfunk;

import java.util.HashSet;
import java.util.Set;

/**
 * Utility functions for working with sets similar to those available for Ruby sets. 
 * Result is always a {@link java.util.HashSet}
 * 
 */
public class Sets {
    
    /**
     * Constructs a new set from the provided elements.
     * 
     * @param elems elements to construct the set from
     * @return set that includes all the elements {@code elems}
     */
    public static <T> Set<T> set(T... elems) {
        Set<T> result = new HashSet<T>();

        for (T e : elems) {
            result.add(e);
        };
        return result;
    }
    
    /**
     * Computing the union of two sets
     * 
     * @param sets - sets to be united
     * @return union of the sets
     */
    @SuppressWarnings("unchecked")
    public static <T> Set<T> union(Set<?>... sets) {
        Set<T> result = new HashSet<T>();
        
        for (Set<?> set : sets) {            
            result.addAll((Set<T>) set);
        };
        return result;
    }
    
    /**
     * Computing the intersection of two sets
     * 
     * @param sets - sets to be intersected
     * @return intersection of the sets
     */
    public static <T> Set<T> intersection(Set<?>...sets) {        
        @SuppressWarnings("unchecked")
        Set<T> result = (sets.length > 0) ? (Set<T>) sets[0] : new HashSet<T>();

        for (int i = 1; i < sets.length; i++) {
            result = intersectionOfTwo(result, sets[i]);
        };
        return result;
    }

    //difference
    //symmetric difference
    //isSubset
    //isSuperset
    
 
    //#classify
    //#divide
    //#flatten
    
    @SuppressWarnings("unchecked")
    private static <T> Set<T> intersectionOfTwo(Set<?> x, Set<?> y) {
        Set<T> result = new HashSet<T>();
        
        for (Object e : x) {
            if (y.contains(e)) {
                result.add((T) e);
            }
        };
        return result;
    }
}