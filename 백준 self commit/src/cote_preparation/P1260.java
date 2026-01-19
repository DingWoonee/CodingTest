package cote_preparation;

import java.util.*;
import java.io.*;

public class P1260 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 정점 수 (1,000개)
        int M = Integer.parseInt(st.nextToken()); // 간선 수 (10,000개)
        int V = Integer.parseInt(st.nextToken()); // 시작 정점 번호

        List<Integer>[] graph = new List[N + 1];
        for (int i = 1; i <= N; i++) graph[i] = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int V1 = Integer.parseInt(st.nextToken());
            int V2 = Integer.parseInt(st.nextToken());

            graph[V1].add(V2);
            graph[V2].add(V1);
        }

        for (int i = 1; i <= N; i++) {
            graph[i].sort(Comparator.comparingInt(n -> n));
        }

        // DFS
        boolean[] visited = new boolean[N + 1];
        List<Integer> answer = new ArrayList<>();
        dfs(graph, visited, V, answer);
        print(answer);

        // BFS
        visited = new boolean[N + 1];
        Deque<Integer> q = new ArrayDeque<>();
        visited[V] = true;
        q.offer(V);
        answer = new ArrayList<>();
        while (!q.isEmpty()) {
            int cur = q.poll();
            answer.add(cur);
            for (int next : graph[cur]) {
                if (!visited[next]) {
                    q.offer(next);
                    visited[next] = true;
                }
            }
        }
        print(answer);
    }

    private static void dfs(
        List<Integer>[] graph, boolean[] visited, 
        int cur, List<Integer> answer
    ) {
        visited[cur] = true;
        answer.add(cur);

        for (int next : graph[cur]) {
            if (!visited[next]) {
                dfs(graph, visited, next, answer);
            }
        }
    }

    private static void print(List<Integer> answer) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < answer.size(); i++) {
            sb.append(answer.get(i));
            if (i < answer.size() - 1) sb.append(" ");
        }
        System.out.println(sb);
    }
}
