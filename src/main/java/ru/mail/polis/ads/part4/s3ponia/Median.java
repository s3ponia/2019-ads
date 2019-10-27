package ru.mail.polis.ads.part4.s3ponia;

import java.io.*;
import java.util.Random;

/**
 * https://www.e-olymp.com/ru/submissions/5960718
 */

public class Median {

    private static void siftUpMax(int[] array, int id) {

        while (id > 0 && array[id] > array[(id - 1) / 2]) {
            int temp = array[id];
            array[id] = array[(id - 1) / 2];
            array[(id - 1) / 2] = temp;
            id = (id - 1) / 2;
        }
    }

    private static void siftDownMax(int[] array, int id, int sz) {
        int left = 2 * id + 1;
        int right = (2 * id) + 2;
        while ((left < sz && array[id] < array[left]) || (right < sz && array[id] < array[right])) {
            int min = left;
            if (right < sz && array[right] > array[min])
                min = right;

            int temp = array[min];
            array[min] = array[id];
            array[id] = temp;
            id = min;
            left = 2 * id + 1;
            right = 2 * id + 2;
            if (left >= sz)
                return;
        }
    }


    private static void pushMaxHeap(int[] array, int el, int sz) {
        array[sz] = el;
        siftUpMax(array, sz);
    }

    private static int popMaxHeap(int[] array, int sz) {
        int val = array[0];
        array[0] = array[sz - 1];
        siftDownMax(array, 0, sz - 1);
        return val;
    }

    private static void siftUpMin(int[] array, int id) {

        while (id > 0 && array[id] < array[(id - 1) / 2]) {
            int temp = array[id];
            array[id] = array[(id - 1) / 2];
            array[(id - 1) / 2] = temp;
            id = (id - 1) / 2;
        }
    }

    private static void siftDownMin(int[] array, int id, int sz) {
        int left = 2 * id + 1;
        int right = (2 * id) + 2;
        while ((left < sz && array[id] > array[left]) || (right < sz && array[id] > array[right])) {
            int min = left;
            if (right < sz && array[right] < array[min])
                min = right;

            int temp = array[min];
            array[min] = array[id];
            array[id] = temp;
            id = min;
            left = 2 * id + 1;
            right = 2 * id + 2;
            if (left >= sz)
                return;
        }
    }


    private static void pushMinHeap(int[] array, int el, int sz) {
        array[sz] = el;
        siftUpMin(array, sz);
    }

    private static int popMinHeap(int[] array, int sz) {
        int val = array[0];
        array[0] = array[sz - 1];
        siftDownMin(array, 0, sz - 1);
        return val;
    }

    public static void main(String[] args) throws IOException {
        Random random = new Random();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter printWriter = new PrintWriter(new BufferedOutputStream(System.out));

        String input = reader.readLine();
        int median = Integer.parseInt(input);
        input = reader.readLine();
        printWriter.println(median);

        int[] left = new int[500000];
        int leftSize = 0;
        int[] right = new int[5000000];
        int rightSize = 0;

        int count = 1;

        while (input != null) {
            ++count;
            int curr = Integer.parseInt(input);

            if (curr > median) {
                pushMinHeap(right, curr, rightSize);
                ++rightSize;
            } else {
                pushMaxHeap(left, curr, leftSize);
                ++leftSize;
            }

            if (leftSize > rightSize + 1) {
                pushMinHeap(right, median, rightSize);
                median = popMaxHeap(left, leftSize);
                --leftSize;
                ++rightSize;
            }

            if (rightSize > leftSize + 1) {
                pushMaxHeap(left, median, leftSize);
                median = popMinHeap(right, rightSize);
                ++leftSize;
                --rightSize;
            }

            if (count % 2 == 0) {
                if (leftSize > rightSize) {
                    printWriter.println((left[0] + median) / 2);
                } else {
                    printWriter.println((right[0] + median) / 2);
                }
            } else {
                printWriter.println(median);
            }

            input = reader.readLine();
        }
        printWriter.flush();
    }
}