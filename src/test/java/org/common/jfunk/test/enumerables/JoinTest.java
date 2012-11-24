package org.common.jfunk.test.enumerables;

import static junit.framework.Assert.assertEquals;
import static org.common.jfunk.Enumerables.join;

import java.util.Arrays;
import java.util.Collections;

import org.junit.Test;

public class JoinTest {
    
    @Test
    public void ifEmptyCollectionIsJoinedThanEmptyStringIsProduced() {
        assertEquals("", join(Collections.emptyList()));
    }

    @Test
    public void ifNoJoinSymbolIsSuppliedThenJoinIsDoneWithComma() {
        assertEquals("1,2,3", join(Arrays.asList(1, 2, 3)));
    }

    @Test
    public void ifJoinSymbolIsSuppliedThenJoinedByThisSymbol() {
        assertEquals("1:2:3", join(Arrays.asList(1, 2, 3), ":"));
    }

    @Test
    public void ifJoinSymbolContainsFewCharacterThenItIsStillUsed() {
        assertEquals("1<->2<->3", join(Arrays.asList(1, 2, 3), "<->"));
    }
}