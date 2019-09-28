package ru.mail.polis.ads.part1.s3ponia;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

/**
 * https://www.e-olymp.com/ru/submissions/5736141
 */
public class LongestCommonSubsequence {
    private LongestCommonSubsequence() {
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int[] firstSequence = new int[in.nextInt()];
        for (int i = 0; i < firstSequence.length; i++) {
            firstSequence[i] = in.nextInt();
        }

        int[] secondSequence = new int[in.nextInt()];
        for (int i = 0; i < secondSequence.length; i++) {
            secondSequence[i] = in.nextInt();
        }

        int[] prevLCS = new int[firstSequence.length + 1];
        int[] secondLCS = new int[firstSequence.length + 1];
        for (int j = 1; j <= secondSequence.length; j++) {
            for (int i = 1; i <= firstSequence.length; i++) {
                if (firstSequence[i - 1] == secondSequence[j-1]) {
                    secondLCS[i] = prevLCS[i - 1] + 1;
                } else {
                    secondLCS[i] = Math.max(prevLCS[i], secondLCS[i - 1]);
                }
            }
            prevLCS = secondLCS;
            secondLCS = new int[secondLCS.length];
        }

        out.print(prevLCS[prevLCS.length - 1]);
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

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
