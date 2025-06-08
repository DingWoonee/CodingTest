import java.util.*;

class Solution {
    public int solution(int n, int w, int num) {
        // 완전한 줄 수?
        int prefectRowNum = n / w;
        // 아래에서 몇 번째 줄?
        int floor = (num - 1) / w + 1;
        // 현재 몇 번재 열?
        int col = num % w == 0 ? w : num % w;
        if (floor % 2 == 0) {
            col = w + 1 - col;
        }
        // 해당 열의 맨 윗 줄에 택배가 있는지?
        int isTopOn = 0;
        // 맨 윗 줄 택배 개수?
        int topCount = n % w;
        // 맨 위에 택배가 있다면
        if (topCount != 0) {
            if (prefectRowNum % 2 == 0) { // 방향 - 우
                if (topCount >= col) {
                    isTopOn++;
                }
            } else { // 방향 - 좌
                if (col > w - topCount) {
                    isTopOn++;
                }
            }
        }
        
        return prefectRowNum - floor + 1 + isTopOn;
    }
}