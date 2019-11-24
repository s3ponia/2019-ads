package ru.mail.polis.ads.bst;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * AVL implementation of binary search tree.
 */
public class AvlBst<Key extends Comparable<Key>, Value>
        implements Bst<Key, Value> {

    private class Node {
        Key key;
        Value value;
        Node left;
        Node right;
        int height;
    }

    @Nullable
    @Override
    public Value get(@NotNull Key key) {
        throw new UnsupportedOperationException("Implement me");
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        throw new UnsupportedOperationException("Implement me");
    }

    @Nullable
    @Override
    public Value remove(@NotNull Key key) {
        throw new UnsupportedOperationException("Implement me");
    }

    @Nullable
    @Override
    public Key min() {
        throw new UnsupportedOperationException("Implement me");
    }

    @Nullable
    @Override
    public Value minValue() {
        throw new UnsupportedOperationException("Implement me");
    }

    @Nullable
    @Override
    public Key max() {
        throw new UnsupportedOperationException("Implement me");
    }

    @Nullable
    @Override
    public Value maxValue() {
        throw new UnsupportedOperationException("Implement me");
    }

    @Nullable
    @Override
    public Key floor(@NotNull Key key) {
        throw new UnsupportedOperationException("Implement me");
    }

    @Nullable
    @Override
    public Key ceil(@NotNull Key key) {
        throw new UnsupportedOperationException("Implement me");
    }

    @Override
    public int size() {
        throw new UnsupportedOperationException("Implement me");
    }

    @Override
    public int height() {
        throw new UnsupportedOperationException("Implement me");
    }
}
