/**
최초 전략
- 먼 곳부터 배달하면서 먼 곳부터 수거해와야 한다.
- 배달을 완료한 인덱스와 수거를 완료한 인덱스를 각각 관리.
- 항상 상품을 풀로 싣고 배달을 먼저하고 빈 공간에서 풀로 수거한다고 생각.
-> 그래서 총 몇 번을 왕복해야 하는지 ㅇㅇ
-> O(n)에 가능
*/
class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        
        // 배달이나 수거를 해야하는 인덱스
        int delIdx = deliveries.length - 1;
        int picIdx = pickups.length - 1;
        // 14 10 6
        while (delIdx >= 0 || picIdx >= 0) {
            // 0인 집 건너뛰기
            while (delIdx >= 0 && deliveries[delIdx] == 0) {
                delIdx--;
            }
            while (picIdx >= 0 && pickups[picIdx] == 0) {
                picIdx--;
            }
            // 왕복 거리 계산
            answer += (long) (Math.max(delIdx, picIdx) + 1) * 2;
            // 어디 집까지 배달했는지?
            int restDel = cap;
            while (delIdx >= 0) {
                int cur = deliveries[delIdx];
                if (cur > restDel) {
                    deliveries[delIdx] -= restDel;
                    break;
                } else if (cur == restDel) {
                    delIdx--;
                    break;
                } else {
                    delIdx--;
                    restDel -= cur;
                }
            }
            // 어디 집까지 수거했는지?
            int restPic = cap;
            while (picIdx >= 0) {
                int cur = pickups[picIdx];
                if (cur > restPic) {
                    pickups[picIdx] -= restPic;
                    break;
                } else if (cur == restPic) {
                    picIdx--;
                    break;
                } else {
                    picIdx--;
                    restPic -= cur;
                }
            }
        }
        
        return answer;
    }
}