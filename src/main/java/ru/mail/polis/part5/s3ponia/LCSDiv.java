package ru.mail.polis.part5.s3ponia;

import java.util.Scanner;

/**
 * https://www.e-olymp.com/ru/submissions/5924751
 */
public class LCSDiv {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = scanner.nextInt();
        }

        int[] length = new int[n];

        length[0] = 1;

        for (int i = 1; i < n; i++) {
            length[i] = 1;
            int max = 0;
            for (int j = i - 1; j >= 0; j--) {
                if (array[j] != 0 && array[i] % array[j] == 0 && max < length[j]) {
                    max = length[j];
                }
            }
            length[i] += max;
        }

        int max = length[n - 1];
        for (int i = 0; i < n - 1; i++) {
            if (max < length[i])
                max = length[i];
        }
        System.out.println(max);
    }
}
