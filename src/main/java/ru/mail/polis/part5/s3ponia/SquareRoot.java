package ru.mail.polis.part5.s3ponia;

import java.util.Scanner;

/**
 * https://www.e-olymp.com/ru/submissions/5923888
 */
public class SquareRoot {
    private final static double EPS = 1e-6;

    private static double func(double x) {
        return x * x + Math.sqrt(x);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double c;
        c = scanner.nextDouble();

        double l = 0.;
        double r = Math.sqrt(c);
        double temp = func((l + r) / 2);

        while (Math.abs(temp - c) > EPS) {
            temp = func((l + r) / 2);

            if (temp > c) {
                r = (l + r) / 2;
            } else {
                l = (l + r) / 2;
            }
        }

        System.out.println((l + r) / 2);
    }
}
