/*
2 ~ 9진법
식 100개
연산자: +, -
피연산자: 0~99
결과값: 0~999 (결과가 X인 수식이 하나 이상 등장)
-----------------------
[풀이 전략]
1. 전체 수식에서 X가 안들어간 수식만 모아서 진법 후보를 가려야 함.
*/
import java.util.*;

class Solution {
    public String[] solution(String[] expressions) {
        List<Integer> perfects = new ArrayList<>();
        List<Integer> bads = new ArrayList<>();
        int max = 1;
        for (int i = 0; i < expressions.length; i++) {
            String exp = expressions[i];
            for (int j = 0; j < exp.length(); j++) {
                if (exp.charAt(j) >= '0' && exp.charAt(j) <= '9') {
                    max = Math.max(max, exp.charAt(j) - '0');
                }
            }
            String[] split = exp.split(" ");
            if (split[4].equals("X")) {
                bads.add(i);
            } else {
                perfects.add(i);
            }
        }
        // 진법 알아내기
        Set<Integer> target = new HashSet<>();
        for (int i = max + 1; i < 10; i++) {
            target.add(i);
        }
        System.out.println(target);
        for (int idx : perfects) {
            List<Integer> remove = new ArrayList<>();
            for (int t : target) {
                if (!possible(expressions[idx], t)) {
                    remove.add(t);
                }
            }
            for (int r : remove) {
                target.remove(r);
            }
        }
        System.out.println(target);
        
        String[] answer = new String[bads.size()];
        // 답 내기
        for (int i = 0; i < bads.size(); i++) {
            String exp = expressions[bads.get(i)];
            answer[i] = recreate(exp, target);
        }
        
        return answer;
    }
    
    private String recreate(String exp, Set<Integer> radixs) {
        Set<String> result = new HashSet<>();
        for (int radix : radixs) {
            String[] split = exp.split(" ");
            int a = Integer.parseInt(split[0], radix);
            int b = Integer.parseInt(split[2], radix);
            if (split[1].equals("+")) {
                result.add(Integer.toString(a + b, radix));
            } else {
                result.add(Integer.toString(a - b, radix));
            }
        }
        String pre = exp.substring(0, exp.length() - 1);
        if (result.size() > 1) {
            return pre + "?";
        } else {
            return pre + (new ArrayList<>(result)).get(0);
        }
    }
    
    private boolean possible(String exp, int radix) {
        String[] split = exp.split(" ");
        int a = Integer.parseInt(split[0], radix);
        int b = Integer.parseInt(split[2], radix);
        int c = Integer.parseInt(split[4], radix);
        if (split[1].equals("+")) {
            return a + b == c;
        } else {
            return a - b == c;
        }
    }
}