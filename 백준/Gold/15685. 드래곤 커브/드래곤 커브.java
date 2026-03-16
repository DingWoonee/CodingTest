import java.io.*;
import java.util.*;

public class Main {

    private static int[] dx = new int[]{1, 0, -1, 0};
    private static int[] dy = new int[]{0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        
        boolean[][] map = new boolean[101][101];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());

            List<int[]> list = new ArrayList<>();
            list.add(new int[]{x, y});
            list.add(new int[]{x + dx[d], y + dy[d]});

            for (int gen = 0; gen < g; gen++) {
                int[] pivot = list.get(list.size() - 1);
                int size = list.size();
                for (int s = size - 2; s >= 0; s--) {
                    int[] rotated = rotate(pivot, list.get(s));
                    list.add(rotated);
                }
            }

            for (int[] cord : list) {
                if (cord[0] < 0 || cord[0] > 100 || cord[1] < 0 || cord[1] > 100)
                    continue;
                map[cord[0]][cord[1]] = true;
            }
        }

        // 개수 세기
        int cnt = 0;
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if (map[i][j] && map[i + 1][j] && map[i][j + 1] && map[i + 1][j + 1])
                    cnt++;
            }
        }
        System.out.println(cnt);
    }

    private static int[] rotate(int[] pivot, int[] cord) {
        int x = cord[0] - pivot[0];
        int y = cord[1] - pivot[1];
        return new int[]{
            pivot[0] - y,
            pivot[1] + x
        };
    }
}
