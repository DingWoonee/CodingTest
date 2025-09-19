/*
n개의 주사위.
주사위 수는 1~n.
수 구성은 모두 다름.
A가 n/2개 가져가고, B가 나머지 n/2개를 가져감.
각각 가져간 주사위를 모두 굴린 뒤 나온 수들을 모두 합함.
-> 점수가 큰 쪽이 승리. 같다면 무승부.
-> 승리할 확률이 가장 높아지기 위해 A가 골라야 하는 주사위 번호를 오름차순으로.
------------------
[풀이 전략]
n/2개 고르는 경우의 수: n_C_n/2 -> 최대 10C5 = 252
각 경우에 대해 6^10의 경우의 수가 생김 = 2^10 * 3^10 = 1024 * 243 * 243 = 62500000
=> 총 경우의 수 약 150억 => 절대 안됨.

*/
import java.util.*;

class Solution {
    
    int[] answer;
    int max = 0;
    int[][] d;
    
    public int[] solution(int[][] dice) {
        answer = new int[dice.length / 2];
        d = dice;
        
        combination(dice.length, 0, 0, new Integer[dice.length / 2]);
        
        return answer;
    }
    
    private void combination(int len, int start, int count, Integer[] selected) {
        if (count == len / 2) {
            List<Integer> A = Arrays.asList(selected);
            List<Integer> B = new ArrayList<>();
            for (int i = 0; i < len; i++) {
                if (!A.contains(i)) {
                    B.add(i);
                }
            }
            compare(A, B);
            return;
        }
        
        for (int i = start; i < len / 2 + 1 + count; i++) {
            selected[count] = i;
            combination(len, i + 1, count + 1, selected);
        }
    }
    
    private void compare(List<Integer> A, List<Integer> B) {
        Map<Integer, Integer> map = new HashMap<>();
        int[] sumA = countCases(A);
        int[] sumB = countCases(B);
        
        int count = 0;
        for (int i = 0; i < sumA.length; i++) {
            for (int j = 0; j < i; j++) {
                if (i > j) {
                    count += sumA[i] * sumB[j];
                }
            }
        }
        
        if (count > max) {
            max = count;
            for (int i = 0; i < A.size(); i++) {
                answer[i] = A.get(i) + 1;
            }
        }
    }
    
    private int[] countCases(List<Integer> list) {
        int[] result = new int[list.size() * 100 + 1];
        result[0] = 1;
        for (int i = 0; i < list.size(); i++) {
            int[] dice = d[list.get(i)];
            int[] next = new int[list.size() * 100 + 1];
            for (int j = 0; j < result.length; j++) {
                if (result[j] > 0) {
                    for (int n : dice) {
                        next[j + n] += result[j];
                    }
                }
            }
            result = next;
        }
        return result;
    }
}