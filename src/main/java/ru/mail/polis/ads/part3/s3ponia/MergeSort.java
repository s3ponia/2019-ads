package ru.mail.polis.ads.part3.s3ponia;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;

/**
 * https://www.e-olymp.com/ru/submissions/5891319
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

        Arrays.sort(robots, MergeSort::comparator);

        StringBuilder stringBuilder = new StringBuilder(20);
        for (Pair pair :
                robots) {
            stringBuilder.append(pair.firstValue).append(" ").append(pair.secondValue).append('\n');
        }
        out.println(stringBuilder);
        out.flush();
    }
}
