package ru.mail.polis.ads.part1.s3ponia;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

/**
 * https://www.e-olymp.com/ru/submissions/5736452
 */
public class SimpleQueue {

    private SimpleQueue() {
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        Queue queue = new Queue();
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
                case "size":
                    out.println(queue.size());
                    break;
                case "clear":
                    queue.clear();
                    out.println("ok");
                    break;
                case "exit":
                    out.println("bye");
                    break endless;
            }
            out.flush();
        }
    }

    private static class Queue {
        private static final int size = 101;
        private int free;
        private int begin;
        private int[] queue;

        Queue() {
            free = 0;
            begin = 0;
            queue = new int[size];
        }

        public void push(int el) {
            queue[free] = el;
            free = (free + 1) % size;
        }

        public int size() {
            if (free < begin) {
                return free + size - begin;
            } else {
                return free - begin;
            }
        }

        public void clear() {
            free = begin;
        }

        public int front() {
            return queue[begin];
        }

        public int pop() {
            int prevBegin = begin;
            begin = (begin + 1) % size;
            return queue[prevBegin];
        }

        private void realloc() {
        }
    }

    private static class FastScanner {
        private final BufferedReader reader;
        private StringTokenizer tokenizer;

        FastScanner(final InputStream in) {
            reader = new BufferedReader(new InputStreamReader(in));
        }

        String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return tokenizer.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
