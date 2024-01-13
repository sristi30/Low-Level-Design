import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TicTacToe {
    private Player[] players;
    private Board board;
    private static final InputStreamReader inputStreamReader = new InputStreamReader(System.in);
    private static final BufferedReader buferedReader = new BufferedReader(inputStreamReader);
    private static int boardSize;

    public TicTacToe() {
        try {
            System.out.println("Enter the no. of players: ");
            final int noOfPlayers = Integer.parseInt(buferedReader.readLine());
            players = new Player[noOfPlayers];
            for (int i = 0; i < noOfPlayers; i++) {
                System.out.println("Enter player " + (i + 1) + "'s name: ");
                final String name = buferedReader.readLine();
                System.out.println("Enter the preferred piece for " + name + ": ");
                final char piece = buferedReader.readLine().charAt(0);
                final Player player = new Player(name, piece);
                players[i] = player;
            }
            System.out.println("Enter the size of the board: ");
            boolean invalidSize;
            do {
                boardSize = Integer.parseInt(buferedReader.readLine());
                invalidSize = boardSize < 3;
                if (invalidSize) {
                    System.out.println("The board size cannot be less than 3. Enter again: ");
                }
            } while (invalidSize);
            board = new Board(boardSize);
        } catch (IOException e) {
            System.out.println("An unexpected error occurred");
        }
    }


    public void runGame() throws IOException {
        int playerIndex = 0;
        boolean winnerAnnounced;
        int turn = 0;
        do {
            board.displayBoard();
            winnerAnnounced = playTurnAndAnnounceWinner(playerIndex);
            playerIndex = (playerIndex + 1) % players.length;
            turn++;
        } while (!winnerAnnounced && turn < (boardSize * boardSize));
        if (!winnerAnnounced) {
            System.out.println("The game is draw");
        }
    }

    private boolean playTurnAndAnnounceWinner(int playerIndex) throws IOException {
        boolean pieceAdded;
        int x;
        int y;
        do {
            System.out.println("Enter the x position for: " + players[playerIndex].getName());
            x = inputPosition();
            System.out.println("Enter the y position for: " + players[playerIndex].getName());
            y = inputPosition();
            pieceAdded = board.addPiece(players[playerIndex].getPiece(), x, y);
            if (!pieceAdded) {
                System.out.println("A piece is already added in this position. Try again.");
            }
        } while (!pieceAdded);
        if (board.winnerFound(x, y, players[playerIndex].getPiece())) {
            System.out.println(players[playerIndex].getName() + " won.");
            return true;
        }
        return false;
    }

    private int inputPosition() throws IOException {
        int pos;
        do {
            pos = Integer.parseInt(buferedReader.readLine());
            if (pos >= boardSize || pos < 0) {
                System.out.println("The position should be between 0 and " + (boardSize - 1));
            }
        } while (pos >= boardSize || pos < 0);
        return pos;
    }


}

