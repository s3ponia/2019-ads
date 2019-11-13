package ru.mail.polis.ads.bst;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Basic binary search tree invariants.
 */
class BstBaseTest {

    private final static double phi = (Math.sqrt(5.) + 1.) / 2.;
    private final static Random rnd = new Random();

    Bst<String, String> newBst() {
        return new AvlBst<>();
    }

    int maxHeight(int n) {
        return (int) (Math.log((double) n) / Math.log(phi)) + 1;
    }

    @Test
    void emptyBst() {
        Bst<String, String> bst = newBst();
        assertNull(bst.get(""));
        assertNull(bst.get("some key"));
        assertEquals(0, bst.size());
        assertEquals(0, bst.height());
    }

    @Test
    void remove() {
        Bst<String, String> bst = newBst();
        bst.put("A", "A");
        assertEquals("A", bst.remove("A"));
        assertNull(bst.remove("C"));

        bst.put("C", "C");
        bst.put("E", "E");
        bst.put("G", "G");
        bst.put("K", "K");

        assert (bst.height() <= maxHeight(4));

        assertEquals("E", bst.remove("E"));
        assertEquals("C", bst.get("C"));
        assert (bst.height() <= maxHeight(3));
        assertEquals("K", bst.get("K"));
    }

    @Test
    void remove2() {
        /*
            Test remove for:
                A
         */
        Bst<Integer, Integer> bst = new AvlBst<>();

        bst.put(1, 1);
        assertEquals(1, bst.height());
        assertEquals(1, bst.remove(1));
        assertNull(bst.get(1));
        assertEquals(0, bst.height());
        /*
            Test remove for:
                A
               /
              B
         */
        bst = new AvlBst<>();

        bst.put(2, 2);
        bst.put(1, 1);

        assertEquals(2, bst.height());
        assertEquals(2, bst.remove(2));
        assertNull(bst.get(2));
        assertEquals(1, bst.get(1));
        assertEquals(1, bst.height());
        /*
            Test remove for:
                A
                 \
                  B
         */
        bst = new AvlBst<>();

        bst.put(1, 1);
        bst.put(2, 2);

        assertEquals(2, bst.height());
        assertEquals(1, bst.remove(1));
        assertNull(bst.get(1));
        assertEquals(2, bst.get(2));
        assertEquals(1, bst.height());
        /*
            Test remove for:
                A
               / \
              B   C
         */
        bst = new AvlBst<>();

        bst.put(2, 2);
        bst.put(1, 1);
        bst.put(3, 3);

        assertEquals(2, bst.height());
        assertEquals(2, bst.remove(2));
        assertNull(bst.get(2));
        assertEquals(1, bst.get(1));
        assertEquals(3, bst.get(3));
        assertEquals(2, bst.height());
        /*
            Test remove for:
                A
               / \
              B   C
             /
            D
         */
        bst = new AvlBst<>();

        bst.put(3, 3);
        bst.put(2, 2);
        bst.put(4, 4);
        bst.put(1, 1);

        assertEquals(3, bst.height());
        assertEquals(3, bst.remove(3));
        assertNull(bst.get(3));
        assertEquals(1, bst.get(1));
        assertEquals(2, bst.get(2));
        assertEquals(4, bst.get(4));
        assertEquals(2, bst.height());
        /*
            Test remove for:
                A
               / \
              B   C
               \
                D
         */
        bst = new AvlBst<>();

        bst.put(3, 3);
        bst.put(1, 1);
        bst.put(4, 4);
        bst.put(2, 2);

        assertEquals(3, bst.height());
        assertEquals(3, bst.remove(3));
        assertNull(bst.get(3));
        assertEquals(1, bst.get(1));
        assertEquals(2, bst.get(2));
        assertEquals(4, bst.get(4));
        assertEquals(2, bst.height());
        /*
            Test remove for:
                A
               / \
              B   C
                 /
                D
         */
        bst = new AvlBst<>();

        bst.put(2, 2);
        bst.put(1, 1);
        bst.put(4, 4);
        bst.put(3, 3);

        assertEquals(3, bst.height());
        assertEquals(2, bst.remove(2));
        assertNull(bst.get(2));
        assertEquals(1, bst.get(1));
        assertEquals(3, bst.get(3));
        assertEquals(4, bst.get(4));
        assertEquals(2, bst.height());
        /*
            Test remove for:
                A
               / \
              B   C
                   \
                    D
         */
        bst = new AvlBst<>();

        bst.put(2, 2);
        bst.put(1, 1);
        bst.put(3, 3);
        bst.put(4, 4);

        assertEquals(3, bst.height());
        assertEquals(2, bst.remove(2));
        assertNull(bst.get(2));
        assertEquals(1, bst.get(1));
        assertEquals(3, bst.get(3));
        assertEquals(4, bst.get(4));
        assertEquals(2, bst.height());
        /*
            Test remove for:
                A
               / \
              B   C
             / \
            D   E
         */
        bst = new AvlBst<>();

        bst.put(4, 4);
        bst.put(5, 5);
        bst.put(2, 2);
        bst.put(3, 3);
        bst.put(1, 1);

        assertEquals(3, bst.height());
        assertEquals(4, bst.remove(4));
        assertNull(bst.get(4));
        assertEquals(1, bst.get(1));
        assertEquals(2, bst.get(2));
        assertEquals(3, bst.get(3));
        assertEquals(5, bst.get(5));
        assertEquals(3, bst.height());
        /*
            Test remove for:
                A
               / \
              B   C
             /   /
            D   E
         */
        bst = new AvlBst<>();

        bst.put(3, 3);
        bst.put(2, 2);
        bst.put(5, 5);
        bst.put(4, 4);
        bst.put(1, 1);

        assertEquals(3, bst.height());
        assertEquals(3, bst.height());
        assertEquals(3, bst.remove(3));
        assertNull(bst.get(3));
        assertEquals(1, bst.get(1));
        assertEquals(2, bst.get(2));
        assertEquals(4, bst.get(4));
        assertEquals(5, bst.get(5));
        /*
            Test remove for:
                A
              /   \
             B     C
            /       \
           D         E
         */
        bst = new AvlBst<>();
        bst.put(3, 3);
        bst.put(4, 4);
        bst.put(2, 2);
        bst.put(5, 5);
        bst.put(1, 1);

        assertEquals(3, bst.height());
        assertEquals(3, bst.remove(3));
        assertNull(bst.get(3));
        assertEquals(3, bst.height());
        assertEquals(5, bst.get(5));
        assertEquals(2, bst.get(2));
        assertEquals(1, bst.get(1));
        assertEquals(4, bst.get(4));
        /*
            Test remove for:
                A
              /   \
             B     C
              \   /
              D   E
         */
        bst = new AvlBst<>();
        bst.put(3, 3);
        bst.put(5, 5);
        bst.put(1, 1);
        bst.put(4, 4);
        bst.put(2, 2);

        assertEquals(3, bst.height());
        assertEquals(3, bst.remove(3));
        assertNull(bst.get(3));
        assertEquals(3, bst.height());
        assertEquals(5, bst.get(5));
        assertEquals(2, bst.get(2));
        assertEquals(1, bst.get(1));
        assertEquals(4, bst.get(4));
        /*
            Test remove for:
                A
              /   \
             B     C
              \     \
               D     E
         */
        bst = new AvlBst<>();
        bst.put(3, 3);
        bst.put(4, 4);
        bst.put(1, 1);
        bst.put(5, 5);
        bst.put(2, 2);

        assertEquals(3, bst.height());
        assertEquals(3, bst.remove(3));
        assertNull(bst.get(3));
        assertEquals(3, bst.height());
        assertEquals(5, bst.get(5));
        assertEquals(2, bst.get(2));
        assertEquals(1, bst.get(1));
        assertEquals(4, bst.get(4));
        /*
            Test remove for:
                A
              /   \
             B     C
                  / \
                 D   E
         */
        bst = new AvlBst<>();
        bst.put(2, 2);
        bst.put(3, 3);
        bst.put(1, 1);
        bst.put(4, 4);
        bst.put(5, 5);

        assertEquals(3, bst.height());
        assertEquals(2, bst.remove(2));
        assertNull(bst.get(2));
        assertEquals(3, bst.height());
        assertEquals(5, bst.get(5));
        assertEquals(3, bst.get(3));
        assertEquals(1, bst.get(1));
        assertEquals(4, bst.get(4));
        /*
            Test remove for:
                A
              /   \
             B     C
            / \   /
           D   E F
         */

        bst = new AvlBst<>();
        bst.put(4, 4);
        bst.put(6, 6);
        bst.put(2, 2);
        bst.put(5, 5);
        bst.put(1, 1);
        bst.put(3, 3);

        assertEquals(3, bst.height());
        assertEquals(4, bst.remove(4));
        assertEquals(3, bst.height());
        assertNull(bst.get(4));
        for (int i = 1; i < 7; i++) {
            if (i != 4)
                assertEquals(i, bst.get(i));
        }
        /*
            Test remove for:
                A
              /   \
             B     C
            / \     \
           D   E     F
         */

        bst = new AvlBst<>();
        bst.put(4, 4);
        bst.put(5, 5);
        bst.put(2, 2);
        bst.put(6, 6);
        bst.put(1, 1);
        bst.put(3, 3);

        assertEquals(3, bst.height());
        assertEquals(4, bst.remove(4));
        assertEquals(3, bst.height());
        assertNull(bst.get(4));
        for (int i = 1; i < 7; i++) {
            if (i != 4)
                assertEquals(i, bst.get(i));
        }
        /*
            Test remove for:
                A
              /   \
             B     C
            /     / \
           D     E   F
         */

        bst = new AvlBst<>();

        bst.put(3, 3);
        bst.put(5, 5);
        bst.put(2, 2);
        bst.put(6, 6);
        bst.put(1, 1);
        bst.put(4, 4);

        assertEquals(3, bst.height());
        assertEquals(3, bst.remove(3));
        assertEquals(3, bst.height());
        assertNull(bst.get(3));
        for (int i = 1; i < 7; i++) {
            if (i != 3)
                assertEquals(i, bst.get(i));
        }
        /*
            Test remove for:
                A
              /   \
             B     C
              \   / \
               D E   F
         */

        bst = new AvlBst<>();

        bst.put(3, 3);
        bst.put(5, 5);
        bst.put(1, 1);
        bst.put(6, 6);
        bst.put(4, 4);
        bst.put(2, 2);

        assertEquals(3, bst.remove(3));
        assertEquals(3, bst.height());
        assertNull(bst.get(3));
        for (int i = 1; i < 7; i++) {
            if (i != 3)
                assertEquals(i, bst.get(i));
        }
        /*
            Test remove for:
                 A
              /    \
             B      C
            / \    / \
           D   E  F   G
         */
        bst = new AvlBst<>();
        bst.put(4, 4);
        bst.put(6, 6);
        bst.put(2, 2);
        bst.put(7, 7);
        bst.put(5, 5);
        bst.put(1, 1);
        bst.put(3, 3);

        assertEquals(4, bst.remove(4));
        assertEquals(3, bst.height());
        assertNull(bst.get(4));
        for (int i = 1; i < 8; i++) {
            if (i != 4)
                assertEquals(i, bst.get(i));
        }
    }

