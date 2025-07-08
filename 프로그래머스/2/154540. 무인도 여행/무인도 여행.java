import java.util.*;
/**
최초 전략 - 그냥 dfs & visited
*/
class Solution {
    
    List<Integer> islands = new ArrayList<>();
    
    int rows, cols;
    
    int[][] ds = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    
    public int[] solution(String[] maps) {
        rows = maps.length;
        cols = maps[0].length();
        
        boolean[][] visited = new boolean[rows][cols];
        
        // 행, 열
        Deque<int[]> stack = new ArrayDeque<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (!visited[i][j]
                   && maps[i].charAt(j) != 'X') {
                    // dfs
                    stack.push(new int[]{i, j});
                    visited[i][j] = true;
                    int count = 0;
                    while (!stack.isEmpty()) {
                        int[] cur = stack.pop();
                        count += (maps[cur[0]].charAt(cur[1]) - '0');
                        
                        for (int[] d : ds) {
                            int[] newPos = new int[]{cur[0] + d[0], cur[1] + d[1]};
                            if (newPos[0] >= 0 && newPos[0] < rows
                                && newPos[1] >= 0 && newPos[1] < cols
                                && !visited[newPos[0]][newPos[1]]
                                && maps[newPos[0]].charAt(newPos[1]) != 'X') {
                                stack.push(newPos);
                                visited[newPos[0]][newPos[1]] = true;
                            }
                        }
                    }
                    System.out.println(count);
                    islands.add(count);
                }
            }
        }
        
        if (islands.size() == 0) {
            return new int[]{-1};
        }
        int[] answer = new int[islands.size()];
        Collections.sort(islands);
        for (int i = 0; i < islands.size(); i++) {
            answer[i] = islands.get(i);
        }
        return answer;
    }
}