class Solution {
    // points 조회할 때는 -1하기
    public int solution(int[][] points, int[][] routes) {
        int answer = 0;
        int robotNum = routes.length;
        // 현재 위치
        int[][] curPos = new int[routes.length][2];
        // 현재 route 인덱스
        int[] routeIdxs = new int[routes.length];
        // 충돌 상태 체크를 위한 현위치 배열
        int[][] map = new int[101][101];
        
        while (true) {
            for (int i = 0; i < robotNum; i++) {
                if (routeIdxs[i] >= routes[i].length) {
                    continue;
                }
                // 최초
                if (routeIdxs[i] == 0) {
                    int[] point = points[routes[i][0] - 1];
                    curPos[i][0] = point[0];
                    curPos[i][1] = point[1];
                    routeIdxs[i]++;
                    map[curPos[i][0]][curPos[i][1]]++;
                    continue;
                }
                // 목적지 도달
                int[] desPos = points[routes[i][routeIdxs[i]] - 1];
                if (curPos[i][0] == desPos[0] && curPos[i][1] == desPos[1]) {
                    routeIdxs[i]++;
                    if (routeIdxs[i] >= routes[i].length) {
                        continue;
                    }
                    desPos = points[routes[i][routeIdxs[i]] - 1];
                }
                // 이동
                if (desPos[0] > curPos[i][0]) {
                    curPos[i][0]++;
                } else if (curPos[i][0] > desPos[0]) {
                    curPos[i][0]--;
                } else if (desPos[1] > curPos[i][1]) {
                    curPos[i][1]++;
                } else {
                    curPos[i][1]--;
                }
                map[curPos[i][0]][curPos[i][1]]++;
            }
            // 충돌 체크
            int count = 0;
            for (int i = 1; i < map.length; i++) {
                for (int j = 1; j < map[0].length; j++) {
                    if (map[i][j] > 1) {
                        count++;
                    }
                    map[i][j] = 0;
                }
            }
            answer += count;
            // 완료 체크
            int checkCount = 0;
            for (int i = 0; i < robotNum; i++) {
                if (routeIdxs[i] >= routes[i].length) {
                    checkCount++;
                }
            }
            if (checkCount == robotNum) {
                break;
            }
        }
        return answer;
    }
}