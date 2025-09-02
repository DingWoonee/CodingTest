/*
[풀이 전략]
슬라이딩 윈도우 방식으로 스캔하면서 각 시간대에 활성화된 서버 수와 필요한 서버 수를 파악
-> 몇 번 증설해야 하는 지 누적

필요 추가 서버 수: 사람 수 / m
*/
class Solution {
    public int solution(int[] players, int m, int k) {
        int answer = 0;
        int cur = 0;
        int[] record = new int[24];
        
        for (int i = 0; i < 24; i++) {
            if (i >= k) {
                cur -= record[i - k];
            }
            int need = players[i] / m;
            if (need > cur) {
                record[i] = need - cur;
                cur += record[i];
                answer += record[i];
            }
        }
        
        return answer;
    }
}