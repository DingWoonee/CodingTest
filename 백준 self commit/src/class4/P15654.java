package class4;

import java.io.*;
import java.util.*;

public class P15654 {
    
    static int N, M;
    static int[] arr;
    
    public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        st = new StringTokenizer(br.readLine());
        arr = new int[N];
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(arr);
        
        dfs(new int[M], new boolean[arr.length], 0, 1);
    }

    private static void dfs(int[] pick, boolean[] visited, int cnt, int start) {
        if (cnt == M) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < pick.length; i++) {
                if (i > 0) sb.append(' ');
                sb.append(pick[i]);
            }
            System.out.println(sb.toString());
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            if (visited[i]) continue;
            pick[cnt] = arr[i];
            visited[i] = true;
            dfs(pick, visited, cnt + 1, i);
            visited[i] = false;
        }
    }
}
