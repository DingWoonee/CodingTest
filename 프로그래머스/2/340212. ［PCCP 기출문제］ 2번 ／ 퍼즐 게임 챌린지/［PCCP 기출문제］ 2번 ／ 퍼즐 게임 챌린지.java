/*
- level >= diff
    time_cur 사용
- level < diff
    (diff - level) * (time_cur + time_prev) + time_cur 사용

[풀이 전략]
브루트포스 => 최대 30만번 * 10만번 => 300억..
이진탐색 => 최대 30만번 * log 100,000 => 약 200만번
*/
class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        int head = 1, tail = 100000;
        
        while (head < tail) {
            long sum = calc(diffs, times, (head + tail) / 2);
            if (sum > limit) {
                head = (head + tail) / 2 + 1;
            } else {
                tail = (head + tail) / 2;
            }
        }
        
        return head;
    }
    
    private long calc(int[] diffs, int[] times, int level) {
        long sum = 0;
        for (int i = 0; i < diffs.length; i++) {
            int diff = diffs[i] - level;
            if (diff <= 0) {
                sum += times[i];
            } else {
                sum += (diff * (times[i] + times[i - 1]) + times[i]);
            }
        }
        return sum;
    }
}