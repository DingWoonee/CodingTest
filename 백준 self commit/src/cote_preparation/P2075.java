package cote_preparation;

import java.io.*;
import java.util.*;
/**
 * 1500 * 1500 = 2,250,000
 */
public class P2075 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        
        int[] arr = new int[N * N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i * N + j] = Integer.parseInt(st.nextToken());
            }
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (pq.size() < N) {
                    pq.offer(arr[i * N + j]);
                    continue;
                }

                if (arr[i * N + j] > pq.peek()) {
                    pq.poll();
                    pq.offer(arr[i * N + j]);
                }
            }
        }

        System.out.println(pq.peek());
    }
}
