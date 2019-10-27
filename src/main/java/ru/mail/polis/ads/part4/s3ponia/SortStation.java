package ru.mail.polis.ads.part4.s3ponia;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * https://www.e-olymp.com/ru/submissions/5961733
 */
public class SortStation {

    private static boolean check(Integer[] array, int m) {
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] < max) {
                if (array[i] + max > m)
                    return false;
            } else {
                max = array[i];
            }
        }

        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] input = reader.readLine().split(" ");

        int n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);

        Integer[] array = new Integer[n];
        input = reader.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            array[i] = Integer.parseInt(input[i]);
        }

        System.out.println(check(array, m) ? "Yes" : "No");
    }
}
