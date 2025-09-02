/*
[풀이 전략]
최대 조합 개수 -> 30C5 = 3 * 29 * 7 * 9 * 26 = 29 * 26 * 189 = 약 18만
18만개에 대해 최대 10번 체크
=> 완전 탐색을 해도 매우 합리적
*/
class Solution {
    
    int answer = 0;
    int[][] q;
    int[] ans;
    
    public int solution(int n, int[][] q, int[] ans) {
        this.q = q;
        this.ans = ans;
        
        combination(n, 1, 0, new boolean[n + 1]);
        
        return answer;
    }
    
    private void check(boolean[] selected) {
        boolean isSuccess = true;
        for (int i = 0; i < q.length; i++) {
            int count = 0;
            for (int n : q[i]) {
                if (selected[n]) {
                    count++;
                }
            }
            if (count != ans[i]) {
                isSuccess = false;
            }
        }
        if (isSuccess) {
            answer++;
        }
    }
    
    private void combination(int max, int start, int count, boolean[] selected) {
        if (count == 5) {
            check(selected);
            return;
        }
        
        for (int i = start; i <= max - 4 + count; i++) {
            selected[i] = true;
            combination(max, i + 1, count + 1, selected);
            selected[i] = false;
        }
    }
    
}