import java.util.*;

class Solution {
    
    public int solution(String[] board) {
        int rows = board.length;
        int cols = board[0].length();
        
        int[] start = new int[2];
        int[] goal = new int [2];
        int[][] map = new int[rows][cols];
        int[][] visited = new int[rows][cols];
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                char ch = board[i].charAt(j);
                if (ch == 'D') {
                    map[i][j] = 1;
                } else if (ch == 'R') {
                    start[0] = i;
                    start[1] = j;
                } else if (ch == 'G') {
                    goal[0] = i;
                    goal[1] = j;
                }
            }
        }
        
        // int[] - 현재 행, 열, depth
        Deque<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{start[0], start[1], 0});
        
        while (!queue.isEmpty()) {
            int[] pos = queue.poll();
            
            if (pos[0] == goal[0] && pos[1] == goal[1]) {
                return pos[2];
            }
            
            visited[pos[0]][pos[1]] = 1;
            
            // 상
            if (pos[0] > 0 && map[pos[0] - 1][pos[1]] == 0) {
                int[] des = new int[]{pos[0] - 1, pos[1], pos[2] + 1};
                while (des[0] > 0 && map[des[0] - 1][des[1]] == 0) {
                    des[0]--;
                }
                if (des[0] == goal[0] && des[1] == goal[1]) {
                    return des[2];
                }
                if (visited[des[0]][des[1]] == 0) {
                    queue.offer(des);
                }
            }
            // 하
            if (pos[0] + 1 < rows && map[pos[0] + 1][pos[1]] == 0) {
                int[] des = new int[]{pos[0] + 1, pos[1], pos[2] + 1};
                while (des[0] + 1 < rows && map[des[0] + 1][des[1]] == 0) {
                    des[0]++;
                }
                if (des[0] == goal[0] && des[1] == goal[1]) {
                    return des[2];
                }
                if (visited[des[0]][des[1]] == 0) {
                    queue.offer(des);
                }
            }
            // 좌
            if (pos[1] > 0 && map[pos[0]][pos[1] - 1] == 0) {
                int[] des = new int[]{pos[0], pos[1] - 1, pos[2] + 1};
                while (des[1] > 0 && map[des[0]][des[1] - 1] == 0) {
                    des[1]--;
                }
                if (des[0] == goal[0] && des[1] == goal[1]) {
                    return des[2];
                }
                if (visited[des[0]][des[1]] == 0) {
                    queue.offer(des);
                }
            }
            // 우
            if (pos[1] + 1 < cols && map[pos[0]][pos[1] + 1] == 0) {
                int[] des = new int[]{pos[0], pos[1] + 1, pos[2] + 1};
                while (des[1] + 1 < cols && map[des[0]][des[1] + 1] == 0) {
                    des[1]++;
                }
                if (des[0] == goal[0] && des[1] == goal[1]) {
                    return des[2];
                }
                if (visited[des[0]][des[1]] == 0) {
                    queue.offer(des);
                }
            }
        }
        
        return -1;
    }
}