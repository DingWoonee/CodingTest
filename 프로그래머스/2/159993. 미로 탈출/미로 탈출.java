import java.util.*;

/**
최초 시도 - 메모리 초과
2차 시도 - 레버까지의 경로에 E가 있으면 거기로 가는 것을 최단 거리로 보자
*/
class Solution {
    public int solution(String[] maps) {
        int answer = 0;
        
        int rows = maps.length;
        int cols = maps[0].length();
        int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        // 위치 찾기
        int[] posS = new int[2];
        int[] posL = new int[2];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (maps[i].charAt(j) == 'S') {
                    posS[0] = i;
                    posS[1] = j;
                } else if (maps[i].charAt(j) == 'L') {
                    posL[0] = i;
                    posL[1] = j;
                }
            }
        }
        // BFS로 레버 찾기
        // 행, 열, 깊이
        Deque<int[]> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[rows][cols];
        
        queue.offer(new int[]{posS[0], posS[1], 0});
        visited[posS[0]][posS[1]] = true;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            if (maps[cur[0]].charAt(cur[1]) == 'L') {
                answer += cur[2];
                break;
            }
            
            for (int[] dir : dirs) {
                int d_row = cur[0] + dir[0];
                int d_col = cur[1] + dir[1];
                if (d_row >= 0 && d_row < rows
                    && d_col >= 0 && d_col < cols
                    && !visited[d_row][d_col] 
                    && maps[d_row].charAt(d_col) != 'X'
                   ) {
                    queue.offer(new int[]{d_row, d_col, cur[2] + 1});
                    visited[d_row][d_col] = true;
                }
            }
        }
        if (queue.isEmpty() && answer == 0) {
            return -1;
        }
        // BFS로 EXIT 찾기
        Deque<int[]> queue2 = new ArrayDeque<>();
        boolean[][] visited2 = new boolean[rows][cols];
        queue2.offer(new int[]{posL[0], posL[1], 0});
        visited2[posL[0]][posL[1]] = true;
        while (!queue2.isEmpty()) {
            int[] cur = queue2.poll();
            visited2[cur[0]][cur[1]] = true;
            if (maps[cur[0]].charAt(cur[1]) == 'E') {
                return answer + cur[2];
            }
            
            for (int[] dir : dirs) {
                int d_row = cur[0] + dir[0];
                int d_col = cur[1] + dir[1];
                if (d_row >= 0 && d_row < rows
                    && d_col >= 0 && d_col < cols
                    && !visited2[d_row][d_col] 
                    && maps[d_row].charAt(d_col) != 'X'
                   ) {
                    queue2.offer(new int[]{d_row, d_col, cur[2] + 1});
                    visited2[d_row][d_col] = true;
                }
            }
        }
        
        return -1;
    }
}