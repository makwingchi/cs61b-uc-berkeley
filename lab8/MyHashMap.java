import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class MyHashMap<K, V> implements Map61B<K, V> {
    private Set<K> hs;       // HashSet that contains the keys in the map
    private int bucketSize;  // number of separate chains
    private double lf;       // load factor
    private ArrayList<Node>[] chain;  // an array containing separate chains

    private class Node {
        private K key;
        private V value;

        private Node(K k, V v) {
            this.key = k;
            this.value = v;
        }
    }

    public MyHashMap() {
        this(16);
    }

    public MyHashMap(int initialSize) {
        this(initialSize, 0.75);
    }

    public MyHashMap(int initialSize, double loadFactor) {
        bucketSize = initialSize;
        lf = loadFactor;
        chain = (ArrayList<Node>[]) new ArrayList[bucketSize];
        hs = new HashSet<K>();

        for (int i = 0; i < bucketSize; i ++) {
            chain[i] = new ArrayList<>();
        }
    }

    private int getHashCode(K key) {
        return (key.hashCode() & 0x7fffffff) % bucketSize;
    }

    @Override
    public void clear() {
        for (ArrayList bucket : chain) {
            bucket.clear();
        }
        hs.clear();
    }

    @Override
    public boolean containsKey(K key){
        return hs.contains(key);
    }

    @Override
    public V get(K key) {
        int code = getHashCode(key);
        for (Node n : chain[code]) {
            if (key.equals(n.key)) {
                return n.value;
            }
        }
        return null;
    }

    @Override
    public int size() {
        return hs.size();
    }

    private void resize() {
        ArrayList<Node>[] newChain = (ArrayList<Node>[]) new ArrayList[bucketSize*2];

        for (int i = 0; i < bucketSize*2; i ++) {
            newChain[i] = new ArrayList<>();
        }

        for (int i = 0; i < bucketSize; i += 1) {
            for (Node n : chain[i]) {
                int code = getHashCode(n.key);
                newChain[code].add(n);
            }
        }
        this.chain = newChain;
        bucketSize *= 2;
    }

    @Override
    public void put(K key, V value) {
        if (1.0 * hs.size() / bucketSize >= lf) {
            this.resize();
        }

        int code = getHashCode(key);
        if (containsKey(key)) {
            for (Node n : chain[code]) {
                if (n.key == key) {
                    n.value = value;
                }
            }
        } else {
            Node newNode = new Node(key, value);
            chain[code].add(newNode);
        }

        hs.add(key);
    }

    @Override
    public Set<K> keySet() {
        return hs;
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException("remove is not available.");
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException("remove is not available.");
    }

    public Iterator<K> iterator() {
        return hs.iterator();
    }
}
