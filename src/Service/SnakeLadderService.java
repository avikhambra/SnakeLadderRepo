package Service;

import java.util.HashMap;

import temp.Ladder;
import temp.Player;
import temp.Snake;
import temp.SnakeLadderBoard;

import java.util.*;

public class SnakeLadderService {
     private int intitalNoPlayers;
     private HashMap<String , String> playerInfo; //id or Pname
     private int noOfDice;
     private String isGameCompleted;
     private boolean mltDiceRollSix;
     private boolean gameContinueTillLastPlayer;
     private Queue<Player> players;
     private static int DEFAULT_NO_OF_DICE = 1;
     private int size;
     private static  int DEFAULT_BOX_SIZE = 100;
     private SnakeLadderBoard snakeLadderBoard;
     public SnakeLadderService(int size){
         snakeLadderBoard = new SnakeLadderBoard(size);
         players = new LinkedList<>();
         this.noOfDice = SnakeLadderService.DEFAULT_NO_OF_DICE;
         playerInfo = new HashMap<>();
     }

     public SnakeLadderService(){
         this(SnakeLadderService.DEFAULT_BOX_SIZE);
     }

     public void setNoOfDice(){
         this.noOfDice = noOfDice;
     }

     public void setGameContinueTillLastPlayer(boolean gameContinueTillLastPlayer){
         this.gameContinueTillLastPlayer = gameContinueTillLastPlayer;
     }

     public void setMltDiceRollSix(boolean mltDiceRollSix){
         this.mltDiceRollSix = mltDiceRollSix;
     }

     public boolean getMltDiceRollSix(){
         return mltDiceRollSix;
     }

     public void setSnakes(List<Snake> snakes){
         snakeLadderBoard.setSnakes(snakes);
     }
     public void setLadder(List<Ladder> ladders){
         snakeLadderBoard.setLadders(ladders);
     }
     public void setPlayer(List<Player> players1){

        this.intitalNoPlayers = players1.size();


        players = new LinkedList<>();

        HashMap<String , Integer> playerPiece = new HashMap<>();

        for(Player player : players1){
            players.add(player);
            playerPiece.put(player.getId() , 0);
            playerInfo.put(player.getId() , player.getName());
        }

        snakeLadderBoard.setPlayerPlace(playerPiece);

    }
     public boolean isGameCompleted(){

         if(gameContinueTillLastPlayer){
             return players.size() == 1;
         }else{
             return this.intitalNoPlayers > players.size();
         }

     }

     public int toGetTotalValueAfterRoll(){

          int totalValue = 0;

          do {
              totalValue += Dice.getRoll();

              if(totalValue % 6 == 0 && totalValue/6 == 3){
                  return 0;
              }

          }while (this.mltDiceRollSix && (totalValue%6 == 0) );

          return totalValue;

     }

     public int getAfterSnakeAndLadder(int newPosition){

         int prePosition;

         do {

             prePosition = newPosition;

             List<Snake> listOfSnakes = snakeLadderBoard.getSnakes();
             for(Snake s : listOfSnakes){
                 if(s.getStart() == newPosition){
                      newPosition = s.getEnd();
                 }
             }

             List<Ladder> listOfLadder = snakeLadderBoard.getLadders();
             for(Ladder l : listOfLadder){
                 if(l.getStart() == newPosition){
                     newPosition = l.getEnd();
                 }
             }


         }while (prePosition != newPosition);

         return newPosition;

     }

     public void movePlayer(Player Cplayer , int position){

         int oldPosition = snakeLadderBoard.getPlayerPlace().get(Cplayer.getId());//id ki position dega
         int newPosition = oldPosition + position;
         int Boardsize = snakeLadderBoard.getSize();

         if(newPosition > Boardsize){
             newPosition = oldPosition;
         }else{
             newPosition = getAfterSnakeAndLadder(newPosition);
         }


         for(HashMap.Entry<String,Integer> entry : snakeLadderBoard.getPlayerPlace().entrySet()){
               if(entry.getValue() == newPosition){
                    snakeLadderBoard.getPlayerPlace().put(entry.getKey() , 0);
                    System.out.println(playerInfo.get(entry.getKey())  + " This Giti Cut By " + Cplayer.getName());
               }
               break;
         }


         snakeLadderBoard.getPlayerPlace().put(Cplayer.getId() , newPosition);
         System.out.println(Cplayer.getName() + " Move To This Positon " + newPosition);

     }

     public boolean hasPlayerWin(Player currPlayer){

         int position = snakeLadderBoard.getPlayerPlace().get(currPlayer.getId());
         int boardSize = snakeLadderBoard.getSize();
         return (position == boardSize);

     }

     public void startGame(){

             while (!isGameCompleted()){

                 int totalDiceValue = toGetTotalValueAfterRoll();
                 System.out.println(" Dice No " + totalDiceValue);

                 Player currPlayer = players.poll();

                 movePlayer(currPlayer, totalDiceValue);

                 if (hasPlayerWin(currPlayer)) {
                     System.out.println(currPlayer.getName() + " Win The Game. ");
                     snakeLadderBoard.getPlayerPlace().remove(currPlayer.getId());
                 } else {
                     players.add(currPlayer);
                 }

             }



     }


}
