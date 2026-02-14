import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) map[i][j] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[M];
        dp[0] = map[0][0];
        for (int i = 1; i < M; i++) {
            dp[i] = map[0][i] + dp[i - 1];
        }
        for (int r = 1; r < N; r++) {
            // 우 -> 좌
            int[] tmp1 = new int[M];
            tmp1[0] = map[r][0] + dp[0];
            for (int c = 1; c < M; c++) {
                tmp1[c] = Math.max(tmp1[c - 1], dp[c]) + map[r][c];
            }
            // 좌 -> 우
            int[] tmp2 = new int[M];
            tmp2[M - 1] = map[r][M - 1] + dp[M - 1];
            for (int c = M - 2; c >= 0; c--) {
                tmp2[c] = Math.max(tmp2[c + 1], dp[c]) + map[r][c];
            }
            // 갱신
            for (int i = 0; i < M; i++) {
                dp[i] = Math.max(tmp1[i], tmp2[i]);
            }
        }

        System.out.println(dp[M - 1]);
    }    
}
