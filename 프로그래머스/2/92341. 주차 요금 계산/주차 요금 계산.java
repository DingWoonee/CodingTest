import java.util.*;

class Solution {
    public int[] solution(int[] fees, String[] records) {
        Map<String, Integer> map = new HashMap<>();
        Map<String, Integer> ans = new HashMap<>();
        
        for (String r : records) {
            String[] split = r.split(" ");
            int time = timeToInt(split[0]);
            if (split[2].equals("IN")) {
                map.put(split[1], time);
            } else { // OUT
                int passed = time - map.get(split[1]);
                ans.put(split[1], ans.getOrDefault(split[1], 0) + passed);
                map.remove(split[1]);
            }
        }
        int end = timeToInt("23:59");
        for (String key : map.keySet()) {
            int passed = end - map.get(key);
            ans.put(key, ans.getOrDefault(key, 0) + passed);
        }
        
        List<String> keys = new ArrayList<>(ans.keySet());
        keys.sort((s1, s2) -> s1.compareTo(s2));
        int[] result = new int[keys.size()];
        for (int i = 0; i < keys.size(); i++) {
            int restM = ans.get(keys.get(i)) - fees[0];
            int fee = fees[1] 
                + (restM <= 0 ? 0 : ((restM - 1) / fees[2] + 1) * fees[3]);
            result[i] = fee;
        }
        return result;
    }
    
    private int timeToInt(String time) {
        String[] split = time.split(":");
        return Integer.parseInt(split[0]) * 60 + Integer.parseInt(split[1]);
    }
}