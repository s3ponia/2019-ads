package ru.mail.polis.ads.part3.s3ponia;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * https://www.e-olymp.com/ru/submissions/5908162
 */
public class SimpleSort {

    private static void merge(int[] input, int start, int med, int e) {
        int[] res = new int[e - start];

        int j = 0;
        int it1 = start;
        int it2 = med;

        while (it1 < med && it2 < e) {
            if (input[it1] <= input[it2]) {
                res[j++] = input[it1++];
            } else {
                res[j++] = input[it2++];
            }
        }

        while (it1 < med) {
            res[j++] = input[it1++];
        }

        while (it2 < e) {
            res[j++] = input[it2++];
        }

        System.arraycopy(res, 0, input, start, res.length);

    }

    private static void sort(int[] arr, int b, int e) {
        if ((e - b) <= 1)
            return;

        sort(arr, b, (e + b) / 2);
        sort(arr, (e + b) / 2, e);
        merge(arr, b, (e + b) / 2, e);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int[] array = new int[Integer.parseInt(in.readLine())];
        String[] ints = in.readLine().split(" ");
        for (int i = 0; i < array.length; i++) {
            array[i] = Integer.parseInt(ints[i]);
        }

        sort(array, 0, array.length);

        for (int value : array) {
            System.out.print("" + value + " ");
        }
    }
}
