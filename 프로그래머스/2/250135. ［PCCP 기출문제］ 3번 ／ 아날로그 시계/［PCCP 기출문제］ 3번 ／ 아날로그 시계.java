class Solution {
    /**
    시침이 움직이는 속도: 1/120도 / 1s
    분침이 움직이는 속도: 1/10도 / 1s
    초침이 움직이는 속도: 6도 / 1s
    
    시침의 위치 = (전체 초 /나누기 120) % 360
    분침의 위치 = (전체 초 /나누기 10) % 360
    초침의 위치 = (전체 초 * 6) % 360
    
    각 시분에
    시침 == 초침 -> 0분0초일 때 시침의 각도 + 현재 분 / 2 + 현재 초 / 120 == 현재 초 * 6
                -> 120 * 0분0초일 때 시침의 각도 + 현재 분 * 60 + 현재 초 = 현재 초 * 720
                -> 현재 초 = (120 * 0분0초일 때 시침의 각도 + 현재 분 * 60) / 719
    분침 == 초침 -> 0초일 때 분침의 각도 + 현재 초 / 10 == 현재 초 * 6
                -> 10 * 0초일 때 분침의 각도 + 현재 초 == 현재 초 * 60
                -> 현재 초 = (10 * 0초일 때 분침의 각도) / 59
    * 719 * 59
    중복 제거
    */
    public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
        int answer = 0;
        
        int crossHS = (120 * 30 * (h1 % 12) + m1 * 60) * 59;
        int crossMS = (m1 * 60) * 719;
        // 시 분이 같은 경우
        if (h1 == h2 && m1 == m2) {
            if (s1 * 719 * 59 <= crossHS && s2 * 719 * 59 > crossHS) {
                answer++;
            }
            if (s1 * 719 * 59 <= crossMS && s2 * 719 * 59 > crossMS) {
                if (crossHS != crossMS) {
                    answer++;
                }
            }
            return answer;
        }
        // 시작 m        
        if (s1 * 719 * 59 <= crossHS && 60 * 719 * 59 > crossHS) {
            answer++;
        }
        if (s1 * 719 * 59 <= crossMS && 60 * 719 * 59 > crossMS) {
            if (crossHS != crossMS) {
                answer++;
            }
        }
        // 중간 m
        final int MAX_S = 60 * 59 * 719;
        for (int h = h1; h <= h2; h++) {
            for (int m = (h == h1 ? m1 + 1 : 0); m < (h == h2 ? m2 : 60); m++) {
                crossHS = (120 * 30 * (h % 12) + m * 60) * 59;
                crossMS = (60 * m) * 719;
                if (crossHS < MAX_S) {
                    answer++;
                }
                if (crossMS < MAX_S) {
                    if (crossMS != crossHS) {
                        answer++;
                    }
                }
            }
        }
        // 끝 m
        crossHS = (120 * 30 * (h2 % 12) + m2 * 60) * 59;
        crossMS = (10 * m2 * 6) * 719;
        if (s2 * 719 * 59 >= crossHS) {
            answer++;
        }
        if (s2 * 719 * 59 >= crossMS) {
            if (crossHS != crossMS) {
                answer++;
            }
        }
        
        return answer;
    }
}