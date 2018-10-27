import context.Goods;
import port.Port;
import ship.Ship;
import ship.ShipGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class StartProcess {

    public static void main(String[] args) throws InterruptedException {

        if (args.length != 3) {
            System.err.println("Arguments error");
            return;
        }

        int shipCounter = Integer.valueOf(args[0]);
        int semaphoreCounter = Integer.valueOf(args[1]);
        int distance = Integer.valueOf(args[2]);

        Semaphore semaphore = new Semaphore(semaphoreCounter, true);
        CountDownLatch countDownLatch = new CountDownLatch(shipCounter);

        List<Port> ports = new ArrayList<>();
        ports.add(new Port(Goods.FOOD));
        ports.add(new Port(Goods.ANIMALS));
        ports.add(new Port(Goods.APPlIANCES));
        ports.add(new Port(Goods.CLOTHES));
        ports.add(new Port(Goods.TOYS));

        for (int j = 0; j < shipCounter; j++) {
            Ship ship = ShipGenerator.generator(ports, semaphore, distance, countDownLatch);
            ship.start();
        }

        Thread.sleep(1000 * shipCounter);


        for (int k = 0; k < ports.size(); k++) {
            if (ports.get(k).getCargosList().size() == 0)
                continue;
            Port port = ports.get(k);
            System.out.println("Port type: " + port.getType());
            for (int l = 0; l < port.getCargosList().size(); l++)
                System.out.println(l + 1 + ". " + port.getCargosList().get(l).getName() +
                        " " + port.getCargosList().get(l).getCount());

        }


    }

}
