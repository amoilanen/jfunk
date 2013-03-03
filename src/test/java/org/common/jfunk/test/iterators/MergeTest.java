package org.common.jfunk.test.iterators;

import static junit.framework.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collections;

import org.common.jfunk.Iterators;
import org.junit.Test;

public class MergeTest {
  
    @Test
    public void mergeZeroIterators() {
        assertEquals(Collections.emptyList(), Iterators.toList(Iterators.merge()));
    }

    @Test
    public void mergeOneIterator() {
        assertEquals(Arrays.asList(1, 2, 3), 
            Iterators.toList(
                Iterators.merge(Arrays.<Integer>asList(1, 2, 3).iterator())));
    }
    
    @Test
    public void mergeTwoIterators() {
        assertEquals(Arrays.asList(1, 4, 2, 5, 3, 6), 
            Iterators.toList(
                Iterators.merge(
                    Arrays.<Integer>asList(1, 2, 3).iterator(),
                    Arrays.<Integer>asList(4, 5, 6).iterator()
                )
            )
        );
    }

    @Test
    public void mergeTwoIteratorsFirstIsShorter() {
        assertEquals(Arrays.asList(1, 4, 2, 5, 3, 6, 7, 8, 9), 
            Iterators.toList(
                Iterators.merge(
                    Arrays.<Integer>asList(1, 2, 3).iterator(),
                    Arrays.<Integer>asList(4, 5, 6, 7, 8, 9).iterator()
                )
            )
        );
    }

    @Test
    public void mergeTwoIteratorsSecondIsShorter() {
        assertEquals(Arrays.asList(1, 7, 2, 8, 3, 9, 4, 5, 6), 
            Iterators.toList(
                Iterators.merge(
                    Arrays.<Integer>asList(1, 2, 3, 4, 5, 6).iterator(),
                    Arrays.<Integer>asList(7, 8, 9).iterator()
                )
            )
        );
    }
    
    @Test
    public void mergeSeveralIterators() {
        assertEquals(Arrays.asList(1, 4, 7, 2, 5, 8, 3, 6, 9), 
            Iterators.toList(
                Iterators.merge(
                    Arrays.<Integer>asList(1, 2, 3).iterator(),
                    Arrays.<Integer>asList(4, 5, 6).iterator(),
                    Arrays.<Integer>asList(7, 8, 9).iterator()
                )
            )
        );
    }
}