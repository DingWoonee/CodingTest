import java.util.*;

class Solution {
    // 다 봐야 할듯.
    public int solution(int[] schedules, int[][] timelogs, int startday) {
        int answer = 0;
        
        for (int i = 0; i < schedules.length; i++) {
            boolean isGood = true;
            int maxTime = plusTen(schedules[i]);
            for (int j = 0; j < 7; j++) {
                int today = (startday + j) % 7;
                if (today == 6 || today == 7 || today == 0) {
                    continue;
                }
                int time = timelogs[i][j];
                if (time > maxTime) {
                    isGood = false;
                    break;
                }
            }
            if (isGood) {
                answer++;
            }
        }
        
        return answer;
    }
    
    private int plusTen(int time) {
        int minute = time % 100;
        if (minute < 50) {
            return time + 10;
        }
        return (time / 100 + 1) * 100 + (minute + 10) % 60;
    }
    
}