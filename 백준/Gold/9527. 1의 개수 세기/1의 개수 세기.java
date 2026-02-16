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

        long sum = 0;
        long level = 1;
        while (level <= B) {
            long range = level * 2;
            long cur = 0;

            cur += ((B + 1) / range) * level;
            cur += Math.max(0, ((B + 1) % range) - level);

            cur -= (A / range) * level;
            cur -= Math.max(0, (A % range) - level);

            sum += cur;
            level *= 2;
        }

        System.out.println(sum);
    }
}
