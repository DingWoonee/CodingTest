package cote_preparation;

import java.io.*;
import java.util.*;

public class P5972 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        List<int[]>[] graph = new List[N + 1];
        for (int i = 0; i < graph.length; i++) graph[i] = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            graph[A].add(new int[]{B, C});
            graph[B].add(new int[]{A, C});
        }

        // 탐색
        // { 번호, 비용 }
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.offer(new int[]{1, 0});
        int[] costs = new int[N + 1];
        Arrays.fill(costs, 50_000_001);
        costs[1] = 0;
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();

            if (cur[0] == N) {
                System.out.println(costs[N]);
                break;
            }
            if (cur[1] > costs[cur[0]]) {
                continue;
            }

            for (int[] next : graph[cur[0]]) {
                int nc = cur[1] + next[1];
                if (costs[next[0]] > nc) {
                    pq.offer(new int[]{next[0], nc});
                    costs[next[0]] = nc;
                }
            }
        }
    }
}
