import java.util.HashMap;
import java.util.Objects;

// In java, hashmap can have null keys and values
public class MyMap<K, V> {

    private static final int MAX_CAPACITY = 1<<30;
    private static final int INITIAL_SIZE = 1<<4;
    Entry<K, V> [] hashTable;


    public MyMap() {
        hashTable = (Entry<K, V>[]) new Entry[INITIAL_SIZE];
    }

    public MyMap(int capacity) {
        int size = getSizeFromCapacity(capacity);
        hashTable = (Entry<K, V>[]) new Entry[size];
    }
    public void put(K key, V value) {
        int index = hash(key);

        Entry<K, V> currentEntry = hashTable[index];
        Entry<K, V> newEntry = new Entry<>(key, value);
        if (Objects.equals(currentEntry, null)) {
            hashTable[index] = newEntry;
            return;
        }
        while (currentEntry != null) {
            if (currentEntry.getKey().equals(key)) {
                currentEntry.setValue(value);
                return;
            }
            if (currentEntry.getNext() == null) {
                break;
            }
            currentEntry = currentEntry.getNext();
        }
            currentEntry.setNext(newEntry);

    }

    public V get(K key) {
        int index = hash(key);
        Entry<K, V> currentEntry = hashTable[index];
        while(currentEntry != null) {
            if (currentEntry.getKey().equals(key)) {
                return currentEntry.getValue();
            }
            currentEntry = currentEntry.getNext();
        }
        return null;
    }

    public V remove(K key) {
        int index = hash(key);
        Entry<K, V> currentEntry = hashTable[index];
        Entry<K, V> previousEntry = null;
        while (currentEntry != null) {
            if (currentEntry.getKey().equals(key)) {
                if (previousEntry == null) {
                    hashTable[index] = currentEntry.getNext();
                } else {
                    previousEntry.setNext(currentEntry.getNext());
                }
                return currentEntry.getValue();
            }
            previousEntry = currentEntry;
            currentEntry = currentEntry.getNext();
        }
        return null;
    }

    public int size() {
        return hashTable.length;
    }

    private int hash(K key) {
        return Math.abs(key.hashCode()) % hashTable.length;
    }

    private int getSizeFromCapacity(int capacity) {
        return (int) Math.pow(2, Math.ceil(Math.log(capacity)/Math.log(2)));
    }

}
