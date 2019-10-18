package ru.mail.polis.ads.part3.s3ponia;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Arrays;

/**
 * https://www.e-olymp.com/ru/submissions/5890221
 */
public class KthStatistics {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int k = Integer.parseInt(in.readLine());
        String[] ints = in.readLine().split(" ");
        BigInteger[] array = new BigInteger[ints.length];
        for (int i = 0; i < ints.length; i++) {
            array[i] = new BigInteger(ints[i]);
        }

        Arrays.sort(array);

        System.out.println(array[array.length - k]);
    }
}
