import java.util.*;

class Solution {
    /**
    루트 -> Non 루트
    홀수 노드 -> 역홀수 노드
    짝수 노드 -> 역짝수 노드
    역홀수 노드 -> 홀수 노드
    역짝수 노드 -> 짝수 노드
    */
    public int[] solution(int[] nodes, int[][] edges) {
        int[] answer = new int[2];
        
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int node : nodes) {
            graph.put(node, new ArrayList<>());
        }
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        
        int[] visited = new int[1000001];
        for (int node : nodes) {
            if (visited[node] != 0) {
                continue;
            }
            
            Deque<Integer> queue = new ArrayDeque<>();
            queue.offer(node);
            visited[node] = 1;
            
            int countHJ = 0;
            int countReverseHJ = 0;
            while (!queue.isEmpty()) {
                int cur = queue.poll();
                if ((cur & 1) == (graph.get(cur).size() & 1)) {
                    countHJ++;
                } else {
                    countReverseHJ++;
                }
                visited[cur] = 1;
                
                for (int sub : graph.get(cur)) {
                    if (visited[sub] == 0) {
                        queue.offer(sub);
                    }
                }
            }
            if (countHJ == 1) {
                answer[0]++;
            }
            if (countReverseHJ == 1) {
                answer[1]++;
            }
        }
        
        return answer;
    }
}