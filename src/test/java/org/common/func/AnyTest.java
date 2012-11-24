package org.common.func;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;
import static org.common.func.TestFunctions.falseValue;

import java.util.Arrays;
import java.util.Collections;

import org.common.func.Enumerables.Predicate;
import org.junit.Test;

public class AnyTest {

	@Test
	public void ifAnyCalledWithEmptyCollectionThenFalse() {
		assertFalse(Enumerables.any(Collections.<Object>emptyList(), falseValue));
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
			Enumerables.any(null, falseValue);
			fail("Exception should have been raised");
		} catch (IllegalArgumentException expected) {
			assertEquals("Null argument number 1", expected.getMessage());
		}
	}
	
	@Test
	public void ifAnyCalledThereIsElementSatisfyingConditionThenTrue() {
		assertTrue(Enumerables.any(Arrays.asList("", "two", ""), new Predicate<String> () {
			public Boolean call(String input) {
				return input.length() > 0;
			}
		}));
	}
	
	@Test
	public void ifAnyCalledNoElementSatisfyingConditionThenFalse() {
		assertFalse(Enumerables.any(Arrays.asList("", "", ""), new Predicate<String> () {
			public Boolean call(String input) {
				return input.length() > 0;
			}
		}));
	}
}