import java.util.*;
import java.io.*;
/**
 * 63C3 = 약 37,000 -> 그냥 모든 경우 BFS
 */
public class Main {

    private static int[][] map;
    private static int N, M;
    private static List<int[]> viruses = new ArrayList<>();

    private static int max = Integer.MIN_VALUE;
    private static int startCnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) viruses.add(new int[]{i, j});
                if (map[i][j] == 0) startCnt++;
            }
        }

        dfs(0, 0, new int[3]);

        System.out.println(max);
    }

    private static void dfs(int cnt, int cur, int[] selected) {
        if (cnt == 3) {
            for (int s : selected) {
                map[s / M][s % M] = 1;
            }
            max = Math.max(max, calc());
            for (int s : selected) {
                map[s / M][s % M] = 0;
            }
            return;
        }

        for (int i = cur; i < N * M; i++) {
            if (map[i / M][i % M] != 0) continue;
            selected[cnt] = i;
            dfs(cnt + 1, i + 1, selected);
        }
    }

    private static int[] dr = new int[]{0, 0, 1, -1};
    private static int[] dc = new int[]{1, -1, 0, 0};

    private static int calc() {
        Deque<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][M];
        int curCnt = startCnt - 3;

        for (int[] v : viruses) {
            q.offer(new int[]{v[0], v[1]});
            visited[v[0]][v[1]] = true;
        }

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            for (int i = 0; i < 4; i++) {
                int nr = cur[0] + dr[i];
                int nc = cur[1] + dc[i];
                if (nr < 0 || nr >= N || nc < 0 || nc >= M || visited[nr][nc] || map[nr][nc] != 0) continue;
                curCnt--;
                q.offer(new int[]{nr, nc});
                visited[nr][nc] = true;
            }
        }

        return curCnt;
    }
}
