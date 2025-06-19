import java.util.*;

class Solution {
    /**
    풀이 방향
    정점을 이동하면서 탐색하면, 하나의 그래프 안에서만 움직일 수 있다는 것을 활용
    1.  화살표를 주는 것과 받는 것을 파악한다.
    2.  화살표를 주기만 함 (새로운 정점 찾기)
            주기만 하는게 하나 -> 정점 + 막대형 그래프
            여러개 -> 다음 정점이 두 개 이상인게 새로운 정점임
        -> 새로운 정점 or 막대형 그래프 시작점
    3.  새로운 정점부터 다음 정점을 하나씩 순회
            끝이 있음 -> 막대형 그래프임
            중간에 두 갈래가 있음 -> 8자형 그래프임
            그 외 순환함 -> 도넛형 그래프임
        -> 숫자 세면서 삭제
    */
    public int[] solution(int[][] edges) {
        int[] answer = new int[4];
        // 가리키는 정점
        Map<Integer, List<Integer>> map = new HashMap<>();
        // 그 반대
        Map<Integer, List<Integer>> unmap = new HashMap<>();
        // 전처리
        for (int[] edge : edges) {
            if (map.containsKey(edge[0])) {
                map.get(edge[0]).add(edge[1]);
            } else {
                map.put(edge[0], new ArrayList<>(Arrays.asList(edge[1])));
            }
            if (unmap.containsKey(edge[1])) {
                unmap.get(edge[1]).add(edge[0]);
            } else {
                unmap.put(edge[1], new ArrayList<>(Arrays.asList(edge[0])));
            }
        }
        // 새로운 정점 찾기
        Set<Integer> mapKeySet = map.keySet();
        Set<Integer> unmapKeySet = unmap.keySet();
        List<Integer> candidate = new ArrayList();
        for (Integer num : mapKeySet) {
            if (!unmapKeySet.contains(num)) {
                candidate.add(num);
            }
        }
        if (candidate.size() == 1) {
            answer[0] = candidate.get(0);
        } else {
            for (int num : candidate) {
                if (map.get(num).size() > 1) {
                    answer[0] = num;
                }
            }
        }
        // 하나씩 순회
        for (int start : map.get(answer[0])) {
            Set<Integer> visited = new HashSet<>();
            int cur = start;
            while (true) {
                visited.add(cur);
                // 막대형 그래프인 경우
                if (!map.containsKey(cur)) {
                    answer[2]++;
                    break;
                }
                // 8자형 그래프인 경우
                if (map.get(cur).size() > 1) {
                    answer[3]++;
                    break;
                }
                // 다음으로 이동
                cur = map.get(cur).get(0);
                // 도넛형 그래프인 경우
                if (visited.contains(cur)) {
                    answer[1]++;
                    break;
                }
            }
        }
        
        return answer;
    }
}