package ru.mail.polis.ads.part3.s3ponia;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * https://www.e-olymp.com/ru/submissions/5889870
 */
public class BubbleSort {

    private static int countBubbleSwaps(int[] arr) {
        int count = 0;

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    ++count;
                }
            }
        }

        return count;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int[] array = new int[Integer.parseInt(in.readLine())];
        String[] ints = in.readLine().split(" ");
        for (int i = 0; i < array.length; i++) {
            array[i] = Integer.parseInt(ints[i]);
        }

        System.out.println(countBubbleSwaps(array));
    }
}
