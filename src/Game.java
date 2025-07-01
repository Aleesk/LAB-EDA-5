import java.util.Scanner;

public class Game {
    private String playerNameA, playerNameB;
    private String winnerPlayerName;
    private String status;
    private ConnectFour board;

    public Game(String playerNameA, String playerNameB) {
        this.playerNameA = playerNameA;
        this.playerNameB = playerNameB;
        this.winnerPlayerName = "";
        this.status = "IN_PROGRESS";
        this.board = new ConnectFour();
    }

    public String play() {
        Scanner scanner = new Scanner(System.in);

        while (status.equals("IN_PROGRESS")) {
            board.printBoard();
            String currentPlayer = board.getCurrentSymbol() == 'X' ? playerNameA : playerNameB;
            System.out.print(currentPlayer + " (" + board.getCurrentSymbol() + "), ingresa columna (0-6): ");

            int col;
            try {
                col = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Intenta de nuevo.");
                continue;
            }

            if (!board.makeMove(col)) {
                System.out.println("Movimiento inválido. Intenta de nuevo.");
                continue;
            }

            char result = board.isGameOver();
            if (result == 'X') {
                status = "VICTORY";
                winnerPlayerName = playerNameA;
            } else if (result == 'O') {
                status = "VICTORY";
                winnerPlayerName = playerNameB;
            } else if (result == 'D') {
                status = "DRAW";
            }
        }

        board.printBoard();
        if (status.equals("DRAW")) {
            System.out.println("Empate.");
            return "";
        } else {
            System.out.println("Ganador: " + winnerPlayerName);
            return winnerPlayerName;
        }
    }

    public String getStatus() {
        return status;
    }

    public String getWinnerPlayerName() {
        return winnerPlayerName;
    }
}

