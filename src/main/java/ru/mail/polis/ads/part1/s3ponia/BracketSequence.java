package ru.mail.polis.ads.part1.s3ponia;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

/**
 * https://www.e-olymp.com/ru/submissions/5729482
 */
public class BracketSequence {
    private BracketSequence() {
        // Should not be instantiated
    }

    private static char getMatchCharByOpen(final char ch) {
        return ch == '(' ? ')' : (ch == '[' ? ']' : '\0');
    }


    private static String print(final String input, final int b, final int e, final int[][] arrayRestore) {
        if (b > e) {
            return "";
        }
        if (b == e) {
            if (input.charAt(b) == ')' || input.charAt(b) == '(') {
                return "()";
            } else {
                return "[]";
            }
        } else if (arrayRestore[b][e] == -1) {
            return input.charAt(b) + print(input, b + 1, e - 1, arrayRestore) + input.charAt(e);
        } else {
            return print(input, b, arrayRestore[b][e], arrayRestore) + print(input, arrayRestore[b][e] + 1, e, arrayRestore);
        }


    }

    // procedure that print solution to out
    private static void solve(final Scanner in, final PrintWriter out) {
        final String input = in.next();

        // arraySmallestCorrection[i][j] - smallest size for part of the input from i to j
        int[][] arraySmallestCorrection = new int[input.length()][input.length()];
        // arrayRestore[i][j] = -1 - input[i] == input[j] and part between i and j is fixed
        // arrayRestore[i][j] >= 0 - part between i and arrayRestore[i][j] and part between arrayRestore[i][j]+1 and j are fixed
        int[][] arrayRestore = new int[input.length()][input.length()];

        for (int i = arraySmallestCorrection.length - 1; i >= 0; i--) {
            for (int j = 0; j < arraySmallestCorrection[0].length; j++) {
                arrayRestore[i][j] = -1;
                arraySmallestCorrection[i][j] = -1;
                if (i == j) {
                    arraySmallestCorrection[i][j] = 1;
                    continue;
                }
                if (i > j) {
                    arraySmallestCorrection[i][j] = 0;
                    continue;
                }
                if (getMatchCharByOpen(input.charAt(i)) == input.charAt(j)) {
                    arraySmallestCorrection[i][j] = arraySmallestCorrection[i + 1][j - 1];
                }
                for (int k = i; k < j; k++) {
                    final int temp = arraySmallestCorrection[i][k] + arraySmallestCorrection[k + 1][j];
                    if (arraySmallestCorrection[i][j] == -1 || temp < arraySmallestCorrection[i][j]) {
                        arraySmallestCorrection[i][j] = temp;
                        arrayRestore[i][j] = k;
                    }
                }
            }
        }

        out.println(print(input, 0, input.length() - 1, arrayRestore));
    }

    public static void main(final String[] arg) {
        final Scanner in = new Scanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        } catch (Exception exc) {
            System.err.println(Arrays.toString(exc.getStackTrace()));
        }
    }
}
