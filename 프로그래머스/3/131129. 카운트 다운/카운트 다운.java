import java.util.*;
/*
1. 최소한의 다트 수
2. 싱글 또는 불을 최대한 많이

dp[i] = i점에 도달할 때 싱글/불의 최대 개수
*/
class Solution {
    public int[] solution(int target) {
        int INF = Integer.MIN_VALUE;
        
        int cnt = Math.max(0, target - 500) / 60;
        target = target - cnt * 60;
        
        int[] dp = new int[target + 1];
        Arrays.fill(dp, INF);
        dp[0] = 0;
        
        while (true) {
            cnt++;
            int[] temp = new int[target + 1];
            Arrays.fill(temp, INF);
            for (int i = 0; i < dp.length; i++) {
                if (dp[i] == INF) continue;
                // 불
                if (i + 50 < dp.length) 
                    temp[i + 50] = Math.max(temp[i + 50], dp[i] + 1);
                for (int s = 1; s <= 20; s++) {
                    // 싱글
                    if (i + s < dp.length) 
                        temp[i + s] = Math.max(temp[i + s], dp[i] + 1);
                    // 더블
                    if (i + s * 2 < dp.length) 
                        temp[i + s * 2] = Math.max(temp[i + s * 2], dp[i]);
                    // 트리플
                    if (i + s * 3 < dp.length) 
                        temp[i + s * 3] = Math.max(temp[i + s * 3], dp[i]);
                }
            }
            dp = temp;
            if (dp[dp.length - 1] != INF) {
                break;
            }
        }
        
        return new int[]{cnt, dp[dp.length - 1]};
    }
}