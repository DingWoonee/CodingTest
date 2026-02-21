import java.util.*;

class Solution {
    
    int rows, cols;
    int[][] beginning, target;
    
    public int solution(int[][] beginning, int[][] target) {
        this.beginning = beginning; this.target = target;
        rows = beginning.length; cols = beginning[0].length;
        
        int INF = Integer.MAX_VALUE;
        int min = INF;
        for (int r = 0; r < (1 << rows); r++) {
            for (int c = 0; c < (1 << cols); c++) {
                if (!check(r, c)) continue;
                min = Math.min(min, count(r, c));
            }
        }
        
        return min == INF ? -1 : min;
    }
    
    private boolean check(int r, int c) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                boolean b1 = (r & (1 << i)) != 0;
                boolean b2 = (c & (1 << j)) != 0;
                
                if (b1 == b2 && beginning[i][j] != target[i][j]) return false;
                if (b1 != b2 && beginning[i][j] == target[i][j]) return false;
            }
        }
        
        return true;
    }
    
    private int count(int r, int c) {
        int cnt = 0;
        for (int i = 0; i < rows; i++) {
            if ((r & (1 << i)) != 0) cnt++;
        }
        for (int j = 0; j < cols; j++) {
            if ((c & (1 << j)) != 0) cnt++;
        }
        return cnt;
    }
}