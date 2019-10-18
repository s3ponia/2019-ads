package ru.mail.polis.ads.part3.s3ponia;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

/**
 * https://www.e-olymp.com/ru/submissions/5889753
 */
public class TrickySort {

    private static int compare(int a, int b) {
        if (a % 10 != b % 10) {
            return a % 10 - b % 10;
        } else {
            return a - b;
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int sz = in.nextInt();
        List<Integer> array = new ArrayList<>(sz);

        for (int i = 0; i < sz; i++) {
            array.add(in.nextInt());
        }

        array.sort(TrickySort::compare);

        for (Integer int_ :
                array) {
            System.out.print(int_ + " ");
        }
    }
}
