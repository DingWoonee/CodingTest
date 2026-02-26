import java.util.*;
import java.io.*;

public class Main {

    private static Set<Integer> visiting = new HashSet<>();
    private static Set<Integer> apples = new HashSet<>();
    private static Deque<Integer> q = new ArrayDeque<>();
    private static int dir = 1; // 상-우-하-좌
    private static int[] dr = new int[]{-1, 0, 1, 0};
    private static int[] dc = new int[]{0, 1, 0, -1};
    private static int N;
    private static int time = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        N = Integer.parseInt(br.readLine()); // 보드 크기 100
        int K = Integer.parseInt(br.readLine()); // 사과 개수 100
        
        for (int i = 0; i < K; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            apples.add((A << 7) | B);
        }

        int L = Integer.parseInt(br.readLine());
        
        q.offerFirst((1 << 7) | 1);
        visiting.add((1 << 7) | 1);
        for (int i = 0; i < L; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int dTime = Integer.parseInt(st.nextToken());

            while (time < dTime) {
                time++;
                int status = go();
                if (status == -1) {
                    return;
                }
            }

            String dDir = st.nextToken();
            if (dDir.equals("D")) {
                dir = (dir + 1) % 4;
            } else {
                dir = (dir + 3) % 4;
            }
        }

        while (true) {
            time++;
            int status = go();
            if (status == -1) {
                return;
            }
        }
    }

    private static int go() {
        int head = q.peekFirst();
        int row = head >> 7;
        int col = head & 127;
        int nRow = row + dr[dir];
        int nCol = col + dc[dir];
        int next = (nRow << 7) | nCol;
        if (nRow < 1 || nRow > N || nCol < 1 || nCol > N || visiting.contains(next)) {
            System.out.println(time);
            return -1;
        }
        
        q.offerFirst(next);
        visiting.add(next);

        if (apples.contains(next)) {
            apples.remove(next);
        } else {
            int tail = q.pollLast();
            visiting.remove(tail);
        }

        return 0;
    }
}
