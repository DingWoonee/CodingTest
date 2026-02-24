import java.io.*;
import java.math.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        BigInteger[] dp = new BigInteger[N + 1];
        dp[0] = new BigInteger("0");
        BigInteger multi = new BigInteger("2");
        BigInteger add = new BigInteger("1");
        for (int i = 0; i < N; i++) {
            dp[i + 1] = dp[i].multiply(multi).add(add);
        }
        System.out.println(dp[N]);
        if (N <= 20) {
            recur(N, 1, 3);
        }
    }

    private static void recur(int N, int cur, int target) {
        int temp = 3;
        for (int i = 1; i <= 3; i++) {
            if (cur != i && target != i) {
                temp = i;
                break;
            }
        }

        if (N > 1) recur(N - 1, cur, temp);
        System.out.println(cur + " " + target);
        if (N > 1) recur(N - 1, temp, target);
    }
}
