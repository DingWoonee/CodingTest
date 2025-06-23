import java.util.*;

class Solution {
    /** 
    문자열을 26진수로 생각
    */
    public String solution(long n, String[] bans) {
        int preCount = 0;
        List<Long> postList = new ArrayList<>();
        for (String ban : bans) {
            long l = toLong(ban);
            if (n >= l) {
                preCount++;
            } else {
                postList.add(l);
            }
        }
        postList.sort(Comparator.comparing(e -> e));
        
        long answer = n + preCount;
        for (long l : postList) {
            if (answer >= l) {
                answer++;
            } else {
                break;
            }
        }
        
        return toStr(answer);
    }
    
    private long toLong(String str) {
        long sum = 0;
        int length = str.length();
        for (int i = 0; i < length; i++) {
            sum += Math.pow(26, length - i - 1) * (str.charAt(i) - 96);
        }
        return sum;
    }
    
    private String toStr(long l) {
        List<Integer> list = new ArrayList<>();
        while (l > 0) {
            long rest = l % 26L;
            l = l / 26L;
            if (rest == 0) {
                rest = 26;
                l--;
            }
            list.add((int) rest);
        }
        String str = "";
        for (int i : list) {
            str = ((char) (i + 96)) + str;
        }
        return str;
    }
}