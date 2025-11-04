package prefix_sum;

import java.io.*;
import java.util.*;
/*
 * 1 2 3 1 2
 * 1 3 6 7 9
 * 1 0 0 1 0
 * 
 * 0: 1
 * 1: 1
 */
public class P10986 {
    public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] nums = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) nums[i] = Integer.parseInt(st.nextToken());

        Map<Long, Long> map = new HashMap<>();
        map.put(0L, 1L);
        long sum = 0;
        long cnt = 0;
        for (int n : nums) {
            sum += n;
            long rem = sum % M;

            cnt += map.getOrDefault(rem, 0L);

            map.put(rem, map.getOrDefault(rem, 0L) + 1);
        }
        System.out.println(cnt);
    }
}
