package cote_preparation;

import java.util.*;
import java.io.*;

public class P16234 {

    private static int[] dx = new int[]{1, -1, 0, 0};
    private static int[] dy = new int[]{0, 0, 1, -1};
    private static int L;
    private static int R;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        
        int[][] land = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                land[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = 0;
        while (true) {
            boolean[][] visited = new boolean[N][N];

            boolean diff = false;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (visited[i][j]) continue;

                    List<int[]> groups = new ArrayList<>();

                    Deque<int[]> q = new ArrayDeque<>();
                    q.offer(new int[]{i, j});
                    visited[i][j] = true;
                    while (!q.isEmpty()) {
                        int[] cur = q.poll();
                        groups.add(cur);

                        for (int d = 0; d < 4; d++) {
                            int[] next = new int[]{cur[0] + dx[d], cur[1] + dy[d]};

                            if (next[0] < 0 || next[0] >= N 
                                || next[1] < 0 || next[1] >= N 
                                || visited[next[0]][next[1]]) continue;

                            if (isInner(Math.abs(land[cur[0]][cur[1]] - land[next[0]][next[1]]))) {
                                q.offer(next);
                                visited[next[0]][next[1]] = true;
                            }
                        }
                    }

                    if (groups.size() == 1) continue;

                    int sum = 0;
                    for (int[] arr : groups) {
                        sum += land[arr[0]][arr[1]];
                    }
                    int mean = sum / groups.size();
                    for (int[] arr : groups) {
                        if (land[arr[0]][arr[1]] != mean) {
                            diff = true;
                        }
                        land[arr[0]][arr[1]] = mean;
                    }
                }
            }
            
            if (diff) answer++;
            else break;
        }

        System.out.println(answer);
    }

    private static boolean isInner(int n) {
        return n >=L && n <= R;
    }
}
