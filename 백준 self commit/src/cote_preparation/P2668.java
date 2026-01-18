package cote_preparation;

import java.io.*;
import java.util.*;

public class P2668 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N + 1];
        for (int i = 1; i <= N; i++) arr[i] = Integer.parseInt(br.readLine());

        Set<Integer> set = new HashSet<>();

        for (int i = 1; i <= N; i++) {
            int next = arr[i];

            if (next == i) {
                set.add(i);
                continue;
            }

            boolean success = false;
            boolean[] visited = new boolean[N + 1];
            while (!visited[next]) {
                visited[next] = true;
                next = arr[next];
                if (next == i) {
                    success = true;
                    break;
                }
            }

            if (!success) continue;

            next = arr[i];
            set.add(i);
            while (next != i) {
                set.add(next);
                next = arr[next];
            }
        }

        System.out.println(set.size());
        List<Integer> sorted = new ArrayList<>(set);
        Collections.sort(sorted);
        for (int n : sorted) {
            System.out.println(n);
        }
    }
}
