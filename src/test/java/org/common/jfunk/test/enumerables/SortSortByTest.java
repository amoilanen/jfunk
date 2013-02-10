package org.common.jfunk.test.enumerables;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;
import static org.common.jfunk.Enumerables.count;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import org.common.jfunk.Enumerables;
import org.junit.Test;

public class SortSortByTest {

    private static int numberOfSymbolOccurrences(char sym, String str) {
        char[] allChars = str.toCharArray();
        int occurrences = 0;

        for (char c : allChars) {
            if (sym == c) {
                occurrences++;
            }
        }
        return occurrences;
    };
    
    @Test
    public void ifSortByIsCalledThenCollectionIsSortedByProvidedComparator() {
        assertEquals(Arrays.asList("bbbbb", "abb", "aab", "aaa", "aaaa"),
            Enumerables.sortBy(Arrays.<String>asList("aab", "abb", "aaa", "aaaa", "bbbbb"), new Comparator<String>() {

                public int compare(String o1, String o2) {
                    return numberOfSymbolOccurrences('a', o1) - numberOfSymbolOccurrences('a', o2);
                }
            })
        );
    };

    @Test
    public void ifSortIsCalledThenCollectionIsSorted() {
        assertEquals(Arrays.asList(1, 2, 3, 4, 5), 
                Enumerables.sort(Arrays.<Integer>asList(5, 2, 1, 4, 3)));
    };

    @Test
    public void ifSortingAnEmptyCollectionThenResultIsEmptyCollection() {
        assertEquals(Collections.emptyList(),
            Enumerables.sortBy(Collections.<String>emptyList(), new Comparator<String>() {

                public int compare(String o1, String o2) {
                    return numberOfSymbolOccurrences('a', o1) - numberOfSymbolOccurrences('a', o2);
                }
            })
        );
        assertEquals(Collections.emptyList(), Enumerables.sort(Collections.<Integer>emptyList()));
    };

    @Test
    public void ifInvalidArgumentsAreProdivedThenExceptionIsRaised() {
        try {
            Enumerables.sortBy(null, new Comparator<String>() {
                
                public int compare(String o1, String o2) {
                    return numberOfSymbolOccurrences('a', o1) - numberOfSymbolOccurrences('a', o2);
                }
            });
            fail("Exception should have been raised");
        } catch (IllegalArgumentException expected) {
            assertEquals("Null argument number 1", expected.getMessage());
        }
        try {
            Enumerables.sortBy(Collections.emptyList(), null);
            fail("Exception should have been raised");
        } catch (IllegalArgumentException expected) {
            assertEquals("Null argument number 2", expected.getMessage());
        }
        try {
            Enumerables.sort(null);
            fail("Exception should have been raised");
        } catch (IllegalArgumentException expected) {
            assertEquals("Null argument number 1", expected.getMessage());
        }
    };
}