public class ReadThrough<K, V> implements ReadStrategy<K, V> {
    private Cache<K, V> cache;
    private LevelCache<K, V> nextLevelCache;
    private String label;

    public void setNextLevelCache(LevelCache<K, V> nextLevelCache) {
        this.nextLevelCache = nextLevelCache;
    }

    public void setCache(Cache<K, V> cache) {
        this.cache = cache;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public V read(K key) {
        if (cache == null) {
            System.out.println("[" + label + "] Cache is null");
            return null;
        }
        V value = cache.read(key);
        if (value == null) {
            System.out.println("[" + label + "] Value is null");
            if (nextLevelCache == null) {
                System.out.println("[" + label + "] NextLevelCache is null");
                return null;
            }
            System.out.println("[" + label + "] Reading from next level cache");
            value = nextLevelCache.read(key);
            if (value != null) {
                System.out.println("[" + label + "] Writing to this level cache");
                cache.write(key, value);
            }
        }
        return value;
    }
}
