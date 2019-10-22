package ru.mail.polis.part5.s3ponia;

import java.util.Scanner;

/**
 * https://www.e-olymp.com/ru/submissions/5924537
 */
public class Diploms {

    private static long canPush(long d, int w, int h) {
        return (d / w) * (d / h);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int w = scanner.nextInt();
        int h = scanner.nextInt();

        int maxPart = w;
        if (maxPart < h)
            maxPart = h;

        long n = scanner.nextLong();

        long l = maxPart;
        long r = n * maxPart;

        while (l < r) {
            long m = (l + r) / 2;
            long nk = canPush(m, w, h);

            if (nk >= n) {
                r = m;
            } else {
                l = m + 1;
            }
        }

        System.out.println(l);
    }
}
