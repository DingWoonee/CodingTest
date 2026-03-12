import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[][] arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // N 최대 20 -> 그냥 dfs
        dfs(arr, N, 0, 1, new int[N / 2]);

        System.out.println(min);
    }

    private static int min = Integer.MAX_VALUE;

    private static void dfs(int[][] arr, int N, int depth, int next, int[] team1) {
        if (depth == N / 2) {
            Set<Integer> set = new HashSet<>();
            for (int i = 1; i <= N; i++) set.add(i);
            for (int t : team1) {
                set.remove(t);
            }
            int[] team2 = new int[N / 2];
            int i = 0;
            for (int n : set) team2[i++] = n;

            min = Math.min(min, Math.abs(calcSum(arr, team1) - calcSum(arr, team2)));
            
            return;
        }

        for (int i = next; i <= N; i++) {
            team1[depth] = i;
            dfs(arr, N, depth + 1, i + 1, team1);
        }
    }

    private static int calcSum(int[][] arr, int[] team) {
        int sum = 0;
        for (int i = 0; i < team.length; i++) {
            for (int j = i + 1; j < team.length; j++) {
                int p1 = team[i], p2 = team[j];
                sum += arr[p1 - 1][p2 - 1];
                sum += arr[p2 - 1][p1 - 1];
            }
        }
        return sum;
    }
}
