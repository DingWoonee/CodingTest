class Solution {
    
    public long solution(int r1, int r2) {
        long answer = 0;
        
        // x축 하나씩 계산
        for (int x = 1; x <= r2; x++) {
            long x_2 = (long) x * x;
            
            long y2 = (long) Math.sqrt((long) r2 * r2 - x_2);
            
            long y1 = x >= r1 ? 0 : (long) Math.ceil(Math.sqrt((long) r1 * r1 - x_2));
            
            answer += (y2 - y1 + 1);
        }
        
        return answer * 4;
    }
}