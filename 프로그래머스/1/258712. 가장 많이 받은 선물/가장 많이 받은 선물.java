import java.util.*;
import java.util.stream.*;

/**
* 알아야 하는 것
* 1. 각 사람의 선물 지수
* 2. 서로 선물을 준 횟수
*/
class Solution {
    
    public int solution(String[] friends, String[] gifts) {
        int[] giftScore = new int[friends.length];
        int[][] table = new int[friends.length][friends.length];
        Map<String, Integer> toIndex = new HashMap<>();
        IntStream.range(0, friends.length).forEach(n -> toIndex.put(friends[n], n));
        
        for (String gift : gifts) {
            String giver = gift.split(" ")[0];
            String receiver = gift.split(" ")[1];
            int giverIndex = toIndex.get(giver);
            int receiverIndex = toIndex.get(receiver);
            
            table[giverIndex][receiverIndex]++;
            giftScore[giverIndex]++;
            giftScore[receiverIndex]--;
        }
        
        int answer = 0;
        for (int i = 0; i < friends.length; i++) {
            int count = 0;
            for (int j = 0; j < friends.length; j++) {
                if (i == j) continue;
                
                if (table[i][j] > table[j][i]
                   || (table[i][j] == table[j][i] && giftScore[i] > giftScore[j])) {
                    count++;
                }
            }
            answer = Math.max(count, answer);
        }
        return answer;
    }
}