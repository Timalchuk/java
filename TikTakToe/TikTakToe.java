import java.util.Scanner;

public class TicTacToe {
    private char[][] board;
    private char currentPlayer;
    private boolean gameEnded;

    public TicTacToe() {
        board = new char[3][3];
        currentPlayer = 'X';
        gameEnded = false;

        initializeBoard();
        playGame();
    }

    private void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '-';
            }
        }
    }

    private void printBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    private void playGame() {
        Scanner scanner = new Scanner(System.in);

        while (!gameEnded) {
            System.out.println("Гравець " + currentPlayer + ", введіть ваш хід (рядок [1-3] стовпчик [1-3]):");
            int row = scanner.nextInt();
            int col = scanner.nextInt();

            if (isValidMove(row, col)) {
                makeMove(row, col);
                printBoard();

                if (checkWin()) {
                    System.out.println("Гравець " + currentPlayer + " переміг!");
                    gameEnded = true;
                } else if (isBoardFull()) {
                    System.out.println("Гра закінчилася у нічию!");
                    gameEnded = true;
                } else {
                    switchPlayer();
                }
            } else {
                System.out.println("Недопустимий хід. Будь ласка, спробуйте ще раз.");
            }
        }

        scanner.close();
    }

    private boolean isValidMove(int row, int col) {
        return row >= 1 && row <= 3 && col >= 1 && col <= 3 && board[row - 1][col - 1] == '-';
    }

    private void makeMove(int row, int col) {
        board[row - 1][col - 1] = currentPlayer;
    }

    private boolean checkWin() {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) {
                return true;
            }
        }

        for (int i = 0; i < 3; i++) {
            if (board[0][i] == currentPlayer && board[1][i] == currentPlayer && board[2][i] == currentPlayer) {
                return true;
            }
        }

        if ((board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) ||
                (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer)) {
            return true;
        }

        return false;
    }

    private boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-') {
                    return false;
                }
            }
        }
        return true;
    }

    private void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    public static void main(String[] args) {
        TicTacToe ticTacToe = new TicTacToe();
    }
}
