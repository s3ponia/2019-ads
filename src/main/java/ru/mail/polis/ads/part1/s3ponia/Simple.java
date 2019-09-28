package ru.mail.polis.ads.part1.s3ponia;

import java.io.PrintWriter;
import java.util.Scanner;

/**
 * https://www.e-olymp.com/ru/submissions/5712065
 */
public class Simple {
    private static String solve(int input) {
        return input / 10 + " " + input % 10;
    }

    public static void main(final String[] arg) {
        final Scanner in = new Scanner(System.in);
        System.out.println(solve(in.nextInt()));
    }
}
