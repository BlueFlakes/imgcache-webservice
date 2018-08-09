package imgcache.utils;

public final class Tuple {
    private Tuple() {
        throw new IllegalStateException();
    }

    public static <T> Unit<T> create(T v1) {
        return new Unit<>(v1);
    }

    public static <T, U> Pair<T, U> create(T v1, U v2) {
        return new Pair<>(v1, v2);
    }

    public static <T, U, P> Triplet<T, U, P> create(T v1, U v2, P v3) {
        return new Triplet<>(v1, v2, v3);
    }

    public static class Unit<T> {
        private final T first;

        private Unit(T first) {
            this.first = first;
        }

        public T _1() {
            return this.first;
        }
    }

    public static class Pair<T, U> extends Unit<T> {
        private final U second;

        private Pair(T first, U second) {
            super(first);
            this.second = second;
        }

        public U _2() {
            return this.second;
        }
    }

    public static class Triplet<T, U, P> extends Pair<T, U> {
        private final P third;

        private Triplet(T first, U second, P third) {
            super(first, second);
            this.third = third;
        }

        public P _3() {
            return this.third;
        }
    }
}
