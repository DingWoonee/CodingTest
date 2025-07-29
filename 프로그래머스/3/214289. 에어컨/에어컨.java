import java.util.*;
/**
최초 전략
- 다음 승객이 타고 내릴 때까지 (각 구간의 최소 소비전력)을 누적
- 방치 - 가동 - 
- 주의: 공조기를 킨 시각 다음부터 적용 & 끈 시각까지 적용

다음 전략
- DP를 이용한 풀이
*/
class Solution {
    public int solution(int temperature, int t1, int t2, int a, int b, int[] onboard) {
        // 배열에 저장하기 위해 온도 보정
        t1 += 10;
        t2 += 10;
        temperature += 10;
        // 분,온도 -> 각 시간에 해당 온도에 도달하는데 든 최소 전력
        int[][] dp = new int[onboard.length + 1][51];
        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        dp[0][temperature] = 0;
        
        for (int i = 0; i < onboard.length; i++) {
            for (int t = 0; t < 51; t++) {
                if (dp[i][t] == Integer.MAX_VALUE) {
                    continue;
                }
                // 난방
                int postTemp = t + 1;
                if (t >= temperature
                    && postTemp >= 0 && postTemp <= 50
                    && (onboard[i] == 0 
                       || (onboard[i] == 1 && postTemp >= t1 && postTemp <= t2))) {
                    dp[i + 1][postTemp] = Math.min(dp[i][t] + a, dp[i + 1][postTemp]);
                }
                // 냉방
                postTemp = t - 1;
                if (t <= temperature
                    && postTemp >= 0 && postTemp <= 50
                    && (onboard[i] == 0 
                       || (onboard[i] == 1 && postTemp >= t1 && postTemp <= t2))) {
                    dp[i + 1][postTemp] = Math.min(dp[i][t] + a, dp[i + 1][postTemp]);
                }
                // 오프
                postTemp = t + (temperature > t ? 1 : (temperature < t ? -1 : 0));
                if (postTemp >= 0 && postTemp <= 50
                   && (onboard[i] == 0 
                       || (onboard[i] == 1 && postTemp >= t1 && postTemp <= t2))) {
                    dp[i + 1][postTemp] = Math.min(dp[i][t], dp[i + 1][postTemp]);
                }
                // 유지
                postTemp = t;
                if (temperature != postTemp
                   && (onboard[i] == 0 
                       || (onboard[i] == 1 && postTemp >= t1 && postTemp <= t2))) {
                    dp[i + 1][postTemp] = Math.min(dp[i][t] + b, dp[i + 1][postTemp]);
                }
            }
        }
        
        int answer = Integer.MAX_VALUE;
        for (int t = 0; t < 51; t++) {
            answer = Math.min(answer, dp[onboard.length][t]);
        }
        
        return answer;
    }
}