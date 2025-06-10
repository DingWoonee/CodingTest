import java.util.*;

class Solution {
    
    public int solution(int[] bandage, int health, int[][] attacks) {
        int curHealth = health;
        int preTime = 0;
        
        for (int[] attack : attacks) {
            int curTime = attack[0];
            int timeDiff = curTime - preTime;
            
            int recoveryCount = (timeDiff - 1) / bandage[0];
            curHealth = Math.min(health, curHealth + recoveryCount * bandage[2] + (timeDiff - 1) * bandage[1]);
            
            curHealth -= attack[1];
            
            if (curHealth <= 0) {
                return -1;
            }
            
            preTime = curTime;
        }
        
        return curHealth;
    }
}