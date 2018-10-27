
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.CyclicBarrier;


public class Container {

    private ThreadPart main;
    private int[][] map;
    private int generation;
    private int genCounter;
    private int threads;

    private int width;
    private int height;


    public Container(int generation, int threades, String inputPath) throws IOException {
        this.generation = generation;
        this.genCounter = 1;
        this.threads = threades;
        doMap(inputPath);
        doDistributeWork();
    }

    private void doMap(String inputPath) throws IOException {
        List<String> list = Files.readAllLines(Paths.get(inputPath));
        this.width = list.get(0).length();
        this.height = list.size();
        this.map = new int[height][width];
        for (int i = 0; i < height; i++)
            for (int j = 0; j < width; j++)
                map[i][j] = Integer.valueOf(list.get(i).charAt(j) + "");
    }


    private void doDistributeWork() {

        while (this.width % this.threads != 0)
            this.threads--;

        if (this.threads == 1) {
            main = new ThreadPart(map, 0, this.width - 1, this.generation);
            main.setNext(main);
            main.setPrev(main);
        } else {

            ThreadPart temp = null;
            int div = this.width / this.threads;
            for (int i = 0, j = 0; i < this.threads; i++, j += div) {
                if (i == 0) {
                    main = new ThreadPart(map, j, j + div - 1, this.generation);
                    i++;
                    j += div;
                    temp = new ThreadPart(map, j, j + div - 1, this.generation);
                    main.setNext(temp);
                    temp.setPrev(main);
                } else {
                    ThreadPart next = new ThreadPart(map, j, j + div - 1, this.generation);
                    temp.setNext(next);
                    next.setPrev(temp);
                    temp = next;
                }
            }
            main.setPrev(temp);
            temp.setNext(main);
        }
    }

    public void doNextGen() {
        ThreadPart temp = main;
        for (int i = 0; i < this.threads; i++) {
            temp.doWork();
            temp = temp.getNext();
        }

        temp = main;
        for (int i = 0; i < this.threads; i++) {
            temp.doNodeMap(map);
            temp = temp.getNext();
        }



    }


    public void printMap(int gen) {

        System.out.println("Gen #" + gen);
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++)
                System.out.print(map[i][j]);
            System.out.println();
        }
    }


    public void start(){
        ThreadPart temp = main;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(threads);
        for (int j = 0; j < this.threads; j++) {
            temp.doSynchronized(cyclicBarrier);
            temp.start();
            temp = temp.getNext();
        }








    }


}