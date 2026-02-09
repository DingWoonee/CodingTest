package cote_preparation;

import java.util.*;
import java.io.*;

public class P24337 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        if (a + b > N + 1) {
            System.out.println(-1);
            return;
        }

        int max = Math.max(a, b);

        StringBuilder sb = new StringBuilder();

        // 앞쪽 1
        if (a != 1) {
            for (int i = 0; i < N - b - a + 2; i++) {
                sb.append("1 ");
            }
        }
        // a
        for (int i = 0; i < a - 2; i++) {
            sb.append(i + 2);
            sb.append(" ");
        }
        // max
        if (max != 1) {
            sb.append(max);
        } else if (a == 1 && b == 1) {
            sb.append(1);
        }
        if (a == 1) {
            for (int i = 0; i < N - a - b + 1; i++) {
                sb.append(" ");
                sb.append(1);
            }
        }
        // b
        for (int i = 0; i < b - 1; i++) {
            sb.append(" ");
            sb.append(b - i - 1);
        }

        System.out.println(sb);
    }
}
