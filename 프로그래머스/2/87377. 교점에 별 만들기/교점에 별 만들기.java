import java.util.*;

class Solution {
    public String[] solution(int[][] line) {
        // 1. 정수 교점 찾기
        List<long[]> stars = new ArrayList<>();
        for (int i = 0; i < line.length; i++) {
            for (int j = i + 1; j < line.length; j++) {
                int[] l1 = line[i];
                int[] l2 = line[j];
                long[] res = findInterPointNullable(l1[0], l1[1], l1[2], l2[0], l2[1], l2[2]);
                if (res != null) stars.add(res);
            }
        }
        
        // 2. x, y 범위 찾기
        long INF = Long.MAX_VALUE - 1;
        long maxX = -INF, minX = INF, maxY = -INF, minY = INF;
        for (long[] s : stars) {
            maxX = Math.max(maxX, s[0]);
            minX = Math.min(minX, s[0]);
            maxY = Math.max(maxY, s[1]);
            minY = Math.min(minY, s[1]);
        }
        
        // 3. 답 만들기
        char[][] chs = new char[(int) (maxY - minY) + 1][(int) (maxX - minX) + 1];
        for (int i = 0; i < chs.length; i++) Arrays.fill(chs[i], '.');
        for (long[] s : stars) {
            chs[(int) (maxY - s[1])][(int) (s[0] - minX)] = '*';
        }
        
        String[] result = new String[chs.length];
        for (int i = 0; i < chs.length; i++) {
            StringBuilder sb = new StringBuilder();
            for (char c : chs[i]) sb.append(c);
            result[i] = sb.toString();
        }
        return result;
    }
    
    private long[] findInterPointNullable(int A1, int B1, int C1, int A2, int B2, int C2) {
        long crit = (long) A1 * B2 - (long) B1 * A2;
        // 평행
        if (crit == 0) return null;
        
        long x = (long) B2 * -C1 + (long) (-B1) * -C2;
        long y = (long) (-A2) * (long) -C1 + A1 * -C2;
        
        if (x % crit != 0 || y % crit != 0) return null;
        
        return new long[]{x / crit, y / crit};
    }
}