package ru.mail.polis.ads.part2.s3ponia;

import java.util.Deque;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Scanner;

/**
 * https://www.e-olymp.com/ru/submissions/5781256
 */
public class Ladder {

    private static int solve(int[] ladder, int k) {
        int it = 1;
        Deque<Integer> solver = new ArrayDeque<>();
        solver.addFirst(ladder[0]);

        while (it < ladder.length) {
            int max = Collections.max(solver);
            if (solver.size() >= k) {
                solver.poll();
            }
            solver.addLast(max + ladder[it++]);
        }

        return solver.getLast();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] ladder = new int[scanner.nextInt() + 2];

        for (int i = 1; i < ladder.length - 1; i++) {
            ladder[i] = scanner.nextInt();
        }

        System.out.println(solve(ladder, scanner.nextInt()));
    }
}                                                        
                                                         