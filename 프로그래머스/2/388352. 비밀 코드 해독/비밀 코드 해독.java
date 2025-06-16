import java.util.*;
import java.util.stream.*;

class Solution {
    /**
    * 전체 경우의 수 -> nC5 = O(n^5) -> 전체 비교는 패스..
    * DFS? -> 5 ^ q.length
    */
    Set<List<Integer>> result = new HashSet<>();
    
    public int solution(int n, int[][] q, int[] ans) {
        Set<Integer> set = new TreeSet<>(); // 순서 유지를 위해 TreeSet 사용
        
        backtrack(n, q, ans, set, 1);
        
        return result.size();
    }
    
    private void backtrack(int n, int[][] inputs, int[] ans, Set<Integer> set, int idx) {
        if (set.size() == 5) {
            // 정답 체크
            if (isRight(inputs, ans, set)) {
                result.add(
                    new ArrayList<>(set)
                );
            }
            // 더 볼 필요 없어서 중간에 끊기
            return;
        }
        // 백트래킹
        for (int i = idx; i <= n; i++) {
            set.add(i);
            backtrack(n, inputs, ans, set, i + 1);
            set.remove(i);
        }
    }
    
    private boolean isRight(int[][] inputs, int[] ans, Set<Integer> set) {
        for (int i = 0; i < inputs.length; i++) {
            int[] input = inputs[i];
            int count = 0;
            for (int num  : input) {
                if (set.contains(num)) {
                    count++;
                }
            }
            if (count != ans[i]) {
                return false;
            }
        }
        return true;
    }
}