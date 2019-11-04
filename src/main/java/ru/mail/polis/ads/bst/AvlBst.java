package ru.mail.polis.ads.bst;

/**
 * AVL implementation of binary search tree.
 */
public class AvlBst<Key extends Comparable<Key>, Value>
        implements Bst<Key, Value> {

    private Node root = null;

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

    private Node rotateLeft(final Node node) {
        if (node == null || node.left == null)
            return node;
        Node x = node.left;
        node.left = node.left.right;
        if (x.right != null)
            x.right.parent = node;
        x.right = node;
        node.parent = x;
        fixHeight(node);
        fixHeight(x);
        return x;
    }

    private Node rotateRight(final Node node) {
        if (node == null || node.right == null)
            return node;

        Node x = node.right;
        node.right = node.right.left;
        if (x.left != null)
            x.left.parent = node;
        x.left = node;
        node.parent = x;
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

        fixHeight(node);

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

    private int factor(final Node node) {
        return height(node.left) - height(node.right);
    }

    private int height(final Node node) {
        return node == null ? 0 : node.height;
    }

    private void fixHeight(final Node node) {
        node.height = Math.max(height(node.left), height(node.right)) + 1;
    }

    private void fixHeightAllSub(final Node node) {
        if (node == null)
            return;
        fixHeightAllSub(node.left);
        fixHeightAllSub(node.right);
        fixHeight(node);
    }

    private Node getKeyPos(Key key, final Node node) {
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

    private Node get(Key key, final Node node) {
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

    private int size(final Node node) {
        if (node == null || (node.left == null && node.right == null))
            return 0;
        return size(node.left) + size(node.right) + 1;
    }

    private Node previous(final Node node) {
        if (node == null)
            return null;

        Node temp = node;
        if (temp.left != null)
            return temp.left;

        while (temp.parent != null && temp.parent.left == temp)
            temp = temp.parent;

        if (temp.parent == null || temp.parent.key.compareTo(node.key) > 0)
            return node;

        return temp.parent;
    }

    private Node next(final Node node) {
        if (node == null)
            return null;

        Node temp = node;
        if (temp.right != null)
            return temp.right;

        while (temp.parent != null && temp.parent.right == temp)
            temp = temp.parent;

        if (temp.parent == null || temp.parent.key.compareTo(node.key) < 0)
            return node;

        return temp.parent;
    }

    @Override
    public Value get(Key key) {
        Node pointer = get(key, root);
        return pointer == null ? null : pointer.value;
    }

    @Override
    public void put(Key key, Value value) {
        if (root == null) {
            root = new Node(key, value, null);
            return;
        }
        Node parent = getKeyPos(key, root);
        if (key.compareTo(parent.key) < 0) {
            parent.left = new Node(key, value, parent);
        } else if (key.compareTo(parent.key) > 0) {
            parent.right = new Node(key, value, parent);
        } else {
            parent.value = value;
        }

        root = fixAllSub(root);
    }

    @Override
    public Value remove(Key key) {
        Node pointer = getKeyPos(key, root);

        if (pointer == null)
            return null;

        Value ret = pointer.value;

        if (pointer.right != null) {
            Node minimum = findMin(pointer.right);
            pointer.key = minimum.key;
            pointer.value = minimum.value;
            if (minimum != pointer.right) {
                minimum.parent.left = null;
            } else if (pointer.right.right == null) {
                pointer.right = null;
            } else {
                pointer.right = pointer.right.right;
            }
        } else if (pointer.left != null) {
            Node maximum = findMax(pointer.left);

            pointer.key = maximum.key;
            pointer.value = maximum.value;
            if (maximum != pointer.left) {
                maximum.parent.right = null;
            } else if (pointer.left.left == null) {
                pointer.left = null;
            } else {
                pointer.left = pointer.left.left;
            }
        } else if (pointer == root) {
            root = null;
        } else if (pointer.parent.left == pointer) {
            pointer.parent.left = null;
        } else {
            pointer.parent.right = null;
        }

        root = fixAllSub(root);

        return ret;
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
        if (pointer == null)
            return null;
        while (pointer.key.compareTo(key) >= 0) {
            Node temp = pointer;
            pointer = previous(temp);
            if (temp == pointer)
                return null;
        }

        return pointer.key;
    }

    @Override
    public Key ceil(Key key) {
        Node pointer = getKeyPos(key, root);
        if (pointer == null)
            return null;
        while (pointer.key.compareTo(key) <= 0) {
            Node temp = pointer;
            pointer = next(temp);
            if (temp == pointer)
                return null;
        }

        return pointer.key;
    }

    @Override
    public int size() {
        return root == null ? 0 : (size(root.left) + size(root.right) + 1);
    }

    @Override
    public int height() {
        fixHeightAllSub(root);
        return height(root);
    }
}
