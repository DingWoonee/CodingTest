import java.util.*;
import java.util.stream.*;

class Solution {
    
    private int answer = 0;
    
    public int solution(int n, int[][] q, int[] ans) {
        int[] visited = new int[n + 1];
        
        backtrack(n, q, ans, 0, visited, 1);
        
        return answer;
    }
    
    private void backtrack(int n, int[][] inputs, int[] ans, int count, int[] visited, int idx) {
        if (count == 5) {
            // 정답 체크
            for (int i = 0; i < inputs.length; i++) {
                int[] input = inputs[i];
                int sum = 0;
                for (int num : input) {
                    if (visited[num] == 1) {
                        sum++;
                    }
                }
                if (ans[i] != sum) {
                    return;
                }
            }
            // 정답
            answer++;
            return;
        }
        // 재귀호출
        for (int i = idx; i <= n; i++) {
            visited[i] = 1;
            backtrack(n, inputs, ans, count + 1, visited, i + 1);
            visited[i] = 0;
        }
    }
}