package ru.mail.polis.ads.part1.s3ponia;

import java.util.Scanner;

/**
 * https://www.e-olymp.com/ru/submissions/5736141
 */
public class LongestCommonSubsequence {
    private static int getSizeOfLongestSubsequence(int[] firstSequence, int[] secondSequence) {

        int[] prevLCS = new int[firstSequence.length + 1];
        int[] secondLCS = new int[firstSequence.length + 1];
        for (int j = 1; j <= secondSequence.length; j++) {
            for (int i = 1; i <= firstSequence.length; i++) {
                if (firstSequence[i - 1] == secondSequence[j - 1]) {
                    secondLCS[i] = prevLCS[i - 1] + 1;
                } else {
                    secondLCS[i] = Math.max(prevLCS[i], secondLCS[i - 1]);
                }
            }
            prevLCS = secondLCS;
            secondLCS = new int[secondLCS.length];
        }

        return prevLCS[prevLCS.length - 1];
    }

    public static void main(final String[] arg) {
        final Scanner in = new Scanner(System.in);
        int[] firstSequence = new int[in.nextInt()];
        for (int i = 0; i < firstSequence.length; i++) {
            firstSequence[i] = in.nextInt();
        }

        int[] secondSequence = new int[in.nextInt()];
        for (int i = 0; i < secondSequence.length; i++) {
            secondSequence[i] = in.nextInt();
        }

        System.out.println(getSizeOfLongestSubsequence(firstSequence, secondSequence));
    }
}
