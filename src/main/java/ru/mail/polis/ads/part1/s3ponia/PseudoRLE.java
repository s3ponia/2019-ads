package ru.mail.polis.ads.part1.s3ponia;

import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;


/**
 * https://www.e-olymp.com/ru/submissions/5738196
 */
public final class PseudoRLE {
    private PseudoRLE() {
    }

    private static boolean checkForRepeats(final String input, final int b, final int m, final int e) {
        int iterator2 = m + 1;
        int iterator1 = b;
        final int size = m - b + 1;
        while (iterator2 <= e && input.charAt(iterator1) == input.charAt(iterator2)) {
            ++iterator2;
            iterator1 = (iterator1 - b + 1) % size + b;
        }
        return iterator2 > e;
    }

    private static class BracketsPair {
        private final int first;
        private final int second;
        private int additionalVariable;

        private BracketsPair(final int b, final int e) {
            this.first = b;
            this.second = e;
        }

        private BracketsPair(final int b, final int e, final int additionalVariable) {
            this.first = b;
            this.second = e;
            this.additionalVariable = additionalVariable;
        }

        static BracketsPair valueOf(final int b, final int e, final int additionalVariable) {
            return new BracketsPair(b, e, additionalVariable);
        }

        static BracketsPair valueOf(final int b, final int e) {
            return new BracketsPair(b, e);
        }
    }

    private static String printSolution(final int[][] arrayRestore, final String input) {
        final Deque<BracketsPair> stack = new ArrayDeque<>();
        stack.add(new BracketsPair(0, input.length() - 1));

        final StringBuilder result = new StringBuilder();

        while (!stack.isEmpty()) {
            final BracketsPair temp = stack.pop();
            if (arrayRestore[temp.first][temp.second] == 0) {
                result.append(input, temp.first, temp.second + 1);
                while (temp.additionalVariable > 0) {
                    --temp.additionalVariable;
                    result.append(')');
                }
                continue;
            }
            if (arrayRestore[temp.first][temp.second] < 0) {
                final int tempInt = -arrayRestore[temp.first][temp.second] - 1;
                result.append((temp.second - temp.first + 1) / (tempInt - temp.first + 1)).append('(');
                stack.push(BracketsPair.valueOf(temp.first, tempInt, 1 + temp.additionalVariable));
            } else {
                stack.push(BracketsPair.valueOf(arrayRestore[temp.first][temp.second],
                        temp.second, temp.additionalVariable)
                );
                stack.push(BracketsPair.valueOf(temp.first, arrayRestore[temp.first][temp.second] - 1));
            }
        }

        return result.toString();
    }

    private static BracketsPair getSmallestSizeOfPart(final int[][] arraySmallestSizePartCanBe,
                                                      final String inputString,
                                                      final int i, final int j) {
        int minSize = arraySmallestSizePartCanBe[i][j];
        int restore = 0;

        for (int k = i; k < j; k++) {
            int temp = arraySmallestSizePartCanBe[i][k]
                    + 2 + Integer.toString((j - k) / (k - i + 1) + 1).length();
            if ((j - k) % (k - i + 1) == 0
                    && checkForRepeats(inputString, i, k, j)
                    && (minSize == -1
                    || temp < minSize)
            ) {
                minSize = temp;
                restore = -(k + 1);
            }

            temp = arraySmallestSizePartCanBe[i][k] + arraySmallestSizePartCanBe[k + 1][j];
            if (minSize == -1 || temp < minSize) {
                minSize = temp;
                restore = k + 1;
            }
        }

        return BracketsPair.valueOf(minSize, restore);

    }

    private static int[][] compileArrayForSolution(final String inputString) {
        final int size = inputString.length();
        // arrayDinamic[i][j] -
        // smallest size for part of the input from i to j
        final int[][] arraySmallestSizePartCanBe = new int[size][size];
        // arrayRestoreValue[i][j] = 0 -
        // part from i to j is without compression
        // arrayRestoreValue[i][j] < 0 -
        // repeat of part from i to |arrayRestoreValue[i][j]|-1
        // arrayRestoreValue[i][j] > 0 -
        // part from i to arrayRestoreValue[i][j]-1 and from arrayRestoreValue[i][j] to j are compressed
        final int[][] arrayRestoreValue = new int[size][size];
        // searching size of compressed input
        for (int j = 0; j < arraySmallestSizePartCanBe[0].length; j++) {
            for (int i = j; i >= 0; i--) {
                arrayRestoreValue[i][j] = 0;
                // -1 equals infinity
                arraySmallestSizePartCanBe[i][j] = -1;

                if (i == j) {
                    arraySmallestSizePartCanBe[i][j] = 1;
                    continue;
                }

                final BracketsPair bracketsPairSolution =
                        getSmallestSizeOfPart(arraySmallestSizePartCanBe,
                                inputString, i, j);
                arraySmallestSizePartCanBe[i][j] = bracketsPairSolution.first;
                arrayRestoreValue[i][j] = bracketsPairSolution.second;
            }
        }

        return arrayRestoreValue;
    }

    private static String getCompressedString(final String input) {
        final int[][] arrayRestoreValue = compileArrayForSolution(input);

        return printSolution(arrayRestoreValue, input);
    }

    public static void main(final String[] arg) {
        final Scanner in = new Scanner(System.in);
        final PrintWriter out = new PrintWriter(System.out);
        out.println(getCompressedString(in.next()));
        out.flush();
    }
}
