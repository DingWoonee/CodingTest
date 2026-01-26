package cote_preparation;

import java.util.*;
import java.io.*;

public class P1253 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(st.nextToken());
            arr[i] = n;
            map.put(n, map.getOrDefault(n, 0) + 1);
        }

        int cnt = 0;
        for (int i = 0; i < N; i++) {
            int cur = arr[i];
            for (int j = 0; j < N; j++) {
                if (i == j) continue;

                int target = cur - arr[j];
                if (!map.containsKey(target)) continue;

                if (target == arr[j] && cur == arr[j]) {
                    if (map.get(cur) >= 3) {
                        cnt++;
                        break;
                    }
                    continue;
                }
                if (target == arr[j]) {
                    if (map.get(target) >= 2) {
                        cnt++;
                        break;
                    }
                    continue;
                }
                if (cur == arr[j]) {
                    if (map.get(cur) >= 2) {
                        cnt++;
                        break;
                    }
                    continue;
                }
                if (cur == target) {
                    if (map.get(cur) >= 2) {
                        cnt++;
                        break;
                    }
                    continue;
                }
                
                cnt++;
                break;
            }
        }

        System.out.println(cnt);
    }
}
