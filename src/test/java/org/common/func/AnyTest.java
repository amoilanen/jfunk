package org.common.func;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;

import java.util.Arrays;
import java.util.Collections;

import org.junit.Test;

public class AnyTest {

	private static final Enumerables.Function<Object, Boolean> FALSE_FUNCTION = new Enumerables.Function<Object, Boolean>() {
		public Boolean call(Object input) {
			return false;
		}
	};

	@Test
	public void ifAnyCalledWithEmptyCollectionThenFalse() {
		assertFalse(Enumerables.any(Collections.<Object>emptyList(), FALSE_FUNCTION));
	}
	
	@Test
	public void ifAnyCalledWithNullArgumentsThenExceptionIsRaised() {
		try {
			Enumerables.any(Collections.emptyList(), null);
			fail("Exception should have been raised");
		} catch (IllegalArgumentException expected) {
			assertEquals("Null argument number 2", expected.getMessage());
		}
		try {
			Enumerables.any(null, FALSE_FUNCTION);
			fail("Exception should have been raised");
		} catch (IllegalArgumentException expected) {
			assertEquals("Null argument number 1", expected.getMessage());
		}
	}
	
	@Test
	public void ifAnyCalledThereIsElementSatisfyingConditionThenTrue() {
		assertTrue(Enumerables.any(Arrays.asList("", "two", ""), new Enumerables.Function<String, Boolean> () {
			public Boolean call(String input) {
				return input.length() > 0;
			}
		}));
	}
	
	@Test
	public void ifAnyCalledNoElementSatisfyingConditionThenFalse() {
		assertFalse(Enumerables.any(Arrays.asList("", "", ""), new Enumerables.Function<String, Boolean> () {
			public Boolean call(String input) {
				return input.length() > 0;
			}
		}));
	}
}