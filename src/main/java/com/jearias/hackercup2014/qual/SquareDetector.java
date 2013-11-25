package com.jearias.hackercup2014.qual;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SquareDetector {

    static char[][] matrix;
    static boolean[][] visited;
    static int n;

    public static void main(String[] args) {
        boolean found = false;
        Scanner in = null;
        // in = new Scanner(System.in);
        try {
            in = new Scanner(new FileInputStream("/Users/jose/Downloads/square_detector.txt"));
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        int t = in.nextInt();
        for (int i = 1; i <= t; i++) {
            n = in.nextInt();
            matrix = new char[n][n];
            visited = new boolean[n][n];
            // fill the matrix
            for (int j = 0; j < n; j++) {
                String st = in.next();
                for (int k = 0; k < n; k++) {
                    matrix[j][k] = st.charAt(k);
                }
            }
            found = detectSquare();
            System.out.format("Case #%d: %s\n", i, found == true ? "YES" : "NO");
        }
    }

    private static boolean detectSquare() {
        int k = 0;
        int elements = 0;
        boolean found = false;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                k = j;
                elements = 0;
                char c = matrix[i][k];
                if (!visited[i][j] && c == '#') {
                    do {
                        elements++;
                        k = k + 1;
                        if (k < n) {
                            c = matrix[i][k];
                        } else {
                            break;
                        }
                    } while (!visited[i][k] && c == '#');
                    found = isSquare(i, j, elements);
                    if (!found) {
                        return false;
                    }
                }
            }
        }
        return found;

    }

    private static boolean isSquare(int posX, int posY, int elements) {
        if (elements == 1) {
            return false;
        }
        for (int i = posX; i < posX + elements; i++) {
            for (int j = posY; j < posY + elements; j++) {
                if (i < n && j < n) {
                    char c = matrix[i][j];
                    visited[i][j] = true;
                    if (c == '.') {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
