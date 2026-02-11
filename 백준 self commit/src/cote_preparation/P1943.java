package cote_preparation;

import java.util.*;
import java.io.*;
/**
 * 260211 골드 2
 * 풀이시간: 42분
 * 풀이전략: dp
 */
public class P1943 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 3; i++) {
            int N = Integer.parseInt(br.readLine());
            int[][] coins = new int[N][2]; // {coin, cnt}
            int sum = 0;
            for (int j = 0; j < N; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int coin = Integer.parseInt(st.nextToken());
                int cnt = Integer.parseInt(st.nextToken());
                sum += coin * cnt;
                coins[j][0] = coin;
                coins[j][1] = cnt;
            }
            System.out.println(halfPossible(coins, sum) ? 1 : 0);
        }
    }

    private static boolean halfPossible(int[][] coins, int sum) {
        if (sum % 2 == 1) return false;

        int t = sum / 2;

        boolean[] dp = new boolean[t + 1];
        dp[0] = true;
        int maxC = 0;
        for (int i = 0; i < coins.length; i++) {
            int coin = coins[i][0];
            int cnt = coins[i][1];
            boolean[] tmp = new boolean[t + 1];
            for (int s = maxC; s >= 0; s--) {
                if (!dp[s]) continue;

                for (int c = 1; c <= cnt; c++) {
                    int next = s + coin * c;
                    if (next < dp.length) {
                        tmp[next] = true;
                    }
                }
            }
            for (int j = 0; j < tmp.length; j++) {
                if (tmp[j]) {
                    dp[j] = true;
                    maxC = Math.max(maxC, j);
                }
            }
        }

        return dp[t];
    }
}
