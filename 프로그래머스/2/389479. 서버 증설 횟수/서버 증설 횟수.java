import java.util.*;

class Solution {
    public int solution(int[] players, int m, int k) {
        int answer = 0;
        
        int servers = 0;
        Deque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < k; i++) {
            queue.offer(0);
        }
        
        for (int player : players) {
            // 서버 비활성화
            servers -= queue.poll();
            // 0 ~ m-1 -> 0대 / m ~ 2m-1 -> 1대 / 2m ~ 3m-1 -> 2대
            int addCnt = Math.max(player / m - servers, 0);
            answer += addCnt;
            servers += addCnt;
            queue.offer(addCnt);
        }
        
        return answer;
    }
}