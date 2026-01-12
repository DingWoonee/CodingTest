package cote_preparation;

import java.io.*;
import java.util.*;

public class P13549 {
    // public static void main(String[] args) throws IOException {
    //     BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    //     StringTokenizer st = new StringTokenizer(br.readLine());

    //     int N = Integer.parseInt(st.nextToken());
    //     int K = Integer.parseInt(st.nextToken());

    //     // { cost, pos }
    //     PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
    //     pq.offer(new int[]{0, N});
    //     int[] costs = new int[100_001];
    //     Arrays.fill(costs, 100_001);
    //     costs[N] = 0;
    //     while (!pq.isEmpty()) {
    //         int[] cur = pq.poll();
    //         int cost = cur[0];
    //         int pos = cur[1];
    //         if (pos == K) {
    //             System.out.println(cur[0]);
    //             return;
    //         }
    //         if (costs[pos] < cost) {
    //             continue;
    //         }
    //         // -1
    //         if (pos > 0 && costs[pos - 1] > cost + 1) {
    //             pq.offer(new int[]{cost + 1, pos - 1});
    //             costs[pos - 1] = cost + 1;
    //         }
    //         // +1
    //         if (pos < 100_000 && costs[pos + 1] > cost + 1) {
    //             pq.offer(new int[]{cost + 1, pos + 1});
    //             costs[pos + 1] = cost + 1;
    //         }
    //         // *2
    //         if (pos * 2 <= 100_000 && costs[pos * 2] > cost) {
    //             pq.offer(new int[]{cost, pos * 2});
    //             costs[pos * 2] = cost;
    //         }
    //     }
    // }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int INF = 100_001;

        Deque<Integer> q = new ArrayDeque<>();
        int[] dist = new int[INF];
        Arrays.fill(dist, INF);

        q.offer(N);
        dist[N] = 0;

        while (!q.isEmpty()) {
            int cur = q.poll();

            if (cur == K) {
                System.out.println(dist[cur]);
                return;
            }

            // -1
            if (cur > 0 && dist[cur - 1] == INF) {
                q.offer(cur - 1);
                dist[cur - 1] = dist[cur] + 1;
            }
            // +1
            if (cur < 100_000 && dist[cur + 1] == INF) {
                q.offer(cur + 1);
                dist[cur + 1] = dist[cur] + 1;
            }
            // *2
            if (cur * 2 <= 100_000 && (dist[cur * 2] == INF || dist[cur * 2] > dist[cur])) {
                q.addFirst(cur * 2);
                dist[cur * 2] = dist[cur];
            }
        }
    }
}
