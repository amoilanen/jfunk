package org.common.jfunk;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Utility functions for working with iterators.
 * 
 */
public class Iterators {

    //merge
    //transform
    //filter
    //drop
    //takeWhile
    //toArray etc

    public static <T> Iterator<T> concat(Iterator<?>... iterators) {        
        @SuppressWarnings("unchecked")
        Iterator<T> result = iterators.length > 0 ? (Iterator<T>) iterators[0] : Collections.<T>emptyList().iterator();
        
        for (int i = 1; i < iterators.length; i++) {
            result = concatTwo(result, iterators[i]);
        };
        return result;
    }

    private static <T> Iterator<T> concatTwo(final Iterator<?> iterator1, final Iterator<?> iterator2) {
        return new Iterator<T>() {

            public boolean hasNext() {
                return iterator1.hasNext() || iterator2.hasNext();
            }

            @SuppressWarnings("unchecked")
            public T next() {
                return (T) (iterator1.hasNext() ? iterator1.next() : iterator2.next());                
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }
            
        };
    }
    
    public static <T> List<T> toList(Iterator<T> iter) {
        List<T> result = new ArrayList<T>();
        
        while (iter.hasNext()) {
            result.add(iter.next());
        };
        return result;
    }

    @SuppressWarnings("unchecked")
    public static <T> T[] toArray(Iterator<T> iter, Class<T> clazz) {
        return (T[]) toList(iter).toArray((T[]) Array.newInstance(clazz, 0));
    }
}
