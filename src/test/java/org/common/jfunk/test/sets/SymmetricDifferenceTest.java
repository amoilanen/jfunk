package org.common.jfunk.test.sets;

import static junit.framework.Assert.assertEquals;

import java.util.Collections;
import java.util.Set;

import org.common.jfunk.Sets;
import org.junit.Test;

public class SymmetricDifferenceTest {
    
    @Test
    public void symmetricDifferenceTwoNonEmptySets() {
        assertEquals(Sets.set(1, 2, 4, 5),
                Sets.symDifference(Sets.<Integer>set(1, 2, 3), Sets.<Integer>set(3, 4, 5)));
    }

    @Test
    public void symmetricDifferenceEmptyAndNonEmptySets() {        
        assertEquals(Sets.set(3, 4, 5), 
                Sets.<Number>symDifference(Collections.emptySet(), Sets.<Integer>set(3, 4, 5)));
    }

    @Test
    public void symmetricDifferenceNonEmptyAndEmptySets() {
        assertEquals(Sets.set(1, 2, 3), 
                Sets.symDifference(Sets.<Integer>set(1, 2, 3), Collections.emptySet()));
    }

    @Test(expected=NullPointerException.class)
    public void symmetricDifferenceIfFirstSetIsNullThenExceptionIsRaised() {
        Sets.symDifference(null, Sets.<Integer>set(1, 2));
    }

    @Test(expected=NullPointerException.class)
    public void symmetricDifferenceIfSecondSetIsNullThenExceptionIsRaised() {
        Sets.symDifference(Sets.<Integer>set(1, 2), null);
    }

    @Test
    public void symmetricDifferenceTwoSetsOfDifferentTypes() {
        assertEquals(Sets.<Object>set(1, 2, "a", "b"), 
                Sets.symDifference(Sets.<Integer>set(1, 2), Sets.<String>set("a", "b")));
    }
    
    @Test(expected=ClassCastException.class)
    public void symmetricDifferenceTwoSetsOfDifferentTypesAndIncorrectResultTypeThenExceptionIsRaised() {
        Set<Integer> result = Sets.symDifference(Sets.<Integer>set(1, 2), Sets.<String>set("a", "b"));

        for (@SuppressWarnings("unused") Integer e : result) {}
    }
}