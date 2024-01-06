import Service.SnakeLadderService;
import temp.Ladder;
import temp.Player;
import temp.Snake;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int noOfPlayer = scanner.nextInt();
        List<Player> players = new ArrayList<>();
        for(int i = 0; i<noOfPlayer; i++){
            players.add(new Player(scanner.next() ));
        }

        System.out.println("------------------");

        int noOfSnakes = scanner.nextInt();
        List<Snake> snakes = new ArrayList<>();
        for(int i = 0; i<noOfSnakes; i++){
            snakes.add(new Snake(scanner.nextInt() , scanner.nextInt()));
        }

        System.out.println("------------------");

        int noOfLadders = scanner.nextInt();
        List<Ladder> ladders = new ArrayList<>();
        for(int i = 0; i<noOfLadders; i++){
            ladders.add(new Ladder(scanner.nextInt() , scanner.nextInt()));
        }

        System.out.println("-------------------");

        boolean isGameTillLastPlayer = scanner.nextBoolean();
        boolean isMltSixAllow = scanner.nextBoolean();

        System.out.println("-------------------");

        SnakeLadderService snakeLadderService = new SnakeLadderService();
        snakeLadderService.setPlayer(players);
        snakeLadderService.setSnakes(snakes);
        snakeLadderService.setLadder(ladders);
        snakeLadderService.setGameContinueTillLastPlayer(isGameTillLastPlayer);
        snakeLadderService.setMltDiceRollSix(isMltSixAllow);

        snakeLadderService.startGame();

    }

}
