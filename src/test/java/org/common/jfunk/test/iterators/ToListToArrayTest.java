package org.common.jfunk.test.iterators;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;

import java.util.Arrays;
import java.util.List;

import org.common.jfunk.Iterators;
import org.junit.Before;
import org.junit.Test;

public class ToListToArrayTest {

    private List<Integer> list;
    
    @Before
    public void before() {
        list = Arrays.asList(1, 2, 3);        
    }
    
    @Test
    public void iteratorToList() {
        assertEquals(list, Iterators.toList(list.iterator()));
    }

    @Test
    public void iteratorToArray() {
        Integer[] expected = new Integer[]{1, 2, 3};
        
        assertArrayEquals(expected, Iterators.toArray(list.iterator(), Integer.class));
    }
}