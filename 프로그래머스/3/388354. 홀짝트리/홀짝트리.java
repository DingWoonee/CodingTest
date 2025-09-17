/*
1개 이상의 트리
모든 노드는 다른 번호
4가지 종류의 노드
2가지 종류의 트리 -> 목표: 각 트리의 종류 개수 세기

노드: 40만개, 간선: 100만개
-------------------------------
어떤 노드가 루트일 때 홀수/짝수 노드 -> 루트가 아니면 역홀수/역짝수 노드
어떤 노드가 루트일 때 역홀수/역짝수 노드 -> 루트가 아니면 홀수/짝수 노드
-> 하나의 트리에 대해 각 노드가 루트일 때 각 노드 종류 개수를 계산
=> 홀짝이 하나 = 홀짝 트리 + 1 / 역홀짝이 하나 = 역홀짝 트리 + 1
*/
import java.util.*;

class Solution {
    public int[] solution(int[] nodes, int[][] edges) {
        int[] answer = new int[2];
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        Set<Integer> visited = new HashSet<>();
        // 전처리
        for (int node : nodes) {
            graph.put(node, new HashSet<>());
        }
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        // 탐색
        for (int key : graph.keySet()) {
            if (visited.contains(key)) {
                continue;
            }
            int hjCount = 0, rhjCount = 0;
            Deque<Integer> stack = new ArrayDeque<>();
            stack.push(key);
            visited.add(key);
            while (!stack.isEmpty()) {
                int cur = stack.pop();
                if (cur % 2 == graph.get(cur).size() % 2) {
                    hjCount++;
                } else {
                    rhjCount++;
                }
                for (int next : graph.get(cur)) {
                    if (!visited.contains(next)) {
                        stack.push(next);
                        visited.add(next);
                    }
                }
            }
            if (hjCount == 1) {
                answer[0]++;
            }
            if (rhjCount == 1) {
                answer[1]++;
            }
        }
        
        return answer;
    }
}