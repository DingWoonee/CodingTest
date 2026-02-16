import java.util.*;
import java.io.*;
/**
 * 1 - 2개 / 2 - 5개 / 3 - 5개
 * 4 - 4개 / 5 - 5개 / 6 - 6개
 * 7 - 3개 / 8 - 7개 / 9 - 6개
 * 0 - 6개
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        for (int i = 0 ; i < N; i++) {
            int n = Integer.parseInt(br.readLine());
            System.out.println(findMin(n) + " " + findMax(n));
        }
    }

    private static String findMax(int n) {
        boolean seven = n % 2 == 1;
        int ones = (n - (seven ? 3 : 0)) / 2;
        return (seven ? "7" : "") + "1".repeat(ones);
    }

    /**
     * 8 -> 10
     * 9 -> 18
     * 10 -> 22
     * 11 -> 20
     * 12 -> 28
     * 13 -> 80
     * 14 -> 88
     * 15 -> 108
     * 길이 -> (n - 1) / 7 + 1
     */
    private static int[] mapping = new int[]{6, 2, 5, 5, 4, 5, 6, 3, 7, 6};
    private static long INF = Long.MAX_VALUE;

    private static String findMin(int n) {
        int len = (n - 1) / 7 + 1;

        int[] nums = new int[len];
        Arrays.fill(nums, -1);
        return Long.toString(dfs(nums, 0, n));
    }

    private static long dfs(int[] nums, int cur, int rest) {
        if (nums[0] != -1 && nums[0] == 0) return INF;
        if (cur != nums.length && rest <= 1) return INF;
        if (rest > (nums.length - cur) * 7) return INF;
        
        if (cur == nums.length) {
            if (rest != 0) return INF;
            long num = 0;
            long multi = 1;
            for (int i = nums.length - 1; i >= 0; i--) {
                num += (multi * nums[i]);
                multi *= 10;
            }
            return num;
        }

        for (int i = 0; i < 10; i++) {
            nums[cur] = i;
            long res = dfs(nums, cur + 1, rest - mapping[i]);
            if (res != INF) return res;
        }
        return INF;
    }
}
