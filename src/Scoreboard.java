import java.util.ArrayList;
import java.util.List;

public class Scoreboard {
    private final BST<Integer, String> winTree;
    private final HashST<String, Player> playerTree;
    private int playedGames;

    public Scoreboard() {
        winTree = new BST<>();
        playerTree = new HashST<>();
        playedGames = 0;
    }

    public void addGameResult(String winnerPlayerName,
                              String loserPlayerName,
                              boolean draw) {

        registerPlayer(winnerPlayerName);
        registerPlayer(loserPlayerName);

        Player winner = playerTree.get(winnerPlayerName);
        Player loser = playerTree.get(loserPlayerName);

        if (draw) {
            winner.addDraw();
            loser.addDraw();
        } else {
            winner.addWin();
            loser.addLoss();
            winTree.insert(winner.getWins(), winner.getPlayerName());
        }

        playedGames++;
    }

    public void registerPlayer(String playerName) {
        if (!playerTree.contains(playerName)) {
            Player newPlayer = new Player(playerName);
            playerTree.put(playerName, newPlayer);
        }
    }

    public boolean checkPlayer(String playerName) {
        return playerTree.contains(playerName);
    }

    public Player[] winRange(int lo, int hi) {
        List<String> names = winTree.range(lo, hi);
        List<Player> result = new ArrayList<>();

        for (String name : names) {
            Player player = playerTree.get(name);
            if (player != null) result.add(player);
        }

        return result.toArray(new Player[0]);
    }

    public Player[] winSuccessor(int wins) {
        List<String> names = winTree.successor(wins);
        List<Player> result = new ArrayList<>();
        for (String name : names) {
            Player player = playerTree.get(name);
            if (player != null) result.add(player);
        }
        return result.toArray(new Player[0]);
    }

    public int getPlayedGames() {
        return playedGames;
    }

    public Player getPlayer(String name) {
        return playerTree.get(name);
    }

    public HashST<String, Player> players() {
        return playerTree;
    }

    public void print(Player player) {
        System.out.println("- " + player.getPlayerName());
        System.out.println("  Ganadas: " + player.getWins());
        System.out.println("  Empates: " + player.getDraws());
        System.out.println("  Perdidas: " + player.getLosses());
        System.out.printf("  WinRate: %.2f%%\n", player.getWinRate() * 100);
    }
}
