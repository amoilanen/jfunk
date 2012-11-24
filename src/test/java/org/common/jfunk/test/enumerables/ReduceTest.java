package org.common.jfunk.test.enumerables;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;
import static org.common.jfunk.test.Functions.mult;
import static org.common.jfunk.test.Functions.sum;

import java.util.Arrays;
import java.util.Collections;

import org.common.jfunk.Enumerables;
import org.junit.Test;

public class ReduceTest {

    @Test
    public void ifReduceWithAllArgumentsThenAllElementsAreUsedForComputation() {
        assertEquals(new Integer(15),
            Enumerables.reduce(Arrays.asList(1, 2, 3, 4, 5), sum, 0));
        assertEquals(new Integer(120),
                Enumerables.reduce(Arrays.asList(1, 2, 3, 4, 5), mult, 1));
    };

    @Test
    public void ifReduceWithEmptyCollectionThenInitialValueIsReturned() {
        assertEquals(new Integer(5),
                Enumerables.reduce(Collections.<Integer>emptyList(), sum, 5));
    };

    @Test
    public void ifReduceWithNullArgumentsThenExceptionIsRaised() {
        try {
            Enumerables.reduce(Arrays.asList(1, 2, 3, 4, 5), null, 0);
            fail("Exception should have been raised");
        } catch (IllegalArgumentException expected) {
            assertEquals("Null argument number 2", expected.getMessage());
        }
        try {
            Enumerables.reduce(Arrays.asList(1, 2, 3, 4, 5), sum, null);
            fail("Exception should have been raised");
        } catch (IllegalArgumentException expected) {
            assertEquals("Null argument number 3", expected.getMessage());
        }
    };
}