package ru.mail.polis.ads.part3.s3ponia;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;

/**
 * https://www.e-olymp.com/ru/submissions/5908069
 */
public class MergeSort {

    static class Pair {
        int firstValue;
        int secondValue;
    }

    private static int comparator(Pair p1, Pair p2) {
        if (p1.firstValue == p2.firstValue) {
            return 0;
        }
        return p1.firstValue - p2.firstValue > 0 ? 1 : -1;
    }

    private static void merge(Pair[] input, int start, int med, int e) {
        Pair[] res = new Pair[e - start];

        int j = 0;
        int it1 = start;
        int it2 = med;

        while (it1 < med && it2 < e) {
            if (comparator(input[it1], input[it2]) <= 0) {
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

    private static void sort(Pair[] arr, int b, int e) {
        if ((e - b) <= 1)
            return;

        sort(arr, b, (e + b) / 2);
        sort(arr, (e + b) / 2, e);
        merge(arr, b, (e + b) / 2, e);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

        int n = Integer.parseInt(in.readLine());
        Pair[] robots = new Pair[n];

        for (int i = 0; i < n; i++) {
            Pair input = new Pair();
            String[] pair = in.readLine().split(" ");
            input.firstValue = Integer.parseInt(pair[0]);
            input.secondValue = Integer.parseInt(pair[1]);
            robots[i] = input;
        }

        sort(robots, 0, robots.length);

        StringBuilder stringBuilder = new StringBuilder(20);
        for (Pair pair :
                robots) {
            stringBuilder.append(pair.firstValue).append(" ").append(pair.secondValue).append('\n');
        }
        out.println(stringBuilder);
        out.flush();
    }
}
