package cote_preparation;

import java.util.*;
import java.io.*;

public class P1806 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());

        int INF = 1_000_000;
        int answer = INF;

        int l = 0, r = 1, sum = arr[0];

        while (r < N) {
            while (sum < S && r < N) {
                sum += arr[r++];
            }

            while (sum - arr[l] >= S) {
                sum -= arr[l++];
            }

            if (sum >= S) {
                answer = Math.min(answer, r - l);
                sum -= arr[l++];
            }
        }

        System.out.println(answer == INF ? 0 : answer);
    }
}
