package ru.mail.polis.ads.part3.s3ponia;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

/**
 * https://www.e-olymp.com/ru/submissions/5908221
 */
public class TrickySort {

    private static int compare(int a, int b) {
        if (a % 10 != b % 10) {
            return a % 10 - b % 10;
        } else {
            return a - b;
        }
    }

    private static void merge(List<Integer> input, int start, int med, int e) {
        int[] res = new int[e - start];

        int j = 0;
        int it1 = start;
        int it2 = med;

        while (it1 < med && it2 < e) {
            if (compare(input.get(it1), input.get(it2)) <= 0) {
                res[j++] = input.get(it1++);
            } else {
                res[j++] = input.get(it2++);
            }
        }

        while (it1 < med) {
            res[j++] = input.get(it1++);
        }

        while (it2 < e) {
            res[j++] = input.get(it2++);
        }

        for (int i = 0; i < res.length; i++) {
            input.set(start + i, res[i]);
        }

    }

    private static void sort(List<Integer> arr, int b, int e) {
        if ((e - b) <= 1)
            return;

        sort(arr, b, (e + b) / 2);
        sort(arr, (e + b) / 2, e);
        merge(arr, b, (e + b) / 2, e);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int sz = in.nextInt();
        List<Integer> array = new ArrayList<>(sz);

        for (int i = 0; i < sz; i++) {
            array.add(in.nextInt());
        }

        sort(array, 0, array.size());

        for (Integer int_ :
                array) {
            System.out.print(int_ + " ");
        }
    }
}
