package dp;

import java.io.*;
/*
 * dp[i][j][k] => 길이가 i이고 j로 끝나면서 k(bit mask)를 포함한 계단 수의 수
 * i -> 100 / j -> 10 / k -> 1024 => 총 100만 굿.
 */
public class P1562 {

    private static final int MOD = 1_000_000_000;

    public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] offset = new int[10];
        for (int i = 0; i < 10; i++) offset[i] = 1 << i;
        
        int[][][] dp = new int[N + 1][10][1024];
        for (int i = 1; i < 10; i++) {
            dp[1][i][offset[i]] = 1;
        }

        for (int i = 1; i < N; i++) {
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < 1024; k++) {
                    if (dp[i][j][k] == 0) continue;
                    int add = dp[i][j][k];
                    if (j > 0) {
                        dp[i + 1][j - 1][k | offset[j - 1]] += add;
                        dp[i + 1][j - 1][k | offset[j - 1]] %= MOD;
                    }
                    if (j < 9) {
                        dp[i + 1][j + 1][k | offset[j + 1]] += add;
                        dp[i + 1][j + 1][k | offset[j + 1]] %= MOD;
                    }
                }
            }
        }

        // 정답 출력
        long answer = 0;
        for (int i = 0; i < 10; i++) answer = (answer + dp[N][i][1023]) % MOD;
        System.out.println(answer);
    }
}
