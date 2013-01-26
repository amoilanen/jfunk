package org.common.jfunk;

import java.util.HashSet;
import java.util.Set;

/**
 * Utility functions for working with sets. Result is always a {@link java.util.HashSet}
 * 
 */
public class Sets {

    //union
    //intersection
    //difference
    //symmetric difference
    //isSubset
    //isSuperset
    
    public static <T> Set<T> set(T... elems) {
        Set<T> result = new HashSet<T>();

        for (T e : elems) {
            result.add(e);
        };
        return result;
    }
    
    @SuppressWarnings("unchecked")
    public static <T> Set<T> union(Set<?>... sets) {
        Set<T> result = new HashSet<T>();
        
        for (Set<?> set : sets) {            
            result.addAll((Set<T>) set);
        };
        return result;
    }
}