import java.util.*;

class Solution {
    public int solution(int[][] land) {
        int rows = land.length, cols = land[0].length;
        int[][] newLand = new int[rows][cols];
        int[] a = {1, -1, 0, 0};
        int[] b = {0, 0, 1, -1};
        // 영역 구분을 위한 번호
        int area = 1;
        // 각 영역의 매장량
        int[] map = new int[rows * cols + 1];
        // 전처리
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (land[i][j] == 1) {
                    Deque<int[]> stack = new ArrayDeque<>();
                    stack.push(new int[]{i, j});
                    land[i][j] = 0;
                    int count = 0;
                    while (!stack.isEmpty()) {
                        int[] cord = stack.pop();
                        int r = cord[0], c = cord[1];
                        newLand[r][c] = area;
                        count++;
                        
                        for (int k = 0; k < 4; k++) {
                            int mr = r + a[k];
                            int mc = c + b[k];
                            if (mr < 0 || mr >= rows || mc < 0 || mc >= cols) {
                                continue;
                            }
                            if (land[mr][mc] == 1) {
                                stack.push(new int[]{mr, mc});
                                land[mr][mc] = 0;
                            }
                        }
                    }
                    map[area] = count;
                    area++;
                }
            }
        }
        // 탐색
        int answer = 0;
        for (int j = 0; j < cols; j++) {
            int count = 0;
            boolean[] visitedArea = new boolean[area + 1];
            for (int i = 0; i < rows; i++) {
                if (!visitedArea[newLand[i][j]]) {
                    count += map[newLand[i][j]];
                    visitedArea[newLand[i][j]] = true;
                }
            }
            if (count > answer) {
                answer = count;
            }
        }
        return answer;
    }
}