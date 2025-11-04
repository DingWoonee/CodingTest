package class4;

import java.io.*;
import java.util.*;

public class P15663 {

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

        dfs(new int[M], new boolean[N], 0);
    }

    private static void dfs(int[] pick, boolean[] visited, int cnt) {
        if (cnt == M) {
            String[] strs = new String[pick.length];
            for (int i = 0; i < pick.length; i++) {
                strs[i] = Integer.toString(pick[i]);
            }
            System.out.println(String.join(" ", strs));
            return;
        }

        boolean[] numVisited = new boolean[10001];
        for (int i = 0; i < N; i++) {
            if (visited[i] || numVisited[arr[i]]) continue;

            pick[cnt] = arr[i];
            numVisited[arr[i]] = true;
            visited[i] = true;
            dfs(pick, visited, cnt + 1);
            visited[i] = false;
        }
    }
}
