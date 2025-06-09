import java.util.*;

class Solution {
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        int videoLenInt = parse(video_len);
        int posInt = parse(pos);
        int opStartInt = parse(op_start);
        int opEndInt = parse(op_end);
        
        for (String command : commands) {
            // 건너뛰기
            if (posInt >= opStartInt && posInt <= opEndInt) {
                posInt = opEndInt;
            }
            if ("prev".equals(command)) { // prev
                posInt = prev(posInt);
            } else { // next
                posInt = next(posInt, videoLenInt);
            }
        }
        // 건너뛰기
        if (posInt >= opStartInt && posInt <= opEndInt) {
            posInt = opEndInt;
        }
        
        return String.format("%02d", posInt / 100) + ":" + String.format("%02d", posInt % 100);
    }
    
    private int parse(String time) {
        return Integer.parseInt(time.split(":")[0]) * 100 + Integer.parseInt(time.split(":")[1]);
    }
    
    private int prev(int pos) {
        int minute = pos / 100;
        int second = pos % 100;
        if (minute == 0 && second <= 10) {
            return 0;
        }
        if (second < 10) {
            return (minute - 1) * 100 + (second + 50);
        }
        return pos - 10;
    }
    
    private int next(int pos, int videoLen) {
        int minute = pos / 100;
        int second = pos % 100;
        if (second >= 50) {
            minute++;
            second -= 50;
        } else {
            second += 10;
        }
        int result = minute * 100 + second;
        return Math.min(videoLen, result);
    }
    
}