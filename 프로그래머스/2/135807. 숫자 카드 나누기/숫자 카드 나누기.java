/*
[풀이 전략]
1. arrayA에서 GCD 구하기 / arrayB에서 GCD 구하기
2. 둘 중 더 큰 것이 답.
3. 둘 다 없을 경우 0이 답.
*/
class Solution {
    public int solution(int[] arrayA, int[] arrayB) {
        int answer = 0;
        int tempA = arrayA[0];
        for (int i = 1; i < arrayA.length; i++) {
            tempA = gcd(tempA, arrayA[i]);
        }
        if (tempA != 1) {
            boolean flag = false;
            for (int b : arrayB) {
                if (b % tempA == 0) {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                answer = tempA;
            }
        }
        
        int tempB = arrayB[0];
        for (int i = 1; i < arrayB.length; i++) {
            tempB = gcd(tempB, arrayB[i]);
        }
        if (tempB != 1) {
            boolean flag = false;
            for (int a : arrayA) {
                if (a % tempB == 0) {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                answer = Math.max(answer, tempB);
            }
        }
        return answer;
    }
    
    private int gcd(int n1, int n2) {
        int temp;
        while (n2 != 0) {
            temp = n2;
            n2 = n1 % n2;
            n1 = temp;
        }
        return n1;
    }
}