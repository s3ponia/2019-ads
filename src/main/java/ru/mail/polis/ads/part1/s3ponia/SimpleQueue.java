package ru.mail.polis.ads.part1.s3ponia;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * https://www.e-olymp.com/ru/submissions/5737704
 */
public final class SimpleQueue {
    private SimpleQueue() {
    }

    private static void solve(final FastScanner in) {
        final Queue queue = new Queue();
        String query = "";
        while (!"exit".equals(query)) {
            query = in.next().trim();
            switch (query) {
                case "push":
                    queue.push(in.nextInt());
                    System.out.println("ok");
                    break;
                case "pop":
                    System.out.println(queue.pop());
                    break;
                case "front":
                    System.out.println(queue.front());
                    break;
                case "size":
                    System.out.println(queue.size());
                    break;
                case "clear":
                    queue.clear();
                    System.out.println("ok");
                    break;
                case "exit":
                    System.out.println("bye");
                    break;
                default:
                    break;
            }
        }
    }

    public static class FastScanner {
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
        final FastScanner in = new FastScanner(System.in);
        solve(in);
    }
}
