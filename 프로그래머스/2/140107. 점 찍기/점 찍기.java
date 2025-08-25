/*
[풀이 전략]
제1사분면에서 원 안에 있는 점의 개수
반지름 = d
-> 각 x좌표에서 개수 구하기
-> x좌표가 a일 때, 개수 = (d^2 - a^2)^(1/2) / k + 1
*/
class Solution {
    public long solution(int k, int d) {
        long answer = 0;
        
        for (int i = 0; i <= d; i += k) {
            answer += ((long) (Math.sqrt((long) d * d - (long) i * i))) / k + 1;
        }
        
        return answer;
    }
}