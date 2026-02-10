package cote_preparation;

import java.util.*;
import java.io.*;
/**
 * 260210 골드 3
 * 풀이시간: 33분
 * 풀이방향: 구현, dfs
 */
public class P15683 {

    private static int N, M;
    private static int[][] map;
    private static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int sixs = 0;
        map = new int[N][M];
        List<int[]> cctvs = new ArrayList<>(); // {r, c, cctv}
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int num = Integer.parseInt(st.nextToken());
                map[i][j] = num;
                if (num > 0 && num < 6) {
                    cctvs.add(new int[]{i, j, num});
                } else if (num == 6) {
                    sixs++;
                }
            }
        }

        dfs(new int[N][M], cctvs, 0);
        System.out.println(N * M - sixs - max);
    }

    private static void dfs(int[][] arr, List<int[]> cctvs, int cur) {
        if (cur == cctvs.size()) {
            int cnt = 0;
            for (int i =0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (arr[i][j] == -1) cnt++;
                }
            }
            max = Math.max(max, cnt);
            return;
        }

        int[] next = cctvs.get(cur);
        int nr = next[0], nc = next[1], nct = next[2];
        int[][] clone = new int[N][];
        if (nct == 1) {
            for (int d = 0; d < 4; d++) {
                for (int i = 0; i < N; i++) clone[i] = arr[i].clone();
                applyCctv1(clone, nr, nc, d);
                dfs(clone, cctvs, cur + 1);
            }
        } else if (nct == 2) {
            for (int d = 0; d < 2; d++) {
                for (int i = 0; i < N; i++) clone[i] = arr[i].clone();
                applyCctv2(clone, nr, nc, d);
                dfs(clone, cctvs, cur + 1);
            }
        } else if (nct == 3) {
            for (int d = 0; d < 4; d++) {
                for (int i = 0; i < N; i++) clone[i] = arr[i].clone();
                applyCctv3(clone, nr, nc, d);
                dfs(clone, cctvs, cur + 1);
            }
        } else if (nct == 4) {
            for (int d = 0; d < 4; d++) {
                for (int i = 0; i < N; i++) clone[i] = arr[i].clone();
                applyCctv4(clone, nr, nc, d);
                dfs(clone, cctvs, cur + 1);
            }
        } else if (nct == 5) {
            for (int i = 0; i < N; i++) clone[i] = arr[i].clone();
            applyCctv5(clone, nr, nc);
            dfs(clone, cctvs, cur + 1);
        }
    }

    private static int[][] applyCctv1(int[][] arr, int r, int c, int d) { // 0, 1, 2, 3
        if (d == 0) {
            for (int i = r; i >= 0; i--) {
                if (map[i][c] == 6) break;
                arr[i][c] = -1;
            }
        } else if (d == 1) {
            for (int i = r; i < N; i++) {
                if (map[i][c] == 6) break;
                arr[i][c] = -1;
            }
        } else if (d == 2) {
            for (int i = c; i >= 0; i--) {
                if (map[r][i] == 6) break;
                arr[r][i] = -1;
            }
        } else if (d == 3) {
            for (int i = c; i < M; i++) {
                if (map[r][i] == 6) break;
                arr[r][i] = -1;
            }
        }
        return arr;
    }

    private static int[][] applyCctv2(int[][] arr, int r, int c, int d) { // 0, 1
        if (d == 0) {
            applyCctv1(arr, r, c, 0);
            applyCctv1(arr, r, c, 1);
        } else if (d == 1) {
            applyCctv1(arr, r, c, 2);
            applyCctv1(arr, r, c, 3);
        }
        return arr;
    }

    private static int[][] applyCctv3(int[][] arr, int r, int c, int d) { // 0, 1, 2, 3
        if (d == 0) {
            applyCctv1(arr, r, c, 0);
            applyCctv1(arr, r, c, 3);
        } else if (d == 1) {
            applyCctv1(arr, r, c, 1);
            applyCctv1(arr, r, c, 2);
        } else if (d == 2) {
            applyCctv1(arr, r, c, 2);
            applyCctv1(arr, r, c, 0);
        } else if (d == 3) {
            applyCctv1(arr, r, c, 3);
            applyCctv1(arr, r, c, 1);
        }
        return arr;
    }

    private static int[][] applyCctv4(int[][] arr, int r, int c, int d) { // 0, 1, 2, 3
        if (d == 0) {
            applyCctv1(arr, r, c, 0);
            applyCctv1(arr, r, c, 2);
            applyCctv1(arr, r, c, 3);
        } else if (d == 1) {
            applyCctv1(arr, r, c, 1);
            applyCctv1(arr, r, c, 2);
            applyCctv1(arr, r, c, 3);
        } else if (d == 2) {
            applyCctv1(arr, r, c, 2);
            applyCctv1(arr, r, c, 0);
            applyCctv1(arr, r, c, 1);
        } else if (d == 3) {
            applyCctv1(arr, r, c, 3);
            applyCctv1(arr, r, c, 0);
            applyCctv1(arr, r, c, 1);
        }
        return arr;
    }

    private static int[][] applyCctv5(int[][] arr, int r, int c) {
        applyCctv1(arr, r, c, 0);
        applyCctv1(arr, r, c, 1);
        applyCctv1(arr, r, c, 2);
        applyCctv1(arr, r, c, 3);
        return arr;
    }
}
