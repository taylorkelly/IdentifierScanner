package identifierscanner.statistics;

import java.util.Comparator;
import java.util.Map;

/**
 * A comparator that compares based on (reverse) values in the map.
 */
public class ReverseValueComparator implements Comparator {
    private Map base;

    /**
     * The map to use as a basis for the comparator
     * @param base The map to use
     */
    public ReverseValueComparator(Map base) {
        this.base = base;
    }

    /**
     * Compares the values of two entries in the map given by their keys
     * @param a the key of the first object to compare
     * @param b the key of the second object to compare
     * @return a positive value if the value from the first key is greater than,
     * a negative if not
     */
    public int compare(Object a, Object b) {

        if ((Integer) base.get(a) < (Integer) base.get(b)) {
            return -1;
        } else if ((Integer) base.get(a) == (Integer) base.get(b)) {
            return -1; //Need it to be positive so we don't lose entries
        } else {
            return 1;
        }
    }
}