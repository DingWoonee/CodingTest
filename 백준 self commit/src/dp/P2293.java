package dp;

import java.io.*;
import java.util.*;
/*
 * 1, 2, 5
 * 0 1 2 3 4 5 6 7 8 9 10
 * 0 1 1 1 1 1 1 1 1 1 1
 * 0 1 2 2 3 3 4 4 5 5 6
 * 0 1 2 2 3 4 5 6 7 8 10
 * 
 * 1 1 2 2 3 4 5 6 7 8 6
 */
public class P2293 {
    public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] coins = new int[N];
        for (int i = 0; i < N; i++) {
            coins[i] = Integer.parseInt(br.readLine());
        }

        int[] dp = new int[K + 1];
        dp[0] = 1;
        for (int i = 0; i < N; i++) {
            int c = coins[i];
            for (int j = 0; j + c <= K; j++) {
                dp[j + c] += dp[j];
            }
        }

        System.out.println(dp[K]);
    }
}
