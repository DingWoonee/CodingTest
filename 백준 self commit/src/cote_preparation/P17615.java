package cote_preparation;

import java.util.*;
import java.io.*;
/*
공 개수 50만 개
R, B

R R B R R B R B B

각각 공의 개수와 각각 끝에 몇 개 있는지 구한다.
*/
public class P17615 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        char[] chs = br.readLine().toCharArray();
        
        int cntR = 0, cntB = 0;
        for (char ch : chs) {
            if (ch == 'R') cntR++;
            else cntB++;
        }
        
        int answer = Math.min(cntR, cntB);
        
        int l = 0;
        char lch = chs[l];
        while (l < N - 1 && chs[++l] == lch) {}

        if (lch == 'R') {
            answer = Math.min(answer, cntR - l);
        } else {
            answer = Math.min(answer, cntB - l);
        }

        int r = N - 1;
        char rch = chs[r];
        while (r > 0 && chs[--r] == rch) {}

        if (rch == 'R') {
            answer = Math.min(answer, cntR - (N - 1 - r));
        } else {
            answer = Math.min(answer, cntB - (N - 1 - r));
        }

        System.out.println(answer);
    }
}