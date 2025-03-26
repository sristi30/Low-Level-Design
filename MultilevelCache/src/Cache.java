public interface Cache<K, V> {
    V read(K key);

    void write(K key, V value);

    void delete(K key);
}

