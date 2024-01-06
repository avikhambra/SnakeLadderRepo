package Service;

import java.util.Random;

public class Dice {

    static Random r = new Random(System.currentTimeMillis());
    public static int getRoll(){

        return r.nextInt(6)+1;

    }



}
