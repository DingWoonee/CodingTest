package class4;

import java.io.*;
import java.util.*;

public class P15652 {
    
    static int N, M;
    
    public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        dfs(new int[M], 0, 1);
    }

    private static void dfs(int[] pick, int cnt, int start) {
        if (cnt == M) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < pick.length; i++) {
                if (i > 0) sb.append(' ');
                sb.append(pick[i]);
            }
            System.out.println(sb.toString());
            return;
        }

        for (int i = start; i <= N; i++) {
            pick[cnt] = i;
            dfs(pick, cnt + 1, i);
        }
    }
}
