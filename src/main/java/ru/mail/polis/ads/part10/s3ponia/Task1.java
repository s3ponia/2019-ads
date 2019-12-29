package ru.mail.polis.ads.part10.s3ponia;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Task1 {

    private static List<List<Pair>> graph;
    private static int[] tin;
    private static int[] fup;
    private static boolean[] used;
    private static int order = 1;
    private static List<Integer> answer;

    static class Pair {
        int edge;
        int end;

        Pair(int edge, int end) {
            this.edge = edge;
            this.end = end;
        }
    }

    static void dfs(int v, int p) {
        used[v] = true;
        tin[v] = fup[v] = order++;
        for (Pair edge :
                graph.get(v)) {
            int to = edge.end;
            if (to == p)
                continue;
            if (used[to]) {
                fup[v] = Math.min(fup[v], tin[to]);
            } else {
                dfs(to, v);
                fup[v] = Math.min(fup[v], fup[to]);
                if (fup[to] > tin[v]) {
                    answer.add(edge.edge);
                }
            }
        }
    }

    static void dfs(int node) {
        dfs(node, -1);
    }

    static void dfsAll() {
        for (int i = 0; i < graph.size(); i++) {
            if (!used[i])
                dfs(i);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));

        int n = scanner.nextInt();
        int m = scanner.nextInt();

        graph = new ArrayList<>(n);
        tin = new int[n];
        fup = new int[n];
        used = new boolean[n];
        answer = new ArrayList<>(m);

        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>(m));
        }

        for (int i = 0; i < m; i++) {
            int b = scanner.nextInt() - 1;
            int e = scanner.nextInt() - 1;

            graph.get(b).add(new Pair(i, e));
            graph.get(e).add(new Pair(i, b));
        }

        dfsAll();

        out.println(answer.size());
        answer.sort(Integer::compareTo);
        for (Integer edge :
                answer) {
            out.printf("%d ", edge + 1);
        }

        out.flush();
    }
}