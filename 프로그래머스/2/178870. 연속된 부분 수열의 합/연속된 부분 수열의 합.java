class Solution {
    public int[] solution(int[] sequence, int k) {
        int[] answer = new int[] {0, sequence.length};
        
        int[] queue = new int[sequence.length];
        int head = 0, tail = 0;
        int sum = 0;
        
        for (int i = 0; i < sequence.length; i++) {
            queue[head++] = sequence[i];
            sum += sequence[i];
            
            while (sum > k) {
                sum -= queue[tail++];
            }
            
            if (sum == k && (answer[1] - answer[0] + 1 > head - tail)) {
                answer[1] = head - 1;
                answer[0] = tail;
            }
        }
        
        return answer;
    }
}