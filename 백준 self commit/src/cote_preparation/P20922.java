import java.util.*;
import java.io.*;
/*
같은 원소가 K개 이하로 들어있는 최장 연속 부분 수열의 길이
수열 길이: 20만
수열 숫자: 10만 이하
K: 100

*/
public class P20922 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());

        int[] cnt = new int[100_001];
        
        int max = 0;
        int l = 0;
        
        for (int r = 0; r < N; r++) {
            int next = arr[r];
            cnt[next]++;

            while (cnt[next] > K) {
                cnt[arr[l++]]--;
            }

            max = Math.max(max, r - l + 1);
        }

        System.out.println(max);
    }
}