class Solution {
    // 첫 문제 풀이 시간 
    // + (다음 문제 풀이 시간 + (다음 문제 풀이 시간 + 이전 문제 풀이 시간) * Math.max(0, diff - level))
    // < limin인 최소 level은?
    
    // 모든 
    public int solution(int[] diffs, int[] times, long limit) {
        int answer = 1;
        
        while (true) {
            long sum = times[0];
            boolean isSuccess = true;
            for (int i = 1; i < diffs.length; i++) {
                sum += (times[i] + (times[i] + times[i - 1]) * Math.max(0, diffs[i] - answer));
                if (sum > limit) {
                    isSuccess = false;
                    break;
                }
            }
            if (isSuccess) {
                break;
            }
            answer++;
        }
        
        return answer;
    }
}