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
        bst.put("A", "B");
        assertEquals("B", bst.remove("A"));
        assertNull(bst.remove("C"));

        bst.put("C", "D");
        bst.put("E", "F");
        bst.put("G", "H");
        bst.put("K", "L");

        assert (bst.height() <= maxHeight(4));

        assertEquals("D", bst.get("C"));
        assertEquals("F", bst.remove("E"));
        assert (bst.height() <= maxHeight(3));
        assertEquals("L", bst.get("K"));
    }

    @Test
    void remove2() {
        Bst<Integer, Integer> bst = new AvlBst<>();

        bst.put(3, 3);
        bst.put(4, 4);
        bst.put(2, 2);
        bst.put(5, 5);
        bst.put(1, 1);

        assert (bst.height() <= maxHeight(5));

        assertEquals(3, bst.remove(3));
        assertEquals(5, bst.get(5));
    }

    @Test
    void orderedPutRemove() {
        Bst<Integer, Integer> function2X = new AvlBst<>();

        for (int i = 0; i < 10000; i++) {
            function2X.put(i, 2 * i);
            if (i % 2 != 0) {
                assertEquals(i / 2 * 2, function2X.remove(i / 2));
            }
            assert (function2X.height() <= maxHeight(i + 1));
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