import java.util.*;

public class Solution {
    // Trie 노드
    private static class Node {
        Node[] next = new Node[26];
        int bannedBelow = 0;    // 이 노드를 루트로 하는 서브트리(길이 L의 금지 문자열)에 속한 금지 문자열 수
        int terminal = 0;       // 정확히 이 노드에서 끝나는 금지 문자열 수 (0 또는 1)
    }

    public String solution(long n, String[] bans) {
        // 1) 길이별 전체 문자열 개수와 금지된 개수 계산
        long[] pow26 = new long[12];
        pow26[0] = 1;
        for (int i = 1; i <= 11; i++) {
            pow26[i] = pow26[i - 1] * 26;
        }

        long[] total = new long[12], bannedLen = new long[12];
        for (int i = 1; i <= 11; i++) {
            total[i] = pow26[i];
        }
        // bans 길이별로 카운트
        Map<Integer, List<String>> bansByLen = new HashMap<>();
        for (String b : bans) {
            int L = b.length();
            if (L <= 11) {
                bannedLen[L]++;
                bansByLen.computeIfAbsent(L, k -> new ArrayList<>()).add(b);
            }
        }

        // 2) n번째가 어느 길이(L)에 있는지 찾고, 그 길이 내에서의 0-based offset 계산
        long cum = 0;
        int targetLen = 0;
        long offset = 0;
        for (int L = 1; L <= 11; L++) {
            long remain = total[L] - bannedLen[L];
            if (cum + remain >= n) {
                targetLen = L;
                offset = n - cum - 1;  // 0-based index
                break;
            }
            cum += remain;
        }

        // 3) Trie 를 만들어, 길이 targetLen 의 금지 문자열만 삽입
        Node root = new Node();
        List<String> banList = bansByLen.getOrDefault(targetLen, Collections.emptyList());
        for (String b : banList) {
            Node cur = root;
            for (char c : b.toCharArray()) {
                int i = c - 'a';
                if (cur.next[i] == null) cur.next[i] = new Node();
                cur = cur.next[i];
            }
            cur.terminal = 1;
        }
        // bannedBelow 누적합 계산 (DFS)
        dfsCount(root);

        // 4) offset번째 비금지 문자열을 한 글자씩 구성
        StringBuilder ans = new StringBuilder();
        Node cur = root;
        for (int pos = 0; pos < targetLen; pos++) {
            long blockSize = pow26[targetLen - pos - 1];
            for (int c = 0; c < 26; c++) {
                // 이 글자(c)를 선택했을 때 나오는 전체 문자열 수
                long valid = blockSize;
                // 이 접두사를 루트로 하는 subtree(길이 targetLen 금지 문자열)에서 금지된 개수
                if (cur.next[c] != null) {
                    valid -= cur.next[c].bannedBelow;
                }
                if (offset < valid) {
                    // 이 글자 선택
                    ans.append((char)('a' + c));
                    // Trie 에서 내려가기 (없는 노드는 빈 노드로 처리)
                    cur = (cur.next[c] != null ? cur.next[c] : new Node());
                    break;
                }
                offset -= valid;
            }
        }

        return ans.toString();
    }

    // DFS 로 bannedBelow 누적합 계산
    private void dfsCount(Node u) {
        u.bannedBelow = u.terminal;
        for (int i = 0; i < 26; i++) {
            if (u.next[i] != null) {
                dfsCount(u.next[i]);
                u.bannedBelow += u.next[i].bannedBelow;
            }
        }
    }
}
