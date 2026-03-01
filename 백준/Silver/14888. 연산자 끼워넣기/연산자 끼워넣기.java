import java.util.*;
import java.io.*;

public class Main {

    private static int[] nums;
    private static int[] ops;
    private static int N;

    private static int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        N = Integer.parseInt(br.readLine());
        nums = new int[N];
        ops = new int[4];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) nums[i] = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) ops[i] = Integer.parseInt(st.nextToken());

        dfs(1, nums[0]);

        System.out.println(max);
        System.out.println(min);
    }

    private static void dfs(int depth, int result) {
        if (depth == N) {
            min = Math.min(min, result);
            max = Math.max(max, result);
            return;
        }

        int next = nums[depth];
        if (ops[0] > 0) {
            ops[0]--;
            dfs(depth + 1, result + next);
            ops[0]++;
        }
        if (ops[1] > 0) {
            ops[1]--;
            dfs(depth + 1, result - next);
            ops[1]++;
        }
        if (ops[2] > 0) {
            ops[2]--;
            dfs(depth + 1, result * next);
            ops[2]++;
        }
        if (ops[3] > 0) {
            ops[3]--;
            dfs(depth + 1, result < 0 ? -((-result) / next) : result / next);
            ops[3]++;
        }
    }
}
