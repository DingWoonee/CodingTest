class Solution {
    
    int max = 0;
    
    public int solution(int k, int[][] dungeons) {
        dfs(dungeons, 0, new boolean[dungeons.length], k);
        
        return max;
    }
    
    private void dfs(int[][] d, int cnt, boolean[] visited, int rest) {
        max = Math.max(max, cnt);
        
        for (int i = 0; i < visited.length; i++) {
            if (visited[i] || d[i][0] > rest) continue;
            
            visited[i] = true;
            dfs(d, cnt + 1, visited, rest - d[i][1]);
            visited[i] = false;
        }
    }
}