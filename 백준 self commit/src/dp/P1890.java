package dp;

import java.io.*;
import java.util.*;

public class P1890 {
    public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[][] arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        long[][] dp = new long[N][N];
        dp[0][0] = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (arr[i][j] == 0 || dp[i][j] == 0) continue;
                int d = arr[i][j];
                if (j + d < N) dp[i][j + d] += dp[i][j];
                if (i + d < N) dp[i + d][j] += dp[i][j];
            }
        }

        System.out.println(dp[N - 1][N - 1]);
    }
}