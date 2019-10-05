package ru.mail.polis.ads.part2.s3ponia;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * https://www.e-olymp.com/ru/submissions/5781401
 */
public class Expressions {

    private static void solveInPlace(String input) {
        int it = input.length() - 1;
        StringBuilder[] strings = new StringBuilder[input.length() / 2 + 1];
        for (int i = 0; i < strings.length; i++) {
            strings[i] = new StringBuilder(10);
        }
        Deque<Integer> stack = new ArrayDeque<>(input.length() / 2 + 1);
        stack.push(0);

        while (it >= 0) {
            int temp = stack.pop();
            char tempChar = input.charAt(it);

            strings[temp].append(tempChar);
            --it;

            if (Character.isUpperCase(tempChar)) {
                stack.push(temp + 1);
                stack.push(temp + 1);
            }
        }

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = strings.length - 1; i >= 0; i--) {
            stringBuilder.append(strings[i]);
        }

        System.out.println(stringBuilder.toString());
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bufferedReader.readLine());
        for (int i = 0; i < n; i++) {
            solveInPlace(bufferedReader.readLine());
        }
    }
}
