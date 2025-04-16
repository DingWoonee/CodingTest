// for문을 돌면서 command를 수행
// command를 수행하기 전에 먼저 '오프닝 건너뛰기' 판단

class Solution {
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        // 시간 초 단위로 변환
        int videoLen = parsingTime(video_len);
        int curTime = parsingTime(pos);
        int opStart = parsingTime(op_start);
        int opEnd = parsingTime(op_end);
        // 명령 수행
        for (int i = 0; i < commands.length; i++) {
            curTime = opJump(curTime, opStart, opEnd);
            if (commands[i].equals("next"))
                curTime = next(curTime, videoLen);
            else
                curTime = prev(curTime);
        }
        curTime = opJump(curTime, opStart, opEnd);
        
        // 이거 분, 초 두 자리로 나오게 해야됨.
        return String.format("%02d", curTime / 60) + ":" + String.format("%02d", curTime % 60);
    }
    
    private int parsingTime(String time) {
        String[] timeArr = time.split(":");
        return Integer.parseInt(timeArr[0]) * 60 + Integer.parseInt(timeArr[1]);
    }
    
    private int next(int curTime, int endTime) {
        curTime += 10;
        return curTime > endTime ? endTime : curTime;
    }
    
    private int prev(int curTime) {
        curTime -= 10;
        return curTime > 0 ? curTime : 0;
    }
    
    private int opJump(int curTime, int opStart, int opEnd) {
        return curTime >= opStart && curTime <= opEnd ?
            opEnd : curTime;
    }
}