class Solution {
    // 첫 문제 풀이 시간 
    // + (다음 문제 풀이 시간 + (다음 문제 풀이 시간 + 이전 문제 풀이 시간) * Math.max(0, diff - level))
    // < limin인 최소 level은?
    
    // brute force
    public int solution(int[] diffs, int[] times, long limit) {
        int answer = 1;
        int high = 100000;
        
        while (answer != high) {
            // 1 10 -> 5 // 1 5 -> 3 // 4 5
            int mid = (answer + high) / 2;
            
            long sum = times[0];
            boolean isSuccess = true;
            for (int i = 1; i < diffs.length; i++) {
                sum += (times[i] + (times[i] + times[i - 1]) * Math.max(0, diffs[i] - mid));
                if (sum > limit) {
                    isSuccess = false;
                    break;
                }
            }
            if (isSuccess) {
                high = mid;
            } else {
                answer = mid + 1;
            }
        }
        
        return answer;
    }
}