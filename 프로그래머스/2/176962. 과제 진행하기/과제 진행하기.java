import java.util.*;
import java.util.stream.*;

/**
일단 정렬

다음 과제까지 완료 가능 -> 완료 -> 시간 중간에 있으면 스택의 과제하기
다음 과제까지 완료 불가능 -> 남은 시간 차감하고 스택에 넣기
*/
class Solution {
    public String[] solution(String[][] plans) {
        // 전처리
        List<Assignment> assigns = Arrays.stream(plans)
                                    .map(Assignment::new)
                                    .sorted(Comparator.comparingInt(a -> a.start))
                                    .collect(Collectors.toList());
        // 연산
        List<String> answer = new ArrayList<>();
        
        Deque<Assignment> stack = new ArrayDeque<>();
        for (int i = 0; i < assigns.size() - 1; i++) {
            Assignment ass = assigns.get(i);
            
            Assignment nextAss = assigns.get(i + 1);
            int elapsed = nextAss.start - ass.start;
            int curTime = ass.start + ass.rest;
            ass.rest -= elapsed;
            
            if (ass.rest > 0) {
                stack.push(ass);
            } else {
                answer.add(ass.name);
                
                while (!stack.isEmpty()) {
                    Assignment preAss = stack.peek();
                    if (preAss.rest > nextAss.start - curTime) {
                        preAss.rest -= (nextAss.start - curTime);
                        break;
                    } else if (preAss.rest == nextAss.start - curTime) {
                        answer.add(preAss.name);
                        stack.pop();
                        break;
                    } else {
                        answer.add(preAss.name);
                        stack.pop();
                        curTime += preAss.rest;
                    }
                }
            }
        }
        
        // 나머지
        answer.add(assigns.get(assigns.size() - 1).name);
        while (!stack.isEmpty()) {
            answer.add(stack.pop().name);
        }
        
        return answer.toArray(new String[answer.size()]);
    }
    
    class Assignment {
        String name;
        int start;
        int rest;
        
        Assignment(String[] arr) {
            this.name = arr[0];
            this.start = Integer.valueOf(arr[1].split(":")[0]) * 60 + Integer.valueOf(arr[1].split(":")[1]);
            this.rest = Integer.valueOf(arr[2]);
        }
        
        @Override
        public String toString() {
            return name + " " + start + " " + rest;
        }
    }
}