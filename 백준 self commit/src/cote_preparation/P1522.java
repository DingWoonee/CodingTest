import java.util.*;
import java.io.*;
/*
b를 한곳으로 모아야 함
*/
public class P1522 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] chs = br.readLine().toCharArray();

        int cntA = 0;
        for (char ch : chs) {
            if (ch == 'a') cntA++;
        }

        int cntB = 0;
        for (int i = 0; i < cntA; i++) {
            if (chs[i] == 'b') cntB++;
        }

        int answer = cntB;
        for (int i = 0; i < chs.length; i++) {
            if (chs[i] == 'b') cntB--;
            if (chs[(i + cntA) % chs.length] == 'b') cntB++;
            answer = Math.min(answer, cntB);
        }

        System.out.println(answer);
    }
}