class Solution {
    
    private int INF = Integer.MIN_VALUE;
    private int maxDiff = INF;
    private int[] ans = new int[11];
    private int[] info;
    
    public int[] solution(int n, int[] info) {
        this.info = info;
        
        comb(n, n, 10, new int[11]);
        
        return maxDiff == INF ? new int[]{-1} : ans;
    }
    
    private void comb(int n, int rest, int cur, int[] lion) {
        if (rest == 0) {
            int diff = calcDiff(lion);
            if (diff <= 0 || maxDiff > diff) return;
            if (maxDiff == diff && !check(lion)) {
                return;
            }
            maxDiff = diff;
            for (int i = 0; i < lion.length; i++) ans[i] = lion[i];
            return;
        }
        
        for (int i = cur; i >= 0; i--) {
            lion[10 - i]++;
            comb(n, rest - 1, i, lion);
            lion[10 - i]--;
        }
    }
    
    private boolean check(int[] lion) {
        for (int i = 10; i >= 0; i--) {
            if (ans[i] > lion[i]) return false;
            if (lion[i] > ans[i]) return true;
        }
        return false;
    }
    
    private int calcDiff(int[] lion) {
        int apeachS = 0;
        int lionS = 0;
        
        for (int i = 0; i < 11; i++) {
            int score = 10 - i;
            if (lion[i] > info[i]) lionS += score;
            else if (info[i] > 0) apeachS += score;
        }
        
        return lionS - apeachS;
    }
}
