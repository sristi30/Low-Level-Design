import org.omg.CORBA.PRIVATE_MEMBER;

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
        if (Objects.equals(currentEntry, null)) {
            hashTable[index] = new Entry<>(key, value);
            return;
        }
        while (currentEntry.getNext() != null) {
            if (currentEntry.getKey().equals(key)) {
                break;
            }
            currentEntry = currentEntry.getNext();
        }
        if (currentEntry.getKey().equals(key)) {
            currentEntry.setValue(value);
        } else {
            Entry<K, V> newEntry = new Entry<>(key, value);
            currentEntry.setNext(newEntry);
        }
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
        if (Objects.equals(currentEntry, null)) {
            return null;
        }
        if (currentEntry.getKey().equals(key)) {
            hashTable[index] = null;
            return currentEntry.getValue();
        }
        if (Objects.equals(currentEntry.getNext(), null)) {
            return null;
        }
        while (currentEntry.getNext().getNext() != null) {
            if (currentEntry.getNext().getKey().equals(key)) {
                break;
            }
            currentEntry = currentEntry.getNext();
        }
        if (currentEntry.getNext().getKey().equals(key)) {
            V deletedValue = currentEntry.getNext().getValue();
            currentEntry.setNext(currentEntry.getNext().getNext());
            return deletedValue;
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
