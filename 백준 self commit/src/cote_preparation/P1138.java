package cote_preparation;

import java.io.*;
import java.util.*;

public class P1138 {
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        Arrays.fill(arr, N);
        for (int i = 0; i < N - 1; i++) {
            int height = i + 1;
            int left = Integer.parseInt(st.nextToken());

            int cnt = 0;
            int j = 0;
            while (cnt != left || arr[j] < height) {
                if (arr[j] >= height) {
                    cnt++;
                }
                j++;
                // 4 2 1 4
            }

            arr[j] = height;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(arr[i]);
            if (i < N - 1) {
                sb.append(" ");
            }
        }
        System.out.println(sb);
    }
}
