package org.common.jfunk;

import java.util.HashMap;
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
     * Constructs a new set from the provided elements.
     * 
     * @param elems elements to construct the set from
     * @return set that includes all the elements {@code elems}
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
     * Constructs a new set from the provided elements.
     * 
     * @param elems elements to construct the set from
     * @return set that includes all the elements {@code elems}
     */
    public static <K, V> Map<K, V> keepIf(Map<K, V> m, final Predicate<Pair<K, V>> p) {
        return deleteIf(m, Predicate.not(p));
    }
    
    /*

    //sortByKey, after implemented Enumerables.sortBy
    //sortByValue, after implemented Enumerables.sortBy

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

//TODO:

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
