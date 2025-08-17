import java.util.*;
/*
완전 탐색 -> n^n -> 최대 1000^1000
*/
class Solution {
    public int solution(int[] elements) {
        int len = elements.length;
        
        Set<Integer> set = new HashSet<>();
        
        int[] concat = new int[len * 2];
        for (int i = 0; i < len; i++) {
            concat[i] = elements[i];
            concat[i + len] = elements[i];
        }
        
        for (int i = 1; i < len + 1; i++) {
            int sum = 0;
            for (int j = 0; j < i; j++) {
                sum += concat[j];
            }
            set.add(sum);
            for (int j = 0; j < len - 1; j++) {
                sum -= concat[j];
                sum += concat[j + i];
                set.add(sum);
            }
        }
        
        return set.size();
    }
}