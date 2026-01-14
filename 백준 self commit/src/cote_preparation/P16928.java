package cote_preparation;

import java.io.*;
import java.util.*;
/**
 * - 칸은 100번까지
 * - 주사위 결과 합이 100을 넘으면 이동 못함
 * - 사다리 -> 더 큰 숫자로
 * - 뱀 -> 더 작은 숫자로
 */
public class P16928 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] graph = new int[101];
        for (int i = 0; i < N + M; i++) {
            st = new StringTokenizer(br.readLine());
            graph[Integer.parseInt(st.nextToken())] = Integer.parseInt(st.nextToken());
        }
        
        Deque<Integer> q = new ArrayDeque<>();
        q.offer(1);
        boolean[] visited = new boolean[101];
        visited[1] = true;
        int[] dist = new int[101];
        dist[1] = 0;
        while (!q.isEmpty()) {
            int cur = q.poll();
            int d = dist[cur];

            if (cur == 100) {
                break;
            }

            for (int i = 1; i <= 6; i++) {
                int next = cur + i;

                if (next > 100) continue;

                next = (graph[next] > 0) ? graph[next] : next;
                
                if (!visited[next]) {
                    q.offer(next);
                    visited[next] = true;
                    dist[next] = d + 1;
                }
            }
        }

        System.out.println(dist[100]);
    }
}
