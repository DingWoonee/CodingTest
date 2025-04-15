import java.util.*;

class Solution {
    public int solution(int[] schedules, int[][] timelogs, int startday) {
        Set<Integer> failed = new HashSet<>();
        int day = startday;
        int j = -1;
        do {
            j++;
            // 주말 건너뛰기
            if (day == 6 || day == 7) {
                day = day == 7 ? 1 : 7;
                continue;
            }
            
            // 검사
            for (int i = 0; i < schedules.length; i++) {
                if (!isSafe(schedules[i], timelogs[i][j])) {
                    failed.add(i);
                }
            }
            
            // 요일 증가
            day = (day == 6 ? 7 : (day + 1) % 7);
        } while (day != startday);
        
        return schedules.length - failed.size();
    }
    
    // 시간 안에 왔는지 체크
    private boolean isSafe(int hopeTime, int realTime) {
        int diff = ((realTime / 100) * 60 + realTime % 100) 
            - ((hopeTime / 100) * 60 + hopeTime % 100);
        return diff <= 10;
    }
}