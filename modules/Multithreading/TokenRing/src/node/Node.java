package node;

import data.Data;
import utility.FontColor;
import utility.Topology;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public abstract class Node extends Thread {

    private BlockingQueue<Data> queue;
    protected int id;
    protected int capacity;
    protected boolean stop = false;

    Topology topology;

    public Node(int storage, Topology topology) {
        this.capacity = storage + 1;
        queue = new ArrayBlockingQueue<>(this.capacity);
        this.topology = topology;
    }

    public void setNodeId(int id) {
        this.id = id;
    }

    public int getNodeId() {
        return this.id;
    }


    public Data get() {
        Data next;
        while (queue.size() == 0) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        next = this.queue.poll();
        System.out.println(FontColor.RED + next.getName() + " was polled from node #" + this.id + FontColor.RESET);
        this.work(next);
        return next;
    }

    public void setData(Data data) {
        try {
            this.queue.put(data);
            System.out.println(FontColor.BLUE + data.getName() + " " +
                    "was set and added to storage of node #" + this.id + FontColor.RESET);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void push(Data data) {
        try {
            this.queue.put(data);
            System.out.println(FontColor.GREEN + data.getName() +
                    " was added to storage of node #" + this.id + FontColor.RESET);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void work(Data data) {

        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        data.setContent(data.getContent() + "\n" + df.format(date) + " " + " work node #" + this.id);
    }


    public void stopWork() {
        this.stop = true;
    }


    @Override
    public String toString() {
        return getClass().getSimpleName() + " " + this.id;
    }

    @Override
    public void run() {
        try {
            throw new Exception("method must be Override");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
