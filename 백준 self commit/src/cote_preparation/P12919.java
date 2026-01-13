package cote_preparation;

import java.io.*;

public class P12919 {

    private static boolean possible = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String S = br.readLine();
        String T = br.readLine();

        StringBuilder sb = new StringBuilder(S);
        
        backtrack(sb, T);

        System.out.println(possible ? 1 : 0);
    }

    private static void backtrack(StringBuilder sb, String s2) {
        int len1 = sb.length();
        if (len1 == s2.length()) {
            if (sb.toString().equals(s2)) possible = true;
            return;
        }
        if (!possible(sb, s2)) {
            return;
        }

        StringBuilder newSb = new StringBuilder(sb);
        newSb.append('A');
        backtrack(newSb, s2);

        newSb = new StringBuilder(sb);
        newSb.append('B');
        newSb.reverse();
        backtrack(newSb, s2);
    }

    private static boolean possible(StringBuilder sb, String s2) {
        StringBuilder newSb = new StringBuilder(sb);
        String s1 = newSb.toString();
        String s11 = newSb.reverse().toString();
        return s2.contains(s1) || s2.contains(s11);
    }
}
