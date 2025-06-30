import java.util.ArrayList;
import java.util.List;

public class HashST<K extends Comparable<K>, V> {
    private static final int INIT_CAPACITY = 16;
    private final Node<K, V>[] table;

    @SuppressWarnings("unchecked")
    public HashST() {
        table = (Node<K, V>[]) new Node[INIT_CAPACITY];
    }

    private int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % INIT_CAPACITY;
    }

    public void put(K key,
                    V value) {
        int i = hash(key);
        Node<K, V> head = table[i];
        for (Node<K, V> x = head; x != null; x = x.right) {
            if (x.key.equals(key)) {
                x.value = value;
                return;
            }
        }

        Node<K, V> newNode = new Node<>(key,
                value);
        newNode.right = head;
        table[i] = newNode;
    }

    public V get(K key) {
        int i = hash(key);
        for (Node<K, V> x = table[i]; x != null; x = x.right) {
            if (x.key.equals(key)) return x.value;
        }
        return null;
    }

    public boolean contains(K key) {
        return get(key) != null;
    }

    public Iterable<V> values() {
        List<V> valuesList = new ArrayList<>();
        for (Node<K, V> bucket : table) {
            for (Node<K, V> x = bucket; x != null; x = x.right) {
                valuesList.add(x.value);
            }
        }
        return valuesList;
    }
}