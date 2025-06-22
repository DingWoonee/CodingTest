import java.util.*;

class Solution {
    
    public int solution(int[][] targets) {
        // 정렬
        Arrays.sort(targets, Comparator
                    .comparingInt((int[] e) -> e[1]));
        
        int answer = 0;
        int last = -1;
        for (int[] target : targets) {
            if (target[1] > last && last >= target[0]) {
                continue;
            }
            last = target[1] - 1;
            answer++;
        }
        
        return answer;
    }
}