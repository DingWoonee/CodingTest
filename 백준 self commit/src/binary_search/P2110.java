package binary_search;

import java.io.*;
import java.util.*;
/*
 * 집 N개가 수직 선 위.
 * 공유기 C개 설치.
 * 인접한 두 공유기 사이의 거리가 가능한 크게 설치.
 * 집 좌표: 0~
 */
public class P2110 {
    public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] split = br.readLine().split(" ");
		int N = Integer.parseInt(split[0]);
        int C = Integer.parseInt(split[1]);
        int[] houses = new int[N];
        for (int i = 0; i < N; i++) {
            houses[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(houses);
        int[] diffs = new int[houses.length - 1];
        for (int i = 0; i < diffs.length; i++) 
            diffs[i] = houses[i + 1] - houses[i];

        int left = 1;
        int right = houses[houses.length - 1] - houses[0];

        while (left <= right) {
            int mid = (left + right) / 2;
            int count = 1;
            int sum = 0;
            for (int i = 0; i < diffs.length; i++) {
                sum += diffs[i];
                if (sum >= mid) {
                    sum = 0;
                    count++;
                }
            }
            if (count >= C) left = mid + 1;
            else right = mid - 1;
        }

        System.out.println(left - 1);
    }
}
