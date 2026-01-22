package bo;

import java.io.*;
import java.util.*;

public class P6087 {

    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};

    private static int W;
    private static int H;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        char[][] land = new char[H][W];
        for (int i = 0; i < H; i++) {
            land[i] = br.readLine().toCharArray();
        }

        int[] start = null, end = null;
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if (land[i][j] == 'C') {
                    if (start == null) {
                        start = new int[]{i, j};
                    } else {
                        end = new int[]{i, j};
                    }
                }
            }
        }

        // {preR, preC, curR, curC, mirrors}
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(arr -> arr[4]));
        int[][][] dist = new int[H][W][4];
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                Arrays.fill(dist[i][j], 10_001);
            }
        }
        for (int i = 0; i < 4; i++) {
            int nextR = start[0] + dx[i];
            int nextC = start[1] + dy[i];
            if (rangeCheck(nextR, nextC) && land[nextR][nextC] != '*') {
                pq.offer(new int[]{start[0], start[1], nextR, nextC, 0});
                dist[nextR][nextC][getDir(start[0], start[1], nextR, nextC)] = 0;
            }
        }
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();

            if (cur[4] > dist[cur[2]][cur[3]][getDir(cur[0], cur[1], cur[2], cur[3])])
                continue;

            if (cur[2] == end[0] && cur[3] == end[1]) {
                System.out.println(cur[4]);
                return;
            }

            // 직진
            int[] next = straight(cur[0], cur[1], cur[2], cur[3]);
            int dir = getDir(cur[2], cur[3], next[0], next[1]);
            if (rangeCheck(next[0], next[1]) 
                && land[next[0]][next[1]] != '*' 
                && dist[next[0]][next[1]][dir] > cur[4]
            ) {
                pq.offer(new int[]{cur[2], cur[3], next[0], next[1], cur[4]});
                dist[next[0]][next[1]][dir] = cur[4];
            }

            // 좌
            next = left(cur[0], cur[1], cur[2], cur[3]);
            dir = getDir(cur[2], cur[3], next[0], next[1]);
            if (rangeCheck(next[0], next[1]) 
                && land[next[0]][next[1]] != '*' 
                && dist[next[0]][next[1]][dir] > cur[4] + 1
            ) {
                pq.offer(new int[]{cur[2], cur[3], next[0], next[1], cur[4] + 1});
                dist[next[0]][next[1]][dir] = cur[4] + 1;
            }
            
            // 우
            next = right(cur[0], cur[1], cur[2], cur[3]);
            dir = getDir(cur[2], cur[3], next[0], next[1]);
            if (rangeCheck(next[0], next[1]) 
                && land[next[0]][next[1]] != '*' 
                && dist[next[0]][next[1]][dir] > cur[4] + 1
            ) {
                pq.offer(new int[]{cur[2], cur[3], next[0], next[1], cur[4] + 1});
                dist[next[0]][next[1]][dir] = cur[4] + 1;
            }
        }
    }

    private static int getDir(int preR, int preC, int curR, int curC) {
        if (preR > curR) return 0;
        else if (curR > preR) return 1;
        else if (preC > curC) return 2;
        else return 3;
    }

    private static boolean rangeCheck(int r, int c) {
        return r >= 0 && r < H && c >= 0 && c < W;
    }

    private static int[] straight(int preR, int preC, int curR, int curC) {
        if (preR == curR) { // 좌우 이동
            if (preC > curC) // 좌
                return new int[]{curR, curC - 1};
            else // 우
                return new int[]{curR, curC + 1};
        } else { // 상하 이동
            if (preR > curR) // 상
                return new int[]{curR - 1, curC};
            else // 하
                return new int[]{curR + 1, curC};
        }
    }

    private static int[] right(int preR, int preC, int curR, int curC) {
        if (preR == curR) { // 좌우 이동
            if (preC > curC) // 좌
                return new int[]{curR - 1, curC};
            else
                return new int[]{curR + 1, curC};
        } else { // 상하 이동
            if (preR > curR) // 상
                return new int[]{curR, curC + 1};
            else // 하
                return new int[]{curR, curC - 1};
        }
    }

    private static int[] left(int preR, int preC, int curR, int curC) {
        if (preR == curR) { // 좌우 이동
            if (preC > curC) // 좌
                return new int[]{curR + 1, curC};
            else // 우
                return new int[]{curR - 1, curC};
        } else { // 상하 이동
            if (preR > curR) // 상
                return new int[]{curR, curC - 1};
            else // 하
                return new int[]{curR, curC + 1};
        }
    }
}
