class Solution {
    public int solution(int n, int k) {
        int answer = 0;
        
        String converted = convert(n, k);
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
        if (num < 2) {
            return false;
        }
        if (num == 2) {
            return true;
        }
        if (num % 2 == 0) {
            return false;
        }
        for (long i = 3; i * i <= num; i += 2) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
    
    private String convert(int n, int k) {
        String result = "";
        while (n > 0) {
            result = n % k + result;
            n = n / k;
        }
        return result;
    }
}