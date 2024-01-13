public class Board {
    private int size;
    private char[][] board;


    public Board(final int size) {
        this.size = size;
        board = new char[size][size];
    }

    public boolean addPiece(final char piece, final int x, final int y) {
        if (board[x][y] != '\u0000') {
            return false;
        }
        board[x][y] = piece;
        return true;
    }

    public boolean winnerFound(int row, int col, char piece) {
        boolean rowMatched = true;
        for (int i = 0; rowMatched && (i < size); i++) {
            rowMatched = board[row][i] == piece;
        }
        if (rowMatched) {
            return true;
        }

        boolean colMatched = true;
        for (int i = 0; colMatched && (i < size); i++) {
            colMatched = board[i][col] == piece;
        }
        if (colMatched) {
            return true;
        }

        if (row == col) {
            boolean leftDiagonalMatched = true;
            for (int i = 0; leftDiagonalMatched && (i < size); i++) {
                leftDiagonalMatched = board[i][i] == piece;
            }
            if (leftDiagonalMatched) {
                return true;
            }

        }

        if ((row + col + 1) == size) {
            boolean rightDiagonalMatched = true;
            for (int i = 0; rightDiagonalMatched && i < size; i++) {
                rightDiagonalMatched = board[i][size - i - 1] == piece;
            }
            if (rightDiagonalMatched) {
                return true;
            }
        }

        return false;
    }

    public void displayBoard() {
        for (int i = 0; i < size; i++) {
            System.out.print("|");
            for (int j = 0; j < size; j++) {
                System.out.print((board[i][j] == '\u0000' ? " " : board[i][j]) + " |");
            }
            System.out.println();
        }
    }
}
