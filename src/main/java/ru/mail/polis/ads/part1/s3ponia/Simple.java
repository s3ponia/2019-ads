package ru.mail.polis.ads.part1.s3ponia;

import java.io.PrintWriter;
import java.util.Scanner;

/**
 * https://www.e-olymp.com/ru/submissions/5712065
 */
public class Simple {
    private Simple() {
        // Should not be instantiated
    }

    private static void solve(final Scanner in, final PrintWriter out) {
        final int input = in.nextInt();
        out.println(input / 10 + " " + input % 10);
    }

    public static void main(final String[] arg) {
        final Scanner in = new Scanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
