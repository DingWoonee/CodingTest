import java.io.*;

public class Main {

    private static final int DIVISOR = 1_000_000_007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        long N = Long.parseLong(br.readLine());

        System.out.println(pivo(N - 1)[0]);
    }

    private static long[] pivo(long N) {
        if (N == 0) return new long[]{1, 0, 0, 1};
        if (N == 1) return new long[]{1, 1, 1, 0};

        long[] arr = pivo(N / 2);
        long[] mul = mul(arr, arr);
        if (N % 2 == 1) mul = mul(mul, pivo(1));
        return mul;
    }
    
    private static long[] mul(long[] a1, long[] a2) {
        long n11 = (a1[0] * a2[0] + a1[1] * a2[2]) % DIVISOR;
        long n12 = (a1[0] * a2[1] + a1[1] * a2[3]) % DIVISOR;
        long n21 = (a1[2] * a2[0] + a1[3] * a2[2]) % DIVISOR;
        long n22 = (a1[2] * a2[1] + a1[3] * a2[3]) % DIVISOR;
        return new long[]{n11, n12, n21, n22};
    }
}
