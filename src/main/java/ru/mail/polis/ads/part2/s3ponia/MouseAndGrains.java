package ru.mail.polis.ads.part2.s3ponia;

import java.util.Scanner;

/**
 * https://www.e-olymp.com/ru/submissions/5781222
 */
public class MouseAndGrains {

    private static String solve(int[][] map) {
        int[][] dinamic = new int[map.length + 1][map[0].length + 1];

        for (int i = dinamic.length - 2; i >= 0; i--) {
            for (int j = 1; j < dinamic[0].length; j++) {
                dinamic[i][j] = Math.max(dinamic[i + 1][j], dinamic[i][j - 1]) + map[i][j - 1];
            }
        }

        char[] result = new char[map.length + map[0].length - 2];

        int itY = 0;
        int itX = dinamic[0].length - 1;
        int it = result.length - 1;

        while (it >= 0) {
            if (itY + 1 < dinamic.length - 1 && dinamic[itY + 1][itX] >= dinamic[itY][itX - 1]) {
                result[it--] = 'F';
                ++itY;
            } else {
                result[it--] = 'R';
                --itX;
            }
        }

        return String.valueOf(result);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int ySize = scanner.nextInt();
        int xSize = scanner.nextInt();

        int[][] map = new int[ySize][xSize];

        for (int i = 0; i < ySize; i++) {
            for (int j = 0; j < xSize; j++) {
                map[i][j] = scanner.nextInt();
            }
        }

        System.out.println(solve(map));
    }
}