    @Test
    void orderInput() {
        Bst<Integer, Integer> function2X = new AvlBst<>();

        function2X.put(0, 0);
        for (int i = 1; i < 10000; i++) {
            function2X.put(i, 2 * i);
            assertEquals(i - 1, function2X.floor(i));
            assertEquals(i, function2X.ceil(i - 1));
            assert (function2X.height() <= maxHeight(i + 1));
        }
    }

    @Test
    void reverseOrderInput() {
        Bst<Integer, Integer> function2X = new AvlBst<>();

        int i = 10000;
        function2X.put(i, 2 * i);
        i--;
        int count = 1;
        for (; i >= 0; i--) {
            function2X.put(i, 2 * i);
            count++;
            assertEquals(i, function2X.floor(i + 1));
            assertEquals(i + 1, function2X.ceil(i));
            assert (function2X.height() <= maxHeight(count));
        }
    }

    @Test
    void randomPut() {
        Bst<Integer, Integer> bst = new AvlBst<>();

        for (int i = 0; i < 10000; i++) {
            bst.put(rnd.nextInt(), rnd.nextInt());
            assert (bst.height() <= maxHeight(i + 1));
        }
    }

    @Test
    void orderedOnEmpty() {
        Bst<String, String> bst = newBst();
        assertNull(bst.ceil("some key"));
        assertNull(bst.floor("some key"));

        assertNull(bst.min());
        assertNull(bst.max());

        assertNull(bst.minValue());
        assertNull(bst.maxValue());
    }

    @Test
    void put() {
        Bst<String, String> bst = newBst();
        bst.put("foo", "bar");

        assertEquals("bar", bst.get("foo"));

        assertEquals(1, bst.size());
        assertEquals(1, bst.height());
    }

    @Test
    void replace() {
        Bst<String, String> bst = newBst();
        bst.put("foo", "bar");
        bst.put("foo", "bee");

        assertEquals("bee", bst.get("foo"));

        assertEquals(1, bst.size());
        assertEquals(1, bst.height());
    }

}