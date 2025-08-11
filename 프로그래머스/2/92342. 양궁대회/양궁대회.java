import java.util.*;
/*
최대 11_C_5 = 462
*/
class Solution {
    
    int[] answer;
    int maxDiff = 0;
    
    int arrowN;
    int[] pInfo;
    
    public int[] solution(int n, int[] info) {
        answer = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, n};
        arrowN = n;
        pInfo = info;
        
        dfs(0, new int[11], 0);
        
        if (maxDiff == 0) {
            return new int[]{-1};
        }
        return answer;
    }
    
    private void dfs(int count, int[] scores, int step) {
        if (count == arrowN) {
            int diff = calculate(scores);
            if (diff == maxDiff) {
                for (int i = 10; i >= 0; i--) {
                    if (answer[i] > scores[i]) {
                        break;
                    }
                    if (scores[i] > answer[i]) {
                        answer = scores.clone();
                        break;
                    }
                }
            } else if (diff > maxDiff) {
                maxDiff = diff;
                answer = scores.clone();
            }
            return;
        }
        
        if (step == 11) {
            return;
        }
        
        for (int i = 0; i <= arrowN - count; i++) {
            scores[step] = i;
            dfs(count + i, scores, step + 1);
        }
        scores[step] = 0;
    }
    
    private int calculate(int[] lion) {
        int pSum = 0, lSum = 0;
        for (int i = 0; i < 11; i++) {
            if (lion[10 - i] > pInfo[10 - i]) {
                lSum += i;
            } else if (pInfo[10 - i] > 0) {
                pSum += i;
            }
        }
        
        return Math.max(0, lSum - pSum);
    }
}