package cote_preparation;

import java.util.*;
import java.io.*;

public class P1238 {

    private static int INF = 1_000_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());

        int[][] costs = new int[N + 1][N + 1];
        List<Integer>[] graph = new List[N + 1];
        List<Integer>[] reverseGraph = new List[N + 1];
        for (int i = 1; i < N + 1; i++) {
            Arrays.fill(costs[i], INF);
            graph[i] = new ArrayList<>();
            reverseGraph[i] = new ArrayList<>();
            costs[i][i] = 0;
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int T = Integer.parseInt(st.nextToken());
            costs[A][B] = T;
            graph[A].add(B);
            reverseGraph[B].add(A);
        }
        //---------------------------------------

        // X -> 나머지
        // {next, cost}
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        int[] cost1 = new int[N + 1];
        Arrays.fill(cost1, INF);
        pq.offer(new int[]{X, 0});
        cost1[X] = 0;

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();

            if (cur[1] > cost1[cur[0]]) {
                continue;
            }

            for (int next : graph[cur[0]]) {
                if (cost1[next] > cur[1] + costs[cur[0]][next]) {
                    cost1[next] = cur[1] + costs[cur[0]][next];
                    pq.offer(new int[]{next, cost1[next]});
                }
            }
        }

        // 나머지 -> X
        // {next, cost}
        pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        int[] cost2 = new int[N + 1];
        Arrays.fill(cost2, INF);
        pq.offer(new int[]{X, 0});
        cost2[X] = 0;

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();

            if (cur[1] > cost2[cur[0]]) {
                continue;
            }

            for (int next : reverseGraph[cur[0]]) {
                if (cost2[next] > cur[1] + costs[next][cur[0]]) {
                    cost2[next] = cur[1] + costs[next][cur[0]];
                    pq.offer(new int[]{next, cost2[next]});
                }
            }
        }

        //---------------------------------------

        int max = 0;
        for (int i = 1; i <= N; i++) {
            if (i == X) continue;

            max = Math.max(max, cost1[i] + cost2[i]);
        }

        System.out.println(max);
    }
}
