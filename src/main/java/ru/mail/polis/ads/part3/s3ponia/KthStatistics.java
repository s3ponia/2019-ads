package ru.mail.polis.ads.part3.s3ponia;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

/**
 * https://www.e-olymp.com/ru/submissions/5916868
 */
public class KthStatistics {

    private static int partition(BigInteger[] arr, int low, int high) {
        BigInteger pivot = arr[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (arr[j].compareTo(pivot) < 0) {
                i++;

                BigInteger temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        BigInteger temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }

    private static BigInteger getKStatistics(BigInteger[] array, int k) {
        int l = 0;
        int r = array.length - 1;

        while (l <= r) {
            int temp = partition(array, l, r);
            if (temp == k) {
                return array[temp];
            }
            if (temp > k) {
                r = temp - 1;
            } else {
                l = temp + 1;
                k = k - temp + l - 1;
            }
        }

        return array[k];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int k = Integer.parseInt(in.readLine());
        String[] ints = in.readLine().split(" ");
        BigInteger[] array = new BigInteger[ints.length];
        for (int i = 0; i < ints.length; i++) {
            array[i] = new BigInteger(ints[i]);
        }

        System.out.println(getKStatistics(array, array.length - k));
    }
}
