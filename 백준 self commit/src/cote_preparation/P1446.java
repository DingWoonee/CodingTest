import java.util.*;
import java.io.*;
/*
지름길 개수(N) 12개, 고속도로 길이(D) 10,000
-> 무조건 DP
dp[i] : i에 도달할 수 있는 최소 비용
*/
public class P1446 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());

        Map<Integer, List<int[]>> map = new HashMap<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            List<int[]> list = map.getOrDefault(start, new ArrayList<>());
            list.add(new int[]{ end, cost });
            map.put(start, list);
        }

        int[] dp = new int[D + 1];
        Arrays.fill(dp, 10_001);
        dp[0] = 0;
        for (int i = 0; i < D; i++) {
            dp[i + 1] = Math.min(dp[i + 1], dp[i] + 1);

            if (map.containsKey(i)) {
                for (int[] sh : map.get(i)) {
                    if (sh[0] <= D) {
                        dp[sh[0]] = Math.min(dp[sh[0]], dp[i] + sh[1]);
                    }
                }
            }
        }

        System.out.println(dp[D]);
    }
}