import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] chs = br.readLine().toUpperCase().toCharArray();

        int[] cnts = new int[26];
        for (char ch : chs) {
            cnts[ch - 'A']++;
        }

        int max = 0;
        for (int c : cnts)
            max = Math.max(max, c);

        int cnt = 0;
        char target = 'A';
        for (int i = 0; i < 26; i++) {
            if (cnts[i] == max) {
                cnt++;
                target = (char) ('A' + i);
            }
        }

        System.out.println(cnt > 1 ? "?" : target);
    }
}
