import java.util.*;
import java.io.*;
/**
 * 풀이 전략: 가치에 대해 내림차순 정렬 & 가방 최대 무게에 대해 내림차순 정렬
 * -> 가치가 높은 순서대로 담을 수 있는 최소 가방을 찾아서 담기 (이진 탐색)
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 총 보석 수 (1~30만)
        int K = Integer.parseInt(st.nextToken()); // 가방 수 (1~30만)
        
        int[][] bos = new int[N][2]; // 무게 (0~100만) | 가치 (0~100만)
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            bos[i][0] = Integer.parseInt(st.nextToken());
            bos[i][1] = Integer.parseInt(st.nextToken());
        }

        Integer[] cs = new Integer[K]; // 각 가방 최대 무게 (1~10억)
        for (int i = 0; i < K; i++) cs[i] = Integer.parseInt(br.readLine());
        
        Arrays.sort(bos, (a, b) -> {
            if (a[1] != b[1]) return b[1] - a[1];
            return a[0] - b[0];
        });
        Arrays.sort(cs);
        parent = new int[K];
        for (int i = 0; i < parent.length; i++) parent[i] = i;

        long sum = 0;
        for (int[] bo : bos) { // {M, V}
            int m = bo[0], v = bo[1];
            int l = 0, r = K;
            while (l < r) {
                int mid = (l + r) / 2;
                if (cs[mid] < m) {
                    l = mid + 1;
                } else {
                    r = mid;
                }
            }
            if (l == K) continue;

            int p = find(l);
            if (p == -1) continue;

            // 가방 찾음
            sum += v;
            union(p);
        }

        System.out.println(sum);
    }

    private static int[] parent; // parent[i] = i 인덱스 사용 가능한 최소 인덱스

    private static void union(int a) {
        if (a == parent.length - 1) {
            parent[a] = -1;
        } else {
            parent[a] = a + 1;
        }
    }

    private static int find(int a) { // -1인 경우 저장 불가
        if (parent[a] == -1) return -1;
        if (parent[a] == a) return a;
        int p = find(parent[a]);
        parent[a] = p;
        return p;
    }
}
