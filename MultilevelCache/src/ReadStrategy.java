public interface ReadStrategy<K, V> {
    V read(K key);

    public void setNextLevelCache(LevelCache<K, V> nextLevelCache);

    public void setCache(Cache<K, V> cache);

    public void setLabel(String label);
}
