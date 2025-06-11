import java.util.*;

class Solution {
    public String[] solution(String[] players, String[] callings) {
        // Map<Integer, String> ranks = new HashMap<>();
        Map<String, Integer> names = new HashMap<>();
        
        for (int i = 0; i < players.length; i++) {
            // ranks.put(i + 1, players[i]);
            names.put(players[i], i + 1);
        }
        
        for (String name : callings) {
            int rank = names.get(name);
            // String subName = ranks.get(rank - 1);
            String subName = players[rank - 2];
            // ranks.put(rank - 1, name);
            // ranks.put(rank, subName);
            players[rank - 2] = name;
            players[rank - 1] = subName;
            names.put(name, rank - 1);
            names.put(subName, rank);
        }
        
        // return ranks.values().stream()
        //     .toArray(String[]::new);
        return players;
    }
}