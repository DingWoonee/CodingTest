package class4;

import java.io.*;
import java.util.*;

public class P11053 {
    public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] nums = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) nums[i] = Integer.parseInt(st.nextToken());
        
        int[] dp = new int[N + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        int len = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j <= len; j++) {
                if (dp[j] >= nums[i]) {
                    dp[j] = nums[i];
                    if (j == len) len++;
                    break;
                }
            }
        }

        System.out.println(len);
    }
}
