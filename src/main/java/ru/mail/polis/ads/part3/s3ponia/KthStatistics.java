package ru.mail.polis.ads.part3.s3ponia;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Arrays;

/**
 * https://www.e-olymp.com/ru/submissions/5907983
 */
public class KthStatistics {

    private static void merge(BigInteger[] input, int start, int med, int e) {
        BigInteger[] res = new BigInteger[e - start];

        int j = 0;
        int it1 = start;
        int it2 = med;

        while (it1 < med && it2 < e) {
            if (input[it1].compareTo(input[it2]) < 0) {
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

    private static void sort(BigInteger[] arr, int b, int e) {
        if ((e - b) <= 1)
            return;

        sort(arr, b, (e + b) / 2);
        sort(arr, (e + b) / 2, e);
        merge(arr, b, (e + b) / 2, e);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int k = Integer.parseInt(in.readLine());
        String[] ints = in.readLine().split(" ");
        BigInteger[] array = new BigInteger[ints.length];
        for (int i = 0; i < ints.length; i++) {
            array[i] = new BigInteger(ints[i]);
        }

        sort(array, 0, array.length);

        System.out.println(array[array.length - k]);
    }
}
