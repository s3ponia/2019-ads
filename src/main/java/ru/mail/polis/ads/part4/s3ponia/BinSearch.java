package ru.mail.polis.ads.part4.s3ponia;

import java.io.*;
import java.util.StringTokenizer;

/**
 * https://www.e-olymp.com/ru/submissions/5963426
 */
public class BinSearch {

    private static int binarySearch(int[] array, int el) {
        int b = 0;
        int e = array.length - 1;

        while (b < e) {
            int m = (b + e) / 2;

            if (array[m] == el)
                return m;

            if (array[m] > el) {
                e = m - 1;
            } else {
                b = m + 1;
            }
        }

        return e;
    }

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

    public static void main(String[] args) throws IOException {
        FastScanner reader = new FastScanner(System.in);
        PrintWriter writer = new PrintWriter(new BufferedOutputStream(System.out));

        int n = reader.nextInt();
        int q = reader.nextInt();

        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = reader.nextInt();
        }

        for (int i = 0; i < q; i++) {
            int el = reader.nextInt();

            writer.println(array[binarySearch(array, el)] == el ? "YES" : "NO");
        }

        writer.flush();

    }
}
