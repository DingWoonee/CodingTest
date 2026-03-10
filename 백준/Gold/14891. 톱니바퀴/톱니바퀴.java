import java.util.*;
import java.io.*;

public class Main {

    private static int[][] arr;
    private static int[] tops;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        arr = new int[5][8];
        tops = new int[]{0, 0, 0, 0, 0};

        for (int i = 1; i < 5; i++) {
            char[] chs = br.readLine().toCharArray();
            for (int j = 0; j < 8; j++) {
                arr[i][j] = chs[j] - '0';
            }
        }

        int K = Integer.parseInt(br.readLine());

        for (int i = 0; i < K; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());

            rotate(num, dir, 0);
        }

        int sum = 0;
        for (int i = 1; i < 5; i++) {
            if (arr[i][tops[i]] == 1) sum += Math.pow(2.0, i - 1);
        }
        System.out.println(sum);
    }

    private static void rotate(int num, int dir, int from) {
        int preTop = tops[num];
        // 회전
        tops[num] = (tops[num] - dir) % 8;
        if (tops[num] < 0) tops[num] = 7;

        // 양 옆 체크
        // 오른쪽으로
        if (from != 1 && num < 4 && arr[num][(preTop + 2) % 8] != arr[num + 1][(tops[num + 1] + 6) % 8]) { 
            rotate(num + 1, -dir, -1);
        }
        // 왼쪽으로
        if (from != -1 && num > 1 && arr[num][(preTop + 6) % 8] != arr[num - 1][(tops[num - 1] + 2) % 8]) {
            rotate(num - 1, -dir, 1);
        }
    }
}
