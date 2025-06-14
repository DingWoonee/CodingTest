import java.util.*;

class Solution {
    public int[] solution(String[] keymap, String[] targets) {
        // 각 키의 최소 접근 세기
        Map<Character, Integer> map = new HashMap<>();
        for (String keys : keymap) {
            for (int i = 0; i < keys.length(); i++) {
                char ch = keys.charAt(i);
                if (!map.containsKey(ch)) {
                    map.put(ch, i + 1);
                } else {
                    map.put(ch, Math.min(map.get(ch), i + 1));
                }
            }
        }
        // 계산
        int[] answer = new int[targets.length];
        for (int i = 0; i < targets.length; i++) {
            int count = 0;
            for (int j = 0; j < targets[i].length(); j++) {
                char ch = targets[i].charAt(j);
                if (!map.containsKey(ch)) {
                    count = -1;
                    break;
                }
                count += map.get(ch);
            }
            answer[i] = count;
        }
        return answer;
    }
}