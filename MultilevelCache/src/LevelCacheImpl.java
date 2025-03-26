//10.23
class LevelCacheImpl<K, V> implements LevelCache<K, V> {
    LevelCache<K, V> nextLevelCache;
    Cache<K, V> cache;
    ReadStrategy<K, V> readStrategy;
    WriteStrategy<K, V> writeStrategy;
    private String label;

    public LevelCacheImpl(LevelCache<K, V> nextLevelCache, Cache<K, V> cache, ReadStrategy<K, V> readStrategy, WriteStrategy<K, V> writeStrategy, String label) {
        this.nextLevelCache = nextLevelCache;
        this.cache = cache;
        this.readStrategy = readStrategy;
        this.writeStrategy = writeStrategy;
        readStrategy.setCache(cache);
        readStrategy.setNextLevelCache(nextLevelCache);
        writeStrategy.setCache(cache);
        writeStrategy.setNextLevelCache(nextLevelCache);
        this.label = label;
        readStrategy.setLabel(label);
        writeStrategy.setLabel(label);
    }

    @Override
    public V read(K key) {
        System.out.println("["+label + "]Reading " + key+" from cache");
        return readStrategy.read(key);
    }

    @Override
    public void write(K key, V value) {
        System.out.println("["+label + "]Writing " + key+" to cache");
        writeStrategy.write(key, value);
    }

    @Override
    public void delete(K key) {
        System.out.println("["+label + "]Deleting " + key+" from cache");
        cache.delete(key);
        nextLevelCache.delete(key);
    }

}
