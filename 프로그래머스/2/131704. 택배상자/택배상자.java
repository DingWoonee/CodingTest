import java.util.*;
/*
메인 벨트에 있는데 순서에 안 맞는다. -> 보조로 옮기면 됨.
보조 벨트에 있는데 순서에 안 맞는다. -> 거기서 끝.
=> 100만개라 할지라도 여러번 체크할 게 없어서 O(n)에 됨.
*/
class Solution {
    public int solution(int[] order) {
        int answer = 0;
        
        int idx = 0; // order에 이 idx보다 작은게 나오면 보조벨트에 있는 것임.
        Deque<Integer> stack = new ArrayDeque<>();
        int orderIdx = 0;
        for (; orderIdx < order.length; orderIdx++) {
            int cur = order[orderIdx];
            if (idx > cur) {
                if (stack.peek() == cur) {
                    stack.pop();
                    answer++;
                    continue;
                } else {
                    break;
                }
            }
            while (idx + 1 != cur) {
                stack.push(idx + 1);
                idx++;
            }
            answer++;
            idx++;
        }
        // 보조벨트 처리
        for (; orderIdx < order.length; orderIdx++) {
            int cur = order[orderIdx];
            if (stack.peek() == cur) {
                stack.pop();
                answer++;
            } else {
                break;
            }
        }
        
        return answer;
    }
}