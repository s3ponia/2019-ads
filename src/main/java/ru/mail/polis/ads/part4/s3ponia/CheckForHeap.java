package ru.mail.polis.ads.part4.s3ponia;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * https://www.e-olymp.com/ru/submissions/5891440
 */
public class CheckForHeap {

    private static boolean checkForHeap(long[] array) {
        for (int i = 0; i < array.length / 2; i++) {
            if ((2 * i + 1 < array.length && array[i] > array[2 * i + 1])
                    || (2 * i + 2 < array.length && array[i] > array[2 * i + 2])) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(in.readLine());
        String[] integers = in.readLine().split(" ");
        long[] array = new long[n];

        for (int i = 0; i < n; i++) {
            array[i] = Long.parseLong(integers[i]);
        }

        if (checkForHeap(array)) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}
