package ru.mail.polis.ads.hash;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class MyHashMap<Key, Value> implements HashTable<Key, Value> {
    private int size = 0;
    private int threshold = 0;
    private List<Node> table = null;

    private static final int START_CAPACITY = 16;
    private static final int START_THRESHOLD = 12;


    class Node {
        Key key;
        Value value;
        int hash;
        Node next = null;

        Node(Key key, Value value, int hash) {
            this.key = key;
            this.value = value;
            this.hash = hash;
        }

    }

    private Node put(Node list, Node el) {
        if (el == null)
            return list;
        el.next = list;
        return el;
    }


    private void resize() {
        List<Node> oldTable = table;

        int oldCap = oldTable == null ? 0 : oldTable.size();
        int oldThr = threshold;
        int newCap;

        if (oldCap > 0) {
            newCap = oldCap << 1;
            threshold = oldThr << 1;
        } else {
            threshold = START_THRESHOLD;
            newCap = START_CAPACITY;
        }

        table = new ArrayList<>(newCap);

        for (int i = 0; i < newCap; i++) {
            table.add(null);
        }

        if (oldTable != null) {
            for (int i = 0; i < oldCap; i++) {
                Node curr = oldTable.get(i);
                while (curr != null) {
                    Node next = curr.next;
                    int hash = curr.hash & (newCap - 1);
                    table.set(hash, put(table.get(hash), curr));
                    curr = next;
                }
            }
        }
    }

    private void putVal(@NotNull Key key, @NotNull Value value) {
        if (table == null)
            resize();

        int hash = key.hashCode();
        int tableHash = hash & (table.size() - 1);
        Node node = get(table.get(tableHash), key, hash);
        if (node == null) {
            ++size;
            table.set(tableHash, put(table.get(tableHash), new Node(key, value, hash)));
        } else {
            node.value = value;
        }

        if (size > threshold)
            resize();

    }

    private Node get(Node list, @NotNull Key key, int hash) {
        if (list == null)
            return null;
        if (list.hash == hash && list.key.equals(key)) {
            return list;
        }
        return get(list.next, key, hash);
    }

    private Node getParent(Node list, @NotNull Key key, int hash) {
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
        Node node = get(table.get(h & (table.size() - 1)), key, h);
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
        int hashIndex = h & (table.size() - 1);
        Node list = table.get(hashIndex);
        if (list == null)
            return null;
        if (list.hash == h && list.key.equals(key)) {
            table.set(hashIndex, list.next);
            --size;
            return list.value;
        }
        Node node = getParent(list, key, h);
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
