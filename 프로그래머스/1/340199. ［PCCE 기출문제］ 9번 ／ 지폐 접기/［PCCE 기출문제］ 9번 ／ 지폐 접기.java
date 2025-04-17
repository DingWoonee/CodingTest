class Solution {
    public int solution(int[] wallet, int[] bill) {
        int answer = 0;
        
        while (!isPossible(wallet, bill)) {
            fold(bill);
            answer++;
        }
        
        return answer;
    }
    
    private void fold(int[] bill) {
        if (bill[0] > bill[1]) {
            bill[0] = bill[0] / 2;
        } else {
            bill[1] = bill[1] / 2;
        }
    }
    
    private boolean isPossible(int[] wallet, int[] bill) {
        return (wallet[0] >= bill[0] && wallet[1] >= bill[1])
            || (wallet[1] >= bill[0] && wallet[0] >= bill[1]);
    }
}