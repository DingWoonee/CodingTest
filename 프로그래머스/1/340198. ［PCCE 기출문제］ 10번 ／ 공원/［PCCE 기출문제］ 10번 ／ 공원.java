import java.util.*;

class Solution {
    public int solution(int[] mats, String[][] park) {
        int max = 0;
        int[][] parkInt = new int[park.length][park[0].length];
        
        for (int i = 0; i < park.length; i++) {
            for (int j = 0; j < park[0].length; j++) {
                if ("-1".equals(park[i][j])) {
                    int up = (i == 0 ? 0 : parkInt[i - 1][j]);
                    int left = (j == 0 ? 0 : parkInt[i][j - 1]);
                    int leftUp = (i == 0 || j == 0 ? 0 : parkInt[i - 1][j - 1]);
                    parkInt[i][j] = Math.min(Math.min(up, left), leftUp) + 1;
                    max = Math.max(max, parkInt[i][j]);
                }
            }
        }
        
        int answer = -1;
        for (int m : mats) {
            if (m <= max && m > answer) {
                answer = m;
            }
        }
        return answer;
    }
}