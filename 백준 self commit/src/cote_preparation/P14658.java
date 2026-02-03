package cote_preparation;

import java.util.*;
import java.io.*;

public class P14658 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 가로
        int M = Integer.parseInt(st.nextToken()); // 세로
        int L = Integer.parseInt(st.nextToken()); // 트램펄린 길이
        int K = Integer.parseInt(st.nextToken()); // 별똥별 수

        int[] xs = new int[K];
        int[] ys = new int[K];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            xs[i] = x; ys[i] = y;
        }

        // 탐색
        int max = 0;
        for (int i = 0; i < K; i++) {
            for (int j = 0; j < K; j++) {
                int top = ys[i]; // 1~5
                int left = xs[j]; // 2~6

                int cnt = 0;

                for (int k = 0; k < K; k++) {
                    if (ys[k] >= top && ys[k] <= top + L 
                        && xs[k] >= left && xs[k] <= left + L
                    ) {
                        cnt++;
                    }
                }

                max = Math.max(max, cnt);
            }
        }

        System.out.println(K - max);
    }
}
