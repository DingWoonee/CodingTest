import java.util.*;
/*
[풀이 전략]
지게차로 꺼내는 함수 -> 각 항목에 대해 DFS로 꺼낼 수 있는지 검사.(일시적으로 이전 버전을 유지해야 함.)
크레인으로 꺼내는 함수
=> 각 request에 대해 처리
(꺼낸 것은 '-'처리)
*/
class Solution {
    
    int answer, rows, cols;
    int[] dx = {1, -1, 0, 0};
    int[] dy = {0, 0, 1, -1};
    
    public int solution(String[] storage, String[] requests) {
        rows = storage.length;
        cols = storage[0].length();
        answer = rows * cols;
        
        char[][] arr = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                arr[i][j] = storage[i].charAt(j);
            }
        }
        
        for (String req : requests) {
            char target = req.charAt(0);
            if (req.length() == 1) {
                arr = byCar(arr, target);
            } else {
                byCrain(arr, target);
            }
        }
        
        return answer;
    }
    
    private char[][] byCar(char[][] storage, char target) {
        char[][] clone = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            clone[i] = storage[i].clone();
        }
        boolean[][] visited = new boolean[rows][cols];
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (storage[i][j] != target) continue;
                
                for (boolean[] arr : visited) {
                    Arrays.fill(arr, false);
                }
                Deque<int[]> stack = new ArrayDeque<>();
                stack.push(new int[]{i, j});
                visited[i][j] = true;
                while (!stack.isEmpty()) {
                    int[] cur = stack.pop();
                    
                    if (cur[0] == 0 
                        || cur[0] == rows - 1
                        || cur[1] == 0
                        || cur[1] == cols - 1) {
                        answer--;
                        clone[i][j] = '-';
                        break;
                    }
                    
                    for (int d = 0; d < 4; d++) {
                        int row = cur[0] + dx[d];
                        int col = cur[1] + dy[d];
                        if (row >= 0 && row < rows
                            && col >= 0 && col < cols
                            && !visited[row][col] 
                            && storage[row][col] == '-') {
                            stack.push(new int[]{row, col});
                            visited[row][col] = true;
                        }
                    }
                }
            }
        }
        return clone;
    }
    
    private void byCrain(char[][] storage, char target) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (storage[i][j] == target) {
                    answer--;
                    storage[i][j] = '-';
                }
            }
        }
    }
}