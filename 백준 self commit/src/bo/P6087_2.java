package bo;

import java.util.*;
import java.io.*;

public class P6087_2 {

    private static int W, H;
    private static char[][] map;

    private static int[] dx = new int[]{-1, 1, 0, 0};
    private static int[] dy = new int[]{0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        map = new char[H][W];
        for (int i = 0; i < H; i++) map[i] = br.readLine().toCharArray();

        int[] start = null, end = null;

        // 출발, 도착 찾기
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if (map[i][j] == 'C') {
                    if (start == null) start = new int[]{i, j};
                    else end = new int[]{i, j};
                }
            }
        }

        // 길 찾기
        // 행, 열, 방향 (0:상, 1:하, 2:좌, 3:우)
        int[][][] dist = new int[H][W][4];
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                Arrays.fill(dist[i][j], 100_000);
            }
        }
        // { 행, 열, 방향, 거울 개수 }
        Deque<int[]> q = new ArrayDeque<>();
        // 처음
        for (int i = 0; i < 4; i++) {
            int nr = start[0] + dx[i];
            int nc = start[1] + dy[i];
            if (isValid(nr, nc)) {
                dist[nr][nc][i] = 0;
                q.offer(new int[]{nr, nc, i, 0});
            }
        }
        // 탐색
        while (!q.isEmpty()) {
            int[] cur = q.poll();
        
            if (cur[0] == end[0] && cur[1] == end[1]) {
                int min = dist[cur[0]][cur[1]][0];
                for (int i = 1; i < 4; i++) {
                    min = Math.min(min, dist[cur[0]][cur[1]][i]);
                }
                if (min == dist[cur[0]][cur[1]][cur[2]]) {
                    System.out.println(cur[3]);
                    return;
                }
            }

            // 전진
            int[] next = straight(cur[0], cur[1], cur[2]);
            if (isValid(next[0], next[1]) && cur[3] < dist[next[0]][next[1]][next[2]]) {
                q.addFirst(new int[]{next[0], next[1], next[2], cur[3]});
                dist[next[0]][next[1]][next[2]] = cur[3];
            }

            // 좌
            next = left(cur[0], cur[1], cur[2]);
            if (isValid(next[0], next[1]) && cur[3] + 1 < dist[next[0]][next[1]][next[2]]) {
                q.offer(new int[]{next[0], next[1], next[2], cur[3] + 1});
                dist[next[0]][next[1]][next[2]] = cur[3] + 1;
            }

            // 우
            next = right(cur[0], cur[1], cur[2]);
            if (isValid(next[0], next[1]) && cur[3] + 1 < dist[next[0]][next[1]][next[2]]) {
                q.offer(new int[]{next[0], next[1], next[2], cur[3] + 1});
                dist[next[0]][next[1]][next[2]] = cur[3] + 1;
            }
        }
    }

    private static int[] straight(int r, int c, int dir) {
        if (dir == 0) return new int[]{r - 1, c, 0};
        if (dir == 1) return new int[]{r + 1, c, 1};
        if (dir == 2) return new int[]{r, c - 1, 2};
        return new int[]{r, c + 1, 3};
    }

    private static int[] left(int r, int c, int dir) {
        if (dir == 0) return new int[]{r, c - 1, 2};
        if (dir == 1) return new int[]{r, c + 1, 3};
        if (dir == 2) return new int[]{r + 1, c, 1};
        return new int[]{r - 1, c, 0};
    }

    private static int[] right(int r, int c, int dir) {
        if (dir == 0) return new int[]{r, c + 1, 3};
        if (dir == 1) return new int[]{r, c - 1, 2};
        if (dir == 2) return new int[]{r - 1, c, 0};
        return new int[]{r + 1, c, 1};
    }

    private static boolean isValid(int r, int c) {
        return r >= 0 && r < H && c >= 0 && c < W && map[r][c] != '*';
    }
}
