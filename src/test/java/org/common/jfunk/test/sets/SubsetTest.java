package org.common.jfunk.test.sets;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

import java.util.Collections;

import org.common.jfunk.Sets;
import org.junit.Test;

public class SubsetTest {
    
    @Test
    public void subsetOneSetIsSubset() {
        assertTrue(Sets.firstContainsSecond(Sets.<Integer>set(1, 2, 3, 4, 5), Sets.<Integer>set(1, 2)));
    }

    @Test
    public void subsetOneSetIsNotASubset() {
        assertFalse(Sets.firstContainsSecond(Sets.<Integer>set(1, 2, 3, 4, 5), Sets.<Integer>set(6, 7)));
    }

    @Test
    public void subsetFirstSetIsEmpty() {
        assertFalse(Sets.firstContainsSecond(Collections.emptySet(), Sets.<Integer>set(1, 2)));
    }

    @Test
    public void subsetSecondSetIsEmpty() {
        assertTrue(Sets.firstContainsSecond(Sets.<Integer>set(1, 2, 3, 4, 5), Collections.emptySet()));
    }

    @Test
    public void subsetBothSetsAreEmpty() {
        assertTrue(Sets.firstContainsSecond(Collections.emptySet(), Collections.emptySet()));
    }

    @Test(expected=NullPointerException.class)
    public void isSubsetFirstSetIsNull() {
        Sets.firstContainsSecond(null, Sets.<Integer>set(1, 2));
    }

    @Test(expected=NullPointerException.class)
    public void isSubsetSecondSetIsNull() {
        Sets.firstContainsSecond(Sets.<Integer>set(1, 2, 3, 4, 5), null);
    }
}