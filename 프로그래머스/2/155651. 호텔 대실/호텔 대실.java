import java.util.*;
import java.util.stream.*;
/**
처음 전략 - 정렬하고 리스트에 끝나는 시각을 담자 
*/
class Solution {
    
    List<Integer> rooms = new ArrayList<>();
    
    public int solution(String[][] book_time) {
        // 분 환산 및 정렬
        List<Integer[]> times = Arrays.stream(book_time)
            .sorted(Comparator.comparing(t -> t[0]))
            .map(t -> {
                String[] split1 = t[0].split(":");
                String[] split2 = t[1].split(":");
                return new Integer[]{
                    Integer.parseInt(split1[0]) * 60 + Integer.parseInt(split1[1]),
                    Integer.parseInt(split2[0]) * 60 + Integer.parseInt(split2[1])
                };
            })
            .collect(Collectors.toList());
        
        // 끝나는 시각을 저장
        for (Integer[] time : times) {
            int roomIdx = getPossibleRoomIdx(time[0]);
            if (roomIdx < 0) { // 새로운 방 추가
                rooms.add(time[1]);
            } else {
                rooms.set(roomIdx, time[1]);
            }
        }
        
        return rooms.size();
    }
    
    private int getPossibleRoomIdx(int startTime) {
        for (int i = 0; i < rooms.size(); i++) {
            int endTime = rooms.get(i);
            if (endTime + 10 <= startTime) {
                return i;
            }
        }
        return -1;
    }
}