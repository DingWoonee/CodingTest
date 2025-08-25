import java.util.*;
/*
개수를 다 세고, 정렬하기 -> 종류 세기
*/
class Solution {
    public int solution(int k, int[] tangerine) {
        // 개수 세기
        Map<Integer, Integer> map = new HashMap<>();
        for (int t : tangerine) {
            map.put(t, map.getOrDefault(t, 0) + 1);
        }
        List<Integer> list = new ArrayList<>(map.values());
        // 정렬
        list.sort((a, b) -> b - a);
        // 종류 세기
        int sum = 0;
        int answer = 0;
        for (int l : list) {
            if (k > sum) {
                sum += l;
                answer++;
            } else {
                break;
            }
        }
        return answer;
    }
}