package cote_preparation;

import java.io.*;

public class P7682 {
    
    static final int[][] LINES = {
            {0,1,2},{3,4,5},{6,7,8},
            {0,3,6},{1,4,7},{2,5,8},
            {0,4,8},{2,4,6}
    };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s;
        while (!(s = br.readLine()).equals("end")) {
            System.out.println(isValid(s) ? "valid" : "invalid");
        }
    }

    static boolean isValid(String s) {
        char[] b = s.toCharArray();
        int x = 0, o = 0;
        for (char c : b) {
            if (c == 'X') x++;
            else if (c == 'O') o++;
        }

        if (!(x == o || x == o + 1)) return false;

        boolean xWin = win(b, 'X');
        boolean oWin = win(b, 'O');

        if (xWin && oWin) return false;

        if (xWin) return x == o + 1;
        if (oWin) return x == o;
        return x + o == 9;
    }

    static boolean win(char[] b, char p) {
        for (int[] line : LINES) {
            if (b[line[0]] == p && b[line[1]] == p && b[line[2]] == p) return true;
        }
        return false;
    }
}
