package ru.mail.polis.ads.part1.s3ponia;

import java.io.PrintWriter;
import java.util.Scanner;

/**
 * https://www.e-olymp.com/ru/submissions/5736452
 */
public class SimpleQueue {

    private SimpleQueue() {
    }

    private static void solve(final Scanner in, final PrintWriter out) {
        final Queue queue = new Queue();
        endless:
        while (true) {
            switch (in.next()) {
                case "push":
                    queue.push(in.nextInt());
                    out.println("ok");
                    break;
                case "pop":
                    out.println(queue.pop());
                    break;
                case "front":
                    out.println(queue.front());
                    break;
                case "SIZE":
                    out.println(queue.size());
                    break;
                case "clear":
                    queue.clear();
                    out.println("ok");
                    break;
                case "exit":
                    out.println("bye");
                    break endless;
                default:
                    break;
            }
            out.flush();
        }
    }

    private static class Queue {
        private static final int SIZE = 101;
        private int free;
        private int begin;
        private int[] data;

        Queue() {
            free = 0;
            begin = 0;
            data = new int[SIZE];
        }

        public void push(final int el) {
            data[free] = el;
            free = (free + 1) % SIZE;
        }

        public int size() {
            if (free < begin) {
                return free + SIZE - begin;
            } else {
                return free - begin;
            }
        }

        public void clear() {
            free = begin;
        }

        public int front() {
            return data[begin];
        }

        public int pop() {
            final int prevBegin = begin;
            begin = (begin + 1) % SIZE;
            return data[prevBegin];
        }
    }

    public static void main(final String[] arg) {
        final Scanner in = new Scanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
