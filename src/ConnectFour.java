import java.util.Arrays;

public class ConnectFour {
    private final char[][] grid;
    private char currentSymbol;

    public ConnectFour() {
        grid = new char[6][7]; // filas x columnas
        for(int i=0; i<6; i++){

            for(int j=0; j<7; j++){

                this.grid[i][j] = ' ';
            }
        }

        this.currentSymbol = 'X';
    }

    public boolean makeMove(int col) {
        if (col < 0 || col >= 7) return false;

        for (int row = 5; row >= 0; row--) {
            if (grid[row][col] == ' ') {
                grid[row][col] = currentSymbol;
                currentSymbol = (currentSymbol == 'X') ? 'O' : 'X';
                return true;
            }
        }
        return false; // columna llena
    }

    public char checkWinner() {
        // Recorremos todo el tablero
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 7; col++) {
                char symbol = grid[row][col];
                if (symbol == ' ') continue;

                // 4 en horizontal
                if (col + 3 < 7 &&
                        symbol == grid[row][col + 1] &&
                        symbol == grid[row][col + 2] &&
                        symbol == grid[row][col + 3])
                    return symbol;

                // 4 en vertical
                if (row + 3 < 6 &&
                        symbol == grid[row + 1][col] &&
                        symbol == grid[row + 2][col] &&
                        symbol == grid[row + 3][col])
                    return symbol;

                // 4 en diagonal
                if (row + 3 < 6 && col + 3 < 7 &&
                        symbol == grid[row + 1][col + 1] &&
                        symbol == grid[row + 2][col + 2] &&
                        symbol == grid[row + 3][col + 3])
                    return symbol;

                // 4 en diagonal
                if (row + 3 < 6 && col - 3 >= 0 &&
                        symbol == grid[row + 1][col - 1] &&
                        symbol == grid[row + 2][col - 2] &&
                        symbol == grid[row + 3][col - 3])
                    return symbol;
            }
        }

        // Empate
        boolean full = true;
        for (int c = 0; c < 7; c++) {
            if (grid[0][c] == ' ') {
                full = false;
                break;
            }
        }
        return full ? 'D' : 'N'; // D: draw, N: not over
    }

    public void printBoard() {
        System.out.println(" 0 1 2 3 4 5 6");
        for (char[] row : grid) {
            for (char cell : row) {
                System.out.print("|" + cell);
            }
            System.out.println("|");
        }
    }

    public char getCurrentSymbol() {
        return currentSymbol;
    }
}

