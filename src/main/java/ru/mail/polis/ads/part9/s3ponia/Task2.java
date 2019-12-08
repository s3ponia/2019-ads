package ru.mail.polis.ads.part9.s3ponia;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * https://www.e-olymp.com/ru/submissions/6298440
 */
public class Task2 {
    private final static int WHITE = 0;
    private final static int GREY = 1;
    private final static int BLACK = 2;

    private static byte[] used;
    private static int[] previous;
    private static List<List<Integer>> graph;
    private static boolean[] inLoop;
    private static int minimum;

    private static void dfs(final int node) {
        Stack<Integer> stack = new Stack<>();
        stack.add(node);

        while (!stack.empty()) {
            int curr = stack.peek();
            used[curr] = GREY;
            int count = 0;

            for (Integer end :
                    graph.get(curr)) {
                if (previous[curr] == end)
                    continue;
                if (used[end] == BLACK)
                    continue;
                if (used[end] == WHITE) {
                    previous[end] = curr;
                    stack.add(end);
                    ++count;
                    break;
                } else if (used[end] == GREY) {

                    int it = curr;
                    inLoop[end] = true;
                    if (inLoop[it])
                        continue;
                    while (it != end) {
                        inLoop[it] = true;
                        it = previous[it];
                        if (inLoop[it])
                            break;
                    }

                }
            }
            if (count == 0) {
                stack.pop();
                used[curr] = BLACK;
            }
        }
    }

    private static void setMinimum() {
        for (int i = 1; i < graph.size(); i++) {
            if (used[i] == 0)
                dfs(i);

        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] query = in.readLine().split(" ");

        int n = Integer.parseInt(query[0]);
        int m = Integer.parseInt(query[1]);

        used = new byte[n + 1];

        previous = new int[n + 1];

        inLoop = new boolean[n + 1];

        graph = new ArrayList<>(n + 1);

        for (int i = 0; i < n + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < n + 1; i++) {
            used[i] = WHITE;
            previous[i] = -1;
            inLoop[i] = false;
        }

        for (int i = 0; i < m; i++) {
            query = in.readLine().split(" ");
            int b = Integer.parseInt(query[0]);
            int e = Integer.parseInt(query[1]);
            graph.get(b).add(e);
            graph.get(e).add(b);
        }

        setMinimum();


        minimum = graph.size();

        for (int i = 1; i < inLoop.length; i++) {
            if (inLoop[i] && minimum > i) {
                minimum = i;
            }
        }

        if (minimum == graph.size()) {
            System.out.println("No");
        } else {
            System.out.printf("Yes\n%d", minimum);
        }
    }
}