package cote_preparation;

import java.util.*;
import java.io.*;

public class P4485 {

    private static int[] dx = new int[]{-1, 1, 0, 0};
    private static int[] dy = new int[]{0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int cnt = 1;

        for (int N = readN(br); N != 0; N = readN(br)) {
            int[][] map = new int[N][N];
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            System.out.println("Problem " +  cnt++ + ": " + solve(N, map));
        }
    }

    private static int readN(BufferedReader br) throws IOException {
        return Integer.parseInt(br.readLine());
    }

    private static int solve(int N, int[][] map) {
        // {row, col, dist}
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[2]));
        int[][] dist = new int[N][N];
        for (int i = 0; i < N; i++) Arrays.fill(dist[i], 1_000_000_000);

        pq.offer(new int[]{0, 0, map[0][0]});
        dist[0][0] = map[0][0];

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();

            if (cur[2] > dist[cur[0]][cur[1]]) {
                continue;
            }

            if (cur[0] == N - 1 && cur[1] == N - 1) {
                return cur[2];
            }

            for (int i = 0; i < 4; i++) {
                int nr = cur[0] + dx[i];
                int nc = cur[1] + dy[i];

                if (nr >= 0 && nr < N && nc >= 0 && nc < N) {
                    int nCost = cur[2] + map[nr][nc];
                    if (dist[nr][nc] > nCost) {
                        pq.offer(new int[]{nr, nc, nCost});
                        dist[nr][nc] = nCost;
                    }
                }
            }
        }

        return -1;
    }
}
