package ru.mail.polis.ads.part9.s3ponia;

import java.io.*;
import java.util.Scanner;

/**
 * https://www.e-olymp.com/ru/submissions/6301118
 */
public class Task3 {

    private static final int INF = 30000;

    static class Edge {
        int begin;
        int end;
        int weight;

        Edge() {
        }

        Edge(int begin, int end, int weight) {
            this.begin = begin;
            this.end = end;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

        int n = in.nextInt();
        int m = in.nextInt();

        Edge[] graph = new Edge[m];

        for (int i = 0; i < graph.length; i++) {
            graph[i] = new Edge();
            graph[i].begin = Integer.parseInt(in.next());
            graph[i].end = Integer.parseInt(in.next());
            graph[i].weight = Integer.parseInt(in.next());
        }

        int[] distance = new int[n + 1];
        distance[1] = 0;
        for (int i = 2; i < distance.length; i++) {
            distance[i] = INF;
        }

        for (int i = 0; i < n - 1; i++) {
            for (Edge edge : graph) {
                if (distance[edge.begin] == INF)
                    continue;
                distance[edge.end] = Math.min(distance[edge.end], distance[edge.begin] + edge.weight);
            }
        }

        for (int i = 1; i < distance.length; i++) {
            out.printf("%d ", distance[i]);
        }
        out.flush();
    }
}
