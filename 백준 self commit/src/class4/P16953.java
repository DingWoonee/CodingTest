package class4;

import java.io.*;
import java.util.*;

public class P16953 {
    public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        // {num, depth}
        Deque<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{A, 1});
        while (!q.isEmpty()) {
            int[] cur = q.poll();

            long next1 = cur[0] * 2L;
            long next2 = cur[0] * 10L + 1;
            if (next1 == B || next2 == B) {
                System.out.println(cur[1] + 1);
                return;
            }
            if (next1 < B) {
                q.offer(new int[]{(int) next1, cur[1] + 1});
            }
            if (next2 < B) {
                q.offer(new int[]{(int) next2, cur[1] + 1});
            }
        }
        System.out.println(-1);
    }
}
