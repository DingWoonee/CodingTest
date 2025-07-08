/**
최초 시도 실패 이유
- 중간에 완성이 되는 것을 고려 못함.
*/
class Solution {
    
    public int solution(String[] board) {
        char[][] newBoard = new char[3][3];
        int totalCount = 0;
        // 전처리
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                newBoard[i][j] = board[i].charAt(j);
                if (board[i].charAt(j) != '.') {
                    totalCount++;
                }
            }
        }
        boolean[][] visited = new boolean[3][3];
        
        // 정답 개수 체크
        int checkO = check(newBoard, 'O');
        int checkX = check(newBoard, 'X');
        System.out.println(checkO + " " + checkX);
        if (checkO > 2)
            return 0;
        if (checkX > 2)
            return 0;
        if (checkO == 1 && checkX == 1)
            return 0;
        
        // dfs
        char[][] curBoard = new char[3][3];
        return dfs(newBoard, curBoard, 'O', visited, totalCount) > 0 ? 1 : 0;
    }
    
    private int check(char[][] board, char ch) {
        int count = 0;
        // 세로
        for (int i = 0; i < 3; i++) {
            if (board[0][i] == ch && board[1][i] == ch && board[2][i] == ch) {
                count++;
            }
        }
        // 가로
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == ch && board[i][1] == ch && board[i][2] == ch) {
                count++;
            }
        }
        // 대각선
        if (board[0][0] == ch && board[1][1] == ch && board[2][2] == ch) {
            count++;
        }
        if (board[0][2] == ch && board[1][1] == ch && board[2][0] == ch) {
            count++;
        }
        return count;
    }
    
    private int dfs(char[][] board, char[][] curBoard, char turn, boolean[][] visited, int restCount) {
        // 정답 체크
        if (restCount == 0) {
            return 1;
        } else {
            int checkO = check(curBoard, 'O');
            if (checkO > 0) {
                return 0;
            }
            int checkX = check(curBoard, 'X');
            if (checkX > 0) {
                return 0;
            }
        }
        
        int returnVal = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (!visited[i][j] && board[i][j] == turn) {
                    visited[i][j] = true;
                    curBoard[i][j] = turn;
                    returnVal += dfs(board, curBoard, turn == 'O' ? 'X' : 'O', visited, restCount - 1);
                    visited[i][j] = false;
                    curBoard[i][j] = '.';
                }
            }
        }
        
        return returnVal;
    }
}