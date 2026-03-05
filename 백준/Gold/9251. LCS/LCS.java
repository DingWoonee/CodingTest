import java.util.*;
import java.io.*;
/**
 * CAPCAK
 * ACAYKP
 * dp[i][j] = 첫 문자열의 i번째까지 보고, 두 번째 문자열의 j번째까지 봤을 때의 최대 길이
 */
public class Main {

    private static char[] ch1;
    private static char[] ch2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ch1 = br.readLine().toCharArray();
        ch2 = br.readLine().toCharArray();

        int[][] dp = new int[ch1.length + 1][ch2.length + 1];
        for (int i = 0; i < ch1.length; i++) {
            for (int j = 0; j < ch2.length; j++) {
                if (ch1[i] == ch2[j]) {
                    dp[i + 1][j + 1] = dp[i][j] + 1;
                } else {
                    dp[i + 1][j + 1] = Math.max(dp[i + 1][j], dp[i][j + 1]);
                }
            }
        }

        System.out.println(dp[ch1.length][ch2.length]);
    }
}
