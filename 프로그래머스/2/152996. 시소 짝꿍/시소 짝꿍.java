import java.util.*;
/**
최초 전략
- 각 무게 당 몇 명이 있는지 전부 세기.
- 그리고 처음부터 짝꿍 찾기.
-> O(n)에 가능함.
두 번째 전략
- 11~14번만 틀린다.
-> 질문하기에서 11~14로 되어있는 것을 본다.
-> 아 overflow가 여기서 날 수가 있구나...
-> 몇 개만 틀리면 overflow를 생각해보자.
*/
class Solution {
    public long solution(int[] weights) {
        // 각 무게별 사람 수 세기.
        Map<Integer, Integer> map = new HashMap<>();
        Set<String> set = new HashSet<>();
        
        for (int weight : weights) {
            int count = map.getOrDefault(weight, 0);
            map.put(weight, count + 1);
        }
        
        // 짝꿍 찾기
        long answer = 0;
        Set<Integer> keys = map.keySet();
        for (int weight : keys) {
            int[] possibles = new int[]{weight * 2, weight * 3, weight * 4};
            int selfCount = map.get(weight);
            for (int possible : possibles) {
                if (possible % 2 == 0 
                    && map.containsKey(possible / 2) 
                    && !set.contains(weight + " " + possible / 2)) {
                    int count = map.get(possible / 2);
                    if (possible / 2 == weight) {
                        answer += ((long) selfCount * (selfCount - 1) / 2);
                    } else {
                        answer += ((long) selfCount * count);
                    }
                    set.add(weight + " " + possible / 2);
                    set.add(possible / 2 + " " + weight);
                }
                if (possible % 3 == 0 
                    && map.containsKey(possible / 3)
                    && !set.contains(weight + " " + possible / 3)) {
                    int count = map.get(possible / 3);
                    if (possible / 3 == weight) {
                        answer += ((long) selfCount * (selfCount - 1) / 2);
                    } else {
                        answer += ((long) selfCount * count);
                    }
                    set.add(weight + " " + possible / 3);
                    set.add(possible / 3 + " " + weight);
                }
                if (possible % 4 == 0 
                    && map.containsKey(possible / 4)
                    && !set.contains(weight + " " + possible / 4)) {
                    int count = map.get(possible / 4);
                    if (possible / 4 == weight) {
                        answer += ((long) selfCount * (selfCount - 1) / 2);
                    } else {
                        answer += ((long) selfCount * count);
                    }
                    set.add(weight + " " + possible / 4);
                    set.add(possible / 4 + " " + weight);
                }
            }
        }
        System.out.println(set);
        
        return answer;
    }
}