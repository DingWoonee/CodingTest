package class4;

import java.io.*;
import java.util.*;

public class P1629 {
    public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        List<Long> remains = new ArrayList<>();
        Set<Long> set = new HashSet<>();

        long num = A % C;
        while (!set.contains(num)) {
            set.add(num);
            remains.add(num);
            num = (num + A) % C;
        }

        System.out.println(remains.get((int) (Math.pow(A, B - 1) % remains.size()) - 1));
    }
}
