class Solution {
    public int solution(int[] mats, String[][] park) {
        int maxSize = 0;
        
        for (int i = 0; i < park.length; i++) {
            for (int j = 0; j < park[0].length; j++) {
                maxSize = Math.max(maxSize, getMaxSize(park, i, j));
            }
        }
        
        int answer = -1;
        for (int mat : mats) {
            if (mat <= maxSize) {
                answer = Math.max(answer, mat);
            }
        }
        
        return answer;
    }
    
    // 좌표 기준으로 오른쪽 아래 영역에서 가질 수 있는 가장 큰 영역 반환
    private int getMaxSize(String[][] park, int i, int j) {
        int answer = 0;
        int parkWidth = park[0].length;
        int parkHeight = park.length;
        while (true) {
            // 세로
            for (int k = 0; k < answer + 1; k++) {
                if (i + k >= parkHeight || j + answer >= parkWidth) {
                    return answer;
                }
                if (!park[i + k][j + answer].equals("-1")) {
                    return answer;
                }
            }
            // 가로
            for (int k = 0; k < answer + 1; k++) {
                if (j + k >= parkWidth || i + answer >= parkHeight) {
                    return answer;
                }
                if (!park[i + answer][j + k].equals("-1")) {
                    return answer;
                }
            }
            answer++;
        }
    }
}