package ru.mail.polis.ads.part2.s3ponia;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * https://www.e-olymp.com/ru/submissions/5783046
 */
public class Brackets {

    private static boolean isRight(String input) {
        int count = 0;

        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == ')') {
                --count;
                if (count < 0) {
                    return false;
                }
            } else {
                ++count;
            }
        }

        return count == 0;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println(isRight(bufferedReader.readLine()) ? "YES" : "NO");
    }
}
