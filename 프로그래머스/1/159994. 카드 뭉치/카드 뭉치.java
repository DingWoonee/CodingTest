class Solution {
    public String solution(String[] cards1, String[] cards2, String[] goal) {
        // cards1과 cards2는 서로 다른 단어만 존재
        int idx1 = 0, idx2 = 0, idxG = 0;
        
        while (idxG < goal.length) {
            String goalStr = goal[idxG];
            if (idx1 < cards1.length && goalStr.equals(cards1[idx1])) {
                idxG++;
                idx1++;
            } else if (idx2 < cards2.length && goalStr.equals(cards2[idx2])) {
                idxG++;
                idx2++;
            } else {
                return "No";
            }
        }
        
        return "Yes";
    }
}