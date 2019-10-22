package ru.mail.polis.ads.part4.s3ponia;

import java.io.*;
import java.util.ArrayList;

public class Median {

    private static void siftDownLast(int[] array, int size) {
        int i = size - 1;
        while (i > 0 && array[i] > array[i - 1]) {
            Integer temp = array[i];
            array[i] = array[i - 1];
            array[i - 1] = temp;
            --i;
        }
    }

    private static void siftDownLast(ArrayList<Integer> arrayList) {
        int i = arrayList.size() - 1;
        while (i > 0 && arrayList.get(i) < arrayList.get(i - 1)) {
            Integer temp = arrayList.get(i);
            arrayList.set(i, arrayList.get(i - 1));
            arrayList.set(i - 1, temp);
            --i;
        }
    }

    public static void main(String[] args) throws IOException {
        int[] array = new int[1000001];
        int free = 0;

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));

        String input;
        while ((input = in.readLine()) != null) {
            array[free++] = Integer.parseInt(input);
            siftDownLast(array, free);

            if (free % 2 == 0) {
                out.println((array[free / 2] + array[free / 2 - 1]) / 2);
            } else {
                out.println(array[free / 2]);
            }
        }
        out.flush();
    }
}
