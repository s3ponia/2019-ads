package ru.mail.polis.ads.bst;

/**
 * AVL implementation of binary search tree.
 */
public class AvlBst<Key extends Comparable<Key>, Value>
        implements Bst<Key, Value> {

    private Node root = null;
    private int size = 0;

    private class Node {
        Node(Key key, Value value, Node parent) {
            this.key = key;
            this.value = value;
            this.parent = parent;
        }

        Key key;
        Value value;
        Node parent;
        Node left = null;
        Node right = null;
        int height = 1;
    }

    private Node rotateLeft(Node node) {
        if (node == null || node.left == null)
            return node;
        Node x = node.left;
        node.left = node.left.right;
        x.right = node;
        fixHeight(node);
        fixHeight(x);
        return x;
    }

    private Node rotateRight(Node node) {
        if (node == null || node.left == null)
            return node;

        Node x = node.right;
        node.right = node.right.left;
        x.left = node;
        fixHeight(node);
        fixHeight(x);
        return x;
    }

    private Node fix(Node node) {
        if (node == null)
            return null;

        if (factor(node) == 2) {
            if (factor(node.left) < 0)
                node.left = rotateRight(node.left);
            node = rotateLeft(node);
        }

        if (factor(node) == -2) {
            if (factor(node.right) > 0)
                node.right = rotateLeft(node.right);
            node = rotateRight(node);
        }

        return node;
    }

    private Node fixAllSub(Node node) {
        if (node == null)
            return null;
        node.left = fixAllSub(node.left);
        node.right = fixAllSub(node.right);
        node = fix(node);
        return node;
    }

    private int factor(Node node) {
        return height(node.left) - height(node.right);
    }

    private int height(Node node) {
        return node == null ? 0 : node.height;
    }

    private void fixHeight(Node node) {
        node.height = Math.max(height(node.left), height(node.right)) + 1;
    }

    private void fixHeightAllSub(Node node) {
        if (node == null)
            return;
        fixHeightAllSub(node.left);
        fixHeightAllSub(node.right);
        fixHeight(node);
    }

    private Node getKeyPos(Key key, Node node) {
        if (node == null)
            return null;

        if (key.compareTo(node.key) < 0) {
            if (node.left == null)
                return node;
            return getKeyPos(key, node.left);
        } else if (key.compareTo(node.key) > 0) {
            if (node.right == null)
                return node;
            return getKeyPos(key, node.right);
        } else {
            return node;
        }
    }

    private Node get(Key key, Node node) {
        if (node == null)
            return null;

        if (key.compareTo(node.key) < 0) {
            return get(key, node.left);
        } else if (key.compareTo(node.key) > 0) {
            return get(key, node.right);
        } else {
            return node;
        }
    }

    private Node findMin(Node node) {
        if (node == null)
            return null;

        while (node.left != null) {
            node = node.left;
        }

        return node;
    }

    private Node findMax(Node node) {
        if (node == null)
            return null;

        while (node.right != null) {
            node = node.right;
        }

        return node;
    }

    private int size(Node node) {
        if (node == null || (node.left == null && node.right == null))
            return 0;
        return size(node.left) + size(node.right) + 1;
    }


    @Override
    public Value get(Key key) {
        Node pointer = get(key, root);
        return pointer == null ? null : pointer.value;
    }

    @Override
    public void put(Key key, Value value) {
        ++size;
        if (root == null) {
            root = new Node(key, value, null);
        }
        Node parent = getKeyPos(key, root);
        if (key.compareTo(parent.key) < 0) {
            parent.left = new Node(key, value, parent);
        } else if (key.compareTo(parent.key) > 0) {
            parent.right = new Node(key, value, parent);
        } else {
            parent.value = value;
        }
        fixHeightAllSub(root);
        fixAllSub(root);
    }

    @Override
    public void remove(Key key) {
        Node pointer = getKeyPos(key, root);
        if (pointer == null)
            return;

        Node minimum = findMin(pointer.right);
        pointer.value = minimum.value;

        minimum.parent = null;
        fixHeightAllSub(pointer);
    }

    @Override
    public Key min() {
        Node pointer = findMin(root);
        return pointer == null ? null : pointer.key;
    }

    @Override
    public Value minValue() {
        Node pointer = findMin(root);
        return pointer == null ? null : pointer.value;
    }

    @Override
    public Key max() {
        Node pointer = findMax(root);
        return pointer == null ? null : pointer.key;
    }

    @Override
    public Value maxValue() {
        Node pointer = findMax(root);
        return pointer == null ? null : pointer.value;
    }

    @Override
    public Key floor(Key key) {
        Node pointer = getKeyPos(key, root);
        return pointer == null ? null : pointer.key;
    }

    @Override
    public Key ceil(Key key) {
        Node parent = getKeyPos(key, root);
        return parent == null ? null : parent.key;
    }

    @Override
    public int size() {
        return size(root) + (root != null ? 1 : 0);
    }

    @Override
    public int height() {
        return height(root);
    }
}
