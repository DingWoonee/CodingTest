/*
최단경로 이동, 행 이동 우선

충돌 횟수를 구해야 함.

[구현 전략]
시뮬레이션
*/
class Solution {
    
    int robots;
    int deses;
    
    public int solution(int[][] points, int[][] routes) {
        int answer = 0;
        robots = routes.length;
        deses = routes[0].length;
        int[][] map = new int[101][101];
        int[][] cur = new int[101][2];
        int[] nextIdx = new int[101];
        
        // 최초 위치
        for (int i = 0; i < robots; i++) {
            int[] point = points[routes[i][0] - 1];
            map[point[0]][point[1]]++;
            cur[i][0] = point[0];
            cur[i][1] = point[1];
            nextIdx[i] = 1;
        }
        // 충돌 체크
        answer += checkCollision(map);
        
        while (true) {
            // 이동
            if (moveRobots(map, cur, nextIdx, points, routes) == robots) {
                break;
            }
            // 충돌 체크
            answer += checkCollision(map);
        }
        
        return answer;
    }
    
    private int moveRobots(int[][] map, int[][] cur, int[] nextIdx, int[][] points, int[][] routes) {
        int count = 0;
        for (int i = 0; i < robots; i++) {
            int[] pos = cur[i];
            int desPoint = routes[i][nextIdx[i]];
            int[] des = points[desPoint - 1];
            if (pos[0] == des[0] && pos[1] == des[1]) { // 도착
                if (nextIdx[i] == deses - 1) { // 완료
                    count++;
                    continue;
                } else { // 다음 목적지
                    des = points[routes[i][++nextIdx[i]] - 1];
                }
            }
            if (pos[0] > des[0]) { // 행 우선 이동
                pos[0]--;
            } else if (des[0] > pos[0]) {
                pos[0]++;
            } else if (pos[1] > des[1]) {
                pos[1]--;
            } else {
                pos[1]++;
            }
            map[pos[0]][pos[1]]++;
        }
        return count;
    }
    
    private int checkCollision(int[][] map) {
        int count = 0;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] > 1) {
                    count++;
                }
                map[i][j] = 0;
            }
        }
        return count;
    }
}