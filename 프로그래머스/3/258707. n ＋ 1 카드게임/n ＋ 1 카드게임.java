/*
- 카드 숫자 1~n / 동전 coin개
- 카드 n/3장을 뽑아 가짐.(n은 6의 배수)
- 각 라운드 시작 시
    - 카드를 두 장 뽑음
    - 카드가 없다면 게임 종료
    - 뽑은 카드를 가지려면 한 장에 동전 1개 내야됨
    - 카드에 적힌 수의 합이 n+1이 되도록 카드 두 장을 내야 다음 라운드 진행 가능
-------------------
[풀이 전략]
각 라운드에 카드를 살 지 버릴지 결정해야됨.
최대 라운드는 333라운드 -> dfs/bfs 불가능.
=> 각 라운드에 뽑은 카드로 따로 저장하고, 나중에 필요할 때 구매
*/
import java.util.*;

class Solution {
    
    public int solution(int coin, int[] cards) {
        Set<Integer> hand = new HashSet<>();
        Set<Integer> added = new HashSet<>();
        int sum = cards.length + 1;
        // 초반 세팅
        for (int i = 0; i < cards.length / 3; i++) {
            hand.add(cards[i]);
        }
        // 라운드 진행
        int round = 1;
        for (int i = cards.length / 3; i < cards.length; i += 2) {
            int card1 = cards[i];
            int card2 = cards[i + 1];
            added.add(card1);
            added.add(card2);
            // 검사
            boolean flag = false;
            for (int c : hand) {
                if (hand.contains(sum - c)) {
                    hand.remove(c);
                    hand.remove(sum - c);
                    flag = true;
                    break;
                }
            }
            if (!flag && coin > 0) {
                for (int c : hand) {
                    if (added.contains(sum - c)) {
                        coin--;
                        hand.remove(c);
                        added.remove(sum - c);
                        flag = true;
                        break;
                    }
                }
            }
            if (!flag && coin > 1) {
                for (int c : added) {
                    if (added.contains(sum - c)) {
                        coin -= 2;
                        added.remove(c);
                        added.remove(sum - c);
                        flag = true;
                        break;
                    }
                }
            }
            if (!flag) {
                break;
            }
            round++;
        }
        return round;
    }
}