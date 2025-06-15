import java.util.*;

class Solution {
    public int solution(String[] storage, String[] requests) {
        int rows = storage.length;
        int cols = storage[0].length();
        int answer = rows * cols;
        // char 배열로 변환
        char[][] storageChar = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                storageChar[i][j] = storage[i].charAt(j);
            }
        }
        // requests 수행
        for (String req : requests) {
            // 크레인 요청인 경우
            if (req.length() == 2) {
                char ch = req.charAt(0);
                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < cols; j++) {
                        if (storageChar[i][j] == ch) {
                            answer--;
                            storageChar[i][j] = ' ';
                        }
                    }
                }
            }
            // 지게차 요청인 경우
            else if (req.length() == 1) {
                char ch = req.charAt(0);
                List<int[]> deleteList = new ArrayList<>();
                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < cols; j++) {
                        if (storageChar[i][j] == ch) {
                            // 깊은 복사
                            char[][] copy = new char[storageChar.length][];
                            for (int k = 0; k < storageChar.length; k++) {
                                copy[k] = storageChar[k].clone();
                            }
                            if (isOut(copy, i, j)) {
                                deleteList.add(new int[]{i, j});
                            }
                        }
                    }
                }
                for (int[] arr : deleteList) {
                    storageChar[arr[0]][arr[1]] = ' ';
                    answer--;
                }
            }
        }
        
        return answer;
    }
    
    // 재귀 함수
    private boolean isOut(char[][] arr, int row, int col) {
        // 바깥인지 판단
        if (row == 0 || row == arr.length - 1 || col == 0 || col == arr[0].length - 1) {
            return true;
        }
        // 바깥으로 못가는거 판단
        if (arr[row - 1][col] != ' ' && arr[row + 1][col] != ' '
           && arr[row][col - 1] != ' ' && arr[row][col + 1] != ' ') {
            return false;
        }
        // 길 찾기
        arr[row][col] = '-';
        return (arr[row - 1][col] == ' ' ? isOut(arr, row - 1, col) : false)
            || (arr[row + 1][col] == ' ' ? isOut(arr, row + 1, col) : false)
            || (arr[row][col - 1] == ' ' ? isOut(arr, row, col - 1) : false)
            || (arr[row][col + 1] == ' ' ? isOut(arr, row, col + 1) : false);
    }
}