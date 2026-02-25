import java.util.*;

class Solution {
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        // {next, cost}
        List<int[]>[] graph = new List[n + 1];
        for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();
        for (int[] p : paths) {
            graph[p[0]].add(new int[]{p[1], p[2]});
            graph[p[1]].add(new int[]{p[0], p[2]});
        }
        
        // {next, cost}
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            if (a[1] != b[1]) return Integer.compare(a[1], b[1]);
            return Integer.compare(a[0], b[0]);
        });
        int[] costs = new int[n + 1];
        Arrays.fill(costs, Integer.MAX_VALUE);
        for (int g : gates) {
            pq.offer(new int[]{g, 0});
            costs[g] = 0;
        }
        
        int[] summitCosts = new int[n + 1];
        Arrays.fill(summitCosts, -1);
        for (int s : summits) summitCosts[s] = Integer.MAX_VALUE;
        
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int node = cur[0];
            int cost = cur[1];
            
            if (cost > costs[node]) continue;
            if (summitCosts[node] > 0) {
                summitCosts[node] = Math.min(summitCosts[node], cost);
                continue;
            }
            
            for (int[] next : graph[node]) {
                int nCost = Math.max(cost, next[1]);
                if (nCost >= costs[next[0]]) continue;
                
                pq.offer(new int[]{next[0], nCost});
                costs[next[0]] = nCost;
            }
        }
        
        int[] answer = new int[2];
        answer[1] = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++) {
            if (summitCosts[i] < 0) continue;
            if (answer[1] > summitCosts[i]) {
                answer[0] = i;
                answer[1] = summitCosts[i];
            }
        }
        return answer;
    }
}