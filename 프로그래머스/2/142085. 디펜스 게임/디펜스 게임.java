import java.util.*;
/*
O(n)에 끝내야 한다.

우선 순위 큐를 통해 계속 넣으면서 죽을 것 같을 때 가장 많은 거를 빼면 될 듯.
*/
class Solution {
    
    public int solution(int n, int k, int[] enemy) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        
        int i = 0;
        for (i = 0; i < enemy.length; i++) {
            int e = enemy[i];
            
            maxHeap.add(e);
            n -= e;
            
            if (n < 0) {
                if (k == 0) {
                    return i;
                }
                
                n += maxHeap.poll();
                k--;
            }
        }
        
        return i;
    }
}