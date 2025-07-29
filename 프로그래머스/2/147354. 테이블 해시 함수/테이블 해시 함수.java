import java.util.*;
/**
100만개 정렬 -> 최악의 경우 O(n^2) -> 100만 * 100만...
근데 평균적으로 O(nlogn) -> 100만 * 20.. -> 할만한데?
*/
class Solution {
    public int solution(int[][] data, int col, int row_begin, int row_end) {
        Arrays.sort(data, Comparator.comparing((int[] d) -> d[col - 1])
                   .thenComparing(Comparator.comparingInt((int[] d) -> d[0]).reversed()));
                    
        
        int answer = calc(data[row_begin - 1], row_begin);
        for (int i = row_begin + 1; i <= row_end; i++) {
            answer ^= calc(data[i - 1], i);
        }
        
        return answer;
    }
    
    private int calc(int[] arr, int i) {
        int result = 0;
        for (int n : arr) {
            result += (n % i);
        }
        return result;
    }
}