import data.Data;
import utility.FontColor;

import java.util.List;

public class StartProcess {



    public static void main(String[] args) throws InterruptedException {


        if(args.length != 4){
            System.err.println("Error setting arguments\n" +
                    "1 - number of nodes\n" +
                    "2 - capacity of nod storage\n" +
                    "3 - number of packages\n" +
                    "4 - work time (ms)");
        }

        int nodes = Integer.valueOf(args[0]);
        int capacity = Integer.valueOf(args[1]);
        int packages = Integer.valueOf(args[2]);
        int time = Integer.valueOf(args[3]);


        List<Data> data;
        try(TokenRing tokenRing = new TokenRing(capacity, nodes, packages)) {
            tokenRing.start();
            Thread.sleep(time);
            data = tokenRing.getData();
        }

        Thread.sleep(1000);

        System.out.println(FontColor.BLUE + "After TokenRing work" + FontColor.RESET);
        for (Data d : data){
            System.out.println(FontColor.YELLOW + d.getName() + " : " + FontColor.RESET);
            System.out.println(d.getContent());
        }
    }


}
