package identifierscanner.statistics;

import java.util.Comparator;
import java.util.Map;

/**
 * A comparator that compares based on values in the map.
 */
public class ValueComparator implements Comparator {
    private Map base;

    /**
     * The map to use as a basis for the comparator
     * @param base The map to use
     */
    public ValueComparator(Map base) {
        this.base = base;
    }

    /**
     * Compares the values of two entries in the map given by their keys
     * @param a the key of the first object to compare
     * @param b the key of the second object to compare
     * @return a positive value if the value from the first key is less or equal
     * to the second, and a negative value if not.
     */
    public int compare(Object a, Object b) {

        if ((Integer) base.get(a) < (Integer) base.get(b)) {
            return 1;
        } else if ((Integer) base.get(a) == (Integer) base.get(b)) {
            return 1; //Need it to be positive so we don't lose entries
        } else {
            return -1;
        }
    }
}