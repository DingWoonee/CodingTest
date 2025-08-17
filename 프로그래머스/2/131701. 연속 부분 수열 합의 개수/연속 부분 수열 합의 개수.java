import java.util.*;
/*
완전 탐색 -> n^n -> 최대 1000^1000
*/
class Solution {
    public int solution(int[] elements) {
        int len = elements.length;
        
        Set<Integer> set = new HashSet<>();
        
        for (int i = 1; i < len + 1; i++) {
            int sum = 0;
            for (int j = 0; j < i; j++) {
                sum += elements[j % len];
            }
            set.add(sum);
            for (int j = 0; j < len - 1; j++) {
                sum -= elements[j % len];
                sum += elements[(j + i) % len];
                set.add(sum);
            }
        }
        
        return set.size();
    }
}