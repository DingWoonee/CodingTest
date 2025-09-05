import java.util.*;

class Solution {
    
    int rows, cols;
    int[] dx = {1, -1, 0, 0};
    int[] dy = {0, 0, 1, -1};
    
    public int solution(int[][] land) {
        rows = land.length;
        cols = land[0].length;
        int mark = 1;
        int[] counts = new int[250001];
        int[][] marked = new int[rows][cols];
        boolean[][] visited = new boolean[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (visited[i][j] || land[i][j] == 0) {
                    continue;
                }
                int count = 0;
                
                Deque<int[]> stack = new ArrayDeque<>();
                stack.push(new int[]{i, j});
                visited[i][j] = true;
                while (!stack.isEmpty()) {
                    int[] cur = stack.pop();
                    marked[cur[0]][cur[1]] = mark;
                    count++;
                    for (int d = 0; d < 4; d++) {
                        int x = cur[0] + dx[d], y = cur[1] + dy[d];
                        if (x >= 0 && x < rows && y >= 0 && y < cols 
                            && land[x][y] == 1 && !visited[x][y]) {
                            stack.push(new int[]{x, y});
                            visited[x][y] = true;
                        }
                    }
                }
                counts[mark] = count;
                mark++;
            }
        }
        
        int answer = 0;
        for (int i = 0; i < cols; i++) {
            Set<Integer> set = new HashSet<>();
            for (int j = 0; j < rows; j++) {
                set.add(marked[j][i]);
            }
            int sum = 0;
            for (int idx : set) {
                sum += counts[idx];
            }
            answer = Math.max(answer, sum);
        }
        return answer;
    }
}