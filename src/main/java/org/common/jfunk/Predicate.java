package org.common.jfunk;

public abstract class Predicate<T> implements Function<T, Boolean> {
    
    public Boolean test(T value) {
        return call(value);
    };

    public static <T> Predicate<T> not(final Predicate<T> p) {
        return new Predicate<T>() {

            public Boolean call(T x) {
                return !p.call(x);
            }
        };
    };

    public static <T> Predicate<T> and(final Predicate<T> p1, final Predicate<T> p2) {
        return new Predicate<T>() {
            
            public Boolean call(T x) {
                return p1.call(x) && p2.call(x);
            }
        };
    };

    public static <T> Predicate<T> or(final Predicate<T> p1, final Predicate<T> p2) {
        return new Predicate<T>() {
            
            public Boolean call(T x) {
                return p1.call(x) || p2.call(x);
            }
        };
    };
}