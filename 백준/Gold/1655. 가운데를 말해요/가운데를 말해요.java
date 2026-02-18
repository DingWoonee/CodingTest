import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine()); // 숫자 10만 개

        PriorityQueue<Integer> lpq = new PriorityQueue<>(Comparator.reverseOrder()); // 내림차순
        PriorityQueue<Integer> rpq = new PriorityQueue<>(); // 오른차순
        
        for (int i = 1; i <= N; i++) {
            int num = Integer.parseInt(br.readLine());

            if (lpq.size() == rpq.size()) {
                lpq.offer(num);
                balance(lpq, rpq);
                System.out.println(lpq.peek());
            } else {
                rpq.offer(num);
                balance(lpq, rpq);
                System.out.println(Math.min(lpq.peek(), rpq.peek()));
            }
        }
    }

    private static void balance(PriorityQueue<Integer> lpq, PriorityQueue<Integer> rpq) {
        if (lpq.isEmpty() || rpq.isEmpty()) return;
        while (lpq.peek() > rpq.peek()) {
            int ltemp = lpq.poll();
            int rtemp = rpq.poll();
            lpq.offer(rtemp);
            rpq.offer(ltemp);
        }
    }
}
