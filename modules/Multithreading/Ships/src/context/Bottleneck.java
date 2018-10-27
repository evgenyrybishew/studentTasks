package context;

import ship.Ship;

public class Bottleneck {


    public static void swimThrough(int length, Ship ship){
        System.out.println(Thread.currentThread().getName() + " start swim through bottleneck");
        try {
            Thread.sleep(length/ship.getSpeed());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " finished through bottleneck");
    }


}
