import java.util.*;
import java.util.stream.*;

class Solution {
    public int[][] solution(int[][] data, String ext, int val_ext, String sort_by) {
        int extIndex = getIndex(ext);
        int sortIndex = getIndex(sort_by);
        
        List<int[]> result = Arrays.stream(data)
            .filter(d -> d[extIndex] < val_ext)
            .sorted(Comparator.comparing(d -> d[sortIndex]))
            .collect(Collectors.toList());
        int[][] answer = new int[result.size()][4];
        for (int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i);
        }
        return answer;
    }
    
    private int getIndex(String ext) {
        if ("code".equals(ext))
            return 0;
        if ("date".equals(ext))
            return 1;
        if ("maximum".equals(ext))
            return 2;
        return 3;
    }
}