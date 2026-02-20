import java.util.*;

class Solution {
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        List<Integer>[] graph = new List[n + 1];
        for (int i = 1; i < graph.length; i++) graph[i] = new ArrayList<>();
        for (int[] r : roads) {
            graph[r[0]].add(r[1]);
            graph[r[1]].add(r[0]);
        }
        
        // {next, cost}
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        int[] costs = new int[n + 1];
        Arrays.fill(costs, Integer.MAX_VALUE);
        pq.offer(new int[]{destination, 0});
        costs[destination] = 0;
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int node = cur[0];
            int cost = cur[1];
            
            if (cost > costs[node]) continue;
            
            for (int next : graph[node]) {
                int nCost = cost + 1;
                if (nCost > costs[next]) continue;
                
                pq.offer(new int[]{next, nCost});
                costs[next] = nCost;
            }
        }
        
        int[] ans = new int[sources.length];
        for (int i = 0; i < sources.length; i++) {
            ans[i] = costs[sources[i]] == Integer.MAX_VALUE ? -1 : costs[sources[i]];
        }
        
        return ans;
    }
}