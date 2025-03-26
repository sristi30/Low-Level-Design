import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;

public class LRUCache<K, V> implements Cache<K, V> {

    private final int capacity;
    Map<K, CacheNode<K, V>> cacheMap;
    Deque<CacheNode<K, V>> cacheList;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cacheMap = new ConcurrentHashMap<>();
        cacheList = new ConcurrentLinkedDeque<>();
    }

    @Override
    public V read(K key) {
        CacheNode<K, V> valueNode = cacheMap.get(key);
        if (valueNode == null) {
            System.out.println("Value at this level does not exist");
            return null;
        }
        System.out.println("Placing value in the end");
        cacheList.remove(valueNode);
        cacheList.add(valueNode);
        return valueNode.getValue();
    }

    @Override
    public void write(K key, V value) {
        if (cacheMap.containsKey(key)) {
            System.out.println("Key at this level already exists. Reshuffling.");
            CacheNode<K, V> valueNode = cacheMap.get(key);
            valueNode.setValue(value);
            cacheList.remove(valueNode);
            cacheList.add(valueNode);
        } else {
            System.out.println("Key at this level does not exist. Creating new one.");
            CacheNode<K, V> newNode = new CacheNode<>(key, value);
            if (cacheList.size() == capacity) {
                System.out.println("Reached the maximum capacity. Evicting them.");
                //Can have a separate class for eviction strategy
                CacheNode<K, V> first = cacheList.getFirst();
                cacheList.removeFirst();
                cacheMap.remove(first.getKey());
            }
            System.out.println("Inserting into this cache");
            cacheList.add(newNode);
            cacheMap.put(key, newNode);
        }
    }

    @Override
    public void delete(K key) {
        if (cacheMap.containsKey(key)) {
            CacheNode<K, V> valueNode = cacheMap.get(key);
            cacheMap.remove(key);
            cacheList.remove(valueNode);
        }
    }
}
