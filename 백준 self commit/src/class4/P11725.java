package class4;

import java.io.*;
import java.util.*;

public class P11725 {
    public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        List<Integer>[] adj = new List[N + 1];
        for (int i = 1; i <= N; i++) adj[i] = new ArrayList<>();
        
        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            adj[n1].add(n2);
            adj[n2].add(n1);
        }
        
        int[] parent = new int[N + 1];
        boolean[] visited = new boolean[N + 1];
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(1);
        visited[1] = true;
        while (!stack.isEmpty()) {
            int cur = stack.pop();
            for (int next : adj[cur]) {
                if (visited[next]) continue;

                stack.push(next);
                visited[next] = true;
                parent[next] = cur;
            }
        }

        for (int i = 2; i <= N; i++) {
            System.out.println(parent[i]);
        }
    }
}
