package cote_preparation;

import java.io.*;
import java.util.*;

public class P20437 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            String W = br.readLine();
            int K = Integer.parseInt(br.readLine());

            solve(W, K);
        }
    }

    private static void solve(String W, int K) {
        Map<Character, List<Integer>> map = new HashMap<>();

        for (int i = 0; i < W.length(); i++) {
            char ch = W.charAt(i);

            if (!map.containsKey(ch)) {
                map.put(ch, new ArrayList<>());
            }
            
            map.get(ch).add(i);
        }

        int min = 10_001;
        int max = -1;

        for (char key : map.keySet()) {
            if (map.get(key).size() < K) continue;

            List<Integer> sorted = map.get(key);
            sorted.sort(Comparator.comparingInt(a -> a));

            for (int i = 0; i <= sorted.size() - K; i++) {
                int len = sorted.get(i + K - 1) - sorted.get(i) + 1;
                min = Math.min(min, len);
                max = Math.max(max, len);
            }
        }

        if (max == -1) {
            System.out.println(-1);
        } else {
            System.out.println(String.format("%d %d", min, max));
        }
    }
}
