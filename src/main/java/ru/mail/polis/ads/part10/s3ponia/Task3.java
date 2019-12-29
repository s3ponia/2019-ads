package ru.mail.polis.ads.part10.s3ponia;

import java.io.*;
import java.util.*;

/**
 * 65%
 * https://www.e-olymp.com/ru/submissions/6427101
 */
public class Task3 {
    private static final int INF = 1_000_000;

    static class Node {
        int node;
        int distance;

        int getDistance() {
            return distance;
        }

        Node(int node, int weight) {
            this.node = node;
            this.distance = weight;
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

        int n = in.nextInt();
        int m = in.nextInt();

        int s = 1;
        int f = n;

        List<List<Node>> graph = new ArrayList<>(n + 1);

        boolean[] used = new boolean[n + 1];

        int[] distance = new int[n + 1];

        PriorityQueue<Node> priorityQueue = new PriorityQueue<>(Comparator.comparing(Node::getDistance));

        for (int i = 0; i < n + 1; i++) {
            graph.add(new ArrayList<>());
            distance[i] = INF;
        }
        distance[s] = 0;

        for (int i = 0; i < m; i++) {
            int b = Integer.parseInt(in.next());
            int e = Integer.parseInt(in.next());
            int w = Integer.parseInt(in.next());

            graph.get(b).add(new Node(e, w));
            graph.get(e).add(new Node(b, w));
        }

        priorityQueue.add(new Node(s, 0));

        while (!priorityQueue.isEmpty()) {
            int curr = priorityQueue.poll().node;
            if (used[curr])
                continue;
            used[curr] = true;
            for (Node node :
                    graph.get(curr)) {
                int b = node.node;
                int w = node.distance;
                int resultDistance = Math.max(w, distance[curr]);
                if (distance[b] > resultDistance) {
                    distance[b] = resultDistance;
                    priorityQueue.add(new Node(b, resultDistance));
                }
            }
        }

        out.printf("%d\n", distance[f]);
        out.flush();
    }
}