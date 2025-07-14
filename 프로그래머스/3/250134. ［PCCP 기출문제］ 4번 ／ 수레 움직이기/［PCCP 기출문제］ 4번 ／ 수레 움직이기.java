/**
최초 전략
- 재귀 dfs
- 도착 완료된 것 움직이지 않기
- 서로 칸 바꾸는 것 안됨
*/
class Solution {
    
    int answer = Integer.MAX_VALUE;
    
    int[] d_row = {1, -1, 0, 0};
    int[] d_col = {0, 0, 1, -1};
    
    int rows, cols;
    
    public int solution(int[][] maze) {
        rows = maze.length;
        cols = maze[0].length;
        
        int[] red = new int[2];
        int[] blue = new int[2];
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (maze[i][j] == 1) {
                    red[0] = i;
                    red[1] = j;
                } else if (maze[i][j] == 2) {
                    blue[0] = i;
                    blue[1] = j;
                }
            }
        }
        
        boolean[][] redVisited = new boolean[rows][cols];
        boolean[][] blueVisited = new boolean[rows][cols];
        
        redVisited[red[0]][red[1]] = true;
        blueVisited[blue[0]][blue[1]] = true;
        dfs(maze, redVisited, blueVisited, red, blue, 0);
        
        return answer == Integer.MAX_VALUE ? 0 : answer;
    }
    
    private void dfs(int[][] maze, boolean[][] rV, boolean[][] bV, int[] red, int[] blue, int depth) {
        // 완료
        // System.out.println(red[0] + " red " + red[1] + " " + depth);
        // System.out.println(blue[0] + " blue " + blue[1]);
        boolean isRedCom = (maze[red[0]][red[1]] == 3);
        boolean isBlueCom = (maze[blue[0]][blue[1]] == 4);
        if (isRedCom && isBlueCom) {
            // System.out.println("complete==============================");
            answer = Math.min(answer, depth);
            return;
        }
        // 움직이기
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int nextRedRow = red[0] + (isRedCom ? 0 : d_row[i]);
                int nextRedCol = red[1] + (isRedCom ? 0 : d_col[i]);
                int nextBlueRow = blue[0] + (isBlueCom ? 0 : d_row[j]);
                int nextBlueCol = blue[1] + (isBlueCom ? 0 : d_col[j]);
                
                if (nextRedRow >= 0 && nextRedRow < rows 
                    && nextRedCol >= 0 && nextRedCol < cols 
                    && nextBlueRow >= 0 && nextBlueRow < rows 
                    && nextBlueCol >= 0 && nextBlueCol < cols 
                    && maze[nextRedRow][nextRedCol] != 5 
                    && maze[nextBlueRow][nextBlueCol] != 5 
                    && (isRedCom || !rV[nextRedRow][nextRedCol]) 
                    && (isBlueCom || !bV[nextBlueRow][nextBlueCol]) 
                    && !(nextRedRow == nextBlueRow && nextRedCol == nextBlueCol)
                    && !(red[0] == nextBlueRow && red[1] == nextBlueCol 
                         && blue[0] == nextRedRow && blue[1] == nextRedCol)) {
                    rV[nextRedRow][nextRedCol] = true;
                    bV[nextBlueRow][nextBlueCol] = true;
                    dfs(maze, rV, bV, new int[]{nextRedRow, nextRedCol}, new int[]{nextBlueRow, nextBlueCol}, depth + 1);
                    rV[nextRedRow][nextRedCol] = false;
                    bV[nextBlueRow][nextBlueCol] = false;
                }
            }
        }
    }
}