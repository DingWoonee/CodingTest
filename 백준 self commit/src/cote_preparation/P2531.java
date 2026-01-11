import java.util.*;
import java.io.*;
/*
N: 30,000, d: 3,000, k: 3,000, c: <= d
*/
public class P2531 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        
        int[] belt = new int[N];
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());
            belt[i] = num;
        }

        int[] cnts = new int[d + 1];
        int distinct = 0;

        for (int i = 0; i < k; i++) {
            if (cnts[belt[i]]++ == 0) distinct++;
        }

        int answer = distinct + (cnts[c] == 0 ? 1 : 0);

        for (int i = 0; i < N; i++) {
            if (cnts[belt[i]]-- == 1) distinct--;
            if (cnts[belt[(i + k) % N]]++ == 0) distinct++;

            answer = Math.max(answer, distinct + (cnts[c] == 0 ? 1 : 0));
        }

        System.out.println(answer);
    }
}