package org.common.jfunk.test.sets;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;

import java.util.Collections;
import java.util.Set;

import org.common.jfunk.Sets;
import org.junit.Test;

public class UnionTest {
    
    @Test
    public void unionOfTwoSets() {
        assertEquals("Union of two sets", 
                Sets.set(1, 2, 3, 4, 5),
                Sets.union(Sets.<Integer>set(1, 2, 3), Sets.<Integer>set(3, 4, 5)));
    }

    @Test
    public void unionOfTwoSetsConvertedToSupertype() {        
        assertEquals("Union of two sets", 
                Sets.<Number>set(1, 2, 3, 4, 5), 
                Sets.<Number>union(Sets.<Integer>set(1, 2, 3), Sets.<Integer>set(3, 4, 5)));
    }

    @Test
    public void unionOfSeveralSets() {
        assertEquals("Union of several sets", 
                Sets.set(1, 2, 3, 4, 5, 6), 
                Sets.union(Sets.<Integer>set(1, 2), Sets.<Integer>set(3, 4), Sets.<Integer>set(5, 6)));
    }

    @Test(expected=NullPointerException.class)
    public void unionOfSeveralSetsOneOfWhichIsNullRaisesException() {
        Sets.union(Sets.<Integer>set(1, 2), null);
    }

    @Test
    public void unionOfSeveralSetsOfDifferentTypesRaisesExceptionLaterInRuntimeIfWrongResultTypeIsUsed() {
        assertEquals("Union of several sets", 
                Sets.<Object>set(1, 2, "a", "b"), 
                Sets.union(Sets.<Integer>set(1, 2), Sets.<String>set("a", "b")));

        try {
            Set<Integer> union = Sets.union(Sets.<Integer>set(1), Sets.<String>set("a"));

            for (@SuppressWarnings("unused") Integer e : union) {
            }
            fail("String should not have been cast to Integer, error in the test itself?");
        } catch (ClassCastException e) {
            assertEquals("java.lang.String cannot be cast to java.lang.Integer", e.getMessage());
        } 
    }

    @Test
    public void unionIfNoArgumentsAreProvidedThenEmptySet() {
        assertEquals("Union of no sets", Collections.<Object>emptySet(), Sets.union());
    }
}