package ru.mail.polis.ads.part1.s3ponia;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

/**
 * https://www.e-olymp.com/ru/submissions/5735965
 */
public class PseudoRLE {
    private PseudoRLE() {
    }

    private static String repeatString(String string, int count) {
        StringBuilder result = new StringBuilder();

        while (count-- > 0)
            result.append(string);

        return result.toString();
    }

    private static void printSolution(String input, int b, int e, int[][] arrayRestore, PrintWriter out) {
        if (b > e) {
            return;
        }
        if (arrayRestore[b][e] == 0) {
            out.print(input.substring(b, e + 1));
            return;
        }
        if (arrayRestore[b][e] < 0) {
            int temp = -arrayRestore[b][e] - 1;

            out.print((e - b + 1) / (temp - b + 1) + "(");
            printSolution(input, b, temp, arrayRestore, out);
            out.print(")");
        } else {
            printSolution(input, b, arrayRestore[b][e] - 1, arrayRestore, out);
            printSolution(input, arrayRestore[b][e], e, arrayRestore, out);
        }
    }

    private static void pseudoRLE(String input, PrintWriter out) {
        int[][] arrayDinamic = new int[input.length()][input.length()];
        int[][] arrayRestore = new int[input.length()][input.length()];

        for (int j = 0; j < arrayDinamic[0].length; j++) {
            for (int i = arrayDinamic.length - 1; i >= 0; i--) {
                arrayRestore[i][j] = 0;
                arrayDinamic[i][j] = -1;
                if (j < i) {
                    arrayDinamic[i][j] = 0;
                    continue;
                }
                if (i == j) {
                    arrayDinamic[i][j] = 1;
                    continue;
                }

                int temp = 0;
                for (int k = i; k < j; k++) {
                    if ((j - k) % (k - i + 1) == 0) {
                        final int eqCount = (j - k) / (k - i + 1);
                        String tempString = repeatString(input.substring(i, k + 1), eqCount);
                        if (tempString.equals(input.substring(k + 1, j + 1))) {
                            temp = arrayDinamic[i][k] + 2 + Integer.toString(eqCount + 1).length();
                            if (arrayDinamic[i][j] == -1 || temp < arrayDinamic[i][j]) {
                                arrayDinamic[i][j] = temp;
                                arrayRestore[i][j] = -(k + 1);
                            }
                        }
                    }

                    temp = arrayDinamic[i][k] + arrayDinamic[k + 1][j];
                    if (arrayDinamic[i][j] == -1 || temp < arrayDinamic[i][j]) {
                        arrayDinamic[i][j] = temp;
                        arrayRestore[i][j] = k + 1;
                    }
                }
            }
        }

        printSolution(input, 0, input.length() - 1, arrayRestore, out);
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        pseudoRLE(in.next(), out);
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
