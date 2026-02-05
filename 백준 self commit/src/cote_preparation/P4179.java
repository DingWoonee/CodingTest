package cote_preparation;

import java.util.*;
import java.io.*;

public class P4179 {

    private static int[] dr = {-1, 1, 0, 0};
    private static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        char[][] map = new char[R][C];
        for (int i = 0; i < R; i++) map[i] = br.readLine().toCharArray();

        int[] J = new int[2];
        List<int[]> Fs = new ArrayList<>();
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] == 'J') {
                    J[0] = i; J[1] = j;
                } else if (map[i][j] == 'F') {
                    Fs.add(new int[]{i, j});
                }
            }
        }

        int answer = -1;

        // {r, c, d}
        Deque<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[R][C];
        q.offer(new int[]{J[0], J[1], 0});
        visited[J[0]][J[1]] = true;

        boolean[][] isFire = new boolean[R][C];
        List<int[]> added = new ArrayList<>();
        for (int[] f : Fs) {
            isFire[f[0]][f[1]] = true;
            added.add(f);
        }
        int curFireD = 0;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            if (cur[0] == 0 || cur[0] == R - 1
                || cur[1] == 0 || cur[1] == C - 1) {
                answer = cur[2] + 1;
                break;
            }

            // 불 번지기
            if (curFireD - 1 < cur[2]) {
                List<int[]> temp = new ArrayList<>();
                for (int[] rc : added) {
                    for (int i = 0; i < 4; i++) {
                        int nr = rc[0] + dr[i];
                        int nc = rc[1] + dc[i];
                        if (nr >= 0 && nr < R && nc >= 0 && nc < C
                            && map[nr][nc] != '#' && !isFire[nr][nc]
                        ) {
                            isFire[nr][nc] = true;
                            temp.add(new int[]{nr, nc});
                        }
                    }
                }
                added = temp;
                curFireD++;
            }

            for (int i = 0; i < 4; i++) {
                int nr = cur[0] + dr[i];
                int nc = cur[1] + dc[i];
                if (nr >=0 && nr < R && nc >= 0 && nc < C && map[nr][nc] != '#'
                    && !visited[nr][nc] && !isFire[nr][nc]
                ) {
                    q.offer(new int[]{nr, nc, cur[2] + 1});
                    visited[nr][nc] = true;
                }
            }
        }

        System.out.println(answer == -1 ? "IMPOSSIBLE" : answer);
    }
}
