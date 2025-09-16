/*
[풀이 전략]
구현 문제
- 각 차량의 누적 주차 시간 저장 -> Map1
- 각 차량의 출입 시간 저장 -> Map2
모든 기록을 본 후에 Map2의 남아있는 출차가 안된 기록을 Map1에 누적
*/
import java.util.*;

class Solution {
    public int[] solution(int[] fees, String[] records) {
        Map<Integer, Integer> map1 = new HashMap<>();
        Map<Integer, Integer> map2 = new HashMap<>();
        
        // 시간 누적
        for (String record : records) {
            String[] split = record.split(" ");
            int time = toMinute(split[0]);
            int number = Integer.parseInt(split[1]);
            if (!map1.keySet().contains(number)) {
                map1.put(number, 0);
            }
            
            if (split[2].equals("IN")) {
                map2.put(number, time);
            } else {
                int inTime = map2.get(number);
                int sum = map1.get(number);
                sum += (time - inTime);
                map1.put(number, sum);
                map2.remove(number);
            }
        }
        // 23시 59분 남아있는 차량 시간 누적
        int endTime = toMinute("23:59");
        for (int key : map2.keySet()) {
            map1.put(key, map1.get(key) + endTime - map2.get(key));
        }
        // 정답 생성
        List<Integer> keys = new ArrayList<>(map1.keySet());
        Collections.sort(keys);
        
        int[] answer = new int[keys.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = calcFee(fees, map1.get(keys.get(i)));
        }
        
        return answer;
    }
    
    private int calcFee(int[] fees, int time) {
        return fees[1] + (int) Math.ceil(Math.max(0, time - fees[0]) * 1.0 / fees[2]) * fees[3];
    }
    
    private int toMinute(String time) {
        String[] split = time.split(":");
        int hour = Integer.parseInt(split[0]);
        int min = Integer.parseInt(split[1]);
        return hour * 60 + min;
    }
}