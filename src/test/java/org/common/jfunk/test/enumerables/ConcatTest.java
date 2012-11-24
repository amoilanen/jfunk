package org.common.jfunk.test.enumerables;

import static junit.framework.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.common.jfunk.Enumerables;
import org.common.jfunk.Function;
import org.junit.Test;

public class ConcatTest {

    @Test
    public void ifTwoCollectionsAreConcatenatedThenResultContainsAllTheirElements() {
        assertEquals(Arrays.asList(1, 2, 3, 4, 5), 
                Enumerables.concat(Arrays.asList(1, 2, 3), Arrays.asList(4, 5)));
    }
    
    @Test
    public void ifCollectConcatIsACompositionOfCollectAndConcat() {
        assertEquals(Arrays.asList(1, 2, 2, 3, 3, 3), Enumerables.collectConcat(Arrays.asList(1, 2, 3), new Function<Integer, Collection<Integer>>() {

            public Collection<Integer> call(Integer input) {
                List<Integer> result = new ArrayList<Integer>();

                for (int i = 0; i < input.intValue(); i++) {
                    result.add(input);
                };
                return result;
            }
        }));
    }
}