package ship;


import cargo.Cargo;
import context.Bottleneck;
import port.Port;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;


public class Ship extends Thread {
    private Cargo cargo;
    private static int counter;
    private int id;
    private int speed;
    Semaphore semaphore;
    int lengthOfBottleNeck;
    CountDownLatch countDownLatch;

    private Port port;

    public Ship(Cargo cargo, Port port, int speed, Semaphore semaphore, int distanceOfRoad, CountDownLatch countDownLatch) {
        this.cargo = cargo;
        Ship.counter++;
        this.id = counter;
        this.port = port;
        this.setName("Ship #" + counter + " goods: " + this.getCargo().getType() + " - " + getCargo().getName());
        this.lengthOfBottleNeck = distanceOfRoad;
        this.semaphore = semaphore;
        this.speed = speed;
        this.countDownLatch = countDownLatch;
    }

    public int getSpeed(){
        return this.speed;
    }

    public Cargo getCargo() {
        return cargo;
    }

    @Override
    public void run() {

        try {
            semaphore.acquire();
            Bottleneck.swimThrough(this.lengthOfBottleNeck, this);
            semaphore.release();
            countDownLatch.countDown();
            countDownLatch.await();
            port.add(this);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}


