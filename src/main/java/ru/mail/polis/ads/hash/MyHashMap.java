package ru.mail.polis.ads.hash;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MyHashMap<Key, Value> implements HashTable<Key, Value> {
    private int size = 0;
    private int threshold = 0;
    private Node<Key, Value>[] table = null;

    private static final int MAX_CAPACITY = 1 << 30;

    private static final int START_CAPACITY = 16;
    private static final int START_THRESHOLD = 12;


    class Node<NodeKey, NodeValue> {
        NodeKey key;
        NodeValue value;
        int hash;
        Node<NodeKey, NodeValue> next = null;

        Node(NodeKey key, NodeValue value) {
            this.key = key;
            this.value = value;
            this.hash = this.key.hashCode();
        }


        Node(NodeKey key, NodeValue value, int hash) {
            this.key = key;
            this.value = value;
            this.hash = hash;
        }

    }

    private Node<Key, Value> put(Node<Key, Value> list, Node<Key, Value> el) {
        if (el == null)
            return list;
        el.next = list;
        return el;
    }

    private Node<Key, Value> remove(Node<Key, Value> el) {
        if (el == null)
            return null;
        return el.next;
    }


    private void resize() {
        Node<Key, Value>[] oldTable = table;

        int oldCap = oldTable == null ? 0 : oldTable.length;
        int oldThr = threshold;
        int newCap, newThr;

        if (oldCap > 0) {
            if (oldCap >= MAX_CAPACITY) {
                threshold = MAX_CAPACITY << 1;
                return;
            } else {
                newCap = oldCap << 1;
                newThr = oldThr << 1;
            }
        } else {
            newThr = START_THRESHOLD;
            newCap = START_CAPACITY;
        }

        Node<Key, Value>[] newTable = (Node<Key, Value>[]) new Node[newCap];

        if (oldTable != null) {
            for (int i = 0; i < oldCap; i++) {
                Node<Key, Value> curr = oldTable[i];
                while (curr != null) {
                    Node<Key, Value> next = curr.next;
                    int hash = curr.hash & (newCap - 1);
                    newTable[hash] = put(newTable[hash], curr);
                    curr = next;
                }
            }
        }

        table = newTable;
        threshold = newThr;
    }

    private void putVal(@NotNull Key key, @NotNull Value value) {
        ++size;
        if (size > threshold)
            resize();
        int hash = key.hashCode();
        int tableHash = hash & (table.length - 1);
        Node<Key, Value> node = get(table[tableHash], key, hash);
        if (node == null) {
            table[tableHash] = put(table[tableHash], new Node<>(key, value, hash));
        } else {
            node.value = value;
        }

    }

    private Node<Key, Value> get(Node<Key, Value> list, @NotNull Key key, int hash) {
        if (list == null)
            return null;
        if (list.hash == hash && list.key.equals(key)) {
            return list;
        }
        return get(list.next, key, hash);
    }

    private Node<Key, Value> getParent(Node<Key, Value> list, @NotNull Key key, int hash) {
        if (list == null || list.next == null)
            return null;
        if (list.next.hash == hash && list.next.key.equals(key)) {
            return list;
        }
        return get(list.next, key, hash);
    }

    @Nullable
    @Override
    public Value get(@NotNull Key key) {
        if (table == null)
            return null;
        int h = key.hashCode();
        Node<Key, Value> node = get(table[h & (table.length - 1)], key, h);
        return node == null ? null : node.value;
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        putVal(key, value);
    }

    @Nullable
    @Override
    public Value remove(@NotNull Key key) {
        if (table == null)
            return null;
        int h = key.hashCode();
        Node<Key, Value> list = table[h & (table.length - 1)];
        if (list == null)
            return null;
        if (list.hash == h && list.key.equals(key)) {
            table[h & (table.length - 1)] = list.next;
            --size;
            return list.value;
        }
        Node<Key, Value> node = getParent(list, key, h);
        if (node == null)
            return null;
        else {
            --size;
            return (node.next = node.next.next).value;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
