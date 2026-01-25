package cote_preparation;

import java.io.*;
/**
 * 전구 10만개
 */
public class P2138 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        char[] chs1 = br.readLine().toCharArray();
        char[] chs2 = br.readLine().toCharArray();

        int INF = 1_000_000;
        int answer = INF;

        // 처음을 킴
        char[] start = new char[N];
        for (int i = 0; i < N; i++) start[i] = chs1[i];
        reverse(start, 0);
        int cnt = 1;
        for (int i = 1; i < N; i++) {
            if (start[i - 1] != chs2[i - 1]) {
                reverse(start, i);
                cnt++;
            }
        }

        if (start[N - 1] == chs2[N - 1]) {
            answer = Math.min(answer, cnt);
        }

        // 처음을 끔
        start = new char[N];
        for (int i = 0; i < N; i++) start[i] = chs1[i];
        cnt = 0;
        for (int i = 1; i < N; i++) {
            if (start[i - 1] != chs2[i - 1]) {
                reverse(start, i);
                cnt++;
            }
        }

        if (start[N - 1] == chs2[N - 1]) {
            answer = Math.min(answer, cnt);
        }

        System.out.println(answer == INF ? -1 : answer);
    }
    
    private static void reverse(char[] chs, int i) {
        if (i - 1 >= 0 && i - 1 < chs.length) {
            chs[i - 1] = chs[i - 1] == '0' ? '1' : '0';
        }
        if (i >= 0 && i < chs.length) {
            chs[i] = chs[i] == '0' ? '1' : '0';
        }
        if (i + 1 >= 0 && i + 1 < chs.length) {
            chs[i + 1] = chs[i + 1] == '0' ? '1' : '0';
        }
    }
}
