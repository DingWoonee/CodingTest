import java.util.*;

public class Solution {
    public int[] solution(int[] nodes, int[][] edges) {
        int n = nodes.length;
        // 1) 노드 번호 → 인덱스 매핑
        int maxId = 0;
        for (int id : nodes) maxId = Math.max(maxId, id);
        int[] idx = new int[maxId + 1];
        Arrays.fill(idx, -1);
        for (int i = 0; i < n; i++) {
            idx[nodes[i]] = i;
        }

        // 2) 인접 리스트 구성
        List<Integer>[] adj = new List[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int[] e : edges) {
            int u = idx[e[0]], v = idx[e[1]];
            adj[u].add(v);
            adj[v].add(u);
        }

        // 3) 컴포넌트별로 BFS/DFS 돌면서 A, B 개수 세기
        boolean[] visited = new boolean[n];
        int oddTreeCount = 0, invOddTreeCount = 0;

        for (int i = 0; i < n; i++) {
            if (visited[i]) continue;
            // 새 컴포넌트 탐색
            Deque<Integer> queue = new ArrayDeque<>();
            queue.offer(i);
            visited[i] = true;

            int countA = 0, compSize = 0;
            while (!queue.isEmpty()) {
                int u = queue.poll();
                compSize++;

                // p(u) = u%2, d(u) = deg(u)%2
                int p = nodes[u] & 1;
                int d = adj[u].size() & 1;
                if (p == d) countA++;

                for (int v : adj[u]) {
                    if (!visited[v]) {
                        visited[v] = true;
                        queue.offer(v);
                    }
                }
            }

            // 홀짝 트리 가능?
            if (countA == 1) {
                oddTreeCount++;
            }
            // 역홀짝 트리 가능?
            if (compSize - countA == 1) {
                invOddTreeCount++;
            }
        }

        return new int[]{oddTreeCount, invOddTreeCount};
    }
}
