package org.common.jfunk.test.enumerables;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;
import static org.common.jfunk.Enumerables.each;

import java.util.Arrays;
import java.util.Collections;

import org.common.jfunk.Action;
import org.junit.Test;

public class EachTest {
    
    @Test
    public void ifEachIsCalledWithActionThenActionIsAppliedToAllElements() {
        final StringBuilder concatenated = new StringBuilder();
        
        each(Arrays.asList("a", "b", "c", "d", "e"), new Action<String>() {

            @Override
            public void perform(String x) {
                concatenated.append(x);
            }
        });
        
        assertEquals("abcde", concatenated.toString());
    };

    @Test
    public void ifEachIsCalledWithEmptyCollectionThenActionIsNotApplied() {                
        each(Collections.<String>emptyList(), new Action<String>() {

            @Override
            public void perform(String x) {
                throw new RuntimeException("No action performed for empty collection");
            }
        });
    };

    @Test
    public void ifOneOfArgumentsIsNullThenExceptionIsRaised() {
        try {
            each(Collections.emptyList(), null);
            fail("Exception should have been raised");
        } catch (IllegalArgumentException expected) {
            assertEquals("Null argument number 2", expected.getMessage());
        }
        try {
            each(null, new Action<String>() {

                @Override
                public void perform(String x) {
                }                
            });
            fail("Exception should have been raised");
        } catch (IllegalArgumentException expected) {
            assertEquals("Null argument number 1", expected.getMessage());
        }
    }
}