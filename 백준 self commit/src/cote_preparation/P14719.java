package cote_preparation;

import java.io.*;
import java.util.*;

public class P14719 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] land = new int[W];
        for (int i = 0; i < W; i++) land[i] = Integer.parseInt(st.nextToken());

        int answer = 0;
        int l = 0, r = W - 1;
        int lMax = land[l], rMax = land[r];

        while (l < r) {
            if (lMax >= rMax) {
                answer += rMax - land[r--];
                rMax = Math.max(rMax, land[r]);
            } else {
                answer += lMax - land[l++];
                lMax = Math.max(lMax, land[l]);
            }
        }

        System.out.println(answer);
    }
}
