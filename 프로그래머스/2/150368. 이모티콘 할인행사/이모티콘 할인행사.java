import java.util.*;
/**
O(n^2)이상도 가능하겠는데..?
-> 핵심은 놓치지 않는것.

4^7 = 2^14 = 대충 16000 = EZ
-> 모든 이모지의 할인률 조합에 대해 계산
*/
class Solution {
    public int[] solution(int[][] users, int[] emoticons) {
        int[] ratio = new int[emoticons.length];
        Arrays.fill(ratio, 10);
        
        int[] answer = new int[2];
        
        for (;ratio != null; ratio = next(ratio)) {
            int[] cur = exec(users, emoticons, ratio);
            if (cur[0] > answer[0] 
                || (cur[0] == answer[0] && cur[1] > answer[1])) {
                answer = cur;
            }
        }
        
        return answer;
    }
    
    private int[] next(int[] ratio) {
        for (int i = 0; i < ratio.length; i++) {
            ratio[i] += 10;
            if (ratio[i] == 50 && i == ratio.length - 1) {
                return null;
            } else if (ratio[i] == 50) {
                ratio[i] = 10;
            } else {
                break;
            }
        }
        return ratio;
    }
    
    // 가입자 수와 이모티콘 판매액을 계산하는 함수
    private int[] exec(int[][] users, int[] emoticons, int[] ratio) {
        int count = 0;
        int sales = 0;
        for (int[] user : users) {
            int sum = 0;
            for (int i = 0; i < emoticons.length; i++) {
                if (ratio[i] >= user[0]) {
                    sum += ((100 - ratio[i]) * emoticons[i] / 100);
                }
            }
            if (sum >= user[1]) {
                count++;
            } else {
                sales += sum;
            }
        }
        
        return new int[]{count, sales};
    }
}