package dp;

import java.io.*;
import java.util.*;
/*
 * dp[i][j] = 숫자 i를 j로 움직이는 경우
 */
public class P2631 {
    public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] nums = new int[N];
        for (int i = 0; i < N; i++) nums[i] = Integer.parseInt(br.readLine());

        int[] dp = new int[N];
        Arrays.fill(dp, Integer.MAX_VALUE);
        int len = 0;
        for (int n : nums) {
            for (int i = 0; i <= len; i++) {
                if (dp[i] >= n) {
                    dp[i] = n;
                    if (i == len) len++;
                    break;
                }
            }
        }
        System.out.println(N - len);
    }
}
