package binary_search;

import java.io.*;
import java.util.*;
/*
- 현재 휴게소 N개.
- 휴게소를 M개 더 지으려고 함.
- 지은 곳에 또 못지음.
- 목표: 휴게소가 없는 구간의 길이의 최댓값을 최소로 하고자 함.
- 출력: M개의 휴게소를 짓고 난 후에 휴게소가 없는 구간의 최댓값의 최솟값 출력.

[풀이 전략]
구간의 최댓값의 최솟값 -> 이진 탐색으로 답을 구하자.
*/
public class P1477 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] split1 = br.readLine().split("\\s+");
        String[] split2 = br.readLine().split("\\s+");
		int N = Integer.parseInt(split1[0]);
		int M = Integer.parseInt(split1[1]);
		int L = Integer.parseInt(split1[2]);
		int[] split2Int = new int[N];
		if (N > 0) {
			for (int i = 0; i < split2.length; i++) 
				split2Int[i] = Integer.parseInt(split2[i]);
		}
		Arrays.sort(split2Int);
		List<Integer> diffs = new ArrayList<>();
		if (split2Int.length > 0) 
			diffs.add(split2Int[0] - 0);
		for (int i = 1; i < split2Int.length; i++) {
			int diff = split2Int[i] - split2Int[i - 1];
			diffs.add(diff);
		}
		if (split2Int.length > 0) {
			diffs.add(L - split2Int[split2Int.length - 1]);
		}

		int max = 0;
		for (int i = 0; i < diffs.size(); i++) {
			if (diffs.get(i) > max) max = diffs.get(i);
		}
		if (max == 0) {
			max = L;
			diffs.add(L);
		}
		int left = 1, right = max + 1;
		while (left < right) {
			int mid = (left + right) / 2;
			int need = 0;
			for (int diff : diffs) {
				need += (diff - 1) / mid;
			}
			if (need <= M) right = mid;
			else left = mid + 1;
		}

		System.out.println(left);
	}
}