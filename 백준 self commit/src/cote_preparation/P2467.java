package cote_preparation;

import java.io.*;
import java.util.*;

public class P2467 {
    // public static void main(String[] args) throws IOException {
    //     BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    //     int N = Integer.parseInt(br.readLine());

    //     StringTokenizer st = new StringTokenizer(br.readLine());

    //     int[] arr = new int[N];
    //     for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());

    //     int min = 2_000_000_001;
    //     int[] answer = new int[2];

    //     for (int i = 0; i < N - 1; i++) {
    //         int l = i + 1, r = N - 1;
    //         while (l <= r) {
    //             int mid = (l + r) / 2;
    //             int sum = arr[i] + arr[mid];
    //             if (min > Math.abs(sum)) {
    //                 min = Math.abs(sum);
    //                 answer[0] = Math.min(arr[i], arr[mid]);
    //                 answer[1] = Math.max(arr[i], arr[mid]);
    //             }
    //             if (sum == 0) {
    //                 break;
    //             }
    //             if (sum > 0) {
    //                 r = mid - 1;
    //             } else {
    //                 l = mid + 1;
    //             }
    //         }
    //     }

    //     System.out.println(answer[0] + " " + answer[1]);
    // }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] arr = new int[N];
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());

        int min = 2_000_000_001;
        int[] answer = new int[2];

        int l = 0, r = N - 1;
        while (l < r) {
            int sum = arr[l] + arr[r];
            if (min > Math.abs(sum)) {
                min = Math.abs(sum);
                answer[0] = Math.min(arr[l], arr[r]);
                answer[1] = Math.max(arr[l], arr[r]);
            }

            if (sum == 0) {
                break;
            }

            if (sum > 0) {
                r--;
            } else {
                l++;
            }
        }
        
        System.out.println(answer[0] + " " + answer[1]);
    }
}
