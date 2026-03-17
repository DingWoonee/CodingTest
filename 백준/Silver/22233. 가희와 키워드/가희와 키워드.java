import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Set<String> keywordSet = new HashSet<>();
        for (int i = 0; i < N; i++) {
            keywordSet.add(br.readLine());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), ",");
            while (st.hasMoreTokens()) {
                String used = st.nextToken();
                if (keywordSet.contains(used)) {
                    keywordSet.remove(used);
                }
            }
            System.out.println(keywordSet.size());
        }
    }
}