package ru.mail.polis.ads.part2.s3ponia;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * https://www.e-olymp.com/ru/submissions/5783119
 */
public class Stack {

    private int free = 0;
    private int[] stack = new int[0];

    private void push(int n) {
        if (this.free >= this.stack.length) {
            realloc();
        }

        this.stack[this.free++] = n;
    }

    private int pop() {
        if (this.free <= 0) {
            throw new Error();
        }
        return this.stack[--this.free];
    }

    private int back() {
        if (this.free <= 0) {
            throw new Error();
        }
        return this.stack[this.free - 1];
    }

    private int size() {
        return this.free;
    }

    private void clear() {
        this.free = 0;
    }

    private void realloc() {
        this.stack = Arrays.copyOf(this.stack, this.stack.length * 2 + 3);
    }


    private static void solve(BufferedReader in) throws IOException {
        final Stack stack = new Stack();
        String query = "";

        while (!"exit".equals(query)) {
            query = in.readLine().trim();
            final String[] queryParts = query.split(" ");
            try {
                switch (queryParts[0]) {
                    case "push":
                        stack.push(Integer.parseInt(queryParts[1]));
                        System.out.println("ok");
                        break;
                    case "pop":
                        System.out.println(stack.pop());
                        break;
                    case "back":
                        System.out.println(stack.back());
                        break;
                    case "size":
                        System.out.println(stack.size());
                        break;
                    case "clear":
                        stack.clear();
                        System.out.println("ok");
                        break;
                    case "exit":
                        System.out.println("bye");
                        break;
                    default:
                        break;
                }
            } catch (Error error) {
                System.out.println("error");
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        solve(in);
    }
}
