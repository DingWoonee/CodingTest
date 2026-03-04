import java.util.*;
import java.io.*;
/**
 * 아기상어 크기 2 / 더 작은거만 먹기 가능 / 크기가 큰 물고기 칸은 지나가기 불가능
 * 
 * 1. 먹을 수 있는 먹이까지의 거리 구하기
 * 2. 먹기
 */
public class Main {

    private static int[] dr = new int[]{1, -1, 0, 0};
    private static int[] dc = new int[]{0, 0, 1, -1};
    private static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        int[][] map = new int[N][N];
        int[] pos = new int[2];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 9) {
                    pos[0] = i; pos[1] = j;
                    map[i][j] = 0;
                }
            }
        }
        
        int size = 2, ate = 0;
        int time = 0;
        while (true) {
            // BFS -> 먹을 칸 결정
            Deque<int[]> q = new ArrayDeque<>(); // {r, c, dist}
            boolean[][] visited = new boolean[N][N];
            q.offer(new int[]{pos[0], pos[1], 0});
            visited[pos[0]][pos[1]] = true;
            int[] target = new int[]{21, 21};
            int minDist = INF;
            while (!q.isEmpty()) {
                int[] cur = q.poll();
                int r = cur[0], c = cur[1], dist = cur[2];

                if (dist > minDist) break;
                if (map[r][c] > 0 && map[r][c] < size 
                    && (r < target[0] || (r == target[0] && c < target[1]))
                ) {
                    target[0] = r;
                    target[1] = c;
                    minDist = dist;
                }

                for (int i = 0; i < 4; i++) {
                    int nr = r + dr[i];
                    int nc = c + dc[i];
                    if (nr < 0 || nr >= N || nc < 0 || nc >= N || visited[nr][nc] || map[nr][nc] > size) continue;

                    q.offer(new int[]{nr, nc, dist + 1});
                    visited[nr][nc] = true;
                }
            }
            // 먹을 수 있는 것이 없음
            if (minDist == INF) break;
            // 먹기
            ate++;
            if (ate == size) {
                ate = 0;
                size++;
            }
            map[target[0]][target[1]] = 0;
            pos[0] = target[0];
            pos[1] = target[1];
            time += minDist;
        }

        System.out.println(time);
    }
}
