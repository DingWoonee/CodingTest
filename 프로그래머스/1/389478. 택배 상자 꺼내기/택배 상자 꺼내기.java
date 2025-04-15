class Solution {
    public int solution(int n, int w, int num) {
        // 완전한 줄 수
        int completeRowNum = n / w;
        // 타겟 줄 위치
        int targetRowNum = (num - 1) / w + 1;
        // 맨 윗 줄 블럭 수
        int topNum = n % w;
        // 타겟의 열
        int targetColumn 
            = ((num - 1) / w) % 2 == 0 ? (num % w == 0 ? w : num % w) : w + 1 - (num % w == 0 ? w : num % w);
        // 결과
        int answer = completeRowNum - targetRowNum;
        if (answer <= 0)
            return 1;
        else if (topNum == 0)
            return answer + 1;
        // 맨 윗 줄 거를 꺼내야 하는지
        if (completeRowNum % 2 == 0) { // 맨 윗 줄이 왼쪽에서 시작
            if (topNum >= targetColumn)
                answer++;
        } else { // 맨 윗 줄이 오른쪽에서 시작
            if (targetColumn + topNum > w)
                answer++;
        }
        
        return answer + 1;
    }
}