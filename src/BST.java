import java.util.ArrayList;
import java.util.List;

public class BST<K extends Comparable<K>, V> {
    public Node<K, V> root;

    public void insert(K key,
                       V value) {
        root = insertRec(root, key, value);
    }

    private Node<K, V> insertRec(Node<K, V> node,
                                 K key,
                                 V value) {
        if (node == null) return new Node<>(key, value);

        int compare = key.compareTo(node.getKey());
        if (compare < 0) {
            node.setLeft(insertRec(node.getLeft(), key, value));
        } else if (compare > 0) {
            node.setRight(insertRec(node.getRight(), key, value));
        } else {
            node.setValue(value);
        }
        return node;
    }

    public V get(K key) {
        Node<K, V> current = root;
        while (current != null) {
            int compare = key.compareTo(current.getKey());
            if (compare < 0) {
                current = current.getLeft();
            } else if (compare > 0) {
                current = current.getRight();
            } else {
                return current.getValue();
            }
        }
        return null;
    }

    public List<V> range(K lo, K hi) {
        List<V> result = new ArrayList<>();
        rangeRec(root, lo, hi, result);
        return result;
    }

    private void rangeRec(Node<K, V> node, K lo, K hi, List<V> result) {
        if (node == null) return;
        if (lo.compareTo(node.getKey()) < 0) rangeRec(node.getLeft(), lo, hi, result);
        if (lo.compareTo(node.getKey()) <= 0 && hi.compareTo(node.getKey()) >= 0)
            result.add(node.getValue());
        if (hi.compareTo(node.getKey()) > 0) rangeRec(node.getRight(), lo, hi, result);
    }

    public List<V> successor(K key) {
        Node<K, V> succ = null;
        Node<K, V> current = root;
        while (current != null) {
            int cmp = key.compareTo(current.getKey());
            if (cmp < 0) {
                succ = current;
                current = current.getLeft();
            } else {
                current = current.getRight();
            }
        }
        List<V> result = new ArrayList<>();
        if (succ != null) result.add(succ.getValue());
        return result;
    }

    public boolean containsKey(K key) {
        return get(key) != null;
    }

    public Node<K, V> getRoot() {
        return root;
    }
}

