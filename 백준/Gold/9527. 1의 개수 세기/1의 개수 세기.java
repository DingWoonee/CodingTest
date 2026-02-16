import java.util.*;
import java.io.*;
/**
 * 01234567890123456
 * 0101010101010101 -> 1 / 2씩
 * 0011001100110011 -> 2 / 4씩
 * 0000111100001111 -> 4 / 8씩
 * 0000000011111111 -> 8 / 16씩
 * 00000000000000001 -> 16 / 32씩
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        long A = Long.parseLong(st.nextToken());
        long B = Long.parseLong(st.nextToken());

        System.out.println(calcCnt(B) - calcCnt(A - 1));
    }
    
    private static long calcCnt(long n) {
        long cnt = 0;

        for (long level = 1; level <= n; level <<= 1) {
            cnt += ((n + 1) / (level * 2)) * level;
            cnt += Math.max(0, (n + 1) % (level * 2) - level);
        }

        return cnt;
    }
}
