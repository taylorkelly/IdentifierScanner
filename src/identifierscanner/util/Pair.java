package identifierscanner.util;

/**
 * A class representing a pair of the same object type
 * @param <K> the object type to use
 */
public class Pair<K> {
    private final K first;
    private final K second;

    public Pair(K first, K second) {
        this.first = first;
        this.second = second;
    }

    public K getFirst() {
        return first;
    }

    public K getSecond() {
        return second;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Pair)) {
            return false;
        } else {
            Pair p = (Pair) o;
            if (p.first.equals(this.first) && p.second.equals(this.second))
                return true;
            else
                return false;
        }
    }

    @Override
    public int hashCode() {
        return first.hashCode() ^ second.hashCode();
    }

    @Override
    public String toString() {
        return "(" + first.toString() + ", " + second.toString() + ")";
    }
}