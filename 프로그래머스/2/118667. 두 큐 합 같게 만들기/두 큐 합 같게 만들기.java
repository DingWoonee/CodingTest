/*
3 2 7 2 / 4 6 5 1 - 반복
*/
class Solution {
    public int solution(int[] queue1, int[] queue2) {
        // target 구하기
        long target = getTarget(queue1, queue2);
        // 배열 합치기
        int[] arr = concat(queue1, queue2);
        // target 찾기
        // start부터 end - 1까지 더한 결과가 sum
        // end는 sum이 target보다 커지게 되는 시점의 인덱스
        int start = 0, end = 0;
        long sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            end = i + 1;
            if (sum == target && end >= queue1.length) {
                return end - queue1.length;
            }
            if (sum > target) {
                sum -= arr[i];
                end--;
                break;
            }
        }
        for (int i = 1; i < arr.length; i++) {
            start = i;
            sum -= arr[i - 1];
            for (int j = end; j < arr.length; j++) {
                if (sum + arr[j] <= target) {
                    sum += arr[j];
                    end = j + 1;
                } else {
                    break;
                }
            }
            if (sum == target && end >= queue1.length) {
                return (start) + (end - queue1.length);
            }
        }
        
        return -1;
    }
    
    private long getTarget(int[] arr1, int[] arr2) {
        long target = 0;
        for (int n : arr1) {
            target += n;
        }
        for (int n : arr2) {
            target += n;
        }
        return target / 2;
    }
    
    private int[] concat(int[] arr1, int[] arr2) {
        int[] result = new int[arr1.length * 2 + arr2.length];
        for (int i = 0; i < arr1.length; i++) {
            result[i] = arr1[i];
        }
        for (int i = 0; i < arr2.length; i++) {
            result[i + arr1.length] = arr2[i];
        }
        for (int i = 0; i < arr1.length; i++) {
            result[i + arr1.length + arr2.length] = arr1[i];
        }
        return result;
    }
}