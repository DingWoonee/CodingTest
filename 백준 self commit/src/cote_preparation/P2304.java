package cote_preparation;

import java.util.*;
import java.io.*;

public class P2304 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[1001];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int L = Integer.parseInt(st.nextToken());
            int H = Integer.parseInt(st.nextToken());
            arr[L] = H;
        }
        
        int l = 0, r = arr.length - 1;
        int lh = 0, rh = 0;
        int sum = 0;
        while (l <= r) {
            if (lh < rh) {
                lh = Math.max(lh, arr[l++]);
                sum += lh;
            } else {
                rh = Math.max(rh, arr[r--]);
                sum += rh;
            }
        }
        
        System.out.println(sum);
    }
}
