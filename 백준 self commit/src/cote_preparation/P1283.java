package cote_preparation;

import java.util.*;
import java.io.*;

public class P1283 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        String[] strs = new String[N];
        for (int i = 0; i < N; i++) strs[i] = br.readLine();

        Set<Character> set = new HashSet<>();
        for (String str : strs) {
            int wordIdx = -1;
            String[] words = str.split(" ");
            // 1단계
            for (int i = 0; i < words.length; i++) {
                String word = words[i];
                if (!set.contains(toLower(word.charAt(0)))) {
                    wordIdx = i;
                    set.add(toLower(word.charAt(0)));
                    break;
                }
            }
            if (wordIdx != -1) {
                for (int i = 0; i < words.length; i++) {
                    if (wordIdx != i) {
                        System.out.print(words[i]);
                    } else {
                        printWordWithBig(words[i], 0);
                    }
                    if (i < words.length - 1) System.out.print(" ");
                    else System.out.println();
                }
                continue;
            }
            // 2단계
            int charIdx = -1;
            char[] chs = str.toCharArray();
            for (int i = 0; i < chs.length; i++) {
                char ch = chs[i];
                if (ch == ' ') continue;

                if (!set.contains(toLower(ch))) {
                    charIdx = i;
                    set.add(toLower(ch));
                    break;
                }
            }
            if (charIdx != -1) {
                printWordWithBig(str, charIdx);
                System.out.println();
                continue;
            }

            System.out.println(str);
        }
    }

    private static char toLower(char ch) {
        // A 65 / a 97
        if (ch < 97) return (char) (ch + 32);
        else return ch;
    }

    private static void printWordWithBig(String str, int idx) {
        char[] chs = str.toCharArray();
        for (int i = 0; i < chs.length; i++) {
            if (i != idx) {
                System.out.print(chs[i]);
            } else {
                System.out.print("[" + chs[i] + "]");
            }
        }
    }
}