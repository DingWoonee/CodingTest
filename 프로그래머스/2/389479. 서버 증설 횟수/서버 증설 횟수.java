import java.util.*;

class Solution {
    public int solution(int[] players, int m, int k) {
        int answer = 0;
        
        int servers = 0;
        int[] record = new int[24];
        
        for (int i = 0; i < players.length; i++) {
            // 서버 비활성화
            if (i >= k) {
                servers -= record[i - k];
            }
            // 0 ~ m-1 -> 0대 / m ~ 2m-1 -> 1대 / 2m ~ 3m-1 -> 2대
            int addCnt = Math.max(players[i] / m - servers, 0);
            answer += addCnt;
            servers += addCnt;
            record[i] = addCnt;
        }
        
        return answer;
    }
}