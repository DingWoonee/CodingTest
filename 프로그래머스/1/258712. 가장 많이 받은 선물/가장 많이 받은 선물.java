import java.util.*;

class Solution {
    public int solution(String[] friends, String[] gifts) {
        // 선물을 준 사람과 받은 사람
        int[][] relation = new int[friends.length][friends.length];
        // 선물 지수
        int[] pScore = new int[friends.length];
        // 인덱스
        Map<String, Integer> index = new HashMap<>();
        for (int i = 0; i < friends.length; i++) {
            index.put(friends[i], i);
        }
        // 선물 내역 분석
        for (String gift : gifts) {
            String[] split = gift.split(" ");
            String giver = split[0];
            String receiver = split[1];
            
            int giverIdx = index.get(giver);
            int receiverIdx = index.get(receiver);
            relation[giverIdx][receiverIdx] += 1;
            pScore[giverIdx] += 1;
            pScore[receiverIdx] -= 1;
        }
        // 답 구하기
        int answer = 0;
        for (int i = 0; i < friends.length; i++) {
            int count = 0;
            for (int j = 0; j < friends.length; j++) {
                if (relation[i][j] > relation[j][i]) {
                    count++;
                    continue;
                }
                if (relation[i][j] == relation[j][i]
                   && pScore[i] > pScore[j]) {
                    count++;
                }
            }
            if (count > answer)
                answer = count;
        }
        return answer;
    }
}