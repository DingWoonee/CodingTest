import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int r = Integer.parseInt(st.nextToken()) - 1;
        int c = Integer.parseInt(st.nextToken()) - 1;
        int k = Integer.parseInt(st.nextToken());

        int[][] arr = new int[3][3];
        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int cnt = 0;
        while (arr.length - 1 < r || arr[0].length - 1 < c || arr[r][c] != k) {
            if (cnt == 100) {
                System.out.println(-1);
                return;
            }

            if (arr.length >= arr[0].length) {
                arr = calcByRow(arr);
            } else {
                arr = calcByCol(arr);
            }
            
            cnt++;
        }

        System.out.println(cnt);
    }

    private static int[][] calcByRow(int[][] arr) {
        List<int[]>[] cntArr = new List[arr.length];
        int maxLen = 0;

        for (int i = 0; i < arr.length; i++) {
            Map<Integer, Integer> map = new HashMap<>();

            for (int j = 0; j < arr[0].length; j++) {
                if (arr[i][j] == 0) continue;
                map.put(arr[i][j], map.getOrDefault(arr[i][j], 0) + 1);
            }

            List<int[]> list = new ArrayList<>();
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                list.add(new int[]{entry.getKey(), entry.getValue()});
            }

            list.sort((a, b) -> {
                if (a[1] == b[1]) return a[0] - b[0];
                return a[1] - b[1];
            });

            cntArr[i] = list;
            maxLen = Math.max(maxLen, list.size() * 2);
        }

        maxLen = Math.min(maxLen, 100);
        int[][] result = new int[arr.length][maxLen];

        for (int i = 0; i < arr.length; i++) {
            int idx = 0;
            for (int[] pair : cntArr[i]) {
                if (idx >= 100) break;
                result[i][idx++] = pair[0];
                if (idx >= 100) break;
                result[i][idx++] = pair[1];
            }
        }

        return result;
    }

    private static int[][] calcByCol(int[][] arr) {
        List<int[]>[] cntArr = new List[arr[0].length];
        int maxLen = 0;

        for (int j = 0; j < arr[0].length; j++) {
            Map<Integer, Integer> map = new HashMap<>();

            for (int i = 0; i < arr.length; i++) {
                if (arr[i][j] == 0) continue;
                map.put(arr[i][j], map.getOrDefault(arr[i][j], 0) + 1);
            }

            List<int[]> list = new ArrayList<>();
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                list.add(new int[]{entry.getKey(), entry.getValue()});
            }

            list.sort((a, b) -> {
                if (a[1] == b[1]) return a[0] - b[0];
                return a[1] - b[1];
            });

            cntArr[j] = list;
            maxLen = Math.max(maxLen, list.size() * 2);
        }

        maxLen = Math.min(maxLen, 100);
        int[][] result = new int[maxLen][arr[0].length];

        for (int j = 0; j < arr[0].length; j++) {
            int idx = 0;
            for (int[] pair : cntArr[j]) {
                if (idx >= 100) break;
                result[idx++][j] = pair[0];
                if (idx >= 100) break;
                result[idx++][j] = pair[1];
            }
        }

        return result;
    }
}
