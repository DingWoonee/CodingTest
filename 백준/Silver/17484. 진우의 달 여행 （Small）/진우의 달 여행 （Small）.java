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
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][][] dp = new int[N][M][3];
        int inf = Integer.MAX_VALUE / 2;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                Arrays.fill(dp[i][j], inf);
            }
        }
        for (int c = 0; c < M; c++) {
            int pre = map[0][c];
            for (int d = 0; d < 3; d++) {
                int nextC = c + (d - 1);
                if (nextC < 0 || nextC >= M) continue;
                dp[1][nextC][d] = Math.min(dp[1][nextC][d], pre + map[1][nextC]);
            }
        }

        for (int r = 2; r < N; r++) {
            for (int c = 0; c < M; c++) {
                for (int preD = 0; preD < 3; preD++) {
                    for (int d = 0; d < 3; d++) {
                        if (d == preD) continue;
                        int nextC = c + (d - 1);
                        if (nextC < 0 || nextC >= M) continue;
                        dp[r][nextC][d] = Math.min(dp[r][nextC][d], dp[r - 1][c][preD] + map[r][nextC]);
                    }
                }
            }
        }

        int min = inf;
        for (int c = 0; c < M; c++) {
            for (int d = 0; d < 3; d++) {
                min = Math.min(min, dp[N - 1][c][d]);
            }
        }
        System.out.println(min);
    }
}
