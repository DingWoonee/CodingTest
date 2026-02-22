/**
* i 행 = i가 i개, i + 1, i + 2, ...
*/
class Solution {
    public int[] solution(int n, long left, long right) {
        int[] ans = new int[(int) (right - left) + 1];
        
        int p = 0;
        for (long i = left; i <= right; i++) {
            int row = (int) (i / n + 1);
            int col = (int) (i % n + 1);
            ans[p++] = col <= row ? row : row + (col - row);
        }
        
        return ans;
    }
}
