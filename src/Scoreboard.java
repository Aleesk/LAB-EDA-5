import java.util.ArrayList;
import java.util.List;

public class Scoreboard {
    private final BST<Integer, String> winTree;
    private final HashST<String, Player> players;
    private int playedGames;

    public Scoreboard() {
        winTree = new BST<>();
        players = new HashST<>();
        playedGames = 0;
    }

    public void addGameResult(String winnerPlayerName,
                              String loserPlayerName,
                              boolean draw) {

        registerPlayer(winnerPlayerName);
        registerPlayer(loserPlayerName);

        Player winner = players.get(winnerPlayerName);
        Player loser = players.get(loserPlayerName);

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
        if (!players.contains(playerName)) {
            Player newPlayer = new Player(playerName);
            players.put(playerName, newPlayer);
        }
    }

    public boolean checkPlayer(String playerName) {
        return players.contains(playerName);
    }

    public Player[] winRange(int lo, int hi) {
        List<String> names = winTree.range(lo, hi);
        List<Player> result = new ArrayList<>();

        for (String name : names) {
            Player player = players.get(name);
            if (player != null) result.add(player);
        }

        return result.toArray(new Player[0]);
    }

    public Player[] winSuccessor(int wins) {
        List<String> names = winTree.successor(wins);
        List<Player> result = new ArrayList<>();
        for (String name : names) {
            Player player = players.get(name);
            if (player != null) result.add(player);
        }
        return result.toArray(new Player[0]);
    }

    public int getPlayedGames() {
        return playedGames;
    }

    public Player getPlayer(String name) {
        return players.get(name);
    }

    public HashST<String, Player> players() {
        return players;
    }

    public void print(Player player) {
        System.out.println("- " + player.getPlayerName());
        System.out.println("  Ganadas: " + player.getWins());
        System.out.println("  Empates: " + player.getDraws());
        System.out.println("  Perdidas: " + player.getLosses());
        System.out.printf("  WinRate: %.2f%%\n", player.getWinRate() * 100);
    }
}
