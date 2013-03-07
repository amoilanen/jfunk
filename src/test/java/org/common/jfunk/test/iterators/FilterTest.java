package org.common.jfunk.test.iterators;

import static junit.framework.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collections;

import org.common.jfunk.Iterators;
import org.common.jfunk.test.Functions;
import org.junit.Test;

public class FilterTest {
    
    @Test
    public void filterEmptyIterator() {
        assertEquals(Collections.emptyList(), 
            Iterators.toList(
                    Iterators.filter(Collections.emptyList().iterator(), Functions.falseValue)));
    }

    @Test
    public void filterIterator() {
        assertEquals(Arrays.asList(0, 2, 4, 6, 8),
            Iterators.toList(
                    Iterators.filter(Arrays.<Integer>asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9).iterator(), Functions.isEven)));
    }
}