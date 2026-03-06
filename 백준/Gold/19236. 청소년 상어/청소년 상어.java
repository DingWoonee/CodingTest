import java.util.*;
import java.io.*;

public class Main {

    private static int[] dr = new int[]{-1, -1, -1, 0, 1, 1, 1, 0, -1};
    private static int[] dc = new int[]{1, 0, -1, -1, -1, 0, 1, 1, 1};

    private static int max = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // map: {num, dir}
        int[][][] map = new int[4][4][2];
        int[][] pos = new int[17][2];
        boolean[] ate = new boolean[17];
        int sum = 0;
        int[] shark = new int[]{0, 0, 0};

        for (int r = 0; r < 4; r++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int c= 0; c < 4; c++) {
                int n = Integer.parseInt(st.nextToken());
                int dir = Integer.parseInt(st.nextToken());
                map[r][c][0] = n;
                map[r][c][1] = dir;
                pos[n][0] = r;
                pos[n][1] = c;
            }
        }

        sum = eat(map[0][0][0], map, pos, ate, sum, shark);
        
        dfs(map, pos, ate, sum, shark);

        System.out.println(max);
    }

    // return sum
    private static int eat(int n, int[][][] map, int[][] pos, boolean[] ate, int sum, int[] shark) {
        int[] p = pos[n];
        map[p[0]][p[1]][0] = 0;
        ate[n] = true;
        shark[0] = p[0];
        shark[1] = p[1];
        shark[2] = map[p[0]][p[1]][1];
        return sum + n;
    }

    // map: {num, dir} / shark: r, c, dir
    private static void dfs(int[][][] map, int[][] pos, boolean[] ate, int sum, int[] shark) {
        max = Math.max(max, sum);
        // 물고기 움직이기
        for (int n = 1; n <= 16; n++) {
            if (ate[n]) continue;

            int[] p = pos[n];
            int r = p[0], c = p[1]; 
            int dir = map[r][c][1];
            int nr = 0, nc = 0;
            boolean flag = false;
            for (int i = 0; i < 8; i++) {
                nr = r + dr[dir];
                nc = c + dc[dir];
                if (nr >= 0 && nr < 4 && nc >= 0 && nc < 4 && (shark[0] != nr || shark[1] != nc)) {
                    flag = true;
                    break;
                }
                dir++;
                dir %= 8;
            }
            if (!flag) continue;
            // 위치 바꾸기
            if (map[nr][nc][0] == 0) { // 물고기 없음
                pos[n][0] = nr;
                pos[n][1] = nc;
                map[r][c][0] = 0;
                map[nr][nc][0] = n;
                map[nr][nc][1] = dir;
            } else { // 물고기 있음
                pos[map[nr][nc][0]][0] = r;
                pos[map[nr][nc][0]][1] = c;
                pos[n][0] = nr;
                pos[n][1] = nc;
                map[r][c][0] = map[nr][nc][0];
                map[r][c][1] = map[nr][nc][1];
                map[nr][nc][0] = n;
                map[nr][nc][1] = dir;
            }
        }
        // 상어 움직이기
        for (int i = 1; i < 4; i++) {
            int nr = shark[0] + dr[shark[2]] * i;
            int nc = shark[1] + dc[shark[2]] * i;
            if (nr < 0 || nr >= 4 || nc < 0 || nc >= 4 || map[nr][nc][0] == 0) continue;

            int[][][] copiedMap = new int[4][4][2];
            int[][] copiedPos = new int[17][2];
            boolean[] copiedAte = new boolean[17];
            int[] copiedShark = new int[]{0, 0, 0};
            for (int a = 0; a < 4; a++) {
                for (int b = 0; b < 4; b++) {
                    copiedMap[a][b] = map[a][b].clone();
                }
            }
            for (int a = 0; a < 17; a++) {
                copiedPos[a] = pos[a].clone();
            }
            copiedAte = ate.clone();
            copiedShark = shark.clone();
            int addedSum = eat(map[nr][nc][0], copiedMap, copiedPos, copiedAte, sum, copiedShark);
            dfs(copiedMap, copiedPos, copiedAte, addedSum, copiedShark);
        }
    }
}
