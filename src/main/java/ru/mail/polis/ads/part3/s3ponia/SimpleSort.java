package ru.mail.polis.ads.part3.s3ponia;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * https://www.e-olymp.com/ru/submissions/5889508
 */
public class SimpleSort {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int[] array = new int[Integer.parseInt(in.readLine())];
        String[] ints = in.readLine().split(" ");
        for (int i = 0; i < array.length; i++) {
            array[i] = Integer.parseInt(ints[i]);
        }

        Arrays.sort(array);

        for (int value : array) {
            System.out.print("" + value + " ");
        }
    }
}
