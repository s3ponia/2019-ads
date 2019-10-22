package ru.mail.polis.part5.s3ponia;

import java.io.BufferedOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * https://www.e-olymp.com/ru/submissions/5925267
 */
public class BruteForce {

    private static void swap(int[] a, int i, int j) {
        int s = a[i];
        a[i] = a[j];
        a[j] = s;
    }

    private static boolean nextPermutation(int[] permutation) {
        int n = permutation.length;
        int j = n - 2;
        while (j != -1 && permutation[j] >= permutation[j + 1]) j--;
        if (j == -1)
            return false;
        int k = n - 1;
        while (permutation[j] >= permutation[k]) k--;
        swap(permutation, j, k);
        int l = j + 1, r = n - 1;
        while (l < r)
            swap(permutation, l++, r--);
        return true;
    }

    private static int fact(int n) {
        int start = n;
        for (int i = 1; i < start; i++) {
            n *= i;
        }
        return n;
    }

    private static void printPermutation(int[] permutation, PrintWriter out) {
        for (int value : permutation) {
            out.print(value + " ");
        }
        out.println();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));

        int n = scanner.nextInt();
        int[] permutation = new int[n];

        for (int i = 0; i < n; i++) {
            permutation[i] = i + 1;
        }

        do {
            printPermutation(permutation, out);
        } while (nextPermutation(permutation));

        out.flush();
    }
}
