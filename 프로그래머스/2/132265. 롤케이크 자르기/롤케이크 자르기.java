import java.util.*;

class Solution {
    public int solution(int[] topping) {
        // 첫 풀이.
//         Map<Integer, Integer> map = new HashMap<>();
//         Set<Integer> set = new HashSet<>();
        
//         for (int t : topping) {
//             int count = map.getOrDefault(t, 0) + 1;
//             map.put(t, count);
//         }
        
//         int answer = 0;
//         for (int t : topping) {
//             int count = map.get(t) - 1;
//             if (count == 0) {
//                 map.remove(t);
//             } else {
//                 map.put(t, count);
//             }
            
//             set.add(t);
            
//             if (map.keySet().size() == set.size()) {
//                 answer++;
//             } else if (map.keySet().size() < set.size()) {
//                 break;
//             }
//         }
        
//         return answer;
        
        // 두 번째 풀이.
        // 각 요소의 개수를 셀 때 Map을 사용할 수도 있지만, 배열을 사용할 수도 있다!!!
        int[] arr1 = new int[10001], arr2 = new int[10001];
        for (int t : topping) {
            arr2[t]++;
        }
        int count1 = 0, count2 = 0;
        for (int i : arr2) {
            if (i > 0) {
                count2++;
            }
        }
        int answer = 0;
        for (int t : topping) {
            arr2[t]--;
            if (arr2[t] == 0) {
                count2--;
            }
            arr1[t]++;
            if (arr1[t] == 1) {
                count1++;
            }
            if (count1 == count2) {
                answer++;
            } else if (count1 > count2) {
                break;
            }
        }
        return answer;
    }
}