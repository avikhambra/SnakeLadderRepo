package temp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SnakeLadderBoard {

    private int size;
    private List<Snake> snakes;
    private List<Ladder> ladders;
    HashMap<String , Integer> playerPlace;//Id , position

    public SnakeLadderBoard(int size){

        this.size = size;
        snakes = new ArrayList<>();
        ladders = new ArrayList<>();
        playerPlace = new HashMap<>();

    }

    public void setPlayerPlace(HashMap<String , Integer> playerPlace){
        this.playerPlace = playerPlace;
    }

    public HashMap<String , Integer> getPlayerPlace(){
        return playerPlace;
    }

    public int getSize(){
        return size;
    }

    public List<Snake> getSnakes(){
        return snakes;
    }

    public void setSnakes(List<Snake> snakes){
        this.snakes = snakes;
    }

    public List<Ladder> getLadders(){
        return ladders;
    }

    public void setLadders(List<Ladder> ladders){
        this.ladders = ladders;
    }


}
