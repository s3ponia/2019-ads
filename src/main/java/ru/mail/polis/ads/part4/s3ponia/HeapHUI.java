package ru.mail.polis.ads.part4.s3ponia;

import java.io.*;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * https://www.e-olymp.com/ru/submissions/5891583
 */
public class HeapHUI {

    private static int compare(Integer a, Integer b) {
        return b - a;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter bufferedWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

        Queue<Integer> heap = new PriorityQueue<>(HeapHUI::compare);

        int n = Integer.parseInt(bufferedReader.readLine());

        for (int i = 0; i < n; i++) {
            String[] query = bufferedReader.readLine().split(" ");
            int frst = Integer.parseInt(query[0]);
            switch (frst) {
                case 1:
                    bufferedWriter.println(heap.poll());
                    break;
                case 0:
                    Integer scd = Integer.parseInt(query[1]);
                    heap.add(scd);
                    break;
                default:
                    throw new IllegalArgumentException("Wrong request! Able only 1 or 0.");
            }
        }

        bufferedWriter.flush();
    }
}
