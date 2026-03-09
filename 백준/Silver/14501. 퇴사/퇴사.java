import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());

        int[][] arr = new int[N][2];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[N + 1]; // dp[i] = i일에 일이 끝나는 경우 중 최대 이익
        
        for (int i = 0; i < N; i++) {
            int[] cur = arr[i];

            int end = i + cur[0];
            if (end > N) continue;

            for (int j = 0; j <= i; j++) {
                dp[end] = Math.max(dp[end], dp[j] + cur[1]);
            }
        }

        int max = 0;
        for (int n : dp) {
            max = Math.max(max, n);
        }
        System.out.println(max);
    }
}
