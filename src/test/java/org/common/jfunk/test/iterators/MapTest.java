package org.common.jfunk.test.iterators;

import static junit.framework.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collections;

import org.common.jfunk.Iterators;
import org.common.jfunk.test.Functions;
import org.junit.Test;

public class MapTest {
    
    @Test
    public void mapEmptyIterator() {
        assertEquals(Collections.emptyList(), 
            Iterators.toList(
                    Iterators.map(Collections.emptyList().iterator(), Functions.falseValue)));
    }

    @Test
    public void mapIterator() {
        assertEquals(Arrays.asList(true, false, true, false, true, false, true, false, true, false),
            Iterators.toList(
                    Iterators.map(Arrays.<Integer>asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9).iterator(), Functions.isEven)));
    }
}