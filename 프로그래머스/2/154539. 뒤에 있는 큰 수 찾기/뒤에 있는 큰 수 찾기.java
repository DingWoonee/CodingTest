import java.util.*;
/**
최초 접근
- Map의 List로 각 숫자의 인덱스를 저장한다.
- 다음 원소를 볼 때 그것보다 작은 Map 요소에 대해서 값을 넣어준다.
*/
class Solution {
    public int[] solution(int[] numbers) {
        int[] answer = new int[numbers.length];
        Arrays.fill(answer, -1);
        
        Map<Integer, List<Integer>> map = new HashMap<>();
        Set<Integer> set = new TreeSet<>(); // 대기 항목들
        for (int i = 0; i < numbers.length; i++) {
            int num = numbers[i];
            // 기존 리스트 항목들 처리하기
            List<Integer> delete = new ArrayList<>();
            for (int n : set) {
                if (n >= num) {
                    break;
                }
                for (int idx : map.get(n)) {
                    answer[idx] = num;
                }
                map.get(n).clear();
                delete.add(n);
            }
            for (int n : delete) {
                set.remove(n);
            }
            // list 업데이트 하기
            List<Integer> list = map.getOrDefault(num, new ArrayList<>());
            list.add(i);
            map.put(num, list);
            // set 업데이트 하기
            set.add(num);
        }
        
        return answer;
    }
}