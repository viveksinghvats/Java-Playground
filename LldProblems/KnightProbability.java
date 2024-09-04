package LldProblems;

import java.util.*;

public class KnightProbability {
    public static void main(String[] args) {
        KnightProbability kp = new KnightProbability();
        System.out.println(kp.knightProbability(14, 13, 6, 9));
    }

    double[][][] dp;

    public double knightProbability(int n, int k, int row, int column) {
        dp = new double[n][n][k];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }
        if (k == 0)
            return 1.0;
        double totalValidSteps = count(row, column, k, n);
        return totalValidSteps / Math.pow(8, k);

    }

    private double count(int i, int j, int k, int n) {
        if (dp[i][j][k - 1] != -1)
            return dp[i][j][k - 1];
        double validCount = 0;
        if (isSafe(i - 2, j - 1, n)) {
            if (k > 1) {
                validCount += count(i - 2, j - 1, k - 1, n);
            } else {
                validCount++;
            }
        }
        if (isSafe(i - 2, j + 1, n)) {
            if (k > 1) {
                validCount += count(i - 2, j + 1, k - 1, n);
            } else {
                validCount++;
            }
        }
        if (isSafe(i + 2, j - 1, n)) {
            if (k > 1) {
                validCount += count(i + 2, j - 1, k - 1, n);
            } else {
                validCount++;
            }
        }
        if (isSafe(i + 2, j + 1, n)) {
            if (k > 1) {
                validCount += count(i + 2, j + 1, k - 1, n);
            } else {
                validCount++;
            }
        }
        if (isSafe(i - 1, j - 2, n)) {
            if (k > 1) {
                validCount += count(i - 1, j - 2, k - 1, n);
            } else {
                validCount++;
            }
        }
        if (isSafe(i + 1, j - 2, n)) {
            if (k > 1) {
                validCount += count(i + 1, j - 2, k - 1, n);
            } else {
                validCount++;
            }
        }
        if (isSafe(i - 1, j + 2, n)) {
            if (k > 1) {
                validCount += count(i - 1, j + 2, k - 1, n);
            } else {
                validCount++;
            }
        }
        if (isSafe(i + 1, j + 2, n)) {
            if (k > 1) {
                validCount += count(i + 1, j + 2, k - 1, n);
            } else {
                validCount++;
            }
        }
        dp[i][j][k - 1] = validCount;
        return validCount;
    }

    private boolean isSafe(int i, int j, int n) {
        return (i > -1 && i < n && j > -1 && j < n);
    }

}