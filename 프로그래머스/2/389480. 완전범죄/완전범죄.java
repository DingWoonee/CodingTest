import java.util.*;
/*
[최초 풀이 전략]
백트랙킹

[풀이 전략]
dp로 각 칸에 B도둑의 해당 누적 흔적일 때의 A도둑의 최소 누적 흔적값을 저장
*/
class Solution {
    
    public int solution(int[][] info, int n, int m) {
        int[] dp = new int[m];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        
        for (int[] arr : info) {
            int A = arr[0], B = arr[1];
            int[] newDp = new int[m];
            Arrays.fill(newDp, Integer.MAX_VALUE);
            for (int b = 0; b < m; b++) {
                if (dp[b] == Integer.MAX_VALUE) {
                    continue;
                }
                // A가 챙길 경우
                if (dp[b] + A < n) {
                    newDp[b] = Math.min(dp[b] + A, newDp[b]);
                }
                // B가 챙길 경우
                if (b + B < m) {
                    newDp[b + B] = Math.min(newDp[b + B], dp[b]);
                }
            }
            dp = newDp;
        }
        int answer = Integer.MAX_VALUE;
        for (int d : dp) {
            answer = Math.min(answer, d);
        }
        return answer == Integer.MAX_VALUE ? -1 : answer;
    }
}