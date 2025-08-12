import java.util.*;
/*
앞에서 부터 슬라이딩 하면서 체크

최종 풀이 시간: 19분 42초
*/
class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;
        
        Map<String, Integer> wantMap = new HashMap<>();
        for (int i = 0; i < want.length; i++) {
            wantMap.put(want[i], number[i]);
        }
        
        Map<String, Integer> sliceMap = new HashMap<>();
        for (int i = 0; i < 9; i++) {
            String str = discount[i];
            sliceMap.put(str, sliceMap.getOrDefault(str, 0) + 1);
        }
        
        for (int i = 9; i < discount.length; i++) {
            String str = discount[i];
            sliceMap.put(str, sliceMap.getOrDefault(str, 0) + 1);
            if (i > 9) {
                String preStr = discount[i - 10];
                sliceMap.put(preStr, sliceMap.get(preStr) - 1);
            }
            boolean flag = false;
            for (String key : wantMap.keySet()) {
                if (wantMap.get(key) > sliceMap.getOrDefault(key, 0)) {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                answer++;
            }
        }
        
        return answer;
    }
}