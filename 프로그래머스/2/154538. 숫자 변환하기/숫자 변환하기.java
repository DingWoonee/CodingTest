import java.util.*;
/**
y - x <= 여기에 n과 x가 몇 번 더해져 있는지
y - x = n*a + x*b

이 조합을 만족하는 a, b 조합이 없다 => -1
조합이 있다 
    => 최소 개수 구하기
    => a와 b를 2, 3에 대해 소인수분해...
*/
class Solution {
    public int solution(int x, int y, int n) {
        boolean[] visited = new boolean[y + 1];
        
        Deque<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{x, 0});
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            if (cur[0] == y) {
                return cur[1];
            }
            if (cur[0] > y) {
                continue;
            }
            
            int newNum = cur[0] + n;
            if (newNum <= y && !visited[newNum]) {
                queue.offer(new int[]{newNum, cur[1] + 1});
                visited[newNum] = true;
            }
            newNum = cur[0] * 2;
            if (newNum <= y && !visited[newNum]) {
                queue.offer(new int[]{newNum, cur[1] + 1});
                visited[newNum] = true;
            }
            newNum = cur[0] * 3;
            if (newNum <= y && !visited[newNum]) {
                queue.offer(new int[]{newNum, cur[1] + 1});
                visited[newNum] = true;
            }
        }
        
        return -1;
    }
}