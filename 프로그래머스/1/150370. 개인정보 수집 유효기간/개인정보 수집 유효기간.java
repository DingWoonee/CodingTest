/*
- 개인정보 1~n번 n개
- 각 약관 유효기간 존재
- 모든 달은 28일까지
----------------------
[깨달음]
- 문자열 간 비교방법
- split을 쓸때 정규표현식 주의
- String.format 사용법
*/
import java.util.*;

class Solution {
    public int[] solution(String today, String[] terms, String[] privacies) {
        List<Integer> answer = new ArrayList<>();
        // terms 전처리
        Map<String, Integer> map = new HashMap<>();
        for (String term : terms) {
            map.put(Character.toString(term.charAt(0)), Integer.parseInt(term.split(" ")[1]));
        }
        // 검사
        int todayInt = toDays(today);
        for (int i = 0; i < privacies.length; i++) {
            String[] split = privacies[i].split(" ");
            int curInt = toDays(split[0]) + map.get(split[1]) * 28 - 1;
            if (todayInt > curInt) {
                answer.add(i + 1);
            }
        }
        
        int[] ans = new int[answer.size()];
        for (int i = 0; i < answer.size(); i++) {
            ans[i] = answer.get(i);
        }
        return ans;
    }
    
    private int toDays(String date) {
        String[] split = date.split("\\.");
        return Integer.parseInt(split[0]) * 12 * 28
            + Integer.parseInt(split[1]) * 28
            + Integer.parseInt(split[2]);
    }
}