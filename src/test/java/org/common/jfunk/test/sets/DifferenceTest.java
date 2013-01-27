package org.common.jfunk.test.sets;

import static junit.framework.Assert.assertEquals;

import java.util.Collections;

import org.common.jfunk.Sets;
import org.junit.Test;

public class DifferenceTest {
    
    @Test
    public void subtractionOfTwoNonEmptySets() {
        assertEquals(Sets.set(3, 4, 5),
                Sets.difference(Sets.<Integer>set(1, 2, 3, 4, 5), Sets.<Integer>set(1, 2)));
    }

    @Test
    public void subtractionOfEmptyAndNonEmptySets() {        
        assertEquals(Collections.emptySet(), 
                Sets.<Number>difference(Collections.emptySet(), Sets.<Integer>set(3, 4, 5)));
    }

    @Test
    public void subtractionOfNonEmptyAndEmptySets() {
        assertEquals(Sets.set(1, 2, 3), 
                Sets.difference(Sets.<Integer>set(1, 2, 3), Collections.emptySet()));
    }

    @Test(expected=NullPointerException.class)
    public void subtractionIfFirstSetIsNullThenExceptionIsRaised() {
        Sets.difference(null, Sets.<Integer>set(1, 2));
    }

    @Test(expected=NullPointerException.class)
    public void subtractionIfSecondSetIsNullThenExceptionIsRaised() {
        Sets.difference(Sets.<Integer>set(1, 2), null);
    }

    @Test
    public void subtractionOfTwoSetsOfDifferentTypes() {
        assertEquals(Sets.<Integer>set(1, 2), 
                Sets.difference(Sets.<Integer>set(1, 2), Sets.<String>set("a", "b")));
    }
}