package ru.mail.polis.ads.part1.s3ponia;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.IOException;

/**
 * https://www.e-olymp.com/ru/submissions/5742516
 */
public final class SimpleQueue {
    private SimpleQueue() {
    }

    private static void solve(final BufferedReader in, final PrintWriter out) throws IOException {
        final Queue queue = new Queue();
        String query = "";
        while (!"exit".equals(query)) {
            query = in.readLine().trim();
            String[] queryParts=query.split(" ");
            switch (queryParts[0]) {
                case "push":
                    queue.push(Integer.parseInt(queryParts[1]));
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
                    break;
                default:
                    break;
            }
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

    public static void main(final String[] arg) throws IOException {
        final BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        final PrintWriter out = new PrintWriter(System.out);
        solve(in, out);
        out.flush();
    }
}
