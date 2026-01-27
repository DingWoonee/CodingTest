package cote_preparation;

import java.util.*;
import java.io.*;

public class P1987 {

    private static char[][] arr;

    private static int R, C, max;

    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        arr = new char[R][C];
        for (int i = 0; i < R; i++) arr[i] = br.readLine().toCharArray();

        max = 1;
        boolean[] visited = new boolean[26];
        visited[arr[0][0] - 'A'] = true;
        dfs(1, 0, 0, visited);

        System.out.println(max);
    }

    private static void dfs(int depth, int r, int c, boolean[] visited) {
        max = Math.max(max, depth);

        for (int i = 0; i < 4; i++) {
            int nr = r + dx[i];
            int nc = c + dy[i];

            if (nr < 0 || nr >= R || nc < 0 || nc >= C || visited[arr[nr][nc] - 'A']) continue;

            int cur = arr[nr][nc] - 'A';

            visited[cur] = true;
            dfs(depth + 1, nr, nc, visited);
            visited[cur] = false;
        }
    }
}
