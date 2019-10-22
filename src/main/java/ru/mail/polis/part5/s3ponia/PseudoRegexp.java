package ru.mail.polis.part5.s3ponia;

import java.util.Scanner;

/**
 * https://www.e-olymp.com/ru/submissions/5925363
 */
public class PseudoRegexp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String word = scanner.nextLine();
        String pattern = scanner.nextLine();

        if(word.contains("*")|| word.contains("?")){
            String temp = word;
            word = pattern;
            pattern = temp;
        }

        boolean[][] isMatch = new boolean[word.length() + 1][pattern.length() + 1];
        isMatch[0][0] = true;

        for (int i = 1; i < word.length() + 1; i++) {
            for (int j = 1; j < pattern.length() + 1; j++) {
                if (word.charAt(i - 1) == pattern.charAt(j - 1) || pattern.charAt(j - 1) == '?') {
                    isMatch[i][j] = isMatch[i - 1][j - 1];
                } else if (pattern.charAt(j - 1) == '*') {
                    isMatch[i][j] = isMatch[i - 1][j] || isMatch[i - 1][j - 1] || isMatch[i][j - 1];
                } else {
                    isMatch[i][j] = false;
                }
            }
        }

        System.out.println(isMatch[isMatch.length - 1][isMatch[0].length - 1] ? "YES" : "NO");
    }
}
