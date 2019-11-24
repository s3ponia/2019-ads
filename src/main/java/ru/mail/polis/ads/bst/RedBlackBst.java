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

    private void flipColors(Node node) {
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
        } else if (cmp < 0) {
            node.left = put(node.left, key, value);
        } else {
            node.value = value;
        }

        return fixUp(node);
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

    private Node fixUp(Node node) {
        if (isRed(node.right))
            node = rotateLeft(node);
        if (isRed(node.left) && isRed(node.left.left))
            node = rotateRight(node);
        if (isRed(node.right) && isRed(node.left))
            flipColors(node);
        return node;
    }

    private Node moveRedRight(Node node) {
        flipColors(node);
        if (isRed(node.left.left)) {
            node = rotateRight(node);
            flipColors(node);
        }
        return node;
    }

    private Node deleteMax(Node node) {
        if (isRed(node.left))
            node = rotateRight(node);
        if (node.right == null)
            return null;
        if (!isRed(node.right) && !isRed(node.right.left))
            node = moveRedRight(node);
        node.right = deleteMax(node.right);
        return fixUp(node);
    }

    private Node moveRedLeft(Node node) {
        flipColors(node);
        if (isRed(node.right.left)) {
            node.right = rotateRight(node.right);
            node = rotateLeft(node);
            flipColors(node);
        }
        return node;
    }

    private Node deleteMin(Node node) {
        if (node.left == null)
            return null;
        if (!isRed(node.left) && !isRed(node.left.left))
            node = moveRedLeft(node);
        node.left = deleteMin(node.left);
        return fixUp(node);
    }

    private Node remove(Node node, @NotNull Key key) {
        if (node == null)
            return null;
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            if (node.left != null) {
                if (!isRed(node.left) && !isRed(node.left.left))
                    node = moveRedLeft(node);
                node.left = remove(node.left, key);
            }
        } else if (cmp > 0) {
            if (isRed(node.left))
                node = rotateRight(node);
            if (node.right != null) {
                if (!isRed(node.right) && !isRed(node.right.right))
                    node = moveRedRight(node);
                node.right = remove(node.right, key);
            }
        } else {
            Node temp = min(node.right);
            Node save_state = node;
            if (temp != null) {
                node.value = temp.value;
                node.key = temp.key;
            }
            if (isRed(node.left))
                node = rotateRight(node);
            if (node.right == null)
                return null;

            if (!isRed(node.right) && !isRed(node.right.right)) {
                node = moveRedRight(node);
            }
            if (node.right == save_state && save_state.right != null)
                node.right.right = deleteMin(node.right.right);
            else
                node.right = deleteMin(node.right);
        }

        return fixUp(node);
    }

    private int size(Node node) {
        if (node == null)
            return 0;
        return size(node.left) + size(node.right) + 1;
    }

    private void resetBLACK(Node node) {
        if (node == null)
            return;
        node.color = BLACK;
    }

    private int height(Node node) {
        if (node == null)
            return 0;
        return Math.max(height(node.left), height(node.right)) + 1;
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
        resetBLACK(root);
    }

    @Nullable
    @Override
    public Value remove(@NotNull Key key) {
        Node temp = get(root, key);
        if (temp == null)
            return null;
        Value val = temp.value;
        root = remove(root, key);
        resetBLACK(root);
        return val;
    }

    private Node ceil(Node node, @NotNull Key key) {
        if (node == null)
            return null;

        int cmp = key.compareTo(node.key);

        if (cmp > 0)
            return ceil(node.right, key);
        else if (cmp == 0)
            return node;

        Node temp = ceil(node.left, key);
        return temp == null ? node : temp;
    }

    private Node floor(Node node, @NotNull Key key) {
        if (node == null)
            return null;

        int cmp = key.compareTo(node.key);

        if (cmp < 0)
            return floor(node.left, key);
        else if (cmp == 0)
            return node;

        Node temp = floor(node.right, key);
        return temp == null ? node : temp;
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
        Node temp = floor(root, key);
        return temp == null ? null : temp.key;
    }

    @Nullable
    @Override
    public Key ceil(@NotNull Key key) {
        Node temp = ceil(root, key);
        return temp == null ? null : temp.key;
    }

    @Override
    public int size() {
        return size(root);
    }

    @Override
    public int height() {
        return height(root);
    }
}
