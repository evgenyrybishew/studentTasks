package ship;

import cargo.Cargo;
import cargo.CargoGenerator;
import port.Port;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class ShipGenerator {
    private static Port findMathPort(Cargo cargo, List<Port> ports){
        for (int i = 0; i < ports.size(); i++){
            if(ports.get(i).getType().equals(cargo.getType()))
                return ports.get(i);
        }
        return null;

    }

    public static Ship generator(List<Port>ports, Semaphore semaphore, int distance, CountDownLatch countDownLatch){
        while (true){
            Cargo cargo = CargoGenerator.generate();
            Port port = findMathPort(cargo, ports);
            if(port != null)
                return new Ship(cargo, findMathPort(cargo, ports), (int)Math.random() * (100 - 10) + 10,
                        semaphore, distance, countDownLatch);
        }
    }

}
