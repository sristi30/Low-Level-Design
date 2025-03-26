public class WriteThrough<K, V> implements WriteStrategy<K, V> {

    private Cache<K, V> cache;
    private LevelCache<K, V> nextLevelCache;
    private String label;

    public void setCache(Cache<K, V> cache) {
        this.cache = cache;
    }

    public void setNextLevelCache(LevelCache<K, V> nextLevelCache) {
        this.nextLevelCache = nextLevelCache;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public void write(K key, V value) {
        if (cache == null) {
            System.out.println("[" + label + "] Cache is null");
            return;
        }
        System.out.println("[" + label + "] Writing to this level cache");
        cache.write(key, value);
        if (nextLevelCache == null) {
            System.out.println("[" + label + "] NextLevelCache is null");
            return;
        }
        System.out.println("[" + label + "] Writing to next level cache");
        nextLevelCache.write(key, value);
    }
}
