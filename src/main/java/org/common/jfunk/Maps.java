package org.common.jfunk;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Utility functions for working with sets.
 * 
 */
public class Maps {

    @SuppressWarnings("unchecked")
    public static <K,V> Map<K, V> new_map(Object...keyValues) {
        Map<K, V> result = new HashMap<K, V>();
        
        for (int i = 0; i < keyValues.length; i += 2) {
            result.put((K) keyValues[i], (V) keyValues[i + 1]);
        };
        return result;
    };
    
    /**
     * Deletes elements in a map based on the provided condition.
     * 
     * @param m map elements of which will be deleted (map itself is not changed)
     * @param p predicate based on which the elements should be deleted
     * @return map from which were deleted all the elements for which {@code p} was true
     */
    public static <K, V> Map<K, V> deleteIf(Map<K, V> m, Predicate<Pair<K, V>> p) {
        Map<K, V> result = new HashMap<K, V>();

        for (K key : m.keySet()) {
            V value = m.get(key);
            if (!p.call(new Pair<K,V>(key, value))) {
                result.put(key, value);
            };
        };
        return result;
    }

    /**
     * Keeps elements in a map based on the provided condition.
     * 
     * @param m map elements of which will be deleted (map itself is not changed)
     * @param p predicate based on which the elements should be kept
     * @return map in which were kept in place all the elements for which {@code p} was true
     */
    public static <K, V> Map<K, V> keepIf(Map<K, V> m, final Predicate<Pair<K, V>> p) {
        return deleteIf(m, Predicate.not(p));
    }

    /**
     * Sorts pairs in a map based on the provided comparator.
     * 
     * @param m map elements of which will be sorted (map itself is not changed)
     * @param comp comparator which will be used during sorting
     * @return collection of sorted with the comparator {@code comp} pairs
     */
    public static <K, V> Collection<Pair<K, V>> sortBy(Map<K, V> m, Comparator<Pair<K, V>> comp) {
        List<Pair<K, V>> result = new ArrayList<Pair<K, V>>();

        for (K key : m.keySet()) {
            V value = m.get(key);

            result.add(new Pair<K, V>(key, value));
        };
        Collections.sort(result, comp);
        return result;
    };

    /**
     * Sorts pairs in a map by value in each pair.
     * 
     * @param m map elements of which will be sorted (map itself is not changed)
     * @return collection of sorted pairs
     */
    public static <K, V extends Comparable<? super V>> Collection<Pair<K, V>> sortByValue(Map<K, V> m) {        
        return sortBy(m, new Comparator<Pair<K, V>>() {

            public int compare(Pair<K, V> o1, Pair<K, V> o2) {
                return ((Comparable<? super V>) o1.t).compareTo(o2.t);
            }            
        });
    };

    /**
     * Sorts pairs in a map by key in each pair.
     * 
     * @param m map elements of which will be sorted (map itself is not changed)
     * @return collection of sorted pairs
     */
    public static <K extends Comparable<? super K>, V> Collection<Pair<K, V>> sortByKey(Map<K, V> m) {
        return sortBy(m, new Comparator<Pair<K, V>>() {

            public int compare(Pair<K, V> o1, Pair<K, V> o2) {
                return ((Comparable<? super K>) o1.h).compareTo(o2.h);
            }            
        });
    };
    
    /*
TODO: Implement

#each_key
#each_pair
#each_value

#flatten
#invert
#key

#has_key?
#has_value?

#key?
#keys

#length
#member?

//TODO: Which methods from the following do we need?

#merge
#merge!
#rassoc
#rehash
#reject
#reject!
#replace
#select
#select!
#shift
#size
#store
#to_a
#to_hash
#to_s
#update
#value?
#values
#values_at
     */
    
}
