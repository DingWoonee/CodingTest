package cote_preparation;

import java.util.*;
import java.io.*;

public class P2206 {

    private static int[] dr = {-1, 1, 0, 0};
    private static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        char[][] map = new char[N][M];
        for (int i = 0; i < N; i++) map[i] = br.readLine().toCharArray();

        // {r, c, d, wallBreak}
        Deque<int[]> q = new ArrayDeque<>();
        boolean[][][] visited = new boolean[N][M][2];
        q.offer(new int[]{0, 0, 1, 0});
        visited[0][0][0] = true;
        while (!q.isEmpty()) {
            int[] cur = q.poll();

            if (cur[0] == N - 1 && cur[1] == M - 1) {
                System.out.println(cur[2]);
                return;
            }

            for (int i = 0; i < 4; i++) {
                int nr = cur[0] + dr[i];
                int nc = cur[1] + dc[i];

                if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue;

                if (!visited[nr][nc][1] && map[nr][nc] == '1' && cur[3] == 0) {
                    q.offer(new int[]{nr, nc, cur[2] + 1, 1});
                    visited[nr][nc][1] = true;
                } else if (!visited[nr][nc][cur[3]] && map[nr][nc] == '0') {
                    q.offer(new int[]{nr, nc, cur[2] + 1, cur[3]});
                    visited[nr][nc][cur[3]] = true;
                }
            }
        }

        System.out.println(-1);
    }
}
