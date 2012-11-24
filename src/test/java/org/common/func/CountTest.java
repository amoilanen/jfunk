package org.common.func;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;
import static org.common.func.Enumerables.count;
import static org.common.func.TestFunctions.falseValue;
import static org.common.func.TestFunctions.isEven;

import java.util.Arrays;
import java.util.Collections;

import org.junit.Test;

public class CountTest {
	
	@Test
	public void ifCountFunctionIsSuppliedThenItIsUsedToCountElements() {
		assertEquals(3, count(Arrays.asList(1, 2, 3, 4, 5, 6), isEven));
	}

	@Test
	public void ifCountingEmptyListThenResultIsZero() {
		assertEquals(0, count(Collections.<Integer>emptyList(), isEven));
	}

	@Test
	public void ifCountCalledWithNullArgumentsThenExceptionIsRaised() {
		try {
			count(Collections.emptyList(), null);
			fail("Exception should have been raised");
		} catch (IllegalArgumentException expected) {
			assertEquals("Null argument number 2", expected.getMessage());
		}
		try {
			count(null, falseValue);
			fail("Exception should have been raised");
		} catch (IllegalArgumentException expected) {
			assertEquals("Null argument number 1", expected.getMessage());
		}
	}
}