import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] edges = new int[M + 1][2];
        List<Integer>[] graph = new List[N + 1];
        for (int i = 1; i <= N; i++) graph[i] = new ArrayList<>();
        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            edges[i][0] = A;
            edges[i][1] = B;
            graph[A].add(i);
            graph[B].add(i);
        }

        if (N == 1 || N == 2 || M < N - 2) {
            System.out.println(-1);
            return;
        }

        Deque<Integer> q = new ArrayDeque<>();
        boolean[] visited = new boolean[N + 1];

        List<Integer> selectedNodes = new ArrayList<>();
        List<Integer> selectedEdges = new ArrayList<>();

        q.offer(1);
        visited[1] = true;

        while (!q.isEmpty()) {
            int cur = q.pollFirst();
            
            selectedNodes.add(cur);
            
            for (int e : graph[cur]) {
                int[] edge = edges[e];
                int next = edge[0] == cur ? edge[1] : edge[0];

                if (visited[next]) continue;

                selectedEdges.add(e);
                visited[next] = true;
                q.offer(next);
            }
        }

        if (selectedNodes.size() == N) {
            print(
                selectedNodes.subList(0, selectedNodes.size() - 1),
                selectedEdges.subList(0, selectedEdges.size() - 1),
                List.of(selectedNodes.get(selectedNodes.size() - 1)),
                List.of()
            );
            return;
        }

        int start = 2;
        for (int i = 1; i <= N; i++) {
            if (visited[i]) continue;
            start = i;
            break;
        }

        // DFS
        Deque<Integer> stack = new ArrayDeque<>();

        List<Integer> selectedNodes2 = new ArrayList<>();
        List<Integer> selectedEdges2 = new ArrayList<>();

        stack.push(start);
        visited[start] = true;

        while (!stack.isEmpty()) {
            int cur = stack.pop();
            
            selectedNodes2.add(cur);
            
            for (int e : graph[cur]) {
                int[] edge = edges[e];
                int next = edge[0] == cur ? edge[1] : edge[0];

                if (visited[next]) continue;

                selectedEdges2.add(e);
                visited[next] = true;
                stack.push(next);
            }
        }

        if (selectedNodes.size() + selectedNodes2.size() != N || selectedNodes.size() == selectedNodes2.size()) {
            System.out.println(-1);
            return;
        }

        print(selectedNodes, selectedEdges, selectedNodes2, selectedEdges2);
    }

    private static void print(List<Integer> n1, List<Integer> e1, List<Integer> n2, List<Integer> e2) {
        StringBuilder sb = new StringBuilder();
        sb.append(n1.size()); sb.append(" "); sb.append(n2.size()); sb.append("\n");
        makeLine(sb, n1);
        makeLine(sb, e1);
        makeLine(sb, n2);
        makeLine(sb, e2);

        System.out.println(sb.toString());
    }

    private static void makeLine(StringBuilder sb, List<Integer> list) {
        for (int i = 0; i < list.size(); i++) {
            if (i != 0) sb.append(" ");
            sb.append(list.get(i));
        }
        sb.append("\n");
    }
}
