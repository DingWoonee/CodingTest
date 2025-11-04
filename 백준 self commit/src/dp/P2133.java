package dp;

import java.io.*;
/*
 * 가로만으로 끝나는 경우 / 세로로 끝나는 경우
 * 1 / 2 = 3
 * 3 / 2 * 3 + 2 = 11
 * 11 / 2 * 11 + 8 = 41
 * 41 / 2 * 41 + 30 = 152
 */
public class P2133 {
    public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        if ((N & 1) == 1) {
            System.out.println(0);
            return;
        }

        int[] dp1 = new int[N + 1];
        int[] dp2 = new int[N + 1];
        dp1[2] = 1;
        dp2[2] = 2;
        for (int i = 2; i < N; i += 2) {
            int target = i + 2;
            dp1[target] = dp1[i] + dp2[i];
            dp2[target] = 2 * dp1[target] + dp2[i];
        }

        System.out.println(dp1[N] + dp2[N]);
    }
}
