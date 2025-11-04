package dp;

import java.io.*;
import java.util.*;

public class P11066 {
    public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int cnt = Integer.parseInt(br.readLine());
        for (int i = 0; i < cnt; i++) {
            int N = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            int[] arr = new int[N];
            for (int j = 0; j < N; j++) arr[j] = Integer.parseInt(st.nextToken());
            int[] ps = new int[N + 1];
            for (int j = 1; j <= N; j++) ps[j] = arr[j - 1] + ps[j - 1];
            System.out.println(calculate(arr, ps));
        }
    }

    private static int calculate(int[] arr, int[] ps) {
        int[][] dp = new int[arr.length][arr.length];

        for (int len = 2; len <= arr.length; len++) {
            for (int l = 0; l <= arr.length - len; l++) {
                int r = l + len - 1;
                dp[l][r] = ps[r + 1] - ps[l];
                int min = Integer.MAX_VALUE;
                for (int i = l; i < r; i++) {
                    min = Math.min(min, dp[l][i] + dp[i + 1][r]);
                }
                dp[l][r] += min;
            }
        }

        return dp[0][arr.length - 1];
    }
}
