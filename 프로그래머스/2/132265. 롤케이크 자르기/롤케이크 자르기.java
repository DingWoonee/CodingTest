import java.util.*;

class Solution {
    public int solution(int[] topping) {
        Map<Integer, Integer> map = new HashMap<>();
        Set<Integer> set = new HashSet<>();
        
        for (int t : topping) {
            int count = map.getOrDefault(t, 0) + 1;
            map.put(t, count);
        }
        
        int answer = 0;
        for (int t : topping) {
            int count = map.get(t) - 1;
            if (count == 0) {
                map.remove(t);
            } else {
                map.put(t, count);
            }
            
            set.add(t);
            
            if (map.keySet().size() == set.size()) {
                answer++;
            } else if (map.keySet().size() < set.size()) {
                break;
            }
        }
        
        return answer;
    }
}