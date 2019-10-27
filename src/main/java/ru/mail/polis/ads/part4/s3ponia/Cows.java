package ru.mail.polis.ads.part4.s3ponia;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * https://www.e-olymp.com/ru/submissions/5964291
 */
public class Cows {

    private static class FastScanner {
        private final BufferedReader reader;
        private StringTokenizer tokenizer;

        FastScanner(final InputStream in) {
            reader = new BufferedReader(new InputStreamReader(in));
        }

        String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return tokenizer.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }

    public static void main(String[] args) {
        FastScanner reader = new FastScanner(System.in);

        int n = reader.nextInt();
        int k = reader.nextInt();

        int[] stables = new int[n];

        for (int i = 0; i < n; i++) {
            stables[i] = reader.nextInt();
        }

        int l = 0;
        int r = stables[stables.length - 1] - stables[0];

        if (k == 2) {
            System.out.println(r);
            return;
        }
        while (l < r) {
            int cows = k - 1;
            int m = (l + r) / 2;
            int j = 0;
            for (int i = 1; i < n; i++) {
                if (stables[i] - stables[j] >= m) {
                    j = i;
                    --cows;
                }
            }

            if (cows > 0)
                r = m;
            else
                l = m + 1;
        }

        System.out.println(l);
    }
}
