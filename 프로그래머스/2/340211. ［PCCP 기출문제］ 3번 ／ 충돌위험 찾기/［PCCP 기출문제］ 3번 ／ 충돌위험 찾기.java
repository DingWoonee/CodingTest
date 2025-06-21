import java.util.*;

class Solution {
    // points 조회할 때는 -1하기
    public int solution(int[][] points, int[][] routes) {
        int answer = 0;
        // List: time, r, c
        Map<String, Integer> events = new HashMap<>();
        
        for (int i = 0; i < routes.length; i++) {
            int time = 0;
            int[] route = routes[i];
            for (int j = 0; j < route.length - 1; j++) {
                int[] start = points[route[j] - 1];
                int[] end = points[route[j + 1] - 1];
                if (end[0] > start[0]) {
                    for (int r = start[0]; r < end[0]; r++) {
                        String event = time + " " +  r + " " + start[1];
                        events.put(event, events.getOrDefault(event, 0) + 1);
                        if (events.get(event) == 2) { // 중복 제거를 위해 딱 한번만 기록
                            answer++;
                        }
                        time++;
                    }
                } else {
                    for (int r = start[0]; r > end[0]; r--) {
                        String event = time + " " +  r + " " + start[1];
                        events.put(event, events.getOrDefault(event, 0) + 1);
                        if (events.get(event) == 2) { // 중복 제거를 위해 딱 한번만 기록
                            answer++;
                        }
                        time++;
                    }
                }
                if (end[1] > start[1]) {
                    for (int c = start[1]; c < end[1]; c++) {
                        String event = time + " " +  end[0] + " " + c;
                        events.put(event, events.getOrDefault(event, 0) + 1);
                        if (events.get(event) == 2) { // 중복 제거를 위해 딱 한번만 기록
                            answer++;
                        }
                        time++;
                    }
                } else {
                    for (int c = start[1]; c > end[1]; c--) {
                        String event = time + " " +  end[0] + " " + c;
                        events.put(event, events.getOrDefault(event, 0) + 1);
                        if (events.get(event) == 2) { // 중복 제거를 위해 딱 한번만 기록
                            answer++;
                        }
                        time++;
                    }
                }
                
            }
            // 루트의 마지막 위치
            int[] last = points[route[route.length - 1] - 1];
            String event = time + " " +  last[0] + " " + last[1];
            events.put(event, events.getOrDefault(event, 0) + 1);
            if (events.get(event) == 2) { // 중복 제거를 위해 딱 한번만 기록
                answer++;
            }
        }
        
        return answer;
    }
}