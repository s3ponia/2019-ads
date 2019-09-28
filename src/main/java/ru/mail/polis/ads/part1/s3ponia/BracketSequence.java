package ru.mail.polis.ads.part1.s3ponia;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

/**
 * https://www.e-olymp.com/ru/submissions/5729482
 */
public class BracketSequence {
    private final static int INF = -1;

    private BracketSequence() {
        // Should not be instantiated
    }

    private static char getMatchCharByOpen(char ch) {
        return ch == '(' ? ')' : ch == '[' ? ']' : '\0';
    }


    private static void print(String input, int b, int e, int[][] arrayRestore, PrintWriter out) {
        if (b > e)
            return;

        if (b == e) {
            if (input.charAt(b) == ')' || input.charAt(b) == '(') {
                out.print("()");
            } else {
                out.print("[]");
            }
        } else if (arrayRestore[b][e] == -1) {
            out.print(input.charAt(b));
            print(input, b + 1, e - 1, arrayRestore, out);
            out.print(input.charAt(e));
        } else {
            print(input, b, arrayRestore[b][e], arrayRestore, out);
            print(input, arrayRestore[b][e] + 1, e, arrayRestore, out);
        }


    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        String input = in.next();
        int[][] arrayLCS = new int[input.length()][input.length()];
        int[][] arrayRestore = new int[input.length()][input.length()];

        for (int i = arrayLCS.length - 1; i >= 0; i--) {
            for (int j = 0; j < arrayLCS[0].length; j++) {
                arrayRestore[i][j] = -1;
                arrayLCS[i][j] = -1;
                if (i == j) {
                    arrayLCS[i][j] = 1;
                    continue;
                }
                if (i > j) {
                    arrayLCS[i][j] = 0;
                    continue;
                }
                if (getMatchCharByOpen(input.charAt(i)) == input.charAt(j)) {
                    arrayLCS[i][j] = arrayLCS[i + 1][j - 1];
                }
                for (int k = i; k < j; k++) {
                    int temp = arrayLCS[i][k] + arrayLCS[k + 1][j];
                    if (arrayLCS[i][j] == -1 || temp < arrayLCS[i][j]) {
                        arrayLCS[i][j] = temp;
                        arrayRestore[i][j] = k;
                    }
                }
            }
        }

        print(input, 0, input.length() - 1, arrayRestore, out);
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
        } catch (Exception exc) {
        }
    }
}
