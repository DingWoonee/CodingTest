package cote_preparation;

import java.io.*;
import java.util.*;

public class P14940 {

    private static int[] dx = new int[]{1, -1, 0, 0};
    private static int[] dy = new int[]{0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] map = new int[n][m];
        int[][] dist = new int[n][m];
        for (int i = 0; i < n; i++) Arrays.fill(dist[i], -1);

        int[] start = new int[2];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                int num = Integer.parseInt(st.nextToken());
                map[i][j] = num;
                if (num == 0) dist[i][j] = 0;
                if (num == 2) {
                    start[0] = i; start[1] = j;
                }
            }
        }

        boolean[][] visited = new boolean[n][m];
        // { n, m, dist }
        Deque<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{start[0], start[1], 0});
        visited[start[0]][start[1]] = true;
        dist[start[0]][start[1]] = 0;
        while (!q.isEmpty()) {
            int[] cur = q.poll();

            for (int i = 0; i < 4; i++) {
                int nn = cur[0] + dx[i];
                int nm = cur[1] + dy[i];
                if (nn >= 0 && nn < n && nm >= 0 && nm < m 
                && !visited[nn][nm] && map[nn][nm] != 0) {
                    q.offer(new int[]{nn, nm, cur[2] + 1});
                    visited[nn][nm] = true;
                    dist[nn][nm] = cur[2] + 1;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(dist[i][j]);
                if (j != m - 1) System.out.print(" ");
            }
            if (i != n - 1) System.out.println();
        }
    }
}
