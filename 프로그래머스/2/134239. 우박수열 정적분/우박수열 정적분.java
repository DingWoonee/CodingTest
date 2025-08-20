class Solution {
    public double[] solution(int k, int[][] ranges) {
        int n = 0;
        int tempK = k;
        // n 구하기
        while (tempK != 1) {
            if ((tempK & 1) == 0) {
                tempK /= 2;
            } else {
                tempK = (tempK * 3 + 1);
            }
            n++;
        }
        // 넓이 구하기
        double[] sizes = new double[n];
        int pre = k;
        for (int i = 0; i < n; i++) {
            if ((k & 1) == 0) {
                k /= 2;
            } else {
                k = (k * 3 + 1);
            }
            sizes[i] = (pre + k) * 1.0 / 2;
            pre = k;
        }
        // 응답 생성하기
        double[] answer = new double[ranges.length];
        for (int i = 0; i < ranges.length; i++) {
            int[] cur = ranges[i];
            int last = n + cur[1];
            if (cur[0] == last) {
                answer[i] = 0;
            } else if (cur[0] > last) {
                answer[i] = -1;
            } else {
                for (int j = cur[0]; j < last; j++) {
                    answer[i] += sizes[j];
                }
            }
        }
        return answer;
    }
}