import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;

public class StartGame {

    public static void main(String[] args) throws IOException, InterruptedException {


        int gen;
        int threads;
        String input;

        if(args.length == 0){
            gen = 8;
            ThreadMXBean threadBean = ManagementFactory.getThreadMXBean();
            threads = threadBean.getThreadCount();
            input = "Game of Life/resource/input/start.txt";
        }
        else{
            gen = Integer.valueOf(args[0]);
            threads = Integer.valueOf(args[1]);
            input = args[2];
        }

        Container container = new Container(gen, threads, input);
        container.start();

        Thread.sleep(1000);
        container.printMap(gen);

    }

}
