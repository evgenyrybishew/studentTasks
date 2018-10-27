import data.Data;
import node.Node;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.concurrent.BlockingQueue;

public class TokenRingTest {

    @Test
    public void tokenRingInitTest() {
        TokenRing tokenRing = new TokenRing(5, 5, 0);
        Node temp = tokenRing.getMain();
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < 5; i++) {
            result.append(temp.getNodeId() + " ");
            temp = tokenRing.getNext(temp);
        }
        Assert.assertTrue(result.toString().trim().equals("0 1 2 3 4"));

        result = new StringBuilder();
        temp = tokenRing.getMain();
        for (int i = 0; i < 5; i++) {
            result.append(temp.getNodeId() + " ");
            temp = tokenRing.getPreve(temp);
        }
        Assert.assertTrue(result.toString().trim().equals("0 4 3 2 1"));
        Assert.assertTrue(tokenRing.getNext(tokenRing.getMain()).getNodeId() == 1);
        Assert.assertTrue(tokenRing.getPreve(tokenRing.getMain()).getNodeId() == 4);
    }


    @Test
    public void tokenRingSetDataTest() throws NoSuchFieldException, IllegalAccessException {

        int capasity = 5;
        int nodes = 5;
        TokenRing tokenRing = new TokenRing(capasity, nodes, (capasity * nodes) + 10);
        Node main = tokenRing.getMain();

        Field queueField = main.getClass().getDeclaredField("queue");
        queueField.setAccessible(true);
        BlockingQueue<Data> queue = (BlockingQueue<Data>) queueField.get(main);

        int i = 0;
        for (Data d : queue) {
            Assert.assertTrue(d.getName().equals("data #" + i));
            i += 5;
        }
    }

}
