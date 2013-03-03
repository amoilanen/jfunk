package org.common.jfunk.test.iterators;

import static junit.framework.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.common.jfunk.Iterators;
import org.junit.Test;

public class ConcatTest {
    
    @Test
    public void concatZeroIterators() {
        assertEquals(Collections.emptyList(), 
                Iterators.toList(Iterators.concat()));
    }

    @Test
    public void concatOneIterator() {
        assertEquals(Arrays.asList(1, 2, 3), 
            Iterators.toList(
                Iterators.concat(Arrays.<Integer>asList(1, 2, 3).iterator())));
    }
    
    @Test
    public void concatTwoIterators() {
        assertEquals(Arrays.asList(1, 2, 3, 4, 5, 6), 
            Iterators.toList(
                Iterators.concat(
                        Arrays.<Integer>asList(1, 2, 3).iterator(),
                        Arrays.<Integer>asList(4, 5, 6).iterator()
                )
            )
        );
    }

    @Test
    public void concatSeveralIterators() {
        List<Integer> result = new ArrayList<Integer>();
        List<Iterator<Integer>> toConcat = new ArrayList<Iterator<Integer>>();
        
        for (int i = 0; i < 10; i++) {
            int sectionStart = i * 10;
            int sectionEnd = (i + 1) * 10;
            List<Integer> section = new ArrayList<Integer>();
            
            for (int j = sectionStart; j < sectionEnd; j++) {
                result.add(j);
                section.add(j);
            }
            toConcat.add(section.iterator());
        };
        
        assertEquals(result, 
            Iterators.toList(
                Iterators.concat(toConcat.toArray(new Iterator<?>[]{}))));
    }
}