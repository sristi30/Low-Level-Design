public class Main {
    public static void main(String[] args) {
        //(LevelCache<K, V> nextLevelCache, Cache<K, V> cache, ReadStrategy<K, V> readStrategy, WriteStrategy<K, V> writeStrategy) {
        ReadStrategy<String, String> readStrategy2 = new ReadThrough<>();
        WriteStrategy<String, String> writeStrategy2 = new WriteThrough<>();
        ReadStrategy<String, String> readStrategy1 = new ReadThrough<>();
        WriteStrategy<String, String> writeStrategy1 = new WriteThrough<>();
        Cache<String, String> L2Cache = new LRUCache<>(3);
        LevelCache<String, String> L2Level = new LevelCacheImpl<>(null, L2Cache, readStrategy2, writeStrategy2, "L2");
        Cache<String, String> L1Cache = new LRUCache<>(2);
        LevelCache<String, String> L1Level = new LevelCacheImpl<>(L2Level, L1Cache, readStrategy1, writeStrategy1, "L1");
        System.out.println(L1Level.read("Rohit"));
        L1Level.write("Rohit", "100");
        System.out.println(L1Level.read("Rohit"));
        L1Level.write("Sristi", "90");
        L1Level.write("Ayush", "95");
        System.out.println(L1Level.read("Rohit"));
        L1Level.write("Neha", "100");
        L1Level.write("Sumit", "120");
       // L1Level.write("Shraddha", "130");
        System.out.println(L1Level.read("Rohit"));
    }
}