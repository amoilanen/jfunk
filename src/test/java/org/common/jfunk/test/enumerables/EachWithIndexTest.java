package org.common.jfunk.test.enumerables;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;
import static org.common.jfunk.Enumerables.eachWithIndex;
import static org.common.jfunk.Pair.pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.common.jfunk.Action;
import org.common.jfunk.Pair;
import org.junit.Test;

public class EachWithIndexTest {

    @Test
    public void ifEachIsCalledWithActionThenActionIsAppliedToAllElements() {
        final List<Pair<String, Integer>> accumulated = new ArrayList<Pair<String, Integer>>();
        @SuppressWarnings("unchecked")
        List<Pair<String, Integer>> expected = Arrays.asList(pair("a", 0), pair("b", 1), pair("c", 2));

        eachWithIndex(Arrays.asList("a", "b", "c"), new Action<Pair<String, Integer>>() {

            @Override
            public void perform(Pair<String, Integer> x) {
                accumulated.add(x);
            }
        });
        assertEquals(expected, accumulated);
    };

    @Test
    public void ifEachIsCalledWithEmptyCollectionThenActionIsNotApplied() {                
        eachWithIndex(Collections.<String>emptyList(), new Action<Pair<String, Integer>>() {

            @Override
            public void perform(Pair<String, Integer> x) {
                throw new RuntimeException("No action performed for empty collection");
            }
        });
    };

    @Test
    public void ifOneOfArgumentsIsNullThenExceptionIsRaised() {
        try {
            eachWithIndex(Collections.emptyList(), null);
            fail("Exception should have been raised");
        } catch (IllegalArgumentException expected) {
            assertEquals("Null argument number 2", expected.getMessage());
        }
        try {
            eachWithIndex(null, new Action<Pair<String, Integer>>() {

                @Override
                public void perform(Pair<String, Integer> x) {
                }
            });
            fail("Exception should have been raised");
        } catch (IllegalArgumentException expected) {
            assertEquals("Null argument number 1", expected.getMessage());
        }
    }
}