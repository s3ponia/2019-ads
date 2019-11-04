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
        Node temp = node.left;
        node.left = node.left.right;
        if (temp.right != null)
            temp.right.parent = node.left;
        x.right = node;
        node.parent = x.right;
        fixHeight(node);
        fixHeight(x);
        return x;
    }

    private Node rotateRight(Node node) {
        if (node == null || node.right == null)
            return node;

        Node x = node.right;
        Node temp = node.right;
        node.right = node.right.left;
        if (temp.left != null)
            temp.left.parent = node.right;
        x.left = node;
        node.parent = x.left;
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
//                pointer.right = pointer.right.right;
            }

            root = fixAllSub(root);

            return ret;
        }

        if (pointer.left != null) {
            Node maximum = findMax(pointer.left);

            pointer.key = maximum.key;
            pointer.value = maximum.value;
            if (maximum != pointer.left) {
                maximum.parent.right = null;
            } else if (pointer.left.left == null) {
                pointer.left = null;
            } else {
//                pointer.left = pointer.left.left;
            }

            root = fixAllSub(root);

            return ret;
        }

        if (pointer == root) {
            root = null;
            return ret;
        }

        if (pointer.parent.left == pointer) {
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
