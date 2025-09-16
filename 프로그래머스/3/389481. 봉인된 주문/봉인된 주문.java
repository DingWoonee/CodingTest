/*
주문 - 알파벳 소문자 11글자 이하
정렬 - 글자수 적은 순 -> 사전 순서
상황 - 중간에 몇 개가 삭제됨
목표 - 삭제된 상황에서 n번째 주문을 찾아야 함.

[풀이 전략]
a~z를 1~26으로 변환해서 풀이
*/
import java.util.*;

public class Solution {
    public String solution(long n, String[] bans) {
        Arrays.sort(bans, (a, b) -> a.length() == b.length() ? a.compareTo(b) : a.length() - b.length());
        
        for (String ban : bans) {
            if (n >= strToLong(ban)) {
                n++;
            }
        }
        
        return longToStr(n);
    }
    
    private long strToLong(String str) {
        long result = 0;
        for (int i = 0; i < str.length(); i++) {
            result += Math.pow(26, str.length() - i - 1) * (str.charAt(i) - 'a' + 1);
        }
        return result;
    }
    
    private String longToStr(long n) {
        String result = "";
        while (n > 0) {
            if (n % 26 == 0) {
                result = "z" + result;
                n = n / 26 - 1;
            } else {
                result = Character.toString((char) (n % 26 - 1) + 'a') + result;
                n /= 26;
            }
        }
        return result;
    }
}