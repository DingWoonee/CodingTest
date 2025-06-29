class Solution {
    /**
    각 곡괭이는 최대 5번 사용할 수 있음
    picks -> [dia, iron, stone]
    */
    int[][] piroArr = {{1, 1, 1}, {5, 1, 1}, {25, 5, 1}};
    int answer = Integer.MAX_VALUE;
    
    public int solution(int[] picks, String[] minerals) {
        // dia: 0, iron: 1, stone: 2
        int[] pickArr = new int[picks[0] + picks[1] + picks[2]];
        for (int i = 0; i < picks[1]; i++) {
            pickArr[i + picks[0]] = 1;
        }
        for (int i = 0; i < picks[2]; i++) {
            pickArr[i + picks[0] + picks[1]] = 2;
        }
        int[] mineArr = new int[minerals.length];
        for (int i = 0; i < minerals.length; i++) {
            String mine = minerals[i];
            mineArr[i] = mine.equals("diamond") ? 0 : (mine.equals("iron") ? 1 : 2);
        }
        boolean[] visited = new boolean[pickArr.length];
        
        dfs(pickArr, mineArr, 0, 0, 0, visited);
        
        return answer;
    }
    
    private void dfs(int[] pickArr, int[] mineArr, int pickCount, int mineIdx, int piro, boolean[] visited) {
        if (piro >= answer) {
            return;
        }
        
        // 다 캐거나 곡괭이를 다 쓴 경우
        if (mineIdx >= mineArr.length || pickCount == pickArr.length) {
            answer = Math.min(answer, piro);
            return;
        }
        
        for (int i = 0; i < pickArr.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                
                // 광물 캐서 피로도 쌓기
                int cost = 0;
                for (int j = mineIdx; j < Math.min(mineIdx + 5, mineArr.length); j++) {
                    cost += piroArr[pickArr[i]][mineArr[j]];
                }
                
                dfs(pickArr, mineArr, pickCount + 1, mineIdx + 5, piro + cost, visited);
                visited[i] = false;
            }
        }
    }
}