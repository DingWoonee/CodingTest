class Solution {
    public int solution(int n, int k) {
        int answer = 0;
        
        String converted = Integer.toString(n, k);
        String[] split = converted.split("0+");
        
        for (String str : split) {
            if (str.isEmpty()) {
                continue;
            }
            long num = Long.parseLong(str);
            if (isPrime(num)) {
                answer++;
            }
        }
        
        return answer;
    }
    
    private boolean isPrime(long num) {
        if (num == 1) {
            return false;
        }
        for (long i = 2; i * i <= num; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}