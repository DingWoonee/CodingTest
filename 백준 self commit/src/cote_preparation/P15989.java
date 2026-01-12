package cote_preparation;

import java.io.*;
/**
 * 1 1 2 3 4 5
 * 1 1 2 3 4 5
 * 5 -> 3 2 / 3 1 1 / 2 2 1 / 2 1 1 1 / 1 1 1 1 1
 */
public class P15989 {
    // public static void main(String[] args) throws IOException {
    //     BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    //     int T = Integer.parseInt(br.readLine());
    //     for (int i = 0; i < T; i++) {
    //         int n = Integer.parseInt(br.readLine());

    //         int answer = 0;
    //         for (int cnt3 = 0; cnt3 <= n / 3; cnt3++) {
    //             int temp = n - cnt3 * 3;
    //             answer += (temp / 2 + 1);
    //         }

    //         System.out.println(answer);
    //     }
    // }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            int n = Integer.parseInt(br.readLine());

            int[] dp = new int[n + 1];
            dp[0] = 1;
            int[] coins = new int[]{3, 2, 1};
            for (int c : coins) {
                for (int k = 0; k + c <= n; k++) {
                    dp[k + c] += dp[k];
                }
            }

            System.out.println(dp[n]);
        }
    }
}
