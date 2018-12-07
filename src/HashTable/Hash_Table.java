package HashTable;

import java.util.TreeMap;

public class Hash_Table<K, V> {
    private static final int upperTol = 10;
    private static final int lowerTol = 2;
    private static final int initCapacity = 7;
    private TreeMap<K, V>[] hashtable;
    private int M;
    private int size;

    public Hash_Table(int M) {
        this.M = M;
        size = 0;
        hashtable = new TreeMap[M];
        for (int i = 0; i < hashtable.length; i++) {
            hashtable[i] = new TreeMap<>();
        }
    }

    public Hash_Table() {
        this(97);
    }

    private int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public int getSize() {
        return size;
    }

    public void add(K key, V value) {
        if (hashtable[hash(key)].containsKey(key)) {
            hashtable[hash(key)].put(key, value);
        } else {
            hashtable[hash(key)].put(key, value);
            size++;
            if (size >= upperTol * M) {
                resize(2 * M);
            }
        }
    }

    public V remove(K key) {
        TreeMap<K, V> treeMap = hashtable[hash(key)];
        V ret = null;
        if (treeMap.containsKey(key)) {
            ret = treeMap.remove(key);
            size--;
            if (size < lowerTol * M && M / 2 > initCapacity) {
                resize(M / 2);
            }
        }
        return ret;
    }

    private void resize(int capacity){
        TreeMap<K, V>[] newHashTable = new TreeMap[capacity];
        for (int i = 0; i < capacity; i++) {
            newHashTable[i] = new TreeMap<>();
        }
        int oldM = this.M;
        this.M = capacity;
        for (int i = 0; i < oldM; i++) {
            TreeMap<K, V> treeMap = hashtable[i];
            for (K key : treeMap.keySet()){
                newHashTable[hash(key)].put(key, treeMap.get(key));
            }
        }

        this.hashtable = newHashTable;
    }

    public void set(K key, V value) {
        TreeMap<K, V> treeMap = hashtable[hash(key)];
        if (!treeMap.containsKey(key)) {
            throw new IllegalArgumentException("no element!");
        } else {
            treeMap.put(key, value);
        }
    }

    public boolean contains(K key) {
        return hashtable[hash(key)].containsKey(key);
    }

    public V get(K key) {
        return hashtable[hash(key)].get(key);
    }
}
