/*
시작행 - left / n
끝행 - right / n
k행의 요소 - k가 k개, k + 1, k + 2, ..., n
*/
class Solution {
    public int[] solution(int n, long left, long right) {
        int len = (int) (right - left) + 1;
        int start = (int) (left / n) + 1;
        int end = (int) (right / n) + 1;
        int[] answer = new int[len];
        int idx = 0;
        for (int i = start; i <= end; i++) {
            for (long j = Math.max(left, (long) (i - 1) * n); 
                 j <= Math.min(right, (long) i * n - 1); 
                 j++
            ) {
                int num = (int) ((j + 1 - (long) (i - 1) * (long) n));
                if (num > i) {
                    answer[idx++] = num;
                } else {
                    answer[idx++] = i;
                }
            }
        }
        return answer;
    }
}
