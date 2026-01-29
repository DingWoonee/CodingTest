package cote_preparation;

import java.io.*;
import java.util.*;

public class P9935 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();
        String bomb = br.readLine();

        char[] chs1 = str.toCharArray();
        char[] chs2 = bomb.toCharArray();

        Deque<Character> stack = new ArrayDeque<>();
        for (char ch : chs1) {
            stack.push(ch);

            if (stack.size() < chs2.length) continue;

            List<Character> temp = new ArrayList<>();
            for (int i = 0; i < chs2.length; i++) {
                if (stack.peek() != chs2[chs2.length - 1 - i]) {
                    for (int t = 0; t < temp.size(); t++) {
                        stack.push(temp.get(temp.size() - 1 - t));
                    }
                    break;
                }
                temp.add(stack.pop());
            }
        }

        if (stack.isEmpty()) {
            System.out.println("FRULA");
            return;
        }

        int size = stack.size();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append(stack.pollLast());
        }
        System.out.println(sb);
    }
}
