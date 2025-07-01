import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Scoreboard scoreboard = new Scoreboard();

        while (true) {
            System.out.println("\n=== Menú Principal ===");
            System.out.println("1. Nueva Partida");
            System.out.println("2. Mostrar Estadísticas");
            System.out.println("3. Jugadores con más victorias");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción (1-4): ");

            String option = scanner.nextLine();

            switch (option) {
                case "1":
                    // Nueva partida
                    System.out.print("Nombre del Jugador A: ");
                    String playerA = scanner.nextLine();
                    scoreboard.registerPlayer(playerA);

                    System.out.print("Nombre del Jugador B: ");
                    String playerB = scanner.nextLine();
                    scoreboard.registerPlayer(playerB);

                    // Jugar partida
                    Game partida = new Game(playerA, playerB);
                    String ganador = partida.play();
                    boolean empate = partida.getStatus().equals("DRAW");

                    if (empate) {
                        scoreboard.addGameResult(playerA, playerB, true);
                    } else {
                        String perdedor = ganador.equals(playerA) ? playerB : playerA;
                        scoreboard.addGameResult(ganador, perdedor, false);
                    }

                    // Mostrar resultado
                    System.out.println("\n--- Resultado ---");
                    if (empate) {
                        System.out.println("Empate entre " + playerA + " y " + playerB);
                    } else {
                        System.out.println("Ganador: " + ganador);
                    }
                    break;

                case "2":
                    // Mostrar estadísticas de todos los jugadores
                    System.out.println("\n--- Estadísticas de todos los jugadores ---");
                    System.out.println("\nPartidas Totales: " + scoreboard.getPlayedGames());
                    System.out.println("Jugadores: ");
                    for (Player player : scoreboard.players().values()) {
                        System.out.println("\t" + player.getPlayerName());
                    }
                    System.out.print("Ingrese nombre del jugador (o presione Enter para ver todos): ");
                    String playerName = scanner.nextLine();

                    if (playerName.isEmpty()) {
                        for (Player player : scoreboard.players().values()) {
                            scoreboard.print(player);
                        }
                    } else {
                        scoreboard.print(scoreboard.players().get(playerName));
                    }
                    break;

                case "3":
                    // Mostrar jugadores con más victorias
                    System.out.print("\nIngrese número mínimo de victorias: ");
                    try {
                        int minWins = Integer.parseInt(scanner.nextLine());
                        System.out.println("\nJugadores con más de " + minWins + " victorias:");
                        Player[] jugadores = scoreboard.winSuccessor(minWins);
                        if (jugadores.length == 0) {
                            System.out.println("No hay jugadores con más de " + minWins + " victorias.");
                        } else {
                            for (Player p : jugadores) {
                                System.out.println(p.getPlayerName() + " (" + p.getWins() + " victorias)");
                            }
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Por favor, ingrese un número válido.");
                    }
                    break;

                case "4":
                    System.out.println("¡Gracias por jugar!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Opción no válida. Por favor, seleccione 1, 2, 3 o 4.");
            }
        }
    }
}
