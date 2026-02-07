package cote_preparation;

import java.util.*;
import java.io.*;
/**
 * 3 7 1 6 3 5 1 7
 * 0 0 1 1 2 2 3 0
 * 1 0 2 1 2 1 1 0
 * 1 0 3 2 4 3 4 0
 * 가장 가까운 자기보다 큰 것을 찾아야 함
 */

public class P22866 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] hs = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) hs[i] = Integer.parseInt(st.nextToken());

        // 우 -> 왼
        int[][] ans1 = new int[N][3]; // {cnt, i, dist}
        Deque<int[]> stack = new ArrayDeque<>(); // {h, i}
        for (int i = N - 1; i >= 0; i--) {
            int curH = hs[i];
            while (!stack.isEmpty() && stack.peek()[0] <= curH) {
                stack.pop();
            }
            if (stack.isEmpty()) {
                stack.push(new int[]{curH, i});
                continue;
            }
            int[] t = stack.peek();
            ans1[i][0] = ans1[t[1]][0] + 1;
            ans1[i][1] = t[1];
            ans1[i][2] = t[1] - i;
            stack.push(new int[]{curH, i});
        }

        // 왼 -> 우
        int[][] ans2 = new int[N][3]; // {cnt, i, dist}
        stack = new ArrayDeque<>(); // {h, i}
        for (int i = 0; i < N; i++) {
            int curH = hs[i];
            while (!stack.isEmpty() && stack.peek()[0] <= curH) {
                stack.pop();
            }
            if (stack.isEmpty()) {
                stack.push(new int[]{curH, i});
                continue;
            }
            int[] t = stack.peek();
            ans2[i][0] = ans2[t[1]][0] + 1;
            ans2[i][1] = t[1];
            ans2[i][2] = i - t[1];
            stack.push(new int[]{curH, i});
        }

        for (int i = 0; i < N; i++) {
            int cnt = ans1[i][0] + ans2[i][0];
            if (cnt == 0) {
                System.out.println(0);
                continue;
            }

            int dist;
            if (ans1[i][0] == 0) dist = ans2[i][1];
            else if (ans2[i][0] == 0) dist = ans1[i][1];
            else if (ans1[i][2] < ans2[i][2]) dist = ans1[i][1];
            else dist = ans2[i][1];

            System.out.println(cnt + " " + (dist + 1));
        }
    }
}
