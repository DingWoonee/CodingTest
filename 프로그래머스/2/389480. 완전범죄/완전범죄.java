class Solution {
    
    private int answer = -1;
    
    public int solution(int[][] info, int n, int m) {
        int[][][] visited = new int[info.length + 1][121][121];
        backtrack(info, 0, n, m, 0, 0, visited);
        return answer;
    }
    
    private void backtrack(
        int[][] info, int idx, int n, int m, int sumA, int sumB, int[][][] visited
    ) {
        if (sumA >= n || sumB >= m || visited[idx][sumA][sumB] == 1) {
            return;
        }
        if (idx == info.length) {
            if (answer == -1) {
                answer = sumA;
            } else {
                answer = Math.min(answer, sumA);
            }
            return;
        }
        visited[idx][sumA][sumB] = 1;
        backtrack(info, idx + 1, n, m, sumA + info[idx][0], sumB, visited);
        backtrack(info, idx + 1, n, m, sumA, sumB + info[idx][1], visited);
    }
}