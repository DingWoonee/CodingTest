package cote_preparation;

import java.util.*;
import java.io.*;

public class P13144 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] arr = new int[N];
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());

        int[] cnts = new int[100_001];

        long cnt = 0;
        
        int l = 0;
        for (int i = 0; i < N; i++) {
            cnts[arr[i]]++;

            while (cnts[arr[i]] > 1) {
                cnts[arr[l]]--;
                l++;
            }

            cnt += i - l + 1;
        }

        System.out.println(cnt);
    }
}
