import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // N 20
        int M = Integer.parseInt(st.nextToken()); // M 20
        int r = Integer.parseInt(st.nextToken()); // r
        int c = Integer.parseInt(st.nextToken()); // c
        int K = Integer.parseInt(st.nextToken()); // K 1000

        int[] dr = new int[]{0, 0, 0, -1, 1};
        int[] dc = new int[]{0, 1, -1, 0, 0};
        
        int[][] map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[] dice = new int[]{0, 0, 0, 0, 0, 0, 0};
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            int op = Integer.parseInt(st.nextToken());

            int nr = r + dr[op];
            int nc = c + dc[op];

            if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue;

            r = nr;
            c = nc;

            int temp = dice[1];
            if (op == 1) { // east
                dice[1] = dice[4];
                dice[4] = dice[6];
                dice[6] = dice[3];
                dice[3] = temp;
            } else if (op == 2) { // west
                dice[1] = dice[3];
                dice[3] = dice[6];
                dice[6] = dice[4];
                dice[4] = temp;
            } else if (op == 3) { // north
                dice[1] = dice[5];
                dice[5] = dice[6];
                dice[6] = dice[2];
                dice[2] = temp;
            } else { // south
                dice[1] = dice[2];
                dice[2] = dice[6];
                dice[6] = dice[5];
                dice[5] = temp;
            }

            if (map[r][c] == 0) {
                map[r][c] = dice[6];
            } else {
                dice[6] = map[r][c];
                map[r][c] = 0;
            }
            System.out.println(dice[1]);
        }
    }
}
