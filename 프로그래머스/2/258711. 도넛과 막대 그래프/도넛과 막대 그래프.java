/*
[풀이 전략]
도넛 모양 그래프 / 막대 모양 그래프 / 8자 모양 그래프
중간에 두 갈래가 있다. -> 무조건 8자 모양
끝이 있다. -> 무조건 막대 모양
두 갈래가 없는데 순환한다. -> 도넛 모양
-----
1. 일단 edges 처리해서 Map<Integer, List<Integer>>로 그래프 만들기
    -> next, before 맵 만들기
2. 정점 찾기.
3. 각 그래프 개수 세기
*/
import java.util.*;

class Solution {
    
    int[] answer = new int[]{0, 0, 0, 0};
    
    public int[] solution(int[][] edges) {
        Map<Integer, List<Integer>> next = new HashMap<>();
        Map<Integer, List<Integer>> before = new HashMap<>();
        for (int[] edge : edges) {
            List<Integer> afterList = next.getOrDefault(edge[0], new ArrayList<>());
            afterList.add(edge[1]);
            next.put(edge[0], afterList);
            //
            List<Integer> beforeList = before.getOrDefault(edge[1], new ArrayList<>());
            beforeList.add(edge[0]);
            before.put(edge[1], beforeList);
        }
        // 정점 찾기
        for (int e : next.keySet()) {
            if (next.get(e).size() > 1 
                && !before.containsKey(e)) {
                answer[0] = e;
                break;
            }
        }
        // 그래프 개수 세기
        for (int e : next.get(answer[0])) {
            // 탐색
            int cur = e;
            while (true) {
                if (!next.containsKey(cur)) { // 막대 그래프
                    answer[2]++;
                    break;
                }
                if (next.get(cur).size() > 1) { // 8자 그래프
                    answer[3]++;
                    break;
                }
                cur = next.get(cur).get(0);
                if (e == cur) { // 도넛 그래프
                    answer[1]++;
                    break;
                }
            }
        }
        
        return answer;
    }
}