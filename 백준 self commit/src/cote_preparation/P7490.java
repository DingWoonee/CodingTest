package cote_preparation;

import java.io.*;
import java.util.*;

public class P7490 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int cases = Integer.parseInt(br.readLine());
        for (int i = 0; i < cases; i++) {
            List<String> answers = new ArrayList<>();
            int N = Integer.parseInt(br.readLine());
            dfs(N, 1, new char[N - 1], answers);

            Collections.sort(answers);
            for (String str : answers) System.out.println(str);
            System.out.println();
        }
    }

    private static void dfs(int N, int cur, char[] chs, List<String> answers) {
        if (cur == N) {
            if (calc(N, chs) == 0) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < N; i++) {
                    sb.append(i + 1);
                    if (i < N - 1) sb.append(chs[i]);
                }
                answers.add(sb.toString());
            }
            return;
        }

        chs[cur - 1] = '+';
        dfs(N, cur + 1, chs, answers);

        chs[cur - 1] = '-';
        dfs(N, cur + 1, chs, answers);

        chs[cur - 1] = ' ';
        dfs(N, cur + 1, chs, answers);
    }

    private static int calc(int N, char[] chs) {
        List<Integer> numList = new ArrayList<>();
        List<Character> chList = new ArrayList<>();

        numList.add(1);
        for (int i = 0; i < N - 1; i++) {
            if (chs[i] == '+') {
                numList.add(i + 2);
                chList.add('+');
            } else if (chs[i] == '-') {
                numList.add(i + 2);
                chList.add('-');
            } else {
                int num = numList.get(numList.size() - 1) * 10 + i + 2;
                numList.remove(numList.size() - 1);
                numList.add(num);
            }
        }

        int result = numList.get(0);
        for (int i = 0; i < chList.size(); i++) {
            if (chList.get(i) == '+') {
                result += numList.get(i + 1);
            } else {
                result -= numList.get(i + 1);
            }
        }

        return result;
    }
}
