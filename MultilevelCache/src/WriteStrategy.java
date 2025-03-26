public interface WriteStrategy<K, V> {
    void write(K key, V value);

    public void setCache(Cache<K, V> cache);

    public void setNextLevelCache(LevelCache<K, V> nextLevelCache);

    public void setLabel(String label);
}
