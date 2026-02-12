package cote_preparation;

import java.util.*;
import java.io.*;
/**
 * 골드 5
 * dp
 * dp[i][j] = i번째 물건을 넣을지 말지 결정한 후 j 무게가 되는 최대 V
 */
public class P12865 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[][] products = new int[N + 1][2]; // {W, V}

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            products[i + 1][0] = Integer.parseInt(st.nextToken());
            products[i + 1][1] = Integer.parseInt(st.nextToken());
        }
        
        int[][] dp = new int[N + 1][K + 1];
        for (int i = 0; i < dp.length; i++) Arrays.fill(dp[i], -1);
        dp[0][0] = 0;
        for (int i = 0; i < N; i++) {
            int w = products[i + 1][0];
            int v = products[i + 1][1];
            for (int j = 0; j <= K; j++) {
                if (dp[i][j] < 0) continue;

                dp[i + 1][j] = Math.max(dp[i + 1][j], dp[i][j]);

                int nW = j + w;
                if (nW <= K) {
                    dp[i + 1][nW] = Math.max(dp[i + 1][nW], dp[i][j] + v);
                }
            }
        }

        int max = 0;
        for (int i = 0; i <= K; i++) {
            max = Math.max(max, dp[N][i]);
        }
        System.out.println(max);
    }
}
