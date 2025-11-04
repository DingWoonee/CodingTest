package class4;

import java.io.*;
import java.util.*;

public class P15666 {

    static int N, M;
    static int[] arr;

    public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(arr);

        dfs(new int[M], 0, 0);
    }

    private static void dfs(int[] pick, int start, int cnt) {
        if (cnt == M) {
            String[] strs = new String[pick.length];
            for (int i = 0; i < pick.length; i++) {
                strs[i] = Integer.toString(pick[i]);
            }
            System.out.println(String.join(" ", strs));
            return;
        }

        int last = 0;
        for (int i = start; i < N; i++) {
            if (arr[i] == last) continue;

            pick[cnt] = arr[i];
            last = arr[i];
            dfs(pick, i, cnt + 1);
        }
    }
}
