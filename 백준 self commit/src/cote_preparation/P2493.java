package cote_preparation;

import java.io.*;
import java.util.*;
/**
 * 레이저는 수평으로 왼쪽으로 발사
 * 각 탑은 어느 위치에서든 레이저 수신 가능
 * 가장 먼저는 탑이 수신
 * 
 * 탑 개수: 50만 개
 * 탑 높이: 1억 이하
 */
public class P2493 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] tops = new int[N];
        for (int i = 0; i < N; i++) tops[i] = Integer.parseInt(st.nextToken());

        int[] answer = new int[N];
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = N - 1; i >= 0; i--) {
            int cur = tops[i];
            while (!stack.isEmpty() && tops[stack.peek()] <= cur) {
                answer[stack.pop()] = i + 1;
            }
            stack.push(i);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < answer.length; i++) {
            sb.append(answer[i]);
            if (i < answer.length - 1) {
                sb.append(" ");
            }
        }

        System.out.println(sb.toString());
    }
}
