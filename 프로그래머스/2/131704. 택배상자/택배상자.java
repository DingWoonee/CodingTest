import java.util.*;
/*
메인 벨트에 있는데 순서에 안 맞는다. -> 보조로 옮기면 됨.
보조 벨트에 있는데 순서에 안 맞는다. -> 거기서 끝.
=> 100만개라 할지라도 여러번 체크할 게 없어서 O(n)에 됨.
*/
class Solution {
    public int solution(int[] order) {
        int answer = 0;
        
        int idx = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < order.length; i++) {
            stack.push(i + 1);
            while (!stack.isEmpty()) {
                if (stack.peek() == order[idx]) {
                    stack.pop();
                    idx++;
                } else if (order[idx] < i + 1) {
                    return idx;
                } else {
                    break;
                }
            }
        }
        
        return idx;
    }
}