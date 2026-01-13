package cote_preparation;

import java.io.*;
import java.util.*;

public class P20055 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int N2 = N * 2;
        int[] lifes = new int[N2];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N2; i++) lifes[i] = Integer.parseInt(st.nextToken());

        int step = 0;
        int cntK = 0;
        int start = 0;
        int[] status = new int[N2];
        while (cntK < K) {
            step++;

            // 1
            start = start == 0 ? N2 - 1 : start - 1;
            int last = (start + N - 1) % N2;
            if (status[last] == 1) status[last] = 0;

            // 2
            for (int i = 1; i < N; i++) {
                int cur = (start + N - 1 - i) % N2;
                int next = (cur + 1) % N2;
                if (status[cur] == 1 && status[next] == 0 && lifes[next] > 0) {
                    status[next] = 1;
                    status[cur] = 0;
                    lifes[next]--;
                    if (lifes[next] == 0) cntK++;
                    if (next == last) status[last] = 0;
                }
            }

            // 3
            if (lifes[start] > 0) {
                status[start] = 1;
                lifes[start]--;
                if (lifes[start] == 0) cntK++;
            }
        }

        System.out.println(step);
    }
}
