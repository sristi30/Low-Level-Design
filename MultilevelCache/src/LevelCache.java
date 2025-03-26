//Cache for a particular level
public interface LevelCache<K, V> {
    public V read(K key);

    public void write(K key, V value);

    public void delete(K key);
}
