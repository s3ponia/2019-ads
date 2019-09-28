package ru.mail.polis.ads.part1.s3ponia;

import java.util.Scanner;

/**
 * https://www.e-olymp.com/ru/submissions/5735965
 */
public class PseudoRLE {
    private static String repeatString(final String inputString, int numberOfRepeats) {
        final StringBuilder result = new StringBuilder();

        while (numberOfRepeats-- > 0) {
            result.append(inputString);
        }

        return result.toString();
    }

    private static String printSolution(final String input, final int b, final int e, final int[][] arrayRestore) {
        if (b > e) {
            return "";
        }
        if (arrayRestore[b][e] == 0) {
            return input.substring(b, e + 1);
        }
        if (arrayRestore[b][e] < 0) {
            final int temp = -arrayRestore[b][e] - 1;

            return (e - b + 1) / (temp - b + 1) + "(" + printSolution(input, b, temp, arrayRestore) + ")";
        } else {
            return printSolution(input, b, arrayRestore[b][e] - 1, arrayRestore) +
                    printSolution(input, arrayRestore[b][e], e, arrayRestore);
        }
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

        // searching size of compressed input
        for (int j = 0; j < arraySmallestSizePartCanBe[0].length; j++) {
            for (int i = arraySmallestSizePartCanBe.length - 1; i >= 0; i--) {
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

                int temp;
                for (int k = i; k < j; k++) {
                    final int eqCount = (j - k) / (k - i + 1);
                    if ((j - k) % (k - i + 1) == 0 &&
                            repeatString(input.substring(i, k + 1), eqCount).equals(input.substring(k + 1, j + 1)) &&
                            (arraySmallestSizePartCanBe[i][j] == -1 |
                                    (temp = arraySmallestSizePartCanBe[i][k] + 2 +
                                            Integer.toString(eqCount + 1).length())
                                            <
                                            arraySmallestSizePartCanBe[i][j]
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

        return printSolution(input, 0, input.length() - 1, arrayRestoreValue);
    }

    public static void main(final String[] arg) {
        final Scanner in = new Scanner(System.in);
        System.out.println(getCompressedString(in.next()));
    }
}
