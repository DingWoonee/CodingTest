/**
구석으로 쳐서 가장 짧은 경우? -> 없음
-> 상하좌우만 보고 최솟값을 정답으로 저장
*/
class Solution {
    public int[] solution(int m, int n, int startX, int startY, int[][] balls) {
        int[] answer = new int[balls.length];
        
        int[][] cords = {
            {startX, 2 * n - startY},
            {startX, -startY},
            {2 * m - startX, startY},
            {-startX, startY}
        };
        
        for (int i = 0; i < balls.length; i++) {
            int min = Integer.MAX_VALUE;
            
            int[] ball = balls[i];
            
            // 상
            if (startX != ball[0] || startY > ball[1]) {
                int xDiff = ball[0] - startX;
                int yDiff = ball[1] - (2 * n - startY);
                min = Math.min(min, xDiff * xDiff + yDiff * yDiff);
            }
            // 하
            if (startX != ball[0] || startY < ball[1]) {
                int xDiff = ball[0] - startX;
                int yDiff = ball[1] + startY;
                min = Math.min(min, xDiff * xDiff + yDiff * yDiff);
            }
            // 좌
            if (startY != ball[1] || startX < ball[0]) {
                int xDiff = ball[0] + startX;
                int yDiff = ball[1] - startY;
                min = Math.min(min, xDiff * xDiff + yDiff * yDiff);
            }
            // 우
            if (startY != ball[1] || startX > ball[0]) {
                int xDiff = ball[0] - (2 * m - startX);
                int yDiff = ball[1] - startY;
                min = Math.min(min, xDiff * xDiff + yDiff * yDiff);
            }
            
            answer[i] = min;
        }
        
        return answer;
    }
}