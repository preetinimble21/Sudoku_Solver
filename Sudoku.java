package Sudoku_Solver;

public class Sudoku {
    static void print(int board[][]) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    static boolean isSafe(int board[][], int row, int col, int ch) {
        for (int i = 0; i < board.length; i++) {
            if (board[i][col] == ch) {
                return false;
            }
        }
        for (int j = 0; j < board[0].length; j++) {
            if (board[row][j] == ch) {
                return false;
            }
        }
        int sr = (row / 3) * 3;
        int sc = (col / 3) * 3;
        for (int i = sr; i < sr + 3; i++) {
            for (int j = sc; j < sc + 3; j++) {
                if (board[i][j] == ch) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean solver(int board[][], int row, int col) {

        if (row == board.length) {
            return true;
        }
        int nextRow = 0;
        int nextCol = 0;
        if (col == board[0].length - 1) {
            nextRow = row + 1;
            nextCol = 0;
        } else {
            nextRow = row;
            nextCol = col + 1;
        }

        if (board[row][col] != 0) {
            return solver(board, nextRow, nextCol);
        }
        for (int ch = 1; ch <= 9; ch++) {
            if (isSafe(board, row, col, ch)) {
                board[row][col] = ch;
                if (solver(board, nextRow, nextCol)) {
                    return true;
                }
                board[row][col] = 0;
            }
        }
        return false;
    }

    public static void main(String args[]) {
        int board[][] = { { 5, 3, 0, 0, 7, 0, 0, 0, 0 }, { 6, 0, 0, 1, 9, 5, 0, 0, 0 }, { 0, 9, 8, 0, 0, 0, 0, 6, 0 },
                { 8, 0, 0, 0, 6, 0, 0, 0, 3 }, { 4, 0, 0, 8, 0, 3, 0, 0, 1 }, { 7, 0, 0, 0, 2, 0, 0, 0, 6 },
                { 0, 6, 0, 0, 0, 0, 2, 8, 0 }, { 0, 0, 0, 4, 1, 9, 0, 0, 5 }, { 0, 0, 0, 0, 8, 0, 0, 7, 9 } };

        if (solver(board, 0, 0) == true) {
            System.out.println("solution is exist");
            print(board);
        } else {
            System.out.println("solution is not exist");
        }
    }

}
