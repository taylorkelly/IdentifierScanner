package identifierscanner.util;

import java.util.Map.Entry;

/**
 * Implements Map's Entry interface, providing basic entry support
 * @param <K> The type used for the keys
 * @param <V> The type used for the values
 */
public class BasicEntry<K, V> implements Entry<K, V> {
    private final K key;
    private V value;
    
    public BasicEntry(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public V setValue(V value) {
        V old = this.value;
        this.value = value;
        return old;
    }
}
