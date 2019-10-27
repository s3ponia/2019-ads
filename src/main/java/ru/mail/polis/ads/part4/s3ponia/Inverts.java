package ru.mail.polis.ads.part4.s3ponia;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * https://www.e-olymp.com/ru/submissions/5960847
 */
public class Inverts {

    private static int inversions = 0;

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
                inversions += med - it1;
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
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int count = 0;

        int n = Integer.parseInt(reader.readLine());
        String[] nums = reader.readLine().split(" ");
        int[] array = new int[n];

        for (int i = 0; i < n; i++) {
            array[i] = Integer.parseInt(nums[i]);
        }

        sort(array,0,array.length);

        System.out.println(inversions);
    }
}
