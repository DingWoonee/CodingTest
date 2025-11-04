package binary_search;

import java.io.*;
import java.util.*;
/*
 * 10 30 5 8 40 20 25 27 50
 * 
 * [핵심 포인트]
 * - 수열의 구성을 알 필요가 없음. 수열의 길이만 알면됨.
 * - -> 각 길이별로 최솟값을 계속 업데이트해 나가면 됨.
 * - -> O(n log n)에 가능
 * 
 * [풀이 전략]
 * 1. 배열(minArr)로 각 길이별로 가장 장은 끝 값을 저장.
 * 2. 이분 탐색으로 자기보다 같거나 큰 값이 나오는 minArr의 첫 인덱스를 찾음
 * 3. 배열(minArr) 업데이트
 */
public class P12015 {
    public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] nums = new int[N];
        for (int i = 0; i < N; i++) nums[i] = Integer.parseInt(st.nextToken());

        int[] minArr = new int[N];
        int len = 0;
        for (int n : nums) {
            int left = 0, right = len;
            while (left < right) {
                int mid = (left + right) / 2;
                if (minArr[mid] >= n) right = mid;
                else left = mid + 1;
            }
            minArr[left] = n;
            if (left == len) len++;
        }

        System.out.println(len);
    }
}
