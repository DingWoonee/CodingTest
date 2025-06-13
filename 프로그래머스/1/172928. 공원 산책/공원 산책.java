class Solution {
    public int[] solution(String[] park, String[] routes) {
        int[] answer = {0, 0};
        int rows = park.length;
        int cols = park[0].length();
        int[][] parkArr = new int[rows][cols];
        
        // 시작 위치 찾기
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (park[i].charAt(j) == 'S') {
                    answer[0] = i;
                    answer[1] = j;
                } else if (park[i].charAt(j) == 'X') {
                    parkArr[i][j] = -1;
                }
            }
        }
        // 이동
        for (String route : routes) {
            int[] moved = move(answer, route);
            if (moved[0] >= rows || moved[0] < 0)
                continue;
            if (moved[1] >= cols || moved[1] < 0)
                continue;
            int sum = 0;
            for (int i = Math.min(moved[0], answer[0]); 
                 i <= Math.max(moved[0], answer[0]); 
                 i++
            ) {
                sum += parkArr[i][moved[1]];
            }
            for (int i = Math.min(moved[1], answer[1]); 
                 i <= Math.max(moved[1], answer[1]); 
                 i++
            ) {
                sum += parkArr[moved[0]][i];
            }
            if (sum != 0) {
                continue;
            }
            answer = moved;
        }
        
        return answer;
    }
    
    private int[] move(int[] pos, String route) {
        int[] result = {pos[0], pos[1]};
        String dir = route.split(" ")[0];
        int dist = Integer.parseInt(route.split(" ")[1]);
        switch (dir) {
                case "E" -> result[1] += dist;
                case "W" -> result[1] -= dist;
                case "N" -> result[0] -= dist;
                case "S" -> result[0] += dist;
        };
        return result;
    }
}