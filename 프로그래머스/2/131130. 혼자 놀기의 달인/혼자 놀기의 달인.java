import java.util.*;
/*
[최초 전략]
그룹을 묶자. 그리고 그룹의 개수를 세자.
가장 긴 거 두 개를 곱하자.
*/
class Solution {
    public int solution(int[] cards) {
        List<Integer> groups = new ArrayList<>();
        boolean[] visited = new boolean[cards.length];
        for (int i = 0; i < cards.length; i++) {
            int next = cards[i];
            if (visited[next - 1]) {
                continue;
            }
            int count = 1;
            visited[i] = true;
            while (cards[i] != cards[next - 1]) {
                next = cards[next - 1];
                count++;
                visited[next - 1] = true;
            }
            groups.add(count);
        }
        
        // 그룹이 하나인 경우
        if (groups.size() == 1) {
            return 0;
        }
        // 두 개 이상인 경우
        groups.sort((g1, g2) -> g2 - g1);
        return groups.get(0) * groups.get(1);
    }
}