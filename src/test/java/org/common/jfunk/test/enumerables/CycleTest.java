package org.common.jfunk.test.enumerables;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;
import static org.common.jfunk.Enumerables.cycle;

import java.util.Arrays;
import java.util.Collections;

import org.common.jfunk.Action;
import org.junit.Test;

public class CycleTest {
	
    private static final int TIMES_TO_CYCLE = 3;
    
	@Test
	public void ifEachIsCalledWithActionThenActionIsAppliedToAllElements() {
		final StringBuilder concatenated = new StringBuilder();
		
		cycle(Arrays.asList("a", "b", "c"), new Action<String>() {

			@Override
			public void perform(String x) {
				concatenated.append(x);
			}
		}, TIMES_TO_CYCLE);
		
		assertEquals("abcabcabc", concatenated.toString());
	};

	@Test
	public void ifEachIsCalledWithEmptyCollectionThenActionIsNotApplied() {				
		cycle(Collections.<String>emptyList(), new Action<String>() {

			@Override
			public void perform(String x) {
				throw new RuntimeException("No action performed for empty collection");
			}
		}, TIMES_TO_CYCLE);
	};

	@Test
	public void ifOneOfArgumentsIsNullThenExceptionIsRaised() {
		try {
			cycle(Collections.emptyList(), null, TIMES_TO_CYCLE);
			fail("Exception should have been raised");
		} catch (IllegalArgumentException expected) {
			assertEquals("Null argument number 2", expected.getMessage());
		}
		try {
			cycle(null, new Action<String>() {

				@Override
				public void perform(String x) {
				}				
			}, TIMES_TO_CYCLE);
			fail("Exception should have been raised");
		} catch (IllegalArgumentException expected) {
			assertEquals("Null argument number 1", expected.getMessage());
		}
		try {
		    cycle(Collections.<String>emptyList(), new Action<String>() {
		        
		        @Override
		        public void perform(String x) {
		        }				
		    }, -1);
		    fail("Exception should have been raised");
		} catch (IllegalArgumentException expected) {
		    assertEquals("'times' should be > 0", expected.getMessage());
		}
	}
}