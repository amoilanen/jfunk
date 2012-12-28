package org.common.jfunk.test.enumerables;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;
import static org.common.jfunk.Enumerables.filter;
import static org.common.jfunk.test.Functions.isEven;

import java.util.Arrays;
import java.util.Collections;

import org.junit.Test;

public class FilterTest {
    
    @Test
    public void ifFilterIsCalledThenElementsAreFiltered() {
        assertEquals(Arrays.asList(0, 2, 4),
        		filter(Arrays.asList(0, 1, 2, 3, 4, 5), isEven));
    };

    @Test
    public void ifFilterIsCalledWithEmptyCollectionThenEmptyCollectionIsReturned() {
        assertEquals(Collections.emptyList(),
        		filter(Collections.<Integer>emptyList(), isEven));
    };

    @Test
    public void ifOneOfArgumentsIsNullThenExceptionIsRaised() {
        try {
            filter(Collections.emptyList(), null);
            fail("Exception should have been raised");
        } catch (IllegalArgumentException expected) {
            assertEquals("Null argument number 2", expected.getMessage());
        }
        try {
            filter(null, isEven);
            fail("Exception should have been raised");
        } catch (IllegalArgumentException expected) {
            assertEquals("Null argument number 1", expected.getMessage());
        }
    }
}