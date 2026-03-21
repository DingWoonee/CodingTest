import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        int[] As = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) As[i] = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int B = Integer.parseInt(st.nextToken()); // 총 감독관
        int C = Integer.parseInt(st.nextToken()); // 부 감독관

        long cnt = 0;
        for (int A : As) {
            cnt++;
            A -= B;
            if (A <= 0) continue;
            cnt += (A - 1) / C + 1;
        }

        System.out.println(cnt);
    }
}
