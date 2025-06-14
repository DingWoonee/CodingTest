class Solution {
    public int solution(int n, int m, int[] section) {
        int answer = 0;
        int lastSection = 0;
        for (int i = 0; i < section.length; i++) {
            if (lastSection >= section[i])
                continue;
            
            answer++;
            lastSection = section[i] + m - 1;
        }
        
        return answer;
    }
}