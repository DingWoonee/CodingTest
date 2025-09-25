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
        for (int i = 0; i < privacies.length; i++) {
            String[] split = privacies[i].split(" ");
            if (addMonth(split[0], map.get(split[1])).compareTo(today) < 0) {
                answer.add(i + 1);
            }
        }
        
        int[] ans = new int[answer.size()];
        for (int i = 0; i < answer.size(); i++) {
            ans[i] = answer.get(i);
        }
        return ans;
    }
    
    private String addMonth(String date, int add) {
        String[] split = date.split("\\.");
        int year = Integer.parseInt(split[0]);
        int month = Integer.parseInt(split[1]);
        int day = Integer.parseInt(split[2]);
        month += add;
        if (month > 12) {
            if (month % 12 == 0) {
                year += (month / 12 - 1);
                month = 12;
            } else {
                year += (month / 12);
                month %= 12;
            }
        }
        day--;
        if (day == 0) {
            month--;
            if (month == 0) {
                year--;
                month = 12;
            }
            day = 28;
        }
        
        return String.format("%d.%02d.%02d", year, month, day);
    }
}