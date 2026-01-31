package cote_preparation;

import java.util.*;
import java.io.*;

public class P1027 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] hs = new int[N];
        for (int i = 0; i < N; i++) hs[i] = Integer.parseInt(st.nextToken());

        int max = 0;
        for (int i = 0; i < N; i++) {
            int cnt = 0;

            // 좌측
            float pre = Integer.MAX_VALUE;
            for (int l = i - 1; l >= 0; l--) {
                float cur = (hs[i] - hs[l]) * 1f / (i - l);
                if (cur < pre) {
                    cnt++;
                }
                pre = Math.min(pre, cur);
            }

            // 우측
            pre = Integer.MIN_VALUE;
            for (int r = i + 1; r < N; r++) {
                float cur = (hs[r] - hs[i]) * 1f / (r - i);
                if (cur > pre) {
                    cnt++;
                }
                pre = Math.max(pre, cur);
            }

            max = Math.max(max, cnt);
        }

        System.out.println(max);
    }
}
