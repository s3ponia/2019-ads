package ru.mail.polis.ads.part9.s3ponia;

import java.io.*;
import java.util.*;

/**
 * https://www.e-olymp.com/ru/submissions/6297827
 */
public class Task1 {
    enum Colors {
        WHITE, GREY, BLACK
    }

    private static List<Integer> nodesArray;
    private static Colors[] used;

    private static void dfs(List<List<Integer>> graph, int node) {
        used[node] = Colors.GREY;
        for (Integer ends :
                graph.get(node)) {
            if (used[ends] == Colors.WHITE)
                dfs(graph, ends);
            else if (used[ends] == Colors.GREY) {
                System.out.println(-1);
                System.exit(0);
            }
        }
        used[node] = Colors.BLACK;
        nodesArray.add(0, node);
    }

    private static void topolSort(List<List<Integer>> graph) {
        for (int i = 1; i < graph.size(); i++) {
            if (used[i] == Colors.WHITE)
                dfs(graph, i);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        List<List<Integer>> graph;
        String[] query = in.readLine().split(" ");

        int n = Integer.parseInt(query[0]);
        int m = Integer.parseInt(query[1]);

        nodesArray = new LinkedList<>();

        used = new Colors[n + 1];

        for (int i = 0; i < n + 1; i++) {
            used[i] = Colors.WHITE;
        }

        graph = new ArrayList<>(n + 1);

        for (int i = 0; i < n + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            query = in.readLine().split(" ");
            int b = Integer.parseInt(query[0]);
            Integer e = Integer.parseInt(query[1]);
            graph.get(b).add(e);
        }

        topolSort(graph);

        for (Integer node :
                nodesArray) {
            out.printf("%d ", node);
        }
        out.flush();
    }
}
