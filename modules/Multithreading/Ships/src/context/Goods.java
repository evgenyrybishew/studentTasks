package context;

import java.util.Random;

public enum Goods {
    TOYS(0),
    FOOD(1),
    CLOTHES(2),
    APPlIANCES(3),
    ANIMALS(4);

    private static int count = values().length;

    private final int value;

    Goods(int value) {
        this.value = value;
    }

    public static int getCount(){
        return count;
    }

    public static Goods getRandomGood(){
        Random random = new Random();
        return values()[random.nextInt((count))];
    }


}


