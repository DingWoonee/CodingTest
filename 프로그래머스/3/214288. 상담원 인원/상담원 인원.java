import java.util.*;
/**
최초 전략
- 모든 경우에 대해 그냥 계산해도 되겠는데?
- 근데 모든 경우를 어떻게...
k = 3, n = 5
3 1 1 / 2 2 1 / 2 1 2 / 1 3 1 / 1 2 2 / 1 1 3 / 
*/
class Solution {
    
    int answer = Integer.MAX_VALUE;
    Type[] types;
    
    public int solution(int k, int n, int[][] reqs) {
        types = new Type[k];
        for (int i = 0; i < k; i++) {
            types[i] = new Type();
        }
        for (int[] req : reqs) {
            types[req[2] - 1].add(req[0], req[1]);
        }
        
        recursive(k, n, new int[k], 0);
        
        return answer;
    }
    
    private void recursive(int k, int n, int[] mentorArr, int idx) {
        if (k == 1) {
            mentorArr[idx] = n;
            int sum = 0;
            for (int i = 0; i < mentorArr.length; i++) {
                Type type = types[i];
                int mentors = mentorArr[i];
                sum += type.calculateWaitingTime(mentors);
            }
            answer = Math.min(answer, sum);
            return;
        }
        
        for (int i = 1; i < n - k + 2; i++) {
            mentorArr[idx] = i;
            recursive(k - 1, n - i, mentorArr, idx + 1);
        }
    }
    
    class Type {
        // 시작 시각, 상담 시간
        List<int[]> reqs = new ArrayList<>();
        
        void add(int start, int term) {
            reqs.add(new int[]{start, term});
        }
        
        int calculateWaitingTime(int mentors) {
            // 총 대기 시간
            int sum = 0;
            // 각 멘토의 상담이 끝나는 시각
            int[] endTimes = new int[mentors];
            for (int[] req : reqs) {
                // 가능한 멘토 탐색
                boolean found = false;
                for (int i = 0; i < endTimes.length; i++) {
                    int endTime = endTimes[i];
                    if (endTime <= req[0]) {
                        endTimes[i] = req[0] + req[1];
                        found = true;
                        break;
                    }
                }
                if (found) {
                    continue;
                }
                // 대기
                int fastIdx = 0;
                for (int i = 0; i < endTimes.length; i++) {
                    if (endTimes[i] < endTimes[fastIdx]) {
                        fastIdx = i;
                    }
                }
                sum += (endTimes[fastIdx] - req[0]);
                endTimes[fastIdx] = endTimes[fastIdx] + req[1];
            }
            
            return sum;
        }
    }
}