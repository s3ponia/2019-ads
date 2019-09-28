package ru.mail.polis.ads.part1.s3ponia;

import java.io.PrintWriter;
import java.util.*;

public final class PseudoRLE {
    private PseudoRLE() {
    }

    private static boolean checkForRepeats(final String input, final int b, final int m, final int e) {
        int iterator2 = m + 1;
        int iterator1 = b;
        int size = m - b + 1;
        while (iterator2 <= e && input.charAt(iterator1) == input.charAt(iterator2)) {
            ++iterator2;
            iterator1 = (iterator1 - b + 1) % size + b;
        }
        return iterator2 > e;
    }

    private static String printSolution(final String input, final int[][] arrayRestore) {
        class Pair {
            int begin;
            int end;
            int closeBracketsCount = 0;

            Pair(int b, int e) {
                this.begin = b;
                this.end = e;
            }

            Pair(int b, int e, int count) {
                this.begin = b;
                this.end = e;
                this.closeBracketsCount = count;
            }
        }

        Stack<Pair> stack = new Stack<>();
        stack.add(new Pair(0, input.length()-1));

        StringBuilder result = new StringBuilder();

        while (!stack.isEmpty()) {
            Pair temp = stack.pop();
            if (arrayRestore[temp.begin][temp.end] == 0) {
                result.append(input, temp.begin, temp.end + 1);
                while (temp.closeBracketsCount > 0) {
                    --temp.closeBracketsCount;
                    result.append(")");
                }
                continue;
            }
            if (arrayRestore[temp.begin][temp.end] < 0) {
                final int tempInt = -arrayRestore[temp.begin][temp.end] - 1;
                result.append((temp.end - temp.begin + 1) / (tempInt - temp.begin + 1)).append("(");
                stack.push(new Pair(temp.begin, tempInt, 1 + temp.closeBracketsCount));
            } else {
                stack.push(new Pair(arrayRestore[temp.begin][temp.end], temp.end, temp.closeBracketsCount));
                stack.push(new Pair(temp.begin, arrayRestore[temp.begin][temp.end] - 1));
            }
        }

        return result.toString();
    }

    private static String getCompressedString(final String input) {
        final int size = input.length();
        // arrayDinamic[i][j] -
        // smallest size for part of the input from i to j
        int[][] arraySmallestSizePartCanBe = new int[size][size];
        // arrayRestoreValue[i][j] = 0 -
        // part from i to j is without compression
        // arrayRestoreValue[i][j] < 0 -
        // repeat of part from i to |arrayRestoreValue[i][j]|-1
        // arrayRestoreValue[i][j] > 0 -
        // part from i to arrayRestoreValue[i][j]-1 and from arrayRestoreValue[i][j] to j are compressed
        int[][] arrayRestoreValue = new int[size][size];

        // utility variables
        int temp;
        int eqCount;
        // searching size of compressed input
        for (int j = 0; j < arraySmallestSizePartCanBe[0].length; j++) {
            for (int i = j; i >= 0; i--) {
                arrayRestoreValue[i][j] = 0;
                // -1 equals infinity
                arraySmallestSizePartCanBe[i][j] = -1;

                if (j < i) {
                    arraySmallestSizePartCanBe[i][j] = 0;
                    continue;
                }

                if (i == j) {
                    arraySmallestSizePartCanBe[i][j] = 1;
                    continue;
                }

                for (int k = i; k < j; k++) {
                    if ((j - k) % (k - i + 1) == 0
                            && checkForRepeats(input, i, k, j)
                            && (arraySmallestSizePartCanBe[i][j] == -1
                            | (temp = arraySmallestSizePartCanBe[i][k]
                            + 2
                            + Integer.toString((j - k) / (k - i + 1) + 1).length())
                            < arraySmallestSizePartCanBe[i][j]
                    )
                    ) {
                        arraySmallestSizePartCanBe[i][j] = temp;
                        arrayRestoreValue[i][j] = -(k + 1);
                    }

                    temp = arraySmallestSizePartCanBe[i][k] + arraySmallestSizePartCanBe[k + 1][j];
                    if (arraySmallestSizePartCanBe[i][j] == -1 || temp < arraySmallestSizePartCanBe[i][j]) {
                        arraySmallestSizePartCanBe[i][j] = temp;
                        arrayRestoreValue[i][j] = k + 1;
                    }
                }
            }
        }

        return printSolution(input, arrayRestoreValue);
    }

    public static void main(final String[] arg) {
        final Scanner in = new Scanner(System.in);
        final PrintWriter out = new PrintWriter(System.out);
        out.println(getCompressedString(in.next()));
        out.flush();
    }
}
