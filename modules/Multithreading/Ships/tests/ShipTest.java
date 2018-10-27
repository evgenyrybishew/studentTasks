import cargo.Cargo;
import context.Goods;
import org.junit.Assert;
import org.junit.Test;
import port.Port;
import ship.Ship;
import ship.ShipGenerator;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class ShipTest {


    @Test
    public void shipGeneratorTest(){
        ArrayList<Port> ports = new ArrayList<>();
        ports.add(new Port(Goods.TOYS));
        Semaphore semaphore = new Semaphore(1, true);
        CountDownLatch countDownLatch = new CountDownLatch(1);

        Ship ship = ShipGenerator.generator(ports, semaphore, 1000, countDownLatch);
        Assert.assertTrue(ship.getCargo().getType().equals(Goods.TOYS));
    }

    @Test
    public void shipRunTest() throws NoSuchFieldException, IllegalAccessException {
        Port port = new Port(Goods.FOOD);
        Semaphore semaphore = new Semaphore(1,true);
        CountDownLatch countDownLatch = new CountDownLatch(1);
        Ship ship = new Ship(new Cargo(Goods.FOOD, "Test", 999), port, 100, semaphore, 10000, countDownLatch);
        ship.run();

        Field cargosField = port.getClass().getDeclaredField("cargos");
        cargosField.setAccessible(true);
        List<Cargo> cargos = (List<Cargo>) cargosField.get(port);

        Assert.assertEquals(cargos.size(), 1);
        Assert.assertTrue(cargos.get(0).getType().equals(Goods.FOOD));
    }

}
