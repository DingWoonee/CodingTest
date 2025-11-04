package class4;

import java.io.*;
import java.util.*;

public class P15650 {
    
    static int N, M;
    
    public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        dfs(new int[M], 0, 1);
    }
    
    private static void dfs(int[] selected, int cnt, int start) {
        if (cnt == M) {
            String[] arr = new String[M];
            for (int i = 0; i < M; i++) arr[i] = Integer.toString(selected[i]);
            System.out.println(String.join(" ", arr));
            return;
        }
        
        for (int i = start; i <= N - M + 1 + cnt; i++) {
            selected[cnt] = i;
            dfs(selected, cnt + 1, i + 1);
        }
    }
}
