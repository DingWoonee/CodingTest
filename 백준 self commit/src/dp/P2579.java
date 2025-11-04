package dp;

import java.io.*;
import java.util.*;

public class P2579 {

    private static int N;
    private static int[][] dp;

    public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        dp = new int[N + 1][2];
        for (int i = 0; i < dp.length; i++) Arrays.fill(dp[i], -1);
        int[] stairs = new int[N + 1];
        for (int i = 0; i < N; i++) {
            stairs[i + 1] = Integer.parseInt(br.readLine());
        }

        if (N == 1) {
            System.out.println(stairs[1]);
            return;
        }
        
        dp[0][0] = 0;
        dp[1][0] = stairs[1];
        dp[2][0] = stairs[1] + stairs[2];
        dp[2][1] = stairs[2];

        for (int i = 3; i <= N; i++) {
            dp[i][0] = dp[i - 1][1] + stairs[i];
            dp[i][1] = Math.max(dp[i - 2][0], dp[i - 2][1]) + stairs[i];
        }

        System.out.println(Math.max(dp[N][0], dp[N][1]));
    }
}
