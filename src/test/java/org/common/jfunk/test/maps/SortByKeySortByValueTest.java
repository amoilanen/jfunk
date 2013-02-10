package org.common.jfunk.test.maps;

import static junit.framework.Assert.assertEquals;

import java.util.Arrays;
import java.util.Comparator;

import org.common.jfunk.Maps;
import org.common.jfunk.Pair;
import org.junit.Test;

public class SortByKeySortByValueTest {
    
    @SuppressWarnings("unchecked")
    @Test
    public void sortBy() {
        assertEquals(
            Arrays.asList(
                new Pair<String, Integer>("key3", 1), 
                new Pair<String, Integer>("key2", 2), 
                new Pair<String, Integer>("key1", 3)
            ), 
            Maps.sortBy(
                Maps.<String, Integer>new_map("key1", 3, "key2", 2, "key3", 1), 
                new Comparator<Pair<String, Integer>>() {

                    public int compare(Pair<String, Integer> o1, Pair<String, Integer> o2) {
                        return o1.t - o2.t;
                    }
                }
            )
        );
    }

    @SuppressWarnings("unchecked")
    @Test
    public void sortByValue() {
        assertEquals(
            Arrays.asList(
                new Pair<String, Integer>("key3", 1), 
                new Pair<String, Integer>("key2", 2), 
                new Pair<String, Integer>("key1", 3)
            ), 
            Maps.sortByValue(
                Maps.<String, Integer>new_map("key1", 3, "key2", 2, "key3", 1)
            )
        );
    }

    @SuppressWarnings("unchecked")
    @Test
    public void sortByKey() {
        assertEquals(
            Arrays.asList(
                new Pair<Integer, String>(1, "value3"), 
                new Pair<Integer, String>(2, "value2"), 
                new Pair<Integer, String>(3, "value1")
            ), 
            Maps.sortByKey(
                Maps.<Integer, String>new_map(3, "value1", 2, "value2", 1, "value3")
            )
        );
    }
}