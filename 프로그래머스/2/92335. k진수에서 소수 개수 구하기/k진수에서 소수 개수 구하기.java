class Solution {
    public int solution(int n, int k) {
        String str = Integer.toString(n, k);
        String[] split = str.split("0+");
        
        int cnt = 0;
        for (String s : split) {
            if (isPrime(Long.parseLong(s))) cnt++;
        }
        
        return cnt;
    }
    
    private boolean isPrime(long n) {
        if (n == 1) return false;
        if (n == 2) return true;
        if (n % 2 == 0) return false;
        for (long i = 3; i * i <= n; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }
}