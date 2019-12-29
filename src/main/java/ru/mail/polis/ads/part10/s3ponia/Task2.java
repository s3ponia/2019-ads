package ru.mail.polis.ads.part10.s3ponia;

import java.io.*;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * https://www.e-olymp.com/ru/submissions/6426994
 */
public class Task2 {

    static class Edge implements Comparable<Edge> {
        int weight;
        int b;
        int e;

        int getWeight() {
            return weight;
        }

        Edge(int weight, int b, int e) {
            this.weight = weight;
            this.b = b;
            this.e = e;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(weight, o.weight);
        }
    }

    static class Set {
        private final int[] parent;
        private final int[] rank;

        Set(int n) {
            parent = new int[n];
            rank = new int[n];
        }

        int findSet(final int a) {
            if (parent[a] != a) {
                parent[a] = findSet(parent[a]);
            }
            return parent[a];
        }

        void makeSet(final int x) {
            rank[x] = 0;
            parent[x] = x;
        }

        void unionSet(final int x, final int y) {
            int a = findSet(x);
            int b = findSet(y);

            if (a != b) {
                if (rank[a] < rank[b]) {
                    int temp = a;
                    a = b;
                    b = temp;
                }
                parent[b] = a;
                if (rank[a] == rank[b]) {
                    ++rank[a];
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        final BufferedReader scanner = new BufferedReader(new InputStreamReader(System.in));
        final PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
        String[] query = scanner.readLine().split(" ");
        final int n = Integer.parseInt(query[0]);
        final int m = Integer.parseInt(query[1]);
        final Queue<Edge> graph = new PriorityQueue<>();

        int cost = 0;

        for (int i = 0; i < m; i++) {
            query = scanner.readLine().split(" ");
            int b = Integer.parseInt(query[0]);
            int e = Integer.parseInt(query[1]);
            int weight = Integer.parseInt(query[2]);
            graph.add(new Edge(weight, b - 1, e - 1));
        }

        Set unions = new Set(n);

        for (int i = 0; i < n; i++) {
            unions.makeSet(i);
        }

        for (int i = 0; i < m; i++) {
            Edge edge = graph.poll();
            int b = edge.b;
            int e = edge.e;
            int weight = edge.weight;
            if (unions.findSet(b) != unions.findSet(e)) {
                cost += weight;
                unions.unionSet(b, e);
            }
        }

        out.println(cost);
        out.flush();
    }
}
