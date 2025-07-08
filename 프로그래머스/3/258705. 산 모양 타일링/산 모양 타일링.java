/**
처음 4가지
그 다음 (4 - 1) * 4 = 12
그 다음 (앞 - 1) * 3 = 33
그 다음 (앞 - 1) * 4 = 128

처음 : 4
그 다음 : 앞 * (3) + ((3)) = 15
그 다음 : 앞 * (2) + 앞앞 * ((2)) + ( ((3)) ) = 41
그 다음 : 앞 * (3) + 앞앞 * ((1)) + ( 앞앞 * ((2)) + (((3))) ) = 149
*/
class Solution {
    public int solution(int n, int[] tops) {
        int[] record = new int[n];
        int[] record2 = new int[n];
        
        // n = 1
        record[0] = tops[0] + 3;
        if (n == 1) {
            return record[0];
        }
        // n == 2
        record2[1] = tops[0] + 2;
        record[1] = record[0] * (tops[1] + 2) + tops[0] + 2;
        if (n == 2) {
            return record[1];
        }
        
        for (int i = 2; i <  tops.length; i++) {
            record2[i] = (record[i - 2] * (tops[i - 1] + 1) + record2[i - 1]) % 10007;
            record[i] = (record[i - 1] * (tops[i] + 2) + record2[i]) % 10007;
        }
        
        // for (int re : record) {
        //     System.out.println(re);
        // }
        
        return record[n - 1] % 10007;
    }
}