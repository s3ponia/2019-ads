package ru.mail.polis.ads.bst;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * LLRB implementation of binary search tree.
 */
public class RedBlackBst<Key extends Comparable<Key>, Value>
        implements Bst<Key, Value> {

    private static final boolean BLACK = false;
    private static final boolean RED = true;
    private Node root = null;

    private class Node {
        Node(Key key, Value value) {
            this.key = key;
            this.value = value;
        }

        Key key;
        Value value;
        Node left = null;
        Node right = null;
        boolean color = RED;
    }

    private boolean isRed(Node node) {
        return node != null && node.color == RED;
    }

    private void flipColorNode(Node node) {
        if (node == null)
            return;

        node.color = !node.color;
    }

    private void flipColor(Node node) {
        if (node == null)
            return;
        flipColorNode(node);
        flipColorNode(node.left);
        flipColorNode(node.right);
    }

    private Node rotateRight(final Node node) {
        if (node == null || node.left == null)
            return node;
        Node x = node.left;
        node.left = x.right;
        x.right = node;
        boolean color = node.color;
        node.color = x.color;
        x.color = color;
        return x;
    }

    private Node rotateLeft(final Node node) {
        if (node == null || node.right == null)
            return node;

        Node x = node.right;
        node.right = x.left;
        x.left = node;
        boolean color = node.color;
        node.color = x.color;
        x.color = color;
        return x;
    }

    private Node put(Node node, @NotNull Key key, @NotNull Value value) {
        if (node == null)
            return new Node(key, value);

        int cmp = key.compareTo(node.key);
        if (cmp > 0) {
            node.right = put(node.right, key, value);
            if (isRed(node.right)) {
                if (isRed(node.left)) {
                    flipColor(node);
                } else {
                    node = rotateLeft(node);
                }
            }
        } else if (cmp < 0) {
            node.left = put(node.left, key, value);
            if (isRed(node.left) && isRed(node.left.left)) {
                node = rotateRight(node);
                flipColor(node);
            }
        } else {
            node.value = value;
        }

        return node;
    }

    private Node get(Node node, @NotNull Key key) {
        if (node == null)
            return null;

        int cmp = key.compareTo(node.key);

        if (cmp > 0)
            return get(node.right, key);
        else if (cmp < 0)
            return get(node.left, key);
        else
            return node;
    }

    private Node min(Node node) {
        if (node == null)
            return null;
        if (node.left == null)
            return node;
        return min(node.left);
    }

    private Node max(Node node) {
        if (node == null)
            return null;
        if (node.right == null)
            return node;
        return max(node.right);
    }


    @Nullable
    @Override
    public Value get(@NotNull Key key) {
        Node node = get(root, key);
        return node == null ? null : node.value;
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        root = put(root, key, value);
        root.color = BLACK;
    }

    @Nullable
    @Override
    public Value remove(@NotNull Key key) {
        throw new UnsupportedOperationException("Implement me");
    }

    @Nullable
    @Override
    public Key min() {
        Node node = min(root);
        return node == null ? null : node.key;
    }

    @Nullable
    @Override
    public Value minValue() {
        Node node = min(root);
        return node == null ? null : node.value;
    }

    @Nullable
    @Override
    public Key max() {
        Node node = max(root);
        return node == null ? null : node.key;
    }

    @Nullable
    @Override
    public Value maxValue() {
        Node node = max(root);
        return node == null ? null : node.value;
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
