import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 총 노드 수 10만
        int M = Integer.parseInt(st.nextToken()); // 횡단보도 수 70만

        // {next, i}
        List<long[]>[] graph = new List[N + 1];
        for (int i = 0; i < graph.length; i++) graph[i] = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            graph[A].add(new long[]{B, i});
            graph[B].add(new long[]{A, i});
        }

        // {next, cost, i}
        PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a[1]));
        long[] costs = new long[N + 1];
        Arrays.fill(costs, Long.MAX_VALUE);
        pq.offer(new long[]{1, 0, -1});
        costs[1] = 0;
        while (!pq.isEmpty()) {
            long[] cur = pq.poll();

            int node = (int) cur[0];
            long cost = cur[1];
            long i = cur[2];

            if (cost > costs[node]) {
                continue;
            }
            
            if (cur[0] == N) {
                System.out.println(cost);
                return;
            }

            for (long[] n : graph[node]) {
                int next = (int) n[0];
                long n_i = n[1];
                long nCost = cost 
                    + (n_i >= i + 1 ? n_i - i - 1 : M + n_i - i - 1) + 1;

                if (nCost >= costs[next]) continue;

                pq.offer(new long[]{next, nCost, n_i});
                costs[next] = nCost;
            }
        }
    }
}
