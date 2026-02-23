import java.util.*;
import java.io.*;
/**
 * 1. BFS로 각 노드에서 각 노드까지의 거리 구하기
 * 2. 각 노드를 루트로하는 트리의 노드 개수 구하기 (루트에서의 거리 별 노드 개수)
 * 3. 1번에서 2번 빼기
 */
public class Main {

    private static int N;
    private static List<Integer>[] graph;
    private static long[] nodes;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 입력 처리
        N = Integer.parseInt(br.readLine()); // 노드 수 30만
        graph = new List[N + 1];
        for (int i = 1; i <= N; i++) graph[i] = new ArrayList<>();
        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            graph[b].add(a);
        }

        // 문제 풀이
        int[] dists = new int[N + 1];
        // {next, dist}
        Deque<int[]> q = new ArrayDeque<>();
        boolean[] visited = new boolean[N + 1];
        q.offer(new int[]{1, 0});
        visited[1] = true;
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int node = cur[0];
            int dist = cur[1];

            dists[node] = dist;

            for (int next : graph[node]) {
                if (visited[next]) continue;

                q.offer(new int[]{next, dist + 1});
                visited[next] = true;
            }
        }

        long result = 0;
        for (int i = 1; i <= N; i++) {
            result += (N - 1) * dists[i];
        }

        // 각 노드를 루트로 하는 트리의 노드 수 세기
        nodes = new long[N + 1];
        dfs(1, 1);
        for (int i = 2; i <= N; i++) {
            result -= (nodes[i] * (nodes[i] - 1)) / 2;
        }

        System.out.println(result);
    }
    
    // 자신을 루트로 하는 서브트리의 노드 개수 반환
    private static int dfs(int cur, int parent) {
        int sum = 1;
        for (int next : graph[cur]) {
            if (next == parent) continue;
            sum += dfs(next, cur);
        }
        nodes[cur] = sum;
        return sum;
    }
}
