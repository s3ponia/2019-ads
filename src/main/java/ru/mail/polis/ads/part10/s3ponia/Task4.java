package ru.mail.polis.ads.part10.s3ponia;

import java.io.*;
import java.util.*;

/**
 * https://www.e-olymp.com/ru/submissions/6427359
 */
public class Task4 {
    private static List<Integer> nodesArray;
    private static boolean[] used;
    private static int[] colors;
    private static List<Set<Integer>> sets;
    private static int curColor = 1;

    private static void dfsComponent(List<List<Integer>> graph, int node) {
        colors[node] = curColor;
        used[node] = false;
        for (Integer ends :
                graph.get(node)) {
            if (used[ends])
                dfsComponent(graph, ends);
            else if (colors[ends] != curColor) {
                sets.get(curColor).add(colors[ends]);
            }
        }
    }

    private static void dfs(List<List<Integer>> graph, int node) {
        used[node] = true;
        for (Integer ends :
                graph.get(node)) {
            if (!used[ends])
                dfs(graph, ends);
        }
        nodesArray.add(node);
    }

    private static void topolSort(List<List<Integer>> graph) {
        for (int i = 1; i < graph.size(); i++) {
            if (!used[i])
                dfs(graph, i);
        }
    }

    private static List<List<Integer>> reverseGraph(List<List<Integer>> graph) {
        List<List<Integer>> reversed = new ArrayList<>();
        for (int i = 0; i < graph.size(); i++) {
            reversed.add(new ArrayList<>());
        }
        for (int i = 0; i < graph.size(); i++) {
            for (Integer end :
                    graph.get(i)) {
                reversed.get(end).add(i);
            }
        }

        return reversed;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        List<List<Integer>> graph;
        String[] query = in.readLine().split(" ");

        int n = Integer.parseInt(query[0]);
        int m = Integer.parseInt(query[1]);

        nodesArray = new ArrayList<>();

        used = new boolean[n + 1];

        colors = new int[n + 1];

        sets = new ArrayList<>();
        sets.add(new HashSet<>());

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

        graph = reverseGraph(graph);

        int count = 0;

        for (int i = nodesArray.size() - 1; i >= 0; --i) {
            if (!used[nodesArray.get(i)])
                continue;

            sets.add(new HashSet<>());
            dfsComponent(graph, nodesArray.get(i));
            ++curColor;
        }

        for (Set<Integer> set : sets) {
            count += set.size();
        }

        out.println(count);
        out.flush();
    }
}