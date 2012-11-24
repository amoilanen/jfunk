package org.common.jfunk.test.enumerables;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;
import static org.common.jfunk.Enumerables.eachWithObject;

import java.util.Arrays;
import java.util.Collections;

import org.common.jfunk.Action;
import org.common.jfunk.Pair;
import org.junit.Test;

public class EachWithObjectTest {

    @Test
    public void ifEachIsCalledWithActionThenActionIsAppliedToAllElements() {
        final StringBuilder accumulated = new StringBuilder();

        eachWithObject(Arrays.asList("a", "b", "c"), new Action<Pair<String, StringBuilder>>() {

            @Override
            public void perform(Pair<String, StringBuilder> x) {
                x.t.append(x.h);
            }
        }, accumulated);
        assertEquals("abc", accumulated.toString());
    };

    @Test
    public void ifEachIsCalledWithEmptyCollectionThenActionIsNotApplied() {                
        eachWithObject(Collections.<String>emptyList(), new Action<Pair<String, StringBuilder>>() {

            @Override
            public void perform(Pair<String, StringBuilder> x) {
                throw new RuntimeException("No action performed for empty collection");
            }
        }, new StringBuilder());
    };

    @Test
    public void ifOneOfArgumentsIsNullThenExceptionIsRaised() {
        try {
            eachWithObject(Collections.emptyList(), null, new StringBuilder());
            fail("Exception should have been raised");
        } catch (IllegalArgumentException expected) {
            assertEquals("Null argument number 2", expected.getMessage());
        }
        try {
            eachWithObject(null, new Action<Pair<String, StringBuilder>>() {

                @Override
                public void perform(Pair<String, StringBuilder> x) {
                }
            }, new StringBuilder());
            fail("Exception should have been raised");
        } catch (IllegalArgumentException expected) {
            assertEquals("Null argument number 1", expected.getMessage());
        }
        try {
            //Does not make any sense for accumulator to be 'null'
            eachWithObject(Collections.<String>emptyList(), new Action<Pair<String, StringBuilder>>() {
                
                @Override
                public void perform(Pair<String, StringBuilder> x) {
                }
            }, null);
            fail("Exception should have been raised");
        } catch (IllegalArgumentException expected) {
            assertEquals("Null argument number 3", expected.getMessage());
        }
    }
}