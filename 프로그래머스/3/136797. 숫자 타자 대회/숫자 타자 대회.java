import java.util.*;
/*
dp[i][j] = 왼손 i, 오른손 j일 때 최소 가중치
*/
class Solution {
    int[][] costs = new int[][]{
      // 0, 1, 2, 3, 4, 5, 6, 7, 8, 9
        {1, 7, 6, 7, 5, 4, 5, 3, 2, 3},
        {7, 1, 2, 4, 2, 3, 5, 4, 5, 6},
        {6, 2, 1, 2, 3, 2, 3, 5, 4, 5},
        {7, 4, 2, 1, 5, 3, 2, 6, 5, 4},
        {5, 2, 3, 5, 1, 2, 4, 2, 3, 5},
        {4, 3, 2, 3, 2, 1, 2, 3, 2, 3},
        {5, 5, 3, 2, 4, 2, 1, 5, 3, 2},
        {3, 4, 5, 6, 2, 3, 5, 1, 2, 4},
        {2, 5, 4, 5, 3, 2, 3, 2, 1, 2},
        {3, 6, 5, 4, 5, 3, 2, 4, 2, 1}
    };
    public int solution(String numbers) {
        int INF = Integer.MAX_VALUE;
        int len = numbers.length();
        
        int[][] dp = new int[10][10];
        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], INF);
        }
        
        dp[4][6] = 0;
        for (char ch : numbers.toCharArray()) {
            int target = ch - '0';
            int[][] temp = new int[10][10];
            for (int[] t : temp) Arrays.fill(t, INF);
            
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    if (dp[i][j] == INF || i == j) continue;
                    
                    if (i == target || j == target) {
                        temp[i][j]
                            = Math.min(temp[i][j], dp[i][j] + 1);
                        continue;
                    }
                    
                    // 왼
                    temp[target][j] 
                        = Math.min(dp[i][j] + costs[i][target], temp[target][j]);
                    // 우
                    temp[i][target] 
                        = Math.min(dp[i][j] + costs[j][target], temp[i][target]);
                }
            }
            
            dp = temp;
        }
        
        
        int min = INF;
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                min = Math.min(min, dp[i][j]);
            }
        }
        return min;
    }
}
