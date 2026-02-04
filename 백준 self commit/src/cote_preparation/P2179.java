package cote_preparation;

import java.util.*;
import java.io.*;

public class P2179 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        
        Map<String, StrIdx> map = new HashMap<>();
        int max = 0, minIdx = Integer.MAX_VALUE;
        String str1 = null, str2 = null;

        for (int i = 0; i < N; i++) {
            String str = br.readLine();

            for (int j = 1; j <= str.length(); j++) {
                String sub = str.substring(0, j);
                if (map.containsKey(sub) 
                    && (j > max || (j == max && map.get(sub).idx < minIdx))
                    && !map.get(sub).equals(str)
                ) {
                    minIdx = map.get(sub).idx;
                    max = j;
                    str1 = map.get(sub).str;
                    str2 = str;
                }
                if (!map.containsKey(sub)) {
                    map.put(sub, new StrIdx(str, i));
                }
            }
        }

        System.out.println(str1);
        System.out.println(str2);
    }

    public static class StrIdx {
        public String str;
        public int idx;

        public StrIdx(String str, int idx) {
            this.str = str;
            this.idx = idx;
        }
    }
}
