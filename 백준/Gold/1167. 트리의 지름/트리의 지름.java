import java.util.*;
import java.io.*;

public class Main {

    private static int V;
    private static List<int[]>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        V = Integer.parseInt(br.readLine()); // 정점 개수 (10만개)
        graph = new List[V + 1]; // {next, cost}
        for (int i = 1; i <= V; i++) graph[i] = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int node = Integer.parseInt(st.nextToken());
            while (true) {
                int next = Integer.parseInt(st.nextToken());
                if (next == -1) break;
                int cost = Integer.parseInt(st.nextToken());
                graph[node].add(new int[]{next, cost});
            }
        }

        findMaxCost(1, -1);

        System.out.println(max);
    }

    private static int max = 0;

    // node를 루트로 하는 서브 트리의 max cost 찾기
    private static int findMaxCost(int node, int parent) {
        List<Integer> costs = new ArrayList<>();
        for (int[] child : graph[node]) {
            if (child[0] == parent) continue;
            costs.add(findMaxCost(child[0], node) + child[1]);
        }
        int size = costs.size();
        if (size == 0) return 0;
        Collections.sort(costs);
        if (size == 1) {
            max = Math.max(max, costs.get(0));
        } else if (size > 1) {
            max = Math.max(max, costs.get(size - 2) + costs.get(size - 1));
        }
        return costs.get(size - 1);
    }
}
