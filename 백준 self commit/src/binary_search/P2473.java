package binary_search;

import java.io.*;
import java.util.*;
/*
 * 산성 -> 1 ~ 10억
 * 알칼리성 -> -1 ~ -10억
 * 
 * 목표: 3개 더해서 0에 가장 가까운 수가 나오는 조합을 고르시오.
 * 개수: 3 ~ 5,000개
 * 두 개 선택하는 경우의 수: 약 12,500,000 -> 그리고 5000개 중에 가장 0에 가까운 것을 탐색
 */
public class P2473 {

    static int N;
    static long[] nums;
    static long min = Long.MAX_VALUE;
    static long[] answer = new long[3];

    public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        nums = new long[N];
        String[] split = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            nums[i] = Long.parseLong(split[i]);
        }
        
        Arrays.sort(nums);
        for (int i = 0; i < N - 2; i++) {
            for (int j = i + 1; j < N - 1; j++) {
                long sum = nums[i] + nums[j];
                int left = j + 1, right = N;
                while (left < right) {
                    int mid = (left + right) / 2;
                    long tmp = sum + nums[mid];
                    if (min > Math.abs(tmp)) {
                        min = Math.abs(tmp);
                        answer[0] = nums[i];
                        answer[1] = nums[j];
                        answer[2] = nums[mid];
                    }
                    if (tmp > 0) right = mid;
                    else if (tmp < 0) left = mid + 1;
                    else {
                        printAnswer();
                        return;
                    }
                }
            }
        }
        printAnswer();
    }

    private static void printAnswer() {
        Arrays.sort(answer);
        System.out.println(String.format("%d %d %d", answer[0], answer[1], answer[2]));
    }
}
