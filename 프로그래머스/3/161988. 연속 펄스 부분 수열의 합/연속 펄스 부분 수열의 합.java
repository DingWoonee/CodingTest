/*
[풀이 전략]
1. 1/-1로 시작하는 두 펄스 수열을 곱한 두 개의 수열을 만듦 -> O(2n)
2. 각 수열에서 앞에서 부터 더했을 때 가장 큰 합을 dp로 저장.
    - 앞에게 음수면 나랑 더했을 때랑, 나 혼자일 때 비교
    - 앞에게 양수면 나랑 무조건 더하기
*/
class Solution {
    public long solution(int[] sequence) {
        // 수열 2개 준비
        int len = sequence.length;
        int[] seq1 = new int[len];
        int[] seq2 = new int[len];
        int k = 1;
        for (int i = 0; i < len; i++) {
            seq1[i] = sequence[i] * k;
            seq2[i] = sequence[i] * -k;
            k = -k;
        }
        // seq1 dp
        long[] dp1 = new long[len];
        dp1[0] = seq1[0];
        for (int i = 1; i < len; i++) {
            long cur = dp1[i - 1] + seq1[i];
            if (dp1[i - 1] >= 0) {
                dp1[i] = cur;
            } else {
                dp1[i] = Math.max(cur, seq1[i]);
            }
        }
        // seq2 dp
        long[] dp2 = new long[len];
        dp2[0] = seq2[0];
        for (int i = 1; i < len; i++) {
            long cur = dp2[i - 1] + seq2[i];
            if (dp2[i - 1] >= 0) {
                dp2[i] = cur;
            } else {
                dp2[i] = Math.max(cur, seq2[i]);
            }
        }
        // 최댓값 찾기
        long answer = Long.MIN_VALUE;
        for (long n : dp1) {
            answer = Math.max(answer, n);
        }
        for (long n : dp2) {
            answer = Math.max(answer, n);
        }
        return answer;
    }
}