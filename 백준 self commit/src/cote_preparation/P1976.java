package cote_preparation;

import java.util.*;
import java.io.*;

public class P1976 {
    // public static void main(String[] args) throws IOException {
    //     BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    //     int N = Integer.parseInt(br.readLine());
    //     int M = Integer.parseInt(br.readLine());

    //     int[][] graph = new int[N + 1][N + 1];
    //     int INF = 1_000_000_000;
    //     for (int i = 1; i <= N; i++) {
    //         StringTokenizer st = new StringTokenizer(br.readLine());
    //         for (int j = 1; j <= N; j++) {
    //             int n = Integer.parseInt(st.nextToken());
    //             graph[i][j] = n == 0 ? INF : n;
    //         }
    //     }

    //     // f-w
    //     for (int i = 1; i <= N; i++) graph[i][i] = 0;
    //     for (int i = 1; i <= N; i++) {
    //         for (int j = 1; j <= N; j++) {
    //             if (i == j) continue;

    //             for (int k = 1; k <= N; k++) {
    //                 if (j == k) continue;

    //                 graph[j][k] = Math.min(graph[j][i] + graph[i][k], graph[j][k]);
    //             }
    //         }
    //     }

    //     int[] plan = new int[M];
    //     StringTokenizer st = new StringTokenizer(br.readLine());
    //     for (int i = 0; i < M; i++) plan[i] = Integer.parseInt(st.nextToken());

    //     int pre = plan[0];
    //     for (int i = 1; i < plan.length; i++) {
    //         int cur = plan[i];
    //         if (graph[pre][cur] == INF) {
    //             System.out.println("NO");
    //             return;
    //         }
    //         pre = cur;
    //     }
    //     System.out.println("YES");
    // }

    private static int[] parent;

    private static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    private static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a != b) parent[b] = a;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        parent = new int[N + 1];
        for (int i = 1; i <= N; i++) parent[i] = i;

        int[][] graph = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 1; i <= N; i++) {
            for (int j = i + 1; j <= N; j++) {
                if (graph[i][j] == 0) continue;

                union(i, j);
            }
        }

        StringTokenizer st = new StringTokenizer(br.readLine());

        int group = find(Integer.parseInt(st.nextToken()));
        for (int i = 1; i < M; i++) {
            if (group != find(Integer.parseInt(st.nextToken()))) {
                System.out.println("NO");
                return;
            }
        }
        System.out.println("YES");
    }
}
