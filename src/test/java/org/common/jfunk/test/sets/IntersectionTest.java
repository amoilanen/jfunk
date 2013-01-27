package org.common.jfunk.test.sets;

import static junit.framework.Assert.assertEquals;

import java.util.Collections;

import org.common.jfunk.Sets;
import org.junit.Test;


public class IntersectionTest {
    
    @Test
    public void intersectionOfTwoSets() {
        assertEquals("Intersection of two sets", 
                Sets.set(3, 4, 5),
                Sets.intersection(Sets.<Integer>set(1, 2, 3, 4, 5), Sets.<Integer>set(3, 4, 5, 6, 7)));
    }

    @Test
    public void intersectionOfTwoSetsConvertedToSupertype() {        
        assertEquals("Intersection of two sets", 
                Sets.<Number>set(3, 4, 5), 
                Sets.<Number>intersection(Sets.<Integer>set(1, 2, 3, 4, 5), Sets.<Integer>set(3, 4, 5, 6, 7)));
    }
    @Test
    public void intersectionOfSeveralSets() {
        assertEquals("Intersection of several sets", 
                Sets.set(1, 2, 3), 
                Sets.intersection(Sets.<Integer>set(1, 2, 3, 4, 5), Sets.<Integer>set(1, 2, 3, 4), Sets.<Integer>set(1, 2, 3)));
    }

    @Test(expected=NullPointerException.class)
    public void intersectionOfSeveralSetsOneOfWhichIsNullRaisesException() {
        Sets.intersection(Sets.<Integer>set(1, 2), null);
    }

    @Test
    public void intersectionOfSeveralSetsOfDifferentTypes() {
        assertEquals("Intersection of sets of different types", Collections.<Object>emptySet(), 
                Sets.intersection(Sets.<Integer>set(1, 2), Sets.<String>set("a", "b")));
    }

    @Test
    public void intersectionIfNoArgumentsAreProvidedThenEmptySet() {
        assertEquals("Intersection of no sets", Collections.<Object>emptySet(), Sets.intersection());
    }
}