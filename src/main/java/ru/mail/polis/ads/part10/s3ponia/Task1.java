package ru.mail.polis.ads.part10.s3ponia;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * https://www.e-olymp.com/ru/submissions/6427186
 */
public class Task1 {

    private static List<List<Pair>> graph;
    private static int[] tin;
    private static int[] fup;
    private static boolean[] isBridge;
    private static int bridgeCount = 0;
    private static int order = 1;

    static class Pair {
        int edge;
        int end;

        Pair(int edge, int end) {
            this.edge = edge;
            this.end = end;
        }
    }

    static void dfs(int v, int p) {
        tin[v] = fup[v] = ++order;
        for (Pair edge :
                graph.get(v)) {
            if (edge.edge == p)
                continue;
            if (tin[edge.end] != 0) {
                fup[v] = Math.min(fup[v], tin[edge.end]);
            } else {
                dfs(edge.end, edge.edge);
                fup[v] = Math.min(fup[v], fup[edge.end]);
            }
        }

        if (p != -1 && fup[v] == tin[v]) {
            isBridge[p] = true;
            ++bridgeCount;
        }
    }

    static void dfs(int node) {
        dfs(node, -1);
    }

    static void dfsAll() {
        for (int i = 0; i < graph.size(); i++) {
            if (tin[i] == 0)
                dfs(i);
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

    private static void solve(final FastScanner in, final PrintWriter out) {
        final int n = in.nextInt();
        final int m = in.nextInt();

        graph = new ArrayList<>();
        isBridge = new boolean[m+1];
        tin = new int[n+1];
        fup = new int[n+1];
        for (int i = 0; i < n+1; i++) {
            graph.add(new ArrayList<>());
            tin[i] = 0;
            fup[i] = 0;
        }
        for (int i = 1; i < m+1; i++) {
            final int a = in.nextInt();
            final int b = in.nextInt();
            graph.get(a).add(new Pair(i, b));
            graph.get(b).add(new Pair(i, a));
        }

        bridgeCount = 0;

        dfsAll();

        out.println(bridgeCount);
        for (int i = 1; i < m+1 && bridgeCount > 0; i++) {
            if (isBridge[i]) {
                out.print(i + " ");
                bridgeCount--;
            }
        }
        out.flush();
    }


    public static void main(String[] args) {
        final FastScanner in = new FastScanner(System.in);
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
        solve(in, out);
    }
}