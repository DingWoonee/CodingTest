package cote_preparation;

import java.util.*;
import java.io.*;
/*
2차원 좌표 0~10만
수빈이는 1초에 1칸 움직이거나 2배 좌표로 움직일 수 있다.

dp[i][j] = i초에 j칸에 도달하는 최소 비용
*/
public class P1697 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int answer = Math.abs(N - K);

        if (N >= K) {
            System.out.println(answer);
            return;
        }

        Deque<int[]> q = new ArrayDeque<>();
        boolean[] visited = new boolean[100_001];
        q.offer(new int[]{N, 0});
        visited[N] = true;
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            // 체크
            if (cur[0] == K) {
                answer = cur[1];
                break;
            }
            // 다음 단계
            if (cur[0] * 2 <= 100_000 && !visited[cur[0] * 2]) {
                q.offer(new int[]{cur[0] * 2, cur[1] + 1});
                visited[cur[0] * 2] = true;
            }
            if (cur[0] + 1 <= 100_000 && !visited[cur[0] + 1]) {
                q.offer(new int[]{cur[0] + 1, cur[1] + 1});
                visited[cur[0] + 1] = true;
            }
            if (cur[0] > 0 && !visited[cur[0] - 1]) {
                q.offer(new int[]{cur[0] - 1, cur[1] + 1});
                visited[cur[0] - 1] = true;
            }
        }

        System.out.println(answer);
    }
}